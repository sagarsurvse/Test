package com.uv.tester;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;

import com.uv.tester.camera.CameraManager;
import com.uv.tester.camera.CameraTest;


public class MainTest extends Activity {
    private static final String LOGTAG ="Device Test";
    private static final int TEST_SPK_SOUND = R.raw.acdc_sample;
    private static final int TEST_RECV_SOUND = R.raw.a1khz_44100hz_16bit;
    private static final long VIBRA_TIME = 2000;
    private MediaPlayer mediaPlayer = null;
    private AudioManager audioManager;
    private CameraManager cameraManager;
    private Vibrator vibra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);
        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        audioManager.setMode(AudioManager.STREAM_MUSIC);
        //int originalVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
        vibra = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        cameraManager = new CameraManager();
    }

    @Override
    protected void onStop(){
        super.onStop();
        cameraManager.disableCamera();
        stopSound();
        audioManager.setMode(AudioManager.MODE_NORMAL);
    }

    @Override
    protected void onPause(){
        super.onPause();
        cameraManager.disableCamera();
        stopSound();
    }

    @Override
    protected void onResume(){ super.onResume(); }

    @Override
    protected void onStart(){
        super.onStart();
        audioManager.setMode(AudioManager.STREAM_MUSIC);
    }
    //TODO: FIX bug onCompletion() were it plays same sound even if changed by selection??
    public void playSound(int testSound) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, testSound);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.seekTo(0);
                }
            });
        }
        if (!mediaPlayer.isPlaying()){
            //mediaPlayer.setAudioSessionId(testSound);
            mediaPlayer.start();
        }else {
            stopSound();
        }
    }

    private void stopSound(){
        if(mediaPlayer == null) return;
        if(mediaPlayer.isPlaying()) {
            mediaPlayer.reset();
            mediaPlayer.release();
        }
        mediaPlayer = null;
    }
    public void playSoundOnSpeaker(View view) {
        audioManager.setSpeakerphoneOn(true);
        playSound(TEST_SPK_SOUND);
    }

    public void frontCameraTest(View view) {cameraTest(CameraManager.FRONT_CAMERA);}
    public void backCameraTest(View view) {
        cameraTest(CameraManager.BACK_CAMERA);
    }
    public void OpenMicTest(View view) {
        Intent intent = new Intent(this, MicrophoneTest.class);
        startActivity(intent);
    }
    private void cameraTest(int cam){
        Intent intent = new Intent(this, CameraTest.class);
        Bundle b = new Bundle();
        b.putInt("camera",cam);
        intent.putExtras(b);
        startActivity(intent);
    }


}
