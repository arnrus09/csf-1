import javax.sound.midi.*;

public class MIDItest {

	public static void main(String[] args) {
		
		try {
			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.open();
			
			Sequence seq = new Sequence(Sequence.PPQ, 4);
			Track track = seq.createTrack();
			
			track.add(makeEvent(144, 1, 68, 100, 0));
			track.add(makeEvent(128, 1, 68, 100, 2));
			
			track.add(makeEvent(144, 1, 72, 100, 4));
			track.add(makeEvent(128, 1, 72, 100, 6));
			
			track.add(makeEvent(144, 1, 74, 100, 8));
			track.add(makeEvent(128, 1, 74, 100, 10));
			
			track.add(makeEvent(144, 1, 77, 100, 12));
			track.add(makeEvent(128, 1, 77, 100, 14));
			
			track.add(makeEvent(144, 1, 75, 100, 14));
			track.add(makeEvent(128, 1, 75, 100, 18));
			
			track.add(makeEvent(144, 1, 72, 100, 20));
			track.add(makeEvent(128, 1, 72, 100, 22));
			
			track.add(makeEvent(144, 1, 68, 100, 24));
			track.add(makeEvent(128, 1, 68, 100, 26));
			
			track.add(makeEvent(144, 1, 65, 100, 28));
			track.add(makeEvent(128, 1, 65, 100, 30));
			
			track.add(makeEvent(144, 1, 62, 100, 30));
			track.add(makeEvent(128, 1, 62, 100, 32));
			
			track.add(makeEvent(144, 1, 62, 100, 32));
			track.add(makeEvent(128, 1, 62, 100, 34));
			
			track.add(makeEvent(144, 1, 62, 100, 34));
			track.add(makeEvent(128, 1, 62, 100, 36));
			
			track.add(makeEvent(144, 1, 63, 100, 36));
			track.add(makeEvent(128, 1, 63, 100, 44));
			
			track.add(makeEvent(144, 1, 60, 100, 36));
			track.add(makeEvent(128, 1, 60, 100, 44));
			
			track.add(makeEvent(144, 1, 56, 100, 36));
			track.add(makeEvent(128, 1, 56, 100, 44));
			
			sequencer.setSequence(seq);
			sequencer.setTempoInBPM(90);
			sequencer.start();
		} catch (Exception ex) {ex.printStackTrace();}
	} //close play

	
	public static MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
		MidiEvent event = null;
		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(comd, chan, one, two);
			event = new MidiEvent(a, tick);
		} catch (Exception e) { }
		return event;
	}

} //close class