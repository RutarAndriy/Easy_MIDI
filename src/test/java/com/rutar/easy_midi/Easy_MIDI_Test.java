package com.rutar.easy_midi;

import org.junit.Test;

import static org.junit.Assert.*;

// ............................................................................

public class Easy_MIDI_Test {

// ............................................................................

@Test
public void test_Notes() {

int[] notes = { Note.p93_7_La,
                Note.p62_5_Re,
                Note.p41_3_Fa,
                Note.p63_5_Re_Diese,
                Note.p27_2_Re_Diese };

int[] instruments = { Note.i108_Kalimba,
                      Note.i57_Trombone,
                      Note.i14_Tubular_Bells,
                      Note.i18_Rock_Organ,
                      Note.i13_Xylophone };

int[] durations = { 900, 700, 500, 800, 900 };

// ............................................................................

try {

for (int z = 0; z < notes.length; z++) {

    Tools.playNote(new Note(notes[z], instruments[z]), durations[z]);
    
    try { Thread.sleep(durations[z]); }
    catch (Exception e) { assertTrue(false); }

}

assertTrue(true);

}

// ............................................................................

catch (Throwable t) {

    t.printStackTrace();
    assertTrue(false);

}
}

// ............................................................................

@Test
public void test_Drums() {

int[] drums = { Drum.d39_Hand_Clap,
                Drum.d49_Crash_Cymbal_1,
                Drum.d41_Low_Floor_Tom };

int[] durations = { 700, 900, 700 };
    
try {

for (int z = 0; z < drums.length; z++) {

    Tools.playDrum(new Drum(drums[z]), durations[z]);
    
    try { Thread.sleep(durations[z]); }
    catch (Exception e) { assertTrue(false); }

}

assertTrue(true);
    
}

// ............................................................................

catch (Throwable t) {

    t.printStackTrace();
    assertTrue(false);

}
}

// ............................................................................

}