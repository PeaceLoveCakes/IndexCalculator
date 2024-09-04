package ru.klingenberg.portlib;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        JFrame jFrame = new JFrame() {};
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int width = 800;
        int height = 500;
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(width,height);
        jFrame.setLocation(
                (dimension.width - width)/2,
                (dimension.height - height)/2);
        jFrame.add(new IndexCalculatorComponent());
        jFrame.revalidate();
    }
}
