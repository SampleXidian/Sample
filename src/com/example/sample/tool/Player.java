package com.example.sample.tool;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

public class Player {
	private MediaPlayer myPlayer;
	private ChipList chipList;
	private boolean isContinuing;//  «∑Ò¡¨≤•

	public Player(MediaPlayer myPlayer) {
		this.myPlayer = myPlayer;
		isContinuing = false;
		
		myPlayer.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer player) {
				if (isContinuing)
					next();
			}
		});
	}

	public void setList(ChipList chipList) {
		this.chipList = chipList;
	}

	public void changeState(boolean c) {
		isContinuing = c;
	}

	public void prepare() {

		try {
			myPlayer.reset();
			myPlayer.setDataSource(chipList.next());
			myPlayer.prepare();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void play() {
		myPlayer.start();
	}

	public void pause() {
		myPlayer.pause();
	}

	public void stop() {
		myPlayer.stop();
	}

	public void next() {
		prepare();
		play();
	}
}
