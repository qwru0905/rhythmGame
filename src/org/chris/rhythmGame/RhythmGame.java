package org.chris.rhythmGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class RhythmGame extends JFrame {
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
    private ImageIcon easyButtonEnteredImage = new ImageIcon(Main.class.getResource("/images/buttons/easyButtonEntered.png"));
    private ImageIcon easyButtonBasicImage = new ImageIcon(Main.class.getResource("/images/buttons/easyButtonBasic.png"));
    private ImageIcon hardButtonEnteredImage = new ImageIcon(Main.class.getResource("/images/buttons/hardButtonEntered.png"));
    private ImageIcon hardButtonBasicImage = new ImageIcon(Main.class.getResource("/images/buttons/hardButtonBasic.png"));
    private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("/images/buttons/backButtonEntered.png"));
    private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("/images/buttons/backButtonBasic.png"));

    private Image background = new ImageIcon(Main.class.getResource("/images/introBackGround.png")).getImage();
    private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("/images/menuBar.png")));
    private JButton exitButton = new JButton(exitButtonBasicImage);
    private JButton startButton = new JButton(startButtonBasicImage);
    private JButton quitButton = new JButton(quitButtonBasicImage);
    private JButton leftButton = new JButton(leftButtonBasicImage);
    private JButton rightButton = new JButton(rightButtonBasicImage);
    private JButton easyButton = new JButton(easyButtonBasicImage);
    private JButton hardButton = new JButton(hardButtonBasicImage);
    private JButton backButton = new JButton(backButtonBasicImage);

    private int mouseX, mouseY;

    private boolean isMainScreen = false;
    private boolean isGameScreen = false;

    ArrayList<Track> trackList = new ArrayList<Track>();

    private JLabel selectedSongName = new JLabel("");
    private Image selectedImage;
    private Music selectedMusic;
    //introMusic: TheFatRat - "Fly Away feat. Anjulie"
    //https://www.youtube.com/watch?v=cMg8KaMdDYo
    private Music introMusic = new Music("introMusic.mp3", true);
    private int nowSelected = 0;

    public static Game game;


    public RhythmGame() {
        trackList.add(new Track("Plum - R", "R Start Image.png",
                "R Game Image.jpg", "Plum - R.mp3", "Plum - R.mp3"));
        trackList.add(new Track("Camellia - Parallel Universe Shifter", "Parallel Universe Shifter Start Image.jpg",
                null, "Camellia - Parallel Universe Shifter.mp3", "Camellia - Parallel Universe Shifter.mp3"));
        trackList.add(new Track("Raimukun - Icyxis", "icyxis Start Image.png",
                null, "Raimukun - Icyxis.mp3", "Raimukun - Icyxis.mp3"));

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

        addKeyListener(new KeyListener());

        introMusic.start();

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
                enterMain();
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
                selectLeft();
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
                selectRight();
            }
        });
        add(rightButton);

        // Easy
        easyButton.setVisible(false);
        easyButton.setBounds(375, 600, 250, 67);
        easyButton.setBorderPainted(false);
        easyButton.setContentAreaFilled(false);
        easyButton.setFocusPainted(false);
        easyButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                easyButton.setIcon(easyButtonEnteredImage);
                easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                easyButton.setIcon(easyButtonBasicImage);
                easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                gameStart(nowSelected, "Easy");
            }
        });
        add(easyButton);

        // Hard
        hardButton.setVisible(false);
        hardButton.setBounds(655, 600, 250, 67);
        hardButton.setBorderPainted(false);
        hardButton.setContentAreaFilled(false);
        hardButton.setFocusPainted(false);
        hardButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hardButton.setIcon(hardButtonEnteredImage);
                hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hardButton.setIcon(hardButtonBasicImage);
                hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                gameStart(nowSelected, "Hard");
            }
        });
        add(hardButton);

        // Back
        backButton.setVisible(false);
        backButton.setBounds(20, 50, 60, 60);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                backButton.setIcon(backButtonEnteredImage);
                backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                backButton.setIcon(backButtonBasicImage);
                backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                backMain();
            }
        });
        add(backButton);

        menuBar.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen() - mouseX;
                int y = e.getYOnScreen() - mouseY;
                setLocation(x, y);
            }
        });
        add(menuBar);

        selectedSongName.setVisible(false);
        selectedSongName.setFont(new Font("Arial", Font.BOLD, 50));
        Dimension size = selectedSongName.getPreferredSize();
        selectedSongName.setBounds((getWidth() - size.width) / 2, 70, size.width, size.height);
        selectedSongName.setForeground(Color.WHITE);
        selectedSongName.setOpaque(true);
        selectedSongName.setBackground(Color.BLACK);

        add(selectedSongName);
    }

    public void paint(Graphics g) {
        screenImage = createImage(getWidth(), getHeight());
        screenGraphic = screenImage.getGraphics();
        screenDraw((Graphics2D) screenGraphic);

        g.drawImage(screenImage, 0, 0, null);
    }

    public void screenDraw(Graphics2D g) {
        g.drawImage(background, 0, 0, null);
        if (isMainScreen) {
            // JPanel의 가운데 좌표 계산
            int x = (getWidth() - selectedImage.getWidth(null)) / 2;
            int y = (getHeight() - selectedImage.getHeight(null)) / 2;
            g.drawImage(selectedImage, x, y, null);
        }
        if (isGameScreen) {
            game.screenDraw(g);
        }
        // JLabel 등을 그림
        paintComponents(g);
        try {
            Thread.sleep(Main.SLEEP_TIME);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.repaint();
    }

    public void selectedTrack(int nowSelected) {
        if (selectedMusic != null) {
            selectedMusic.close();
        }
        selectedSongName.setText(trackList.get(nowSelected).getTitle());
        Dimension size = selectedSongName.getPreferredSize();
        selectedSongName.setBounds((getWidth() - size.width) / 2, 70, size.width, size.height);

        selectedImage = new ImageIcon(Main.class.getResource("/images/levels/" + trackList.get(nowSelected).getStartImage())).getImage();
        selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true, 2);
        selectedMusic.start();
    }

    public void selectLeft() {
        if (nowSelected == 0) {
            nowSelected = trackList.size() - 1;
        } else {
            nowSelected--;
        }
        selectedTrack(nowSelected);
    }

    public void selectRight() {
        if (nowSelected == trackList.size() - 1) {
            nowSelected = 0;
        } else {
            nowSelected++;
        }
        selectedTrack(nowSelected);
    }

    public void gameStart(int nowSelected, String difficulty) {
        if (selectedMusic != null) {
            selectedMusic.close();
        }
        isMainScreen = false;
        leftButton.setVisible(false);
        rightButton.setVisible(false);
        easyButton.setVisible(false);
        hardButton.setVisible(false);
        selectedSongName.setVisible(false);
        if (trackList.get(nowSelected).getGameImage() == null) {
            background = new ImageIcon(Main.class.getResource("/images/mainBackGround.png")).getImage();
        } else {
            background = new ImageIcon(Main.class.getResource("/images/levels/" + trackList.get(nowSelected).getGameImage())).getImage();
        }
        backButton.setVisible(true);
        isGameScreen = true;
        game = new Game(trackList.get(nowSelected).getTitle(), difficulty, trackList.get(nowSelected).getGameMusic());
        game.start();
        setFocusable(true);
    }

    public void backMain() {
        isMainScreen = true;
        leftButton.setVisible(true);
        rightButton.setVisible(true);
        easyButton.setVisible(true);
        hardButton.setVisible(true);
        selectedSongName.setVisible(true);
        background = new ImageIcon(Main.class.getResource("/images/mainBackGround.png")).getImage();
        backButton.setVisible(false);
        selectedTrack(nowSelected);
        isGameScreen = false;
        game.close();
    }

    public void enterMain() {
        startButton.setVisible(false);
        quitButton.setVisible(false);
        background = new ImageIcon(Main.class.getResource("/images/mainBackGround.png")).getImage();
        isMainScreen = true;
        leftButton.setVisible(true);
        rightButton.setVisible(true);
        easyButton.setVisible(true);
        hardButton.setVisible(true);
        selectedSongName.setVisible(true);
        introMusic.close();
        selectedTrack(0);
    }
}
