using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Bullet : MonoBehaviour {


    public float inheritedSpeed = 10f;
    public int inheritedDamage;
    public float inheritedExplosionRadius;

    public Transform target;

    // Use this for initialization
    void Start () {
        
	}

    private void OnCollisionEnter(Collision collision)
    {

        if (collision.gameObject.tag == "enemy")
        {

            Collider[] cols = Physics.OverlapSphere(transform.position, inheritedExplosionRadius);


        foreach(Collider c in cols)
        {
            Enemy e = c.GetComponent<Enemy>();

            if (e != null)
            {
                Debug.Log(e.ToString());
                e.GetComponent<Enemy>().health -= inheritedDamage;
            }
        }

            Destroy(gameObject);


        }
     
    }

    void DoBulletTravel() {
    
        if (target!= null)
        {


                transform.position = Vector3.MoveTowards(transform.position, target.position, inheritedSpeed * Time.deltaTime);
                transform.LookAt(target);
        }
    } 
    
	// Update is called once per frame
	void Update () {
        DoBulletTravel();

        if (target == null)
        {
            Destroy(gameObject);
        }
       
    }
}
