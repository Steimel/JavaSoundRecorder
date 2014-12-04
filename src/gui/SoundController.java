package gui;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import record.PlayingThread;
import record.RecordingThread;

public class SoundController {

	private SoundGui gui;
	private final String soundDirectory = "sounds/";
	private String[] filenames;
	private AudioFormat format = new AudioFormat(8000.0f, 16, 1, true, true);

	public SoundController(SoundGui gui) {
		this.gui = gui;
	}

	void init() {
		refreshFilenames();
	}

	private void refreshFilenames() {
		String[] fNames = new File(soundDirectory).list();
		if(fNames != null) {
			Arrays.sort(fNames);
		}
		int total = 0;
		for(int i = 0; i < fNames.length; i++) {
			if(!fNames[i].startsWith(".")) total++;
		}
		filenames = new String[total];
		int index = 0;
		for(int i = 0; i < fNames.length; i++) {
			if(!fNames[i].startsWith(".")) {
				filenames[index] = fNames[i];
				index++;
			}
		}
		gui.lstFiles.setListData(filenames);
	}

	private String getSelectedFilename() {
		return (String) gui.lstFiles.getSelectedValue();
	}

	private String getSelectedFilepath() {
		String filename = (String) gui.lstFiles.getSelectedValue();
		if (filename == null)
			return null;
		return soundDirectory + filename;
	}

	void mnuExitPressedHandler() {
		gui.dispose();
	}

	void mnuOpenPressedHandler() {
		String name = getSelectedFilename();
		if (name == null) {
			System.out.println("No file selected");
			return;
		}
		open(name);
	}

	private void open(String filename) {
		gui.tabMain.addTab(filename, new SoundTab(filename, this));
	}

	private final String invalidChars = " */\\\"?:<>|";
	private final int VALIDITY_VALID = 0;
	private final int VALIDITY_INVALID_CHAR = 1;
	private final int VALIDITY_EMPTY = 2;
	private final int VALIDITY_TAKEN = 3;
	private final int VALIDITY_STARTS_WITH_PERIOD = 4;

	private int validFilename(String name) {
		if (name.length() == 0)
			return VALIDITY_EMPTY;
		if (name.startsWith("."))
			return VALIDITY_STARTS_WITH_PERIOD;
		for (int i = 0; i < name.length(); i++) {
			if (invalidChars.contains(name.subSequence(i, i + 1))) {
				return VALIDITY_INVALID_CHAR;
			}
		}
		for (int i = 0; i < filenames.length; i++) {
			if (filenames[i].equalsIgnoreCase(name)) {
				return VALIDITY_TAKEN;
			}
		}
		return VALIDITY_VALID;
	}

	void mnuNewPressedHandler() {
		String newFilename = JOptionPane.showInputDialog(gui,
				"New Sound Name:", "Name That Sound!",
				JOptionPane.PLAIN_MESSAGE);
		if (newFilename == null) {
			System.out.println("File creation cancelled");
			return;
		}
		int validity = validFilename(newFilename);
		if (validity == VALIDITY_INVALID_CHAR) {
			System.out.println("Invalid character in filename");
			return;
		}
		if (validity == VALIDITY_EMPTY) {
			System.out.println("Filename empty");
			return;
		}
		if (validity == VALIDITY_TAKEN) {
			System.out.println("File already exists");
			return;
		}
		if (validity == VALIDITY_STARTS_WITH_PERIOD) {
			System.out.println("Filename cannot start with a period");
			return;
		}
		File newFile = new File(soundDirectory + newFilename);
		try {
			newFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		refreshFilenames();
	}

	void mnuDeletePressedHandler() {
		String path = getSelectedFilepath();
		if (path == null) {
			System.out.println("No file selected");
			return;
		}
		File f = new File(path);
		if (!f.isFile()) {
			System.out.println("File doesn't exist");
			return;
		}
		int response = JOptionPane.showConfirmDialog(gui, "Really delete "
				+ getSelectedFilename() + "?", "Are you sure?",
				JOptionPane.YES_NO_OPTION);
		if (response != JOptionPane.YES_OPTION) {
			return;
		}
		if (f.delete()) {
			System.out.println("Deleted " + getSelectedFilename()
					+ " successfully!");
			refreshFilenames();
		} else {
			System.out.println("Error deleting " + getSelectedFilename());
		}
	}

	public void recordPressHandler(SoundTab tab) {
		tab.recording = !tab.recording;
		if (tab.recording) {
			System.out.println("Recording: " + tab.filename);
			if (!(validFilename(tab.filename) == VALIDITY_TAKEN)) {
				tab.recording = false;
				System.out.println("Invalid Filename");
				return;
			}
			tab.btnPlay.setEnabled(false);
			tab.btnRecord.setText("Stop");
			tab.recThread = new RecordingThread(format, soundDirectory
					+ tab.filename);
			tab.recThread.startRecording();
		} else {
			System.out.println("Finished recording: " + tab.filename);
			tab.btnPlay.setEnabled(true);
			tab.btnRecord.setText("Record");
			tab.recThread.stopRecording();
		}
	}

	private boolean fileExists(String filename) {
		return (new File(soundDirectory + filename).isFile());
	}

	public void playPressHandler(SoundTab tab) {
		tab.playing = !tab.playing;
		if (tab.playing) {
			System.out.println("Playing: " + tab.filename);
			if (!(validFilename(tab.filename) == VALIDITY_TAKEN)) {
				tab.playing = false;
				System.out.println("Invalid filename");
				return;
			}
			if (!fileExists(tab.filename)) {
				tab.playing = false;
				System.out.println("File does not exist");
				return;
			}
			tab.btnRecord.setEnabled(false);
			tab.btnPlay.setText("Stop");
			tab.playThread = new PlayingThread(format, soundDirectory
					+ tab.filename);
			final SoundTab tabHolder = tab;
			tab.playThread.startPlaying(new LineListener() {

				@Override
				public void update(LineEvent event) {
					if (event.getType() == LineEvent.Type.STOP) {
						if (!tabHolder.recording) {
							System.out.println("Playback stopped");
							tabHolder.playing = false;
							tabHolder.btnPlay.setText("Play");
							tabHolder.btnRecord.setEnabled(true);
						}
					}
				}

			});
		} else {
			tab.btnRecord.setEnabled(true);
			tab.btnPlay.setText("Play");
			tab.playThread.stopPlaying();
		}
	}
}
