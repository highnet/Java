using UnityEngine;
using System.Collections;
using UnityEngine.UI;
using System.Collections.Generic;

// The code example shows how to implement a metronome that procedurally generates the click sounds via the OnAudioFilterRead callback.
// While the game is paused or the suspended, this time will not be updated and sounds playing will be paused. Therefore developers of music scheduling routines do not have to do any rescheduling after the app is unpaused

[RequireComponent(typeof(AudioSource))]
public class Metronome : MonoBehaviour
{
    public double bpm = 140.0F;
    public float gain = 0.5F;
    public int signatureHi = 4;
    public int signatureLo = 4;
    private double nextTick = 0.0F;
    private float amp = 0.0F;
    private float phase = 0.0F;
    private double sampleRate = 0.0F;
    private int accent;
    private bool running = false;

    private Queue<string> randomRudimentQueue;

    public Text text0;
    public Text text1;
    public Text text2;
    public Text text3;
    public Text text4;
    public Text text5;

    private bool listShifted = false;

    void Start()
    {
        accent = signatureHi;
        double startTick = AudioSettings.dspTime;
        sampleRate = AudioSettings.outputSampleRate;
        nextTick = startTick * sampleRate;
        running = true;

        randomRudimentQueue = new Queue<string>();
        randomRudimentQueue.Enqueue("xxx");
        randomRudimentQueue.Enqueue("xxx");

        // 0 = paraddidle
        // 1 = single stroke
        // 2 = double stroke

        for (int i = 0; i < 1000; i++)
        {
            int random = (int)Random.Range(0.0f, 6.0f);

            switch (random)
            {
                case 0:
                    randomRudimentQueue.Enqueue("16th note paradiddle");
                    break;

                case 1:
                    randomRudimentQueue.Enqueue("16th note single stroke");
                    break;

                case 2:
                    randomRudimentQueue.Enqueue("16th note double stroke");
                    break;
                case 3:
                    randomRudimentQueue.Enqueue("8th note double stroke");
                    break;
                case 4:
                    randomRudimentQueue.Enqueue("8th note single stroke");
                    break;
                case 5:
                    randomRudimentQueue.Enqueue("triplets single stroke");
                    break;
            }
        }

        text0.text = randomRudimentQueue.Dequeue();
        text1.text = randomRudimentQueue.Dequeue();
        text2.text = randomRudimentQueue.Dequeue();
        text3.text = randomRudimentQueue.Dequeue();
        text4.text = randomRudimentQueue.Dequeue();
        text5.text = randomRudimentQueue.Dequeue();

    }

    private void Update()
    {

        if (accent == 1 && listShifted == false)
        {
            shiftTexts();
        }
        if (accent != 1)
        {
            listShifted = false;
        }
    }

    void shiftTexts()
    {
       
        text0.text = text1.text;
        text1.text = text2.text;
        text2.text = text3.text;
        text3.text = text4.text;
        text4.text = randomRudimentQueue.Dequeue();
        listShifted = true;

    }

    void OnAudioFilterRead(float[] data, int channels)
    {
        if (!running)
            return;

        double samplesPerTick = sampleRate * 60.0F / bpm * 4.0F / signatureLo;
        double sample = AudioSettings.dspTime * sampleRate;
        int dataLen = data.Length / channels;
        int n = 0;
        while (n < dataLen)
        {
            float x = gain * amp * Mathf.Sin(phase);
            int i = 0;
            while (i < channels)
            {
                data[n * channels + i] += x;
                i++;
            }
            while (sample + n >= nextTick)
            {
                nextTick += samplesPerTick;
                amp = 1.0F;
                if (++accent > signatureHi)
                {
                    accent = 1;
                    amp *= 2.0F;
                }
                Debug.Log("Tick: " + accent + "/" + signatureHi);
       
            }
            phase += amp * 0.3F;
            amp *= 0.993F;
            n++;
        }
    }
}
