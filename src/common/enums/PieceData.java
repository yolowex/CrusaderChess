package common.enums;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public enum PieceData {
    // put data of each piece, here, it helps with organization and scalability
    WHITE_SOLDIER("Soldier","assets/crus_soldier.png"
            ,"assets/audio/crus-soldier.wav"
    ),
    WHITE_KNIGHT("Knight","assets/crus_knight.png"
            ,"assets/audio/crus-knight.wav"),
    WHITE_ASSASSIN("Assassin","assets/crus_assasin.png"
            ,"assets/audio/crus-assassin.wav"),
    WHITE_ARCHER("Archer","assets/crus_archer.png"
            ,"assets/audio/crus-archer.wav"),
    WHITE_STRONGHOLD("Stronghold","assets/crus_stronghold.png"
            ,"assets/audio/crus-stronghold.wav"),




    // put data of each piece, here, it helps with organization and scalability
    BLACK_SOLDIER("Soldier","assets/arab_soldier.png",
            "assets/audio/arab-soldier.wav"
    ),
    BLACK_KNIGHT("Knight","assets/arab_knight.png",
            "assets/audio/arab-knight.wav"
    ),
    BLACK_ASSASSIN("Assassin","assets/arab_assasin.png",
            "assets/audio/arab-assassin.wav"
    ),
    BLACK_ARCHER("Archer","assets/arab_archer.png",
            "assets/audio/arab-archer.wav"
    ),
    BLACK_STRONGHOLD("Stronghold","assets/arab_stronghold.png",
            "assets/audio/arab-stronghold.wav"
    ),;

    public final String name;
    public final BufferedImage image;
    public final Clip audioClip;

    // add field for the audio
    PieceData(String name, String imagePath, String audioPath) {
        this.name = name;
        this.image = loadImage(imagePath);
        this.audioClip = loadAudio(audioPath);
    }

    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new Error("Could not find file at " + path);
        }
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
