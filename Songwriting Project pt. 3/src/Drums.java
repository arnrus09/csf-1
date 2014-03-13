import java.awt.*;

import javax.swing.*;
import javax.sound.midi.*;
import java.util.*;
import java.awt.event.*;

public class Drums {

	JPanel mainPanel;
	ArrayList<JCheckBox> checkboxList;
	Sequencer sequencer;
	Sequence sequence;
	Track track;
	JFrame theFrame;
	String tempo = null;
	int speed = 120;
	
	String[] instrumentNames = {"Bass Drum", "Pedal Hi-Hat", 
			"Open Hi-Hat", "Electric Snare"};
	int[] instruments = {35, 44, 46, 40}; 
	int sectionLength = 64;
		
	public void buildGUI() {
		theFrame = new JFrame("Drums");
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BorderLayout layout = new BorderLayout();
		JPanel background = new JPanel(layout);
		background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		checkboxList = new ArrayList<JCheckBox>();
		Box buttonBox = new Box(BoxLayout.Y_AXIS);
		
		//JTextField showTempo = new JTextField();
		//buttonBox.add(showTempo);
		//showTempo.disable();
		//showTempo.setText(tempo);
		
		JButton start = new JButton("Start");
		start.addActionListener(new MyStartListener());
		buttonBox.add(start);
				
		JButton pause = new JButton("Pause");
		pause.addActionListener(new MyPauseListener());
		buttonBox.add(pause);
		
		JButton stop = new JButton("Stop");
		stop.addActionListener(new MyStopListener());
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
		
		//JButton save = new JButton("Save");
		//save.addActionListener(new MySaveListener());
		//buttonBox.add(save);
		
		//JButton load = new JButton("Load");
		//load.addActionListener(new MyLoadListener());
		//buttonBox.add(load);
		
		// create a name box for each instrument
		Box nameBox = new Box(BoxLayout.Y_AXIS);
		for (int i = 0; i < (instruments.length) ; i++) {
			nameBox.add(new Label(instrumentNames[i]));
		}
		
		background.add(BorderLayout.EAST, buttonBox);
		background.add(BorderLayout.WEST, nameBox);
		
		theFrame.getContentPane().add(background);
		
		// grid of x rows by y columns
		GridLayout grid = new GridLayout(instruments.length, sectionLength);
		grid.setVgap(1);
		grid.setHgap(2);;
		mainPanel = new JPanel(grid);
		background.add(BorderLayout.CENTER, mainPanel);
		
		// create the total number of check boxes
		for (int i = 0; i < (instruments.length * sectionLength); i++) {
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
			tempo = Float.toString(sequencer.getTempoInBPM());
						
		} catch(Exception e) {e.printStackTrace();}
	} //close setUpMidi method
	
	public void buildTrackAndStart() {
		int[] trackList = null;
		
		sequence.deleteTrack(track);
		track = sequence.createTrack();
		
			for (int i = 0; i < instruments.length; i++) {		// for each row
				trackList = new int[sectionLength];
				
				int key = instruments[i];
				
				for (int j = 0; j < sectionLength; j++) {  // for each beat in this row
					
					JCheckBox jc = checkboxList.get(j + sectionLength * i);		// ?
					if (jc.isSelected()) {
						trackList[j] = key;
					} else {
						trackList[j] = 0;
					}
				} //close inner for loop
			
				makeTracks(trackList);
				track.add(makeEvent(176, 1, 127, 0, sectionLength));	// for this instrument and for all beats
			} //close outer for loop
		
		track.add(makeEvent(192, 9, 1, 0, sectionLength - 1));
		try {
			sequencer.setSequence(sequence);
			sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
			sequencer.start();
			sequencer.setTempoInBPM(speed);
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
	
	public class MyStopListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			sequencer.stop();
			sequencer.setTickPosition(0);
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
	
	//public class MySaveListener implements ActionListener {
		//public void actionPerformed(ActionEvent a) {
			//for (int i = 0; i < checkboxList.size() ; i++) {
				//if (checkboxList.get(i).isSelected() == true) {
					//...
				//}
			//}
		//}
	//} //close inner class
	
	//public class MyLoadListener implements ActionListener {
		//public void actionPerformed(ActionEvent a) {
			//for (int i = 0; i < checkboxList.size() ; i++) {
				//for (int j = 0, i <) 
				//checkboxList.get(i).setSelected(true)
				//}
			//}
		//}
	//} //close inner class	
	
	
	public void makeTracks(int[] list) {		//makes events for one instrument at a time, for all beats
												
		for (int i = 0; i < sectionLength; i++) {
			int key = list[i];
			
			if (key != 0) {
				track.add(makeEvent(144, 9, key, 100, i));
				track.add(makeEvent(128, 9, key, 100, i+1));
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
