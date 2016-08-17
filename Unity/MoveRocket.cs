using UnityEngine;
using System.Collections;

public class MoveRocket : MonoBehaviour {

    public float thrust;
    private Rigidbody rb;
    public float gravity;


	// Use this for initialization
	void Start ()
    {
        Physics.gravity = new Vector3(0, gravity, 0);
        rb = GetComponent<Rigidbody>();
	}
	
	// Update is called once per frame
	void Update ()
    {
	
	}

    void FixedUpdate ()
    {
        float verticalMovement = Input.GetAxisRaw("Vertical");
        float horizontalMovement = Input.GetAxisRaw("Horizontal");
        float rollMovement = Input.GetAxisRaw("Roll");


        if (Input.GetKey(KeyCode.Space))
        {
            rb.AddForce(transform.up  * thrust);

        }


        rb.AddTorque(new Vector3(verticalMovement, rollMovement, -horizontalMovement) * thrust * 1 );
    }
}
