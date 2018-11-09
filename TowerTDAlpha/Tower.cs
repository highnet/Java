using System.Collections;
using System.Collections.Generic;
using UnityEngine;

// TODO: emp tower
// energy pylon
// machine gun
// super tower

public class Tower : MonoBehaviour {

    Transform turretTransform;
    public float fireRange;
    public GameObject bulletPrefab;
    public float fireCooldown;
    public float fireCooldownLeft;
    public AudioClip fireSound;
    public float turretFiringPositionVisualOffsetY;
    public int cost;


    public int bulletDamage;
    public float bulletSpeed;
    public float explosionRadius;
    
    Enemy nearestEnemy = null;

    Vector3 nearestEnemyDirection;
    AudioSource audio;

    // Use this for initialization
    void Start () {
        turretTransform = transform.Find("Turret");

       audio = GetComponent<AudioSource>();
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

    void LookTowardsNearestTarget()
    {
        Quaternion lookRot = Quaternion.LookRotation(nearestEnemyDirection);
        turretTransform.rotation = Quaternion.Euler(0, lookRot.eulerAngles.y, 0);
    }
	
    void UpdateShootingLogic()
    {
        fireCooldownLeft -= Time.deltaTime;
        if (nearestEnemy != null && fireCooldownLeft <= 0 && nearestEnemyDirection.magnitude <= fireRange)
        {
            fireCooldownLeft = fireCooldown;
            ShootAt(nearestEnemy);
        }
    }

    void ShootAt(Enemy e) {
        
        audio.clip = fireSound;
        audio.Play();
        Vector3 turretFiringPosition = this.transform.position;
        turretFiringPosition.y += turretFiringPositionVisualOffsetY;
        GameObject bulletGO = (GameObject)Instantiate(bulletPrefab, turretFiringPosition, this.transform.rotation);
        Bullet b = bulletGO.GetComponent<Bullet>();
        b.inheritedDamage = bulletDamage;
        b.inheritedSpeed = bulletSpeed;
        b.inheritedExplosionRadius = explosionRadius;
        b.target = e.transform;
  
    }
    // Update is called once per frame
    void Update ()
    {
       
        TryLockToNearestTarget();
        if (nearestEnemy != null)
        {
            nearestEnemyDirection = nearestEnemy.transform.position - this.transform.position;

            if (nearestEnemyDirection.magnitude <= fireRange)
            {
                LookTowardsNearestTarget();
            }

        }
        UpdateShootingLogic();

        if (nearestEnemy == null || nearestEnemyDirection.magnitude > fireRange)
        {

            turretTransform.Rotate(30 * Vector3.up * Time.deltaTime, Space.World);
        }
      
	}
   
}
