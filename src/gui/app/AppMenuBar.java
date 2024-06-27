package gui.app;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppMenuBar implements ActionListener {
    JMenuBar menuBar;
    JMenu menu1, menu2, menu3;
    JMenuItem i1,i2,i3,i4,i5,i6;
    public AppMenuBar(JFrame parentFrame){
        i1 = new JMenuItem("Item 1");
        i2 = new JMenuItem("Item 2");
        i3 = new JMenuItem("Item 3");
        i4 = new JMenuItem("Item 4");
        i5 = new JMenuItem("Item 5");
        i6 = new JMenuItem("Item 6");
//        cut.addActionListener(this);
        menuBar =new JMenuBar();
        menu1 =new JMenu("Menu1");
        menu2 =new JMenu("Menu2");
        menu3 =new JMenu("Menu3");

        menu1.add(i1);
        menu1.add(i2);
        menu2.add(i3);
        menu2.add(i4);
        menu3.add(i5);
        menu3.add(i6);

        menuBar.add(menu1);
        menuBar.add(menu2);
        menuBar.add(menu3);
        parentFrame.add(menuBar);
        parentFrame.setJMenuBar(menuBar);

    }

    public void actionPerformed(ActionEvent actionEvent) {
//        if(e.getSource()==cut){}

    }

}
