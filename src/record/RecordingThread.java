package record;

import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class RecordingThread implements Runnable {
	private AudioFormat format;
	private String outputFilename;
	private boolean recording;

	public RecordingThread(AudioFormat format, String outputFilename) {
		this.format = format;
		this.outputFilename = outputFilename;
		this.recording = false;
	}

	public void startRecording() {
		new Thread(this).start();
	}

	public void stopRecording() {
		this.recording = false;
	}

	@Override
	public void run() {
		TargetDataLine microphone = null;
		try {
			microphone = AudioSystem.getTargetDataLine(format);
			microphone.open();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Assume that the TargetDataLine, line, has already
		// been obtained and opened.
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int numBytesRead;
		byte[] data = new byte[microphone.getBufferSize() / 5];

		// Begin audio capture.
		microphone.start();
		this.recording = true;
		int z = 0;

		while (this.recording && z == 0) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			numBytesRead = microphone.read(data, 0, data.length);
			// Save this chunk of data.
			out.write(data, 0, numBytesRead);
		}

		microphone.close();

		try {
			FileWriter f = new FileWriter(this.outputFilename);
			f.write(out.toString());
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
