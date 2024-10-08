package org.chris.rhythmGame;

import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class Music extends Thread {

    private Player player;
    private boolean isLoop;
    private File file;
    private FileInputStream fis;
    private BufferedInputStream bis;

    public Music(String name, boolean isLoop) {
        try {
            this.isLoop = isLoop;
            file = new File(Main.class.getResource("/music/" + name).toURI());
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            player = new Player(bis);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param name 곡의 이름
     * @param isLoop 이 곡은 루프 중인지를 정함
     * @param songAttitude 곡의 성격으로, 1은 레벨 노래, 2는 하이라이트 노래를 칭함.
     */
    public Music(String name, boolean isLoop, int songAttitude) {
        if (songAttitude == 1) {
            try {
                this.isLoop = isLoop;
                file = new File(Main.class.getResource("/music/levels/" + name).toURI());
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                player = new Player(bis);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (songAttitude == 2) {
            try {
                this.isLoop = isLoop;
                file = new File(Main.class.getResource("/music/highlight/" + name).toURI());
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                player = new Player(bis);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            try {
                this.isLoop = isLoop;
                file = new File(Main.class.getResource("/music/" + name).toURI());
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                player = new Player(bis);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public int getTime() {
        if (player == null) {
            return 0;
        }
        return player.getPosition();
    }

    public void close() {
        isLoop = false;
        player.close();
        this.interrupt();
    }

    @Override
    public void run() {
        try {
            do {
                player.play();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                player = new Player(bis);
            } while (isLoop);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
