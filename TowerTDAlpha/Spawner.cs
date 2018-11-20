using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Spawner : MonoBehaviour {

    public GameObject buggyPrefab;
    public GameObject warriorPrefab;
    public GameObject dronePreFab;
    public GameObject podBossPrefab;
    public GameObject footmanPrefab;

    public float spawnTimer = 0.0f;
    public float spawnCooldown = 10.0f;
    public Queue<GameObject> spawnQueue;


    public void setSpawnData(int waveID)
         {
        if (waveID == 1)
        {
            spawnQueue.Enqueue(footmanPrefab);
            spawnQueue.Enqueue(footmanPrefab);
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(podBossPrefab);
            spawnQueue.Enqueue(footmanPrefab);
            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(dronePreFab); 
            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(footmanPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
        } else if (waveID == 2)
        {
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(dronePreFab);
            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(warriorPrefab);

            spawnQueue.Enqueue(footmanPrefab);
            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(dronePreFab);
            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(buggyPrefab);

            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(buggyPrefab);


        }

        else if (waveID == 3)
        {
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(dronePreFab);
            spawnQueue.Enqueue(buggyPrefab);

            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(dronePreFab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(dronePreFab);

            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(dronePreFab);
            spawnQueue.Enqueue(buggyPrefab);
        }

        else if (waveID == 4)
        {
            spawnCooldown /= 2;
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(buggyPrefab);

            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(buggyPrefab);

            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(buggyPrefab);
        }

        else if (waveID == 5)
        {
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(warriorPrefab);

            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(buggyPrefab);
        }

        else if (waveID == 6)
        {

            spawnCooldown /= 2;
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
        }

        else if (waveID == 7)
        {
            spawnCooldown /= 2;
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(buggyPrefab);
        }

        else if (waveID == 8)
        {
            spawnCooldown /= 2;
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
        }

        else if (waveID == 2)
        {
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(warriorPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(warriorPrefab);
        }
    }

    void TrySpawnFromQueue()
    {
        if (spawnQueue.Count != 0) {

            GameObject toSpawn = spawnQueue.Dequeue();

            if (toSpawn != null) {


            
            Instantiate(toSpawn, this.transform.position, this.transform.rotation);
            }

        }
    }
	// Use this for initialization
	void Start () {
        spawnQueue = new Queue<GameObject>();

    }


	
	// Update is called once per frame
	void Update () {
        spawnTimer += Time.deltaTime;
        if (spawnTimer > spawnCooldown)
        {
            TrySpawnFromQueue();
            spawnTimer = 0;
        }
	}
}
