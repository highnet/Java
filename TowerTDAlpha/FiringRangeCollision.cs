using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class FiringRangeCollision : MonoBehaviour {

    public bool targetInRange = false;



	// Use this for initialization
	void Start () {
  
  
    }

    private void OnTriggerEnter(Collider other)
    {
        if (other.tag == "enemy") {
            if(other.transform == this.transform.parent.transform.parent.gameObject.GetComponent<Tower>().hasTrackingTurret && other.transform == this.transform.parent.transform.parent.gameObject.GetComponent<Tower>().nearestEnemy.transform)
              {
                targetInRange = true;
            }

            if (other.transform == this.transform.parent.transform.parent.gameObject.GetComponent<Tower>().isAOETower)
            {
                targetInRange = true;
            }
        }
    }

    private void OnTriggerStay(Collider other)
    {
        if (other.tag == "enemy")
        {
            if (other.transform == this.transform.parent.transform.parent.gameObject.GetComponent<Tower>().hasTrackingTurret && other.transform == this.transform.parent.transform.parent.gameObject.GetComponent<Tower>().nearestEnemy.transform)
            {
                targetInRange = true;
            }

            if (other.transform == this.transform.parent.transform.parent.gameObject.GetComponent<Tower>().isAOETower)
            {
                targetInRange = true;
            }
        }
    }


    private void OnTriggerExit(Collider other)
    {

        if (other.tag == "enemy")
        {
            targetInRange = false;
        }



    }
    // Update is called once per frame
    void Update () {

        targetInRange = false;
    }
}
