package common.enums;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public enum PieceData {
    // put data of each piece, here, it helps with organization and scalability
    WHITE_SOLDIER("Soldier","assets/pwhite.png"),
    BLACK_SOLDIER("Soldier","assets/pblack.png"),



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
