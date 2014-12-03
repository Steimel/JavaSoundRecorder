package gui;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

import record.PlayingThread;
import record.RecordingThread;

public class SoundAppController {
	private SoundAppGui gui;
	private boolean recording;
	private boolean playing;
	private AudioFormat format = new AudioFormat(8000.0f, 16, 1, true, true);
	private RecordingThread recThread;
	private PlayingThread playThread;
	private final String soundDirectory = "sounds\\";

	public SoundAppController(SoundAppGui gui) {
		this.gui = gui;
		this.recording = false;
		this.playing = false;
	}

	private String getFilename() {
		return gui.txtFilename.getText();
	}

	private String getFilepath() {
		return soundDirectory + getFilename();
	}

	private final String invalidChars = " */\\\"?:<>|";

	private boolean validFilename() {
		String name = getFilename();
		if (name.length() == 0)
			return false;
		for (int i = 0; i < name.length(); i++) {
			if (invalidChars.contains(name.subSequence(i, i + 1))) {
				return false;
			}
		}
		return true;
	}

	private boolean fileExists() {
		return (new File(getFilepath()).isFile());
	}

	public void recordPressHandler() {
		this.recording = !this.recording;
		if (this.recording) {
			if (!validFilename()) {
				this.recording = false;
				System.out.println("Invalid Filename");
				return;
			}
			gui.btnPlay.setEnabled(false);
			gui.btnRecord.setText("Stop");
			recThread = new RecordingThread(format, getFilepath());
			recThread.startRecording();
		} else {
			gui.btnPlay.setEnabled(true);
			gui.btnRecord.setText("Record");
			recThread.stopRecording();
		}
	}

	public void playPressHandler() {
		this.playing = !this.playing;
		if (this.playing) {
			if (!validFilename()) {
				this.playing = false;
				System.out.println("Invalid filename");
				return;
			}
			if (!fileExists()) {
				this.playing = false;
				System.out.println("File does not exist");
				return;
			}
			gui.btnRecord.setEnabled(false);
			gui.btnPlay.setText("Pause");
			playThread = new PlayingThread(format, getFilepath());
			playThread.startPlaying(new LineListener() {

				@Override
				public void update(LineEvent event) {
					if (event.getType() == LineEvent.Type.STOP) {
						if (!recording) {
							playing = false;
							gui.btnPlay.setText("Play");
							gui.btnRecord.setEnabled(true);
						}
					}
				}

			});
		} else {
			gui.btnRecord.setEnabled(true);
			gui.btnPlay.setText("Play");
			playThread.stopPlaying();
		}
	}
}
