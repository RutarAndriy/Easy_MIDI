package com.rutar.easy_midi;

import java.util.*;

// ............................................................................

public class Ticker {

public int counter16 = 0;
    
private final Timer timer;

private Phrase phrase = null;
private int currentChord = 0;
private int delay16 = 0;
private boolean on = false;

// ............................................................................

public Ticker (int tempo, Phrase p) {

    timer = new Timer(true);
    phrase = p;
    delay16 = (int) (12000.0 / tempo);
    
    timer.schedule(new TimerTask() {
        
        @Override
        public void run() {
            
            if (on) { counter16++;
                      tick();
                      onTick(counter16); }
        }

    }, 0, delay16);

}

// ............................................................................

public void onTick (long count) {}

// ............................................................................

public void onFinish() { restart(); }

// ............................................................................

void tick() {

    if (phrase == null || phrase.chords == null) { return; }

    if (currentChord >= phrase.chords.size()) {
        onFinish();
        return;
    }

    phrase.chords.get(currentChord).counter16++;
    int pp = 16 / phrase.chords.get(currentChord).part;
    
    if (phrase.chords.get(currentChord).counter16 >= pp) {
        
        currentChord++;
        if (currentChord >= phrase.chords.size()) { onFinish(); }
        else { phrase.chords.get(currentChord).play(); }

    }

    else {

        for (int i = 0; i < phrase.chords.size(); i++) {
            phrase.chords.get(i).tick();
        }

    }
}

// ............................................................................

public void play() { on = true; }

// ............................................................................

public void restart() {

    on = true;
    counter16 = 0;
    currentChord = 0;

    if (currentChord >= phrase.chords.size()) {
        return;
    }

    phrase.chords.get(currentChord).play();

}

// ............................................................................

public void stop() {

    on = false;

    for (int i = 0; i < phrase.chords.size(); i++) {
        phrase.chords.get(i).stop();
    }
}

// ............................................................................

}