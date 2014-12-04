package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import record.PlayingThread;
import record.RecordingThread;

@SuppressWarnings("serial")
public class SoundTab extends JPanel {
	String filename;
	SoundController controller;

	JButton btnRecord;
	JButton btnPlay;

	boolean recording;
	boolean playing;
	RecordingThread recThread;
	PlayingThread playThread;

	public SoundTab(String filename, SoundController theController) {
		this.filename = filename;
		this.controller = theController;
		this.recording = false;
		this.playing = false;
		this.recThread = null;
		this.playThread = null;
		GridLayout layout = new GridLayout(5, 5);
		layout.setHgap(5);
		layout.setVgap(5);
		layout.setColumns(1);
		this.setLayout(layout);

		addBlanks(11);
		btnRecord = new JButton();
		this.add(btnRecord);
		btnRecord.setText("Record");
		final SoundTab me = this;
		btnRecord.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.recordPressHandler(me);
			}

		});
		addBlanks(1);
		btnPlay = new JButton();
		this.add(btnPlay);
		btnPlay.setText("Play");
		btnPlay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.playPressHandler(me);
			}

		});
		addBlanks(9);
	}

	private void addBlanks(int num) {
		for (int i = 0; i < num; i++) {
			this.add(new JLabel());
		}
	}
}
