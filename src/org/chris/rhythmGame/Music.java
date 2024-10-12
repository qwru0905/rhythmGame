package org.chris.rhythmGame;

import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.InputStream;

public class Music extends Thread {

    private Player player;
    private boolean isLoop;
    private BufferedInputStream bis;
    private InputStream is;
    private String path;  // 음악 파일의 경로

    public Music(String name, boolean isLoop) {
        try {
            this.isLoop = isLoop;
            this.path = "/music/" + name;  // 기본 경로 설정
            is = Main.class.getResourceAsStream(path);
            bis = new BufferedInputStream(is);
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
        try {
            this.isLoop = isLoop;

            // Song attitude에 따라 경로 설정
            if (songAttitude == 1) {
                this.path = "/music/levels/" + name;
            } else if (songAttitude == 2) {
                this.path = "/music/highlight/" + name;
            } else {
                this.path = "/music/" + name;  // 기본 경로
            }

            // Use getResourceAsStream for all cases
            is = Main.class.getResourceAsStream(path);
            bis = new BufferedInputStream(is);
            player = new Player(bis);
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
        if (player != null) {
            player.close();
        }
        this.interrupt();
    }

    @Override
    public void run() {
        try {
            do {
                player.play();
                // 재생이 끝난 후 다시 InputStream을 열어야 합니다
                is = Main.class.getResourceAsStream(path);  // 인스턴스 변수 path 사용
                bis = new BufferedInputStream(is);
                player = new Player(bis);
            } while (isLoop);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
