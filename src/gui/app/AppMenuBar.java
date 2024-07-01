package gui.app;

import common.enums.SoundEffects;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppMenuBar implements ActionListener {
    JMenuBar menuBar;
    JMenu menu1;
    JMenuItem muteSound, muteMusic;

    public AppMenuBar(JFrame parentFrame){
        muteSound = new JMenuItem("Mute sound effects");
        muteMusic = new JMenuItem("Mute background music");

        muteSound.addActionListener(this);
        muteMusic.addActionListener(this);

        menuBar = new JMenuBar();
        menu1 = new JMenu("Settings");

        menu1.add(muteSound);
        menu1.add(muteMusic);

        menuBar.add(menu1);

        parentFrame.add(menuBar);
        parentFrame.setJMenuBar(menuBar);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        AppSettings appSettings = AppSettings.getInstance();
        Object source = actionEvent.getSource();

        if (source == muteSound) {
            appSettings.soundMuted = !appSettings.soundMuted;
            if (appSettings.soundMuted) {
                muteSound.setText("Play sound effects");
                // Add code to mute sound effects
            } else {
                muteSound.setText("Mute sound effects");
                // Add code to play sound effects
            }
        } else if (source == muteMusic) {
            appSettings.musicMuted = !appSettings.musicMuted;
            if (appSettings.musicMuted) {
                SoundEffects.BACKGROUND_MUSIC.audioClip.stop();
                muteMusic.setText("Play background music");
                // Add code to mute background music
            } else {
                SoundEffects.BACKGROUND_MUSIC.audioClip.setFramePosition(0);
                SoundEffects.BACKGROUND_MUSIC.audioClip.start();
                muteMusic.setText("Mute background music");
                // Add code to play background music
            }
        }
    }

}
