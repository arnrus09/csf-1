import java.awt.*;

import javax.swing.*;
import java.awt.event.*;

public class All {
	
	JPanel mainPanel;
	JFrame theFrame;
	Drums d = new Drums();
	Chords c = new Chords();
	Bass b = new Bass();
	Melody m = new Melody();
	
	public static void main (String[] args){
		new All().buildGUI();
	}
	
	public void buildGUI() {
		d.buildGUI();
		c.buildGUI();
		b.buildGUI();
		m.buildGUI();		
		
		theFrame = new JFrame("All");
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BorderLayout layout = new BorderLayout();
		JPanel background = new JPanel(layout);
		background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		Box buttonBox = new Box(BoxLayout.Y_AXIS);
		
		JButton startAll = new JButton("Start All");
		startAll.addActionListener(new MyStartAllListener());
		buttonBox.add(startAll);
		
		JButton pauseAll = new JButton("Pause All");
		pauseAll.addActionListener(new MyPauseAllListener());
		buttonBox.add(pauseAll);
		
		JButton stopAll = new JButton("Stop All");
		stopAll.addActionListener(new MyStopAllListener());
		buttonBox.add(stopAll);
		
		//JButton stop = new JButton("Stop");
		//buttonBox.add(stop);
		
		//JButton upTempoAll = new JButton("Tempo Up All");
		//upTempoAll.addActionListener(new MyUpTempoAllListener());
		//buttonBox.add(upTempoAll);
		
		//JButton downTempoAll = new JButton("Tempo Down All");
		//downTempoAll.addActionListener(new MyDownTempoAllListener());
		//buttonBox.add(downTempoAll);
				
		//JButton saveAll = new JButton("Save All");
		//buttonBox.add(saveAll);
		
		//JButton loadAll = new JButton("Load All");
		//buttonBox.add(loadAll);
		
		//JTextField bpmTempo = new JTextField();
		//bpmTempo.addActionListener(new MyBpmTempoListener());
		//bpmTempo.setText(Float.toString(sequencer.getTempoInBPM()));
				
		// create a name box for each instrument
		Box nameBox = new Box(BoxLayout.Y_AXIS);
		for (int i = 0; i < 12; i++) {
	//		nameBox.add(new Label(instrumentNames[i]));
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
		
		//} //end for loop
		
		//setUpMidi();
		
		theFrame.setBounds(50, 50, 300, 300);
		theFrame.pack();
		theFrame.setVisible(true);
		
	} //close buildGUI method
	
	public class MyStartAllListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			d.buildTrackAndStart();
			c.buildTrackAndStart();
			b.buildTrackAndStart();
			m.buildTrackAndStart();		
		}
	} //close inner class
	
	public class MyPauseAllListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			c.sequencer.stop(); 
			d.sequencer.stop();
			b.sequencer.stop();
			m.sequencer.stop();	
		}
	} //close inner class
	
	public class MyStopAllListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			c.sequencer.stop(); 
			c.sequencer.setTickPosition(0);
			d.sequencer.stop();
			d.sequencer.setTickPosition(0);
			b.sequencer.stop();
			b.sequencer.setTickPosition(0);
			m.sequencer.stop();	
			m.sequencer.setTickPosition(0);
		}
	} //close inner class
	
	//public class MyUpTempoAllListener implements ActionListener {
		//public void actionPerformed(ActionEvent a) {
			//float tempoFactor = sequencer.getTempoFactor();
			//sequencer.setTempoFactor((float)(tempoFactor * 1.03));
		//}
	//} //close inner class

	//public class MyDownTempoAllListener implements ActionListener {
		//public void actionPerformed(ActionEvent a) {
			//float tempoFactor = sequencer.getTempoFactor();
			//sequencer.setTempoFactor((float)(tempoFactor * .97));
		//}
	//} //close inner class
}
