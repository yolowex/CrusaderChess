import javax.swing.*;
import java.awt.*;

public class MainFrame {
    private JFrame frame;

    public MainFrame(){
        initialize();
    }

    private void initialize(){
        frame = new JFrame();
        frame.setTitle("Crusader Chess");

        Dimension scrSize = HelperMethods.getScreenSize();

        frame.setSize((int)(scrSize.height * 0.7 * 0.6),(int)(scrSize.height * 0.7));//400 width and 500 height
        frame.setResizable(false);
        frame.setLayout(null);//using no layout managers
        frame.setVisible(true);//making the frame visible
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
