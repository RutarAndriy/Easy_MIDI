package com.rutar.easy_midi;

import javax.sound.midi.*;

// ............................................................................

public class Tools {

private static Synthesizer synthesizer;             // Об'єкт класу Synthesizer
private static MidiChannel main_channel;                 // Головний MIDI канал
private static Instrument[] instruments;        // Масив доступних інструментів

private static final int[][] channels = new int[8][128];              // Канали

// ............................................................................

    /**
     * Метод ініціалізує MIDI систему
     */

public static void initSynthesizer() {

    if (synthesizer != null) { return; }

    try {

        synthesizer = MidiSystem.getSynthesizer();
        synthesizer.open();
        
        instruments = synthesizer.getDefaultSoundbank().getInstruments();
        main_channel = synthesizer.getChannels()[9];

    }

    catch (Throwable t) { t.printStackTrace(); }

}

// ............................................................................

    /**
     * Метод дозволяє відтворити ноту
     * @param note_to_play нота для відтворення, об'єкт класу Note
     * @param time час відтворення ноти
     */

public static void playNote (final Note note_to_play,
                             final int time) {

    final int note       = note_to_play.getNote();
    final int instrument = note_to_play.getInstrument();
    final int volume     = note_to_play.getVolume();
    final int channel    = openNote(note, instrument, volume);
    
    new Thread(() -> {
        
        try { Thread.sleep(time);
              closeNote(note, channel); }
        catch (Throwable t) { t.printStackTrace(); }

    }).start();

}

// ............................................................................

    /**
     * Метод починає відтворювати ноту
     * @param note нота для відтворення
     * @param instrument інструмент для відтворення ноти
     * @param volume гучність ноти
     * @return канал, на якому буде відтворюватися нота
     */

public static int openNote (int note,
                            int instrument,
                            int volume) {

    initSynthesizer();
    int channel = 0;
    
    for (int z = 0; z < channels.length; z++) {
        
        if (channels[z][note] == 0) { channels[z][note] = 1;
                                      channel = z;
                                      break; }
    
    }
    
    MidiChannel temp_channel = synthesizer.getChannels()[channel];
    synthesizer.loadInstrument(instruments[instrument]);
    temp_channel.programChange(instrument);
    temp_channel.noteOn(note, volume);
    
    return channel;

}

// ............................................................................

    /**
     * Метод завершує відтворення ноти
     * @param note нота для закриття
     * @param channel канал, на якому відтворюється нота
     */

public static void closeNote (int note, int channel) {

    MidiChannel temp_channel = synthesizer.getChannels()[channel];
    temp_channel.noteOff(note);
    channels[channel][note] = 0;

}

// ............................................................................

    /**
     * Метод дозволяє відтворити ударний інструмент
     * @param drum_to_play ударний інструмент для відтворення,
     *                     об'єкт класу Drum
     * @param time час відтворення ударного інструменту
     */

public static void playDrum (final Drum drum_to_play,
                             final int time) {

    final int instrument = drum_to_play.getInstrument();
    final int volume     = drum_to_play.getVolume();

    openDrum(instrument, volume);

    new Thread(() -> {
        
        try { Thread.sleep(time);
              closeDrum(instrument); }
        catch (Throwable t) { t.printStackTrace(); }
    
    }).start();

}

// ............................................................................

    /**
     * Метод починає відтворювати ударний інструмент
     * @param instrument ударний інструмент для відтворення
     * @param volume гучність ударного інструменту
     */

public static void openDrum (int instrument, int volume) {

    initSynthesizer();
    main_channel.noteOn(instrument, volume);

}

// ............................................................................

    /**
     * Метод завершує відтворення ударного інструменту
     * @param instrument ударний інструмент для закриття
     */

public static void closeDrum (int instrument) {

    main_channel.noteOff(instrument);
    
}

// ............................................................................

}