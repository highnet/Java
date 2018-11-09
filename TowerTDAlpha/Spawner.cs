using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Spawner : MonoBehaviour {

    public GameObject buggyPrefab;

    public float spawnTimer = 0.0f;
    public float spawnCooldown = 10.0f;
    public Queue<GameObject> spawnQueue;


    public void setSpawnData(int waveID)
         {
        if (waveID == 1)
        {
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(buggyPrefab);

            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
        } else if (waveID == 2)
        {
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(buggyPrefab);

            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(buggyPrefab);

            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
        }

        else if (waveID == 3)
        {
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);

            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(buggyPrefab);

            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
        }

        else if (waveID == 4)
        {
            spawnCooldown /= 2;
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);

            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(buggyPrefab);

            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
        }

        else if (waveID == 5)
        {
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);

            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(null);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
        }

        else if (waveID == 6)
        {

            spawnCooldown /= 2;
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
        }

        else if (waveID == 7)
        {
            spawnCooldown /= 2;
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
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
        }

        else if (waveID == 8)
        {
            spawnCooldown /= 2;
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
        }

        else if (waveID == 2)
        {
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
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
            spawnQueue.Enqueue(buggyPrefab);
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
