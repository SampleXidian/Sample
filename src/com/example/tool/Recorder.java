package com.example.tool;

import java.io.IOException;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;

public class Recorder {

	private MediaRecorder myrecorder;
	private MediaPlayer myplayer;
	private String FileName;

	public Recorder(MediaRecorder myrecorder,MediaPlayer myplayer) {
		this.myrecorder = myrecorder;
		this.myplayer = myplayer;
		FileName = Environment.getExternalStorageDirectory().getAbsolutePath();  
        FileName += "/audiorecordtest.3gp";
	}
	
	public void startRecord(){
		
        System.out.println(FileName);
        try {  
        	myrecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            myrecorder.setOutputFile(FileName);
            myrecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        	myrecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            myrecorder.prepare();  
            myrecorder.start();
        } catch (IllegalStateException e) { 
        	System.out.println("something wrong");
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        
	}
	
	public void stopRecord(){
		myrecorder.stop();  
        myrecorder.release();  
        myrecorder = null;
	}
	
	public void startPlay(){
		myplayer = new MediaPlayer();  
        try{  
            myplayer.setDataSource(FileName);  
            myplayer.prepare();  
            myplayer.start();  
        }catch(IOException e){  
        	System.out.println("start to play");
        }
	}
	
}
