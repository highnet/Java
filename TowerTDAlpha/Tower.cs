using System.Collections;
using System.Collections.Generic;
using UnityEngine;

// TODO: emp tower
// energy pylon
// machine gun
// super tower

public class Tower : MonoBehaviour {

    Transform trackingTurretTransform;
    public float fireRangeDiameter;
    public GameObject bulletPrefab;
    public float fireCooldown;
    public float fireCooldownLeft;
    public AudioClip fireSound;
    public float turretFiringPositionVisualOffsetY;
    public int cost;


    public int towerDamage;
    public float bulletSpeed;
    public float explosionRadius;

    public float rngSeed;

    public float rotationSpeed;
    
    
    public Enemy nearestEnemy = null;

    Vector3 nearestEnemyDirection;
    AudioSource audio;

     bool targetLockedOnAndOnSights = false;


    public bool hasTrackingTurret;
    public bool isAOETower;

    GameObject firingRangeIndicatorGO;

    // Use this for initialization
    void Start () {
        rngSeed = Random.Range(-1, 1);

        audio = GetComponent<AudioSource>();


            trackingTurretTransform = transform.Find("Turret");
            firingRangeIndicatorGO = this.gameObject.transform.Find("Turret").gameObject.transform.Find("FiringRange").gameObject;

   
   
    }

    void TryLockToNearestTarget()
    {
        Enemy[] enemies = FindObjectsOfType<Enemy>();

        float tentativeClosestEnemyDistance = Mathf.Infinity;

        foreach (Enemy e in enemies)
        {
            float dist = Vector3.Distance(this.transform.position, e.transform.position);
            if (nearestEnemy == null || dist < tentativeClosestEnemyDistance)
            {
                nearestEnemy = e;
                tentativeClosestEnemyDistance = dist;
            }
        }
    }

    void RotateTurretTowardsNearestTarget()

    {
            Quaternion lookRot = Quaternion.LookRotation(nearestEnemyDirection);
            this.trackingTurretTransform.rotation = Quaternion.Lerp(trackingTurretTransform.rotation, lookRot, Time.deltaTime * rotationSpeed);
    }

    void CheckFiringRangeIndicator()
    {
        targetLockedOnAndOnSights = firingRangeIndicatorGO.GetComponent<FiringRangeCollision>().targetInRange;
    }

    void trackingTurretidleAnimation()
    {
        if (nearestEnemy == null || nearestEnemyDirection.magnitude > fireRangeDiameter) // idling conditions
        {

            trackingTurretTransform.Rotate(Mathf.Sign(rngSeed) * 30 * Vector3.up * Time.deltaTime, Space.World);
        }
    }

    void aoeTowerIdleAnimation() // TODO
    {
        if (nearestEnemy == null || nearestEnemyDirection.magnitude > fireRangeDiameter)
        {
            // do idle animation
        }
    }



    void TrackingTurretShootAt(Enemy e) {
        
   
        Vector3 turretFiringPosition = this.transform.position;
        turretFiringPosition.y += turretFiringPositionVisualOffsetY;
        GameObject bulletGO = (GameObject)Instantiate(bulletPrefab, turretFiringPosition, this.transform.rotation);
        Bullet b = bulletGO.GetComponent<Bullet>();
        b.inheritedDamage = towerDamage;
        b.inheritedSpeed = bulletSpeed;
        b.inheritedExplosionRadius = explosionRadius;
        b.target = e.transform;
  
    }



    void TryToShoot()
    {
        fireCooldownLeft -= Time.deltaTime;
        if (nearestEnemy != null && targetLockedOnAndOnSights && fireCooldownLeft <= 0 && nearestEnemyDirection.magnitude <= fireRangeDiameter)
        {

            audio.clip = fireSound;
            audio.Play();

            fireCooldownLeft = fireCooldown;
            if (hasTrackingTurret)
            {
                TrackingTurretShootAt(nearestEnemy);
            }

            if (isAOETower)
            {
                AoeTowerShootAt();
            }
       
        }
    }


    void AoeTowerShootAt()
    {

        this.transform.Find("Emitter").GetComponent<ParticleSystem>().Play();
  

        Collider[] cols = Physics.OverlapBox(this.transform.position, new Vector3(fireRangeDiameter/2, 2f, fireRangeDiameter/2), this.transform.rotation);

    //    Collider[] cols = Physics.OverlapSphere(transform.position, fireRangeDiameter);
        
        foreach (Collider c in cols)
        {
            Enemy e = c.GetComponent<Enemy>();

            if (e != null)
            {
                e.GetComponent<Enemy>().health -= towerDamage;
            }
        }

    }

    // Update is called once per frame
    void Update ()
    {

        TryLockToNearestTarget();
        CheckFiringRangeIndicator();

        if (nearestEnemy != null)
        {
            nearestEnemyDirection = nearestEnemy.transform.position - this.transform.position;

        }


        if (nearestEnemy != null)
        {
            if (hasTrackingTurret && nearestEnemyDirection.magnitude <= fireRangeDiameter)
            {
                RotateTurretTowardsNearestTarget();
            }
            TryToShoot();
        }
        else
        {
            targetLockedOnAndOnSights = false;

        }


        ///
        if (isAOETower) { 
            aoeTowerIdleAnimation(); // TODO
        }


            ///
        if (hasTrackingTurret)
        {
            trackingTurretidleAnimation();
        }
    }
   
}
