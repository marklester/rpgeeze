package view;

import javax.sound.midi.*;
import java.io.*;


public class AudioThread extends Thread {

	private volatile boolean muted = false;
	
	public AudioThread() {
		super();
	}
	
	public void run() {
		while(!interrupted()) {
			if(!muted) {
		        File midiFile = new File("res/audio/finalcd.mid");
		        if(!midiFile.exists() || midiFile.isDirectory() || !midiFile.canRead()) {
		            System.out.println("Can't find file");
		        }
		        // Play once
		        try {
		            Sequencer sequencer = MidiSystem.getSequencer();
		            sequencer.setSequence(MidiSystem.getSequence(midiFile));
		            sequencer.open();
		            sequencer.start();
		            while(true) {
		                if(sequencer.isRunning()) {
		                    try {
		                        Thread.sleep(1000); // Check every second
		                    } catch(InterruptedException ignore) {
		                        this.interrupt();
		                    }
		                } else {
		                    break;
		                }
		            }
		            // Close the MidiDevice & free resources
		            sequencer.stop();
		            sequencer.close();
		        } catch(MidiUnavailableException mue) {
		            System.out.println("Midi device unavailable!");
		        } catch(InvalidMidiDataException imde) {
		            System.out.println("Invalid Midi data!");
		        } catch(IOException ioe) {
		            System.out.println("I/O Error!");
		        } 
			}
		}
	}
	
	public void setMute(boolean b) {
		muted = b;
	}
	
	public boolean isMuted() {
		return muted;
	}
	
}
