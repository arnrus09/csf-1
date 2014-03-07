import java.awt.*;

import javax.swing.*;
import javax.sound.midi.*;
import java.util.*;
import java.awt.event.*;

public class Melody {

	JPanel mainPanel;
	ArrayList<JCheckBox> checkboxList;
	Sequencer sequencer;
	Sequence sequence;
	Track track;
	JFrame theFrame;
	
	String[] instrumentNames = {"7", "b7", "6", "b6", "5", "b5", "4", "3", "b3", "2", "b2", "1"};
	int[] instruments = {12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1}; 
	
	public void buildGUI() {
		theFrame = new JFrame("Melody");
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BorderLayout layout = new BorderLayout();
		JPanel background = new JPanel(layout);
		background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		checkboxList = new ArrayList<JCheckBox>();
		Box buttonBox = new Box(BoxLayout.Y_AXIS);
		
		JButton start = new JButton("Start");
		start.addActionListener(new MyStartListener());
		buttonBox.add(start);
		
		JButton pause = new JButton("Pause");
		pause.addActionListener(new MyPauseListener());
		buttonBox.add(pause);
		
		JButton stop = new JButton("Stop");
		buttonBox.add(stop);
		
		JButton upTempo = new JButton("Tempo Up");
		upTempo.addActionListener(new MyUpTempoListener());
		buttonBox.add(upTempo);
		
		JButton downTempo = new JButton("Tempo Down");
		downTempo.addActionListener(new MyDownTempoListener());
		buttonBox.add(downTempo);
		
		JButton clear = new JButton("Clear");
		clear.addActionListener(new MyClearListener());
		buttonBox.add(clear);
		
		JButton save = new JButton("Save");
		buttonBox.add(save);
		
		JButton load = new JButton("Load");
		buttonBox.add(load);
		
		//JTextField bpmTempo = new JTextField();
		//bpmTempo.addActionListener(new MyBpmTempoListener());
		//bpmTempo.setText(Float.toString(sequencer.getTempoInBPM()));
				
		// create a name box for each instrument
		Box nameBox = new Box(BoxLayout.Y_AXIS);
		for (int i = 0; i < 12; i++) {
			nameBox.add(new Label(instrumentNames[i]));
		}
		
		background.add(BorderLayout.EAST, buttonBox);
		background.add(BorderLayout.WEST, nameBox);
		
		theFrame.getContentPane().add(background);
		
		// grid of x rows by y columns
		GridLayout grid = new GridLayout(12, 32);
		grid.setVgap(1);
		grid.setHgap(2);
		mainPanel = new JPanel(grid);
		background.add(BorderLayout.CENTER, mainPanel);
		
		// create the total number of boxes
		for (int i = 0; i < 384; i++) {
			JCheckBox c = new JCheckBox();
			c.setSelected(false);
			checkboxList.add(c);
			mainPanel.add(c);
		} //end for loop
		
		setUpMidi();
		
		theFrame.setBounds(50, 50, 300, 300);
		theFrame.pack();
		theFrame.setVisible(true);
	} //close buildGUI method
	
	public void setUpMidi() {
		try {
			sequencer = MidiSystem.getSequencer();
			sequencer.open();sequence = new Sequence(Sequence.PPQ, 4);
			track = sequence.createTrack();
			sequencer.setTempoInBPM(120);
						
	
		} catch(Exception e) {e.printStackTrace();}
	} //close setUpMidi method
	
	public void buildTrackAndStart() {
		int[] trackList = null;
		
		sequence.deleteTrack(track);
		track = sequence.createTrack();
		
			for (int i = 0; i < 12; i++) {		// for each row
				trackList = new int[32];
				
				int key = instruments[i];
				
				for (int j = 0; j < 32; j++) {  // for each beat in this row
					
					JCheckBox jc = checkboxList.get(j + 32 * i);		// ?
					if (jc.isSelected()) {
						trackList[j] = key;
					} else {
						trackList[j] = 0;
					}
				} //close inner for loop
			
				makeTracks(trackList);
				track.add(makeEvent(176, 1, 127, 0, 32));	// for this instrument and for all beats
			} //close outer for loop
		
		track.add(makeEvent(192, 9, 1, 0, 31));
		try {
			sequencer.setSequence(sequence);
			sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
			sequencer.start();
			sequencer.setTempoInBPM(120);
		} catch(Exception e) {e.printStackTrace();}
	} //close buildTrackAndStart method

	public class MyStartListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			buildTrackAndStart();
		}
	} //close inner class
	
	public class MyPauseListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			sequencer.stop();
		}
	} //close inner class
	
	public class MyUpTempoListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			float tempoFactor = sequencer.getTempoFactor();
			sequencer.setTempoFactor((float)(tempoFactor * 1.03));
		}
	} //close inner class

	public class MyDownTempoListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			float tempoFactor = sequencer.getTempoFactor();
			sequencer.setTempoFactor((float)(tempoFactor * .97));
		}
	} //close inner class
	
	public class MyClearListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			for (int i = 0; i < checkboxList.size() ; i++) {
				checkboxList.get(i).setSelected(false); 
			}
		}
	} //close inner class
	
	//public class MyBpmTempoListener implements ActionListener 
	
	public void makeTracks(int[] list) {		//makes events for one instrument at a time, for all beats
												
		for (int i = 0; i < 32; i++) {
			int key = list[i];
			
			// another for loop could go here
			// 1
			if (key == 1) {
				track.add(makeEvent(144, 8, 84, 100, i));
				track.add(makeEvent(128, 8, 84, 100, i+1));
			}
			
			// b2
			if (key == 2) {
				track.add(makeEvent(144, 8, 85, 100, i));
				track.add(makeEvent(128, 8, 85, 100, i+1));
			}
			
			// 2
			if (key == 3) {
				track.add(makeEvent(144, 8, 86, 100, i));
				track.add(makeEvent(128, 8, 86, 100, i+1));
			}
						
			// b3
			if (key == 4) {
				track.add(makeEvent(144, 8, 87, 100, i));
				track.add(makeEvent(128, 8, 87, 100, i+1));
			}
			
			// 3
			if (key == 5) {
				track.add(makeEvent(144, 8, 88, 100, i));
				track.add(makeEvent(128, 8, 88, 100, i+1));
			}			
			
			// 4
			if (key == 6) {
				track.add(makeEvent(144, 8, 89, 100, i));
				track.add(makeEvent(128, 8, 89, 100, i+1));
			}
			
			// b5
			if (key == 7) {
				track.add(makeEvent(144, 8, 90, 100, i));
				track.add(makeEvent(128, 8, 90, 100, i+1));
			}
			
			// 5
			if (key == 8) {
				track.add(makeEvent(144, 8, 91, 100, i));
				track.add(makeEvent(128, 8, 91, 100, i+1));
			}
			
			// b6
			if (key == 9) {
				track.add(makeEvent(144, 8, 92, 100, i));
				track.add(makeEvent(128, 8, 92, 100, i+1));
			}
			
			// 6
			if (key == 10) {
				track.add(makeEvent(144, 8, 93, 100, i));
				track.add(makeEvent(128, 8, 93, 100, i+1));
			}
			
			// b7
			if (key == 11) {
				track.add(makeEvent(144, 8, 94, 100, i));
				track.add(makeEvent(128, 8, 94, 100, i+1));
			}
			
			// 7
			if (key == 12) {
				track.add(makeEvent(144, 8, 95, 100, i));
				track.add(makeEvent(128, 8, 95, 100, i+1));
			}
		}
	}

	public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
		MidiEvent event = null;
		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(comd, chan, one, two);
			event = new MidiEvent(a, tick);
			
		} catch(Exception e) {e.printStackTrace(); }
		return event;
	}


} //close class
