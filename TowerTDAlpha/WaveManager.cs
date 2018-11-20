using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class WaveManager : MonoBehaviour {

    public Button nextWaveReadyButton;

    void ClickedNextWaveReadyButton()
    {


       if (GameObject.FindObjectOfType<Spawner>().spawnQueue.Count == 0 && GameObject.FindObjectOfType<ScoreManager>().currentwave <= GameObject.FindObjectOfType<ScoreManager>().maxWave)
        {
            GameObject.FindObjectOfType<ScoreManager>().currentwave++;
            GameObject.FindObjectOfType<Spawner>().setSpawnData(GameObject.FindObjectOfType<ScoreManager>().currentwave);
        }



    }
    void bindButtons()
    {
        nextWaveReadyButton.GetComponent<Button>().onClick.AddListener(ClickedNextWaveReadyButton);
    }
	// Use this for initialization
	void Start () {
        bindButtons();
    }
	
	// Update is called once per frame
	void Update () {
		
	}
}
