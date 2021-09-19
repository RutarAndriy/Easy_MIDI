package com.rutar.easy_midi;

import java.util.*;

// ............................................................................

public class Ticker {

///////////////////////////////////////////////////////////////////////////////
// Ticker - клас/маятник, відповідає за безперервне відтворення акордів ///////

private int tick_counter = 0;                               // Лічильник тактів
private int current_chord = 0;                                // Поточний акорд

private final boolean loop;                       // Циклічне відтворення фрази
private final int tick_delay;                           // Час затримки 1 такту

private final Timer timer;                                // Об'єкт класу Timer
private final Phrase phrase;                             // Об'єкт класу Phrase

private boolean on = false;                 // Перевірка, чи маятник є активним

// ............................................................................

    /**
     * Конструктор класу Ticker
     * @param tempo темп відтворення, кількість четвертинних нот за хвилину
     * @param phrase фраза для відтворення, об'єкт класу Phrase
     * @param loop циклічне відтвлрення фрази
     */

public Ticker (int tempo, Phrase phrase, boolean loop) {

    this.timer  = new Timer(true);
    this.phrase = phrase;
    this.loop   = loop;
    
    // tempo = 120
    // tempo = 120/4 = 30 нот/хв
    // 60 сек / 30 = 2 сек = 2000 мс
    
    // 1/1  = 2000 мс
    // 1/2  = 1000 мс
    // 1/4  = 500  мс
    // 1/8  = 250  мс
    // 1/16 = 125  мс

    tick_delay = (int) (60000.0 / (tempo / 4) / 16);
    Tools.initSynthesizer();
    
    // ........................................................................
    
    timer.schedule(new TimerTask() {
        
        @Override
        public void run() {
            
            if (on) { tick_counter++;
                      tick(); }
            
        }

    }, 0, tick_delay);

}

// ............................................................................

    /**
     * Метод починає відтворювати фразу
     */

public void play() { 
    
    if (on) { return; }
    else { on = true; }
    
    tick_counter = 0;
    current_chord = 0;

    if (current_chord < phrase.getChordsCount()) {
        phrase.getChord(current_chord).play();
    }
    
}

// ............................................................................

    /**
     * Метод завершує відтворення фрази
     */

public void stop() {

    on = false;

    for (int z = 0; z < phrase.getChordsCount(); z++) {
        phrase.getChord(z).stop();
    }
}

// ............................................................................

private void finish() { 

    if (loop) { on = false;
                play(); }
    
    else      { stop(); }

}

// ............................................................................

private void tick() {
    
    // Перевірка вхідних параметрів
    if (phrase == null ||
        phrase.getChordsCount() == 0) { return; }

    // Перевірка завершеності звучання фрази
    if (current_chord >= phrase.getChordsCount()) {
        finish();
        return;
    }

    final Chord chord = phrase.getChord(current_chord);
    
    chord.increaseTickCounter();
    int reverse_part = 16 / chord.getPart();
    
    if (chord.getTickCounter() >= reverse_part) {
        
        current_chord++;
        if (current_chord >= phrase.getChordsCount()) { finish(); }
        else { phrase.getChord(current_chord).play(); }

    }

    else {

        for (int i = 0; i < phrase.getChordsCount(); i++) {
            phrase.getChord(i).tick();
        }

    }
}

// ............................................................................

}