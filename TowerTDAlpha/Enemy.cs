using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Enemy : MonoBehaviour {

    GameObject pathGameObject;
    Transform targetPathNode;
    int pathNodeCount = 0;
    int maxPathNodes;

    public float speed;
    float hoverOriginalY;

    public int health;
    public float floatStrength;

    public int bountyRewardValue;

    // Use this for initialization
    void Start () {
        pathGameObject = GameObject.Find("Path");
        hoverOriginalY = transform.position.y;
        maxPathNodes = pathGameObject.transform.childCount;
    }

    void checkHealthPoints()
    {
        if (health <= 0)
        {
            Die();
            
        }
    }
   
    void ReachedGoal()
    {
     
        GameObject.FindObjectOfType<ScoreManager>().LoseLife();
        Destroy(gameObject);
    }

    void Die()
    {
        GameObject.FindObjectOfType<ScoreManager>().money += bountyRewardValue;
        Destroy(gameObject);
    }
	
	// Update is called once per frame
	void Update () {

        checkHealthPoints();
        AssertTargetWayPoint();
        DoWayPointMovements();
        HoverAnimation();

        
    }

    void HoverAnimation()
    {
        transform.position = new Vector3(transform.position.x, hoverOriginalY + ((float)System.Math.Sin(Time.time) * floatStrength), transform.position.z);
    }

    void AssertTargetWayPoint()
    {

        if (targetPathNode == null)
        {
            GetNextPathNode();
            if (targetPathNode == null) // run out of path
            {
                ReachedGoal();
            }
        }
    }

    void DoWayPointMovements()
    {
        try { 
        if (Vector3.Distance(this.transform.localPosition, targetPathNode.position) <= 1) // check if waypoint reached
        {
            targetPathNode = null; // target reached

        }
        else
        {
            transform.position = Vector3.MoveTowards(transform.position, targetPathNode.position, speed * Time.deltaTime); // move towards target 
            transform.LookAt(targetPathNode); // look at the target
        }
        } catch(System.Exception ignored) { }
    }

    void GetNextPathNode()
    {
        if (pathNodeCount < maxPathNodes)
        {
            targetPathNode = pathGameObject.transform.GetChild(pathNodeCount++);
        }
      
    }


}
