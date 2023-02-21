package com.uv.tester;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tyorikan.voicerecordingvisualizer.RecordingSampler;
import com.tyorikan.voicerecordingvisualizer.VisualizerView;
public class VoiceRecActivity extends AppCompatActivity implements
        RecordingSampler.CalculateVolumeListener {

    private static final String TAG = VoiceRecActivity.class.getSimpleName();

    // Recording Info
    private RecordingSampler mRecordingSampler;

    // View
    private VisualizerView mVisualizerView3;
    private FloatingActionButton mFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_rec);

        mVisualizerView3 = (VisualizerView) findViewById(R.id.visualizer3);

        // create AudioRecord
        mRecordingSampler = new RecordingSampler();
        mRecordingSampler.setVolumeListener(this);
        mRecordingSampler.setSamplingInterval(200);
        mRecordingSampler.link(mVisualizerView3);

        mRecordingSampler.startRecording();
    }

    @Override
    protected void onDestroy() {
        mRecordingSampler.release();
        super.onDestroy();
    }

    @Override
    public void onCalculateVolume(int volume) {
        // for custom implement
        Log.d(TAG, String.valueOf(volume));
        if (volume < 30){
            mVisualizerView3.setBackgroundColor(Color.parseColor("#808080"));
        }
        else {
            mVisualizerView3.setBackgroundColor(Color.parseColor("#FFC0CB"));
        }
    }
}