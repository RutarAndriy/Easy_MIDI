package com.rutar.easy_midi;

import java.util.*;

// ............................................................................

public class Phrase {

final ArrayList <Chord> chords = new ArrayList<>();

// ............................................................................

public Phrase chord (Chord chord) {

    chords.add(chord);
    return this;

}

// ............................................................................

}
