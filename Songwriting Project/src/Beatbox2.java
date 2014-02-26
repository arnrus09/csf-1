import java.awt.*;
import javax.swing.*;
import javax.sound.midi.*;
import java.util.*;
import java.awt.event.*;

public class Beatbox2 {

	JPanel mainPanel;
	ArrayList<JCheckBox> checkboxList;
	Sequencer sequencer;
	Sequence sequence;
	Track track;
	JFrame theFrame;
	
	String[] instrumentNames = {"Bass Drum", "Pedal Hi-Hat", 
			"Open Hi-Hat", "Electric Snare"};
	int[] instruments = {35, 44, 46, 40}; 
	
	public static void main (String[] args){
		new Beatbox2().buildGUI();
	}
	
	public void buildGUI() {
		theFrame = new JFrame("Beatbox2");
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BorderLayout layout = new BorderLayout();
		JPanel background = new JPanel(layout);
		background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		checkboxList = new ArrayList<JCheckBox>();
		Box buttonBox = new Box(BoxLayout.Y_AXIS);
		
		JButton start = new JButton("Start");
		start.addActionListener(new MyStartListener());
		buttonBox.add(start);
		
		JButton stop = new JButton("Stop");
		stop.addActionListener(new MyStopListener());
		buttonBox.add(stop);
		
		JButton upTempo = new JButton("Tempo Up");
		upTempo.addActionListener(new MyUpTempoListener());
		buttonBox.add(upTempo);
		
		JButton downTempo = new JButton("Tempo Down");
		downTempo.addActionListener(new MyDownTempoListener());
		buttonBox.add(downTempo);
		
		//JTextField bpmTempo = new JTextField();
		//bpmTempo.addActionListener(new MyBpmTempoListener());
		//bpmTempo.setText(Float.toString(sequencer.getTempoInBPM()));
				
		// create a name box for each instrument
		Box nameBox = new Box(BoxLayout.Y_AXIS);
		for (int i = 0; i < 4; i++) {
			nameBox.add(new Label(instrumentNames[i]));
		}
		
		background.add(BorderLayout.EAST, buttonBox);
		background.add(BorderLayout.WEST, nameBox);
		
		theFrame.getContentPane().add(background);
		
		// grid of x rows by y columns
		GridLayout grid = new GridLayout(4, 32);
		grid.setVgap(1);
		grid.setHgap(2);;
		mainPanel = new JPanel(grid);
		background.add(BorderLayout.CENTER, mainPanel);
		
		// create the total number of boxes
		for (int i = 0; i < 128; i++) {
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
		
			for (int i = 0; i < 4; i++) {		// for each row
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
	
	public class MyStopListener implements ActionListener {
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
	
	//public class MyBpmTempoListener implements ActionListener 
	
	public void makeTracks(int[] list) {		//makes events for one instrument at a time, for all beats
												//I think this block is the problem when reducing # of instruments
		for (int i = 0; i < 32; i++) {
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
