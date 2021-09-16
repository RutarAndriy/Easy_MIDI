package com.rutar.easy_midi;

// ............................................................................

public class Drum {

///////////////////////////////////////////////////////////////////////////////
// Drum - клас відповідає за представлення ударних інструментів ///////////////

// Список ударних інструментів ................................................
public static final int d35_Acoustic_Bass_Drum = 35;
public static final int d36_Bass_Drum_1        = 36;
public static final int d37_Side_Stick         = 37;
public static final int d38_Acoustic_Snare     = 38;
public static final int d39_Hand_Clap          = 39;
public static final int d40_Electric_Snare     = 40;
public static final int d41_Low_Floor_Tom      = 41;
public static final int d42_Closed_Hi_Hat      = 42;
public static final int d43_High_Floor_Tom     = 43;
public static final int d44_Pedal_Hi_Hat       = 44;
public static final int d45_Low_Tom            = 45;
public static final int d46_Open_Hi_Hat        = 46;
public static final int d47_Low_Mid_Tom        = 47;
public static final int d48_Hi_Mid_Tom         = 48;
public static final int d49_Crash_Cymbal_1     = 49;
public static final int d50_High_Tom           = 50;
public static final int d51_Ride_Cymbal_1      = 51;
public static final int d52_Chinese_Cymbal     = 52;
public static final int d53_Ride_Bell          = 53;
public static final int d54_Tambourine         = 54;
public static final int d55_Splash_Cymbal      = 55;
public static final int d56_Cowbell            = 56;
public static final int d57_Crash_Cymbal_2     = 57;
public static final int d58_Vibraslap          = 58;
public static final int d59_Ride_Cymbal_2      = 59;
public static final int d60_Hi_Bongo           = 60;
public static final int d61_Low_Bongo          = 61;
public static final int d62_Mute_Hi_Conga      = 62;
public static final int d63_Open_Hi_Conga      = 63;
public static final int d64_Low_Conga          = 64;
public static final int d65_High_Timbale       = 65;
public static final int d66_Low_Timbale        = 66;
public static final int d67_High_Agogo         = 67;
public static final int d68_Low_Agogo          = 68;
public static final int d69_Cabasa             = 69;
public static final int d70_Maracas            = 70;
public static final int d71_Short_Whistle      = 71;
public static final int d72_Long_Whistle       = 72;
public static final int d73_Short_Guiro        = 73;
public static final int d74_Long_Guiro         = 74;
public static final int d75_Claves             = 75;
public static final int d76_Hi_Wood_Block      = 76;
public static final int d77_Low_Wood_Block     = 77;
public static final int d78_Mute_Cuica         = 78;
public static final int d79_Open_Cuica         = 79;
public static final int d80_Mute_Triangle      = 80;
public static final int d81_Open_Triangle      = 81;

// ............................................................................

private int instrument;                                   // Ударний інструмент
private int volume;                            // Гучність ударного інструменту
private int part;                       // Частина ноти, наприклад 4 = 1/4 ноти

private int tick_counter = 0;                               // Лічильник тактів

private boolean on = false;                    // Перевірка, чи нота є активною

// ............................................................................

    /**
     * Конструктор класу Drum
     * @param instrument ударний інструмент, від 35 до 81
     * @param volume гучність інструменту, від 0 до 127
     * @param part частина звучання ноти, наприклад 1/4
     */

public Drum (int instrument, int volume, int part) {

    this (instrument, part);
    
    this.volume = volume;

    if      (this.volume > 127) { this.volume = 127; }
    else if (this.volume < 0)   { this.volume = 0;   }

}

// ............................................................................

    /**
     * Конструктор класу Drum
     * @param instrument ударний інструмент, від 35 до 81
     * @param part частина звучання ноти, наприклад 1/4
     */

public Drum (int instrument, int part) {

    this (instrument);
    
    this.part = part;
    
    if      (this.part > 16) { this.part = 16; }
    else if (this.part < 1)  { this.part = 1;  }

}

// ............................................................................

    /**
     * Конструктор класу Drum
     * @param instrument ударний інструмент, від 35 до 81
     */

public Drum (int instrument) {

    this.instrument = instrument;
    this.volume     = 127;
    this.part       = 1;
    
    if      (this.instrument > 81) { this.instrument = 81; }
    else if (this.instrument < 35) { this.instrument = 35; }

}

// ............................................................................

    /**
     * Метод починає відтворення ударного інструменту
     */

public void play() {
    
    Tools.openDrum(instrument, volume);
    tick_counter = 0;
    on = true;

}
    
// ............................................................................

    /**
     * Метод завершує відтворення ударного інструменту
     */

public void stop() {

    Tools.closeDrum(instrument);
    on = false;

}

// ............................................................................

    /**
     * Метод контролює час звучання ударного інструменту.
     * 1 такт = 1/16 часу звучання ноти.
     */

public void tick() {

    if (on) {
        
        tick_counter++;
        int reverse_part = 16 / part;
        
        if (tick_counter >= reverse_part) { stop(); }
    }
}

// ............................................................................

public int getInstrument() { return instrument; }
public int getVolume()     { return volume;     }
public int getPart()       { return part;       }

// ............................................................................

}