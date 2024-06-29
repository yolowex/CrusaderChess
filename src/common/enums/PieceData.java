package common.enums;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public enum PieceData {
    // put data of each piece, here, it helps with organization and scalability
    WHITE_SOLDIER("Soldier",        "assets/crus_soldier.png"),
    WHITE_KNIGHT("Knight",          "assets/crus_knight.png"),
    WHITE_ASSASSIN("Assassin",      "assets/crus_assasin.png"),
    WHITE_ARCHER("Archer",          "assets/crus_archer.png"),
    WHITE_STRONGHOLD("Stronghold",  "assets/crus_stronghold.png"),




    // put data of each piece, here, it helps with organization and scalability
    BLACK_SOLDIER("Soldier",        "assets/arab_soldier.png"),
    BLACK_KNIGHT("Knight",          "assets/arab_knight.png"),
    BLACK_ASSASSIN("Assassin",      "assets/arab_assasin.png"),
    BLACK_ARCHER("Archer",          "assets/arab_archer.png"),
    BLACK_STRONGHOLD("Stronghold",  "assets/arab_stronghold.png"),




    ;
    public final String name;
    public final String imagePath;
    public final BufferedImage image;
    PieceData(String name, String imagePath) {
        this.name = name;
        this.imagePath = imagePath;
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            throw new Error("Could not find file at "+imagePath);
        }
    }
}
