package com.rutar.easy_midi;

import java.util.*;

// ............................................................................

public class Phrase {

///////////////////////////////////////////////////////////////////////////////
// Phrase - клас відповідає за представлення фраз /////////////////////////////
// Фраза - сукупність акордів /////////////////////////////////////////////////

private final ArrayList <Chord> chords;                        // Масив акордів

// ............................................................................

    /**
     * Конструктор класу Phrase
     */

public Phrase() { chords = new ArrayList<>(); }

// ............................................................................

    /**
     * Метод додає акорд до фрази
     * @param chord акорд, екземпляр класу Chord
     * @return екземпляр класу Phrase
     */

public Phrase chord (Chord chord) {

    chords.add(chord);
    return this;

}

// ............................................................................

    /**
     * Метод повертає акорд з масиву по його порядковому номеру
     * @param index порядковий номер акорду
     * @return акорд, екземпляр класу Chord
     */

public Chord getChord (int index) { return chords.get(index); }

// ............................................................................

    /**
     * Метод повертає масив акордів
     * @return масив акордів, екземпляр класу ArrayList
     */

public ArrayList <Chord> getChords() { return chords; }

// ............................................................................

    /**
     * Метод повертає кількість акордів у фразі
     * @return кількість акордів у фразі
     */

public int getChordsCount() { return chords.size(); }

// ............................................................................

}