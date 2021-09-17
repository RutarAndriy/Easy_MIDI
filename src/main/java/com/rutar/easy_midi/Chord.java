package com.rutar.easy_midi;

import java.util.*;

// ............................................................................

public class Chord {

///////////////////////////////////////////////////////////////////////////////
// Chord - клас відповідає за представлення акордів ///////////////////////////
// Акорд - сукупність нот та ударних інструментів /////////////////////////////

private int part;                       // Частина ноти, наприклад 4 = 1/4 ноти
private int tick_counter = 0;                               // Лічильник тактів

private final ArrayList <Note> notes;                              // Масив нот
private final ArrayList <Drum> drums;             // Масив ударних інструментів

// ............................................................................

    /**
     * Конструктор класу Chord
     */

public Chord() { 

    part = 1;
    
    notes = new ArrayList<>();
    drums = new ArrayList<>();
}

// ............................................................................

    /**
     * Конструктор класу Chord
     * @param part частина ноти, наприклад 4 = 1/4 ноти
     */

public Chord (int part) {
    
    this();
    
    this.part = part;
    
    if      (this.part > 16) { this.part = 16; }
    else if (this.part < 1)  { this.part = 1;  }

}

// ............................................................................

    /**
     * Метод задає частину ноти для акорду
     * @param part частина ноти, наприклад 4 = 1/4 ноти
     * @return екземпляр класу Chord
     */

public Chord part (int part) {
    
    this.part = part;
    
    if      (this.part > 16) { this.part = 16; }
    else if (this.part < 1)  { this.part = 1;  }
    
    return this;
    
}

// ............................................................................

    /**
     * Метод додає ноту до акорду
     * @param note нота, екземпляр класу Note
     * @return екземпляр класу Chord
     */

public Chord note (Note note) {
    
    notes.add(note);
    return this;

}

// ............................................................................

    /**
     * Метод додає ноту до акорду
     * @param note висота ноти, від 0 до 127
     * @param instrument інструмент, від 0 до 127
     * @param volume гучність ноти, від 0 до 127
     * @param part частина звучання ноти, наприклад 1/4
     * @return екземпляр класу Chord
     */

public Chord note (int note, int instrument, int volume, int part) {

    Note new_note = new Note(note, instrument, volume, part);
    return note(new_note);

}

// ............................................................................

    /**
     * Метод додає ноту до акорду
     * @param note висота ноти, від 0 до 127
     * @param instrument інструмент, від 0 до 127
     * @param part частина звучання ноти, наприклад 1/4
     * @return екземпляр класу Chord
     */

public Chord note (int note, int instrument, int part) {

    Note new_note = new Note(note, instrument, part);
    return note(new_note);

}

// ............................................................................

    /**
     * Метод додає ноту до акорду
     * @param note висота ноти, від 0 до 127
     * @param instrument інструмент, від 0 до 127
     * @return екземпляр класу Chord
     */

public Chord note (int note, int instrument) {

    Note new_note = new Note(note, instrument);
    return note(new_note);

}

// ............................................................................

    /**
     * Метод додає ударний інструмент до акорду
     * @param drum ударний інструмент, екземпляр класу Drum
     * @return екземпляр класу Chord
     */

public Chord drum (Drum drum) {

    drums.add(drum);
    return this;

}

// ............................................................................

    /**
     * Метод додає ударний інструмент до акорду
     * @param instrument ударний інструмент, від 35 до 81
     * @param volume гучність інструменту, від 0 до 127
     * @param part частина звучання ноти, наприклад 1/4
     * @return екземпляр класу Chord
     */

public Chord drum (int instrument, int volume, int part) {

    Drum new_drum = new Drum(instrument, volume, part);
    return drum(new_drum);

}

// ............................................................................

    /**
     * Метод додає ударний інструмент до акорду
     * @param instrument ударний інструмент, від 35 до 81
     * @param part частина звучання ноти, наприклад 1/4
     * @return екземпляр класу Chord
     */

public Chord drum (int instrument, int part) {

    Drum new_drum = new Drum(instrument, part);
    return drum(new_drum);

}

// ............................................................................

    /**
     * Метод додає ударний інструмент до акорду
     * @param instrument ударний інструмент, від 35 до 81
     * @return екземпляр класу Chord
     */

public Chord drum (int instrument) {

    Drum new_drum = new Drum(instrument);
    return drum(new_drum);

}

// ............................................................................

    /**
     * Метод починає відтворення акорду
     */

public void play() {

    tick_counter = 0;
    
    for (int z = 0; z < drums.size(); z++) { drums.get(z).play(); }
    for (int z = 0; z < notes.size(); z++) { notes.get(z).play(); }

}

// ............................................................................

    /**
     * Метод завершує відтворення акорду
     */

public void stop() {

    for (int z = 0; z < drums.size(); z++) { drums.get(z).stop(); }
    for (int z = 0; z < notes.size(); z++) { notes.get(z).stop(); }

}

// ............................................................................

    /**
     * Метод контролює час звучання акорду.
     * 1 такт = 1/16 часу звучання ноти.
     */

public void tick() {

    for (int z = 0; z < drums.size(); z++) { drums.get(z).tick(); }
    for (int z = 0; z < notes.size(); z++) { notes.get(z).tick(); }

}

// ............................................................................

    /**
     * Метод збільшує лічильник тактів на 1
     */

public void increaseTickCounter() { tick_counter++; }

// ............................................................................

public int getPart()        { return part;         }
public int getTickCounter() { return tick_counter; }

// ............................................................................

}