package com.rutar.easy_midi;

import java.awt.event.*;
import java.util.regex.*;
import java.lang.reflect.*;

import javax.swing.*;

// ............................................................................

public class Demo extends JFrame {

private int tempo = 120;
private int instrument = Note.i35_Fretless_Bass;

private final Drum hat   = new Drum(Drum.d42_Closed_Hi_Hat, 4);
private final Drum bass  = new Drum(Drum.d35_Acoustic_Bass_Drum, 4);
private final Drum snare = new Drum(Drum.d38_Acoustic_Snare, 4);

private final Note bullet = new Note(Note.p96_8_Do, Note.i127_Gunshot, 4);

// ............................................................................

private Phrase main_phrase;
private Ticker main_ticker;

// ............................................................................

private final Phrase gun_serie_phrase = new Phrase()
             .chord(new Chord(8).note(bullet))
             .chord(new Chord(8).note(bullet))
             .chord(new Chord(8).note(bullet))
             .chord(new Chord(8).note(bullet))
             .chord(new Chord(8).note(bullet));

// ............................................................................

private Ticker gun_series_ticker = new Ticker (180, gun_serie_phrase, false);

// ............................................................................

private final String [] button_names = { "Грати",
                                         "Зупинити",
                                         "Постріл",
                                         "Серія пострілів",
                                         "Нота" };

// ............................................................................

private final JButton play  = new JButton(button_names[0]);
private final JButton stop  = new JButton(button_names[1]);
private final JButton gun   = new JButton(button_names[2]);
private final JButton mgun  = new JButton(button_names[3]);
private final JButton tweet = new JButton(button_names[4]);

// ............................................................................

private final JLabel instrument_label = new JLabel("Інструмент: ");
private final JComboBox instrument_combo_box = new JComboBox();

private final JLabel tempo_label = new JLabel("Темп: ");
private final JComboBox tempo_combo_box = new JComboBox();

// ............................................................................

public static void main (String[] args) { new Demo(); }

// ............................................................................

public Demo() {
    
this.setTitle("Easy_MIDI Demo");
this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

JPanel panel_top    = new JPanel();
JPanel panel_bottom = new JPanel();

this.add(panel_top);
this.add(panel_bottom);

panel_top.add(instrument_label);
panel_top.add(instrument_combo_box);

panel_top.add(tempo_label);
panel_top.add(tempo_combo_box);

panel_bottom.add(play);
panel_bottom.add(stop);
panel_bottom.add(gun);
panel_bottom.add(mgun);
panel_bottom.add(tweet);

play  .addActionListener(action_listener);
stop  .addActionListener(action_listener);
gun   .addActionListener(action_listener);
mgun  .addActionListener(action_listener);
tweet .addActionListener(action_listener);

// ............................................................................

String field_name;
Field[] fields = Note.class.getFields();
Pattern pattern = Pattern.compile("i{1}\\d{1,3}.+");

for (Field field : fields) {
    
    field_name = field.getName();
    
    if (pattern.matcher(field_name).matches()) {
        instrument_combo_box.addItem(field.getName());
    }

}

instrument_combo_box.addItemListener(instrument_listener);
instrument_combo_box.setSelectedIndex(instrument);

// ............................................................................

tempo_combo_box.addItem("60");
tempo_combo_box.addItem("75");
tempo_combo_box.addItem("90");
tempo_combo_box.addItem("105");
tempo_combo_box.addItem("120");
tempo_combo_box.addItem("135");
tempo_combo_box.addItem("150");
tempo_combo_box.addItem("165");
tempo_combo_box.addItem("180");

tempo_combo_box.setSelectedItem(String.valueOf(tempo));
tempo_combo_box.addItemListener(tempo_listener);

// ............................................................................

this.setResizable(false);
this.pack();

this.setLocationRelativeTo(null);
this.setVisible(true);

}

// ............................................................................

private void set_Instrument (int new_instrument) {

if (new_instrument != -1) {

    instrument = new_instrument;
    
    main_phrase = new Phrase()
        .chord(new Chord(8).drum(hat).drum(bass)
            .note(Note.p28_2_Mi, instrument, 8))
        .chord(new Chord(8).drum(hat)
            .note(Note.p28_2_Mi, instrument, 8))
        .chord(new Chord(8).drum(hat).drum(snare)
            .note(Note.p28_2_Mi, instrument, 8))
        .chord(new Chord(8).drum(hat)
            .note(Note.p28_2_Mi, instrument, 8))
        .chord(new Chord(8).drum(hat).drum(bass)
            .note(Note.p28_2_Mi, instrument, 8))
        .chord(new Chord(8).drum(hat).drum(bass)
            .note(Note.p28_2_Mi, instrument, 8))
        .chord(new Chord(8).drum(hat).drum(snare)
            .note(Note.p34_2_La_Diese, instrument, 8))
        .chord(new Chord(8).drum(hat)
            .note(Note.p35_2_Si, instrument, 8));

}

if (main_ticker != null) { main_ticker.stop(); }

main_ticker = new Ticker (tempo, main_phrase, true);
main_ticker.play();

}

// ............................................................................

private final ActionListener action_listener = new ActionListener() {
    
    @Override
    public void actionPerformed (ActionEvent e) {

        Object source = e.getSource();
        
        // Грати
        if (source == play) { main_ticker.play(); }
        
        // Зупинити
        else if (source == stop) { main_ticker.stop(); }
        
        // Постріл
        else if (source == gun) {
            Tools.playNote(new Note(Note.p93_7_La,
                                    Note.i127_Gunshot), 1200);
        }
        
        // Серія пострілів
        else if (source == mgun) {
            gun_series_ticker.play();
        }
        
        // Нота
        else { Tools.playNote(new Note(Note.p69_5_La,
                                       Note.i123_Bird_Tweet), 3000); }

    }
};

// ............................................................................

private final ItemListener instrument_listener = (ItemEvent e) -> {
    
    if (e.getStateChange() == ItemEvent.SELECTED) {
        
        String item = e.getItem().toString();
        
        int start = item.indexOf("i") + 1;
        int end   = item.indexOf("_");
        
        int new_instrument = Integer.parseInt(item.substring(start, end));
        
        set_Instrument(new_instrument);
        
    }
};

// ............................................................................

private final ItemListener tempo_listener = (ItemEvent e) -> {

    if (e.getStateChange() == ItemEvent.SELECTED) {

        tempo = Integer.parseInt(e.getItem().toString());
        set_Instrument(-1);

    }
};

// ............................................................................

}