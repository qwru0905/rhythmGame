package org.chris.rhythmGame;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {
    private Image screenImage;
    private Graphics screenGraphic;

    private Image introBackGround;


    public Game() {
        setTitle("리겜"); // FIXME: 추후에 이름 정해지면 수정
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        setVisible(true);

        introBackGround = new ImageIcon("../intro_background.png").getImage();
    }

    public void paint(Graphics g) {
        screenImage = createImage(getWidth(), getHeight());
        screenGraphic = screenImage.getGraphics();
        screenDraw(screenGraphic);

        g.drawImage(screenImage, 0, 0, null);
    }

    public void screenDraw(Graphics g) {
        g.drawImage(introBackGround, 0, 0, null);
    }
}
