package common.enums;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public enum SoundEffects {
    // don't ask why piece specific audio files aren't placed here
    // it is probably because I added these files after I added those
    // the proper way is to migrate them all here, but I'm lazy af

    MOVE_PIECE("assets/audio/move_piece.wav"),
    TAKE_PIECE("assets/audio/take_piece.wav"),
    BACKGROUND_MUSIC("assets/audio/music.wav"),
    ;

    public final Clip audioClip;
    SoundEffects(String audioPath) {
        audioClip = loadAudio(audioPath);
    }

    private Clip loadAudio(String path) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(path));
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new Error("Could not find or load audio file at " + path);
        }
    }
}
