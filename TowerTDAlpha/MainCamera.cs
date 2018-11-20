using UnityEngine;
using System.Collections;



/*  This class controls the Main camera.
*/


public class MainCamera : MonoBehaviour {




	public Camera cam;
	public GameObject target;
	public float cameraSpeed;

	private Rigidbody rb;

	// Use this for initialization
	void Start () {
		rb = GetComponent<Rigidbody> ();
	}
	
	// Update is called once per frame
	void LateUpdate () {



		if (target != null) {  // move to position behind target.
		    	transform.position = target.transform.position + (-0.2f * target.transform.forward) + (0.2f * target.transform.up) + new Vector3 (0, 1, 0);
		}

		cam.transform.LookAt (rb.transform); // look at target.






		if (Input.GetMouseButton (0)) {  // Rotate camera with mouse.
			rb.transform.Rotate (new Vector3 (Input.GetAxis ("Mouse Y") * -1, Input.GetAxis ("Mouse X"), 0) * 3);

		}

		if (Input.GetMouseButton (1)) {  // Snap back to original rotation.
			if (target != null) {
				rb.transform.rotation = target.transform.rotation;
			}
		}




	

	}
}
