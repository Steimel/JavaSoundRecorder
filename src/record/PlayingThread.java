package record;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;

public class PlayingThread implements Runnable {
	private AudioFormat format;
	private String inputFilename;
	private Clip line;
	private LineListener lineListener;

	public PlayingThread(AudioFormat format, String inputFilename) {
		this.format = format;
		this.inputFilename = inputFilename;
	}

	public void startPlaying(LineListener lineListener) {
		this.lineListener = lineListener;
		new Thread(this).run();
	}

	public void stopPlaying() {
		line.stop();
	}

	@Override
	public void run() {

		ByteArrayOutputStream bytes = new ByteArrayOutputStream();

		InputStream in = null;
		try {
			in = new FileInputStream(this.inputFilename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final byte[] buffer = new byte[512 * 1024]; // 512kb
		while (true) {
			int read = 0;
			try {
				read = in.read(buffer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (read < 0) {
				break;
			}
			bytes.write(buffer, 0, read);
		}

		DataLine.Info info = new DataLine.Info(Clip.class, format);
		line = null;
		try {
			line = (Clip) AudioSystem.getLine(info);
			line.open(format, bytes.toByteArray(), 0,
					bytes.toByteArray().length);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		line.addLineListener(lineListener);
		line.start();
	}

}
