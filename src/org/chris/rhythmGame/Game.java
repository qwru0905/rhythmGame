package org.chris.rhythmGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Game extends JFrame {
    private Image screenImage;
    private Graphics screenGraphic;

    private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("/images/buttons/exitButtonEntered.png"));
    private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("/images/buttons/exitButtonBasic.png"));
    private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("/images/buttons/startButtonBasic.png"));
    private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("/images/buttons/startButtonEntered.png"));
    private ImageIcon quitButtonEnteredImage = new ImageIcon(Main.class.getResource("/images/buttons/quitButtonEntered.png"));
    private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("/images/buttons/quitButtonBasic.png"));
    private ImageIcon leftButtonEnteredImage = new ImageIcon(Main.class.getResource("/images/buttons/leftButtonEntered.png"));
    private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("/images/buttons/leftButtonBasic.png"));
    private ImageIcon rightButtonEnteredImage = new ImageIcon(Main.class.getResource("/images/buttons/rightButtonEntered.png"));
    private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("/images/buttons/rightButtonBasic.png"));

    private Image selectedImage = new ImageIcon(Main.class.getResource("/images/levels/R Start Image.png")).getImage();
    private Image background = new ImageIcon(Main.class.getResource("/images/introBackGround.png")).getImage();
    private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("/images/menuBar.png")));
    private JButton exitButton = new JButton(exitButtonBasicImage);
    private JButton startButton = new JButton(startButtonBasicImage);
    private JButton quitButton = new JButton(quitButtonBasicImage);
    private JButton leftButton = new JButton(leftButtonBasicImage);
    private JButton rightButton = new JButton(rightButtonBasicImage);

    private int mouseX, mouseY;

    private boolean isMainScreen = false;


    public Game() {
        // 기본적으로 존재하는 메뉴 바 X
        setUndecorated(true);
        setTitle("리겜"); // FIXME: 추후에 이름 정해지면 수정
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setBackground(new Color(0, 0, 0, 0));
        setLayout(null);

        menuBar.setBounds(0, 0, 1280, 40);
        menuBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });

        // Exit
        exitButton.setBounds(1245, 5, 30, 30);
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusPainted(false);
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setIcon(exitButtonEnteredImage);
                exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setIcon(exitButtonBasicImage);
                exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.exit(0);
            }
        });
        add(exitButton);

        // Start
        startButton.setBounds(40, 200, 400, 100);
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                startButton.setIcon(startButtonEnteredImage);
                startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                startButton.setIcon(startButtonBasicImage);
                startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                startButton.setVisible(false);
                quitButton.setVisible(false);
                leftButton.setVisible(true);
                rightButton.setVisible(true);
                background = new ImageIcon(Main.class.getResource("/images/mainBackGround.png")).getImage();
                isMainScreen = true;
            }
        });
        add(startButton);

        // Quit
        quitButton.setBounds(40, 330, 400, 100);
        quitButton.setBorderPainted(false);
        quitButton.setContentAreaFilled(false);
        quitButton.setFocusPainted(false);
        quitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                quitButton.setIcon(quitButtonEnteredImage);
                quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                quitButton.setIcon(quitButtonBasicImage);
                quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.exit(0);
            }
        });
        add(quitButton);

        // Left
        leftButton.setVisible(false);
        leftButton.setBounds(140, 310, 60, 60);
        leftButton.setBorderPainted(false);
        leftButton.setContentAreaFilled(false);
        leftButton.setFocusPainted(false);
        leftButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                leftButton.setIcon(leftButtonEnteredImage);
                leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                leftButton.setIcon(leftButtonBasicImage);
                leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // FIXME: 왼쪽 버튼 이벤트
            }
        });
        add(leftButton);

        // Right
        rightButton.setVisible(false);
        rightButton.setBounds(1000, 310, 60, 60);
        rightButton.setBorderPainted(false);
        rightButton.setContentAreaFilled(false);
        rightButton.setFocusPainted(false);
        rightButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                rightButton.setIcon(rightButtonEnteredImage);
                rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                rightButton.setIcon(rightButtonBasicImage);
                rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // FIXME: 오른쪽 버튼 이벤트
            }
        });
        add(rightButton);

        menuBar.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen() - mouseX;
                int y = e.getYOnScreen() - mouseY;
                setLocation(x, y);
            }
        });
        add(menuBar);

        //introMusic: TheFatRat - "Fly Away feat. Anjulie"
        //https://www.youtube.com/watch?v=cMg8KaMdDYo
        Music introMusic = new Music("introMusic.mp3", true);
        introMusic.start();
    }

    public void paint(Graphics g) {
        screenImage = createImage(getWidth(), getHeight());
        screenGraphic = screenImage.getGraphics();
        screenDraw(screenGraphic);

        g.drawImage(screenImage, 0, 0, null);
    }

    public void screenDraw(Graphics g) {
        g.drawImage(background, 0, 0, null);
        if (isMainScreen) {
            g.drawImage(selectedImage, 340, 100, null);
            // FIXME: 이름 알리기
        }
        // JLabel 등을 그림
        paintComponents(g);
        this.repaint();
    }
}
