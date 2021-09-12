package com.rutar.easy_midi;

import java.util.*;

// ............................................................................

public class Chord {

int part = 4;
int counter16 = 0;
    
private final ArrayList <Drum> drums = new ArrayList<>();
private final ArrayList <Note> notes = new ArrayList<>();

// ............................................................................

public Chord (int part) { this.part = part; }

// ............................................................................

public Chord drum (Drum drum) {

    drums.add(drum);
    return this;

}

// ............................................................................

public Chord drum (int part, int instrument, int velocity) {

    return drum(new Drum(part, instrument, velocity));

}

// ............................................................................

public Chord drum (int part, int instrument) {

    return drum(new Drum(part, instrument));

}
    
// ............................................................................

public Chord note (Note note) {
    
    notes.add(note);
    return this;

}

// ............................................................................

public Chord note (int part, int pitch, int instrument, int velocity) {

    return note(new Note(part, pitch, instrument, velocity));

}

// ............................................................................

public Chord note (int part, int pitch, int instrument) {

    return note(new Note(part, pitch, instrument));

}

// ............................................................................

void tick() {

    for (int i = 0; i < drums.size(); i++) {
        drums.get(i).tick();
    }

    for (int i = 0; i < notes.size(); i++) {
        notes.get(i).tick();
    }
}

// ............................................................................

void stop() {

    counter16 = 0;

    for (int i = 0; i < drums.size(); i++) {
        drums.get(i).stop();
    }

    for (int i = 0; i < notes.size(); i++) {
        notes.get(i).stop();
    }
}

// ............................................................................

void play() {

    counter16 = 0;

    for (int i = 0; i < drums.size(); i++) {
        drums.get(i).play();
    }

    for (int i = 0; i < notes.size(); i++) {
        notes.get(i).play();
    }
}

// ............................................................................

}