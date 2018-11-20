﻿using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class ScoreManager : MonoBehaviour {

    public int lives = 20;
    public int money = 100;
    public Text moneyText;
    public Text livesText;
    public Text wavesText;
    public int currentwave = 0;
    public int maxWave;

    public void LoseLife()
    {
        lives -= 1;
        if (lives <= 0)
        {
            GameOver();
        }
    }

    public void GameOver()
    {
        Debug.Log("Game Over");
        SceneManager.LoadScene("Level_1");
    }
    // Use this for initialization
    void Start () {

        
		
	}
	
	// Update is called once per frame
	void Update () {

        moneyText.text = "Money = $" + money.ToString();
        livesText.text = "Lives = " + lives.ToString();
        wavesText.text = "Wave :" + currentwave + "/" + maxWave;
	}
}
