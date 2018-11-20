using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class FootmanAnimator : MonoBehaviour {

	// Use this for initialization
	void Start () {
     
    }
	
	// Update is called once per frame
	void Update () {

        this.GetComponent<Animator>().SetFloat("Speed", 1);

     



    }
}
