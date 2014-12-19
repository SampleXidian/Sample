package com.example.sample.tool;

import java.io.IOException;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;

public class Recorder {

	private MediaRecorder myRecorder;
	private MediaPlayer myPlayer;
	public static String LOCAL_PATH;

	public Recorder(MediaRecorder myrecorder,MediaPlayer myplayer) {
		this.myRecorder = myrecorder;
		this.myPlayer = myplayer;
		LOCAL_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();  
        LOCAL_PATH += "/audiorecordtest.mp3";
	}
	
	public void startRecord(){
		
        System.out.println(LOCAL_PATH);
        try {  
        	myRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            myRecorder.setOutputFile(LOCAL_PATH);
            myRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        	myRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            myRecorder.prepare();  
            myRecorder.start();
        } catch (IllegalStateException e) { 
        	System.out.println("something wrong");
        } catch (IOException e) {
			e.printStackTrace();
		}  
        
	}
	
	public void stopRecord(){
		myRecorder.stop();  
        myRecorder.release();  
        myRecorder = null;
	}
	
	public void startPlay(){
		myPlayer.reset();  
        try{  
            myPlayer.setDataSource(LOCAL_PATH);  
            myPlayer.prepare();  
            myPlayer.start();  
        }catch(IOException e){  
        	System.out.println("start to play");
        }
	}
	
}
