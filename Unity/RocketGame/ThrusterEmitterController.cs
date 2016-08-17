using UnityEngine;
using System.Collections;

public class ThrusterEmitterController : MonoBehaviour {


    ParticleSystem ps;
    AudioSource ac;


	// Use this for initialization
	void Start () {
        ps = GetComponent<ParticleSystem>();
        ac = GetComponent<AudioSource>();
	}
	
	// Update is called once per frame
	void Update () {
	}

    void FixedUpdate()
    {
            
        bool SPACE_PRESSED = Input.GetKey(KeyCode.Space);
        if (SPACE_PRESSED)
        {
            if (!ac.isPlaying)
            {
                ac.Play();

            }
        } else
        {
            ac.Stop();
        }

        var em = ps.emission;
        em.enabled = SPACE_PRESSED;

    
    }
}
