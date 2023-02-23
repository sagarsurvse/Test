package com.uv.tester;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Toast;

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

    View v1,v2,v3,v4,v5,v6,v7,v8,v9,v10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_rec);

        mVisualizerView3 = (VisualizerView) findViewById(R.id.visualizer3);

        v1 = findViewById(R.id.v1);
        v2 = findViewById(R.id.v2);
        v3 = findViewById(R.id.v3);
        v4 = findViewById(R.id.v4);
        v5 = findViewById(R.id.v5);
        v6 = findViewById(R.id.v6);
        v7 = findViewById(R.id.v7);
        v8 = findViewById(R.id.v8);
        v9 = findViewById(R.id.v9);
        v10 = findViewById(R.id.v10);



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
        if (volume >= 25){
            v1.setBackgroundResource(R.drawable.greenhape);
            v2.setBackgroundResource(R.drawable.greenhape);
            v3.setBackgroundResource(R.drawable.greenhape);
            v4.setBackgroundResource(R.drawable.greenhape);
            v5.setBackgroundResource(R.drawable.greenhape);
            v6.setBackgroundResource(R.drawable.greyshape);
            v7.setBackgroundResource(R.drawable.greyshape);
            v8.setBackgroundResource(R.drawable.greyshape);
            v9.setBackgroundResource(R.drawable.greyshape);
            v10.setBackgroundResource(R.drawable.greyshape);

            if(volume >= 30 && volume <= 50){
                v1.setBackgroundResource(R.drawable.greenhape);
                v2.setBackgroundResource(R.drawable.greenhape);
                v3.setBackgroundResource(R.drawable.greenhape);
                v4.setBackgroundResource(R.drawable.greenhape);
                v5.setBackgroundResource(R.drawable.greenhape);
                v6.setBackgroundResource(R.drawable.greenhape);
                v7.setBackgroundResource(R.drawable.greenhape);
                v8.setBackgroundResource(R.drawable.greenhape);
                v9.setBackgroundResource(R.drawable.greyshape);
                v10.setBackgroundResource(R.drawable.greyshape);
            }
        }
        else {
            v1.setBackgroundResource(R.drawable.greyshape);
            v2.setBackgroundResource(R.drawable.greyshape);
            v3.setBackgroundResource(R.drawable.greyshape);
            v4.setBackgroundResource(R.drawable.greyshape);
            v5.setBackgroundResource(R.drawable.greyshape);
            v6.setBackgroundResource(R.drawable.greyshape);
            v7.setBackgroundResource(R.drawable.greyshape);
            v8.setBackgroundResource(R.drawable.greyshape);
            v9.setBackgroundResource(R.drawable.greyshape);
            v10.setBackgroundResource(R.drawable.greyshape);
        }

        if (volume < 30){
            mVisualizerView3.setBackgroundColor(Color.parseColor("#808080"));
        }
        else {
            mVisualizerView3.setBackgroundColor(Color.parseColor("#FFC0CB"));
        }
    }
}