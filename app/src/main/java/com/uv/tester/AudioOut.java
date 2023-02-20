package com.uv.tester;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

public class AudioOut
{
    private AudioTrack my;

    public AudioOut (int samplerate, int buffersize)
    {
        my = new AudioTrack(AudioManager.STREAM_MUSIC, samplerate, AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT, buffersize, AudioTrack.MODE_STREAM);
        my.play();
    }

    public void submit (short[] data, int size)
    {
        my.write(data, 0, size);
    }
}