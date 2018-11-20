using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TowerBuildZone : MonoBehaviour {
    

	// Use this for initialization
	void Start () {
		
	}

    private void OnMouseUp()
    {
        BuildingManager bm = GameObject.FindObjectOfType<BuildingManager>();

        if (this.transform.childCount == 1 && bm.selectedShopTower != null) // childcount limits creating more than 1 turret per spot. a tower build zone has 1 object child to indicate its location and 1 possible turret attached to it.
        {
            ScoreManager sm = GameObject.FindObjectOfType<ScoreManager>();
            if (sm.money >= bm.selectedShopTower.GetComponent<Tower>().cost)
            {
                Instantiate(bm.selectedShopTower, transform.position, transform.rotation, this.transform);
                sm.money -= bm.selectedShopTower.GetComponent<Tower>().cost;
                this.transform.GetChild(0).GetComponent<MeshRenderer>().enabled = false;
                
            }
           
        }
    }

    // Update is called once per frame
    void Update () {
	}
}
