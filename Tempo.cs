using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[RequireComponent(typeof(AudioSource))]
public class Tempo : MonoBehaviour {



    public Color colorHi = Color.green;
    public Color colorLow = Color.red;
    public Color colorOff = Color.black;
    float t = 0;
    float i = 0;
    bool startMoving = false;

    public Renderer rend;
    public Rigidbody rb;

    public float bpm = 140.0F;
    public float gain = 0.5F;
    public int signatureHi = 4;
    public int signatureLo = 4;
    private double nextTick = 0.0F;
    private float amp = 0.0F;
    private float phase = 0.0F;
    private double sampleRate = 0.0F;
    private int accent;
    private int oldAccent;
    private bool running = false;
    void Start()
    {
        rend = GetComponent<Renderer>();
        rb = GetComponent<Rigidbody>();

        accent = signatureHi;
        double startTick = AudioSettings.dspTime;
        sampleRate = AudioSettings.outputSampleRate;
        nextTick = startTick * sampleRate;
        running = true;
        rend.material.color = colorOff;
    }

    private void Update()
    {


      

            i += Time.deltaTime / ((4f / bpm) * 60f);

            rb.transform.position = Vector3.Lerp(new Vector3(-4, 0, 0), new Vector3(4f, 0, 0), i);


        if (i > 1)
        {
            i = 0;
        }

    
    


    rend.material.color = colorOff;
        if (oldAccent != accent) {
            t = 0;
        }
        t += Time.deltaTime / 0.1f;
        if (accent == 1) {
            rend.material.color = Color.Lerp(colorHi, colorOff, t);

            if (oldAccent != accent)
            {
                i = 0;
            }
        } else 
        {
            rend.material.color = Color.Lerp(colorLow, colorOff, t);
        }

        oldAccent = accent;
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
