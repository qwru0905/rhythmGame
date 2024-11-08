package org.chris.rhythmGame;

public class Track {
    private String title; // 곡 이름
    private String startImage; // 게임 선택 창 표시 이미지
    private String gameImage; // 게임 곡을 실행했을 때 표지 이미지
    private String startMusic; // 게임 선택 창 음악
    private String gameMusic; // 게임 곡을 실행했을 때 음악

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartImage() {
        return startImage;
    }

    public void setStartImage(String startImage) {
        this.startImage = startImage;
    }

    public String getGameImage() {
        return gameImage;
    }

    public void setGameImage(String gameImage) {
        this.gameImage = gameImage;
    }

    public String getStartMusic() {
        return startMusic;
    }

    public void setStartMusic(String startMusic) {
        this.startMusic = startMusic;
    }

    public String getGameMusic() {
        return gameMusic;
    }

    public void setGameMusic(String gameMusic) {
        this.gameMusic = gameMusic;
    }

    public Track(String title, String startImage, String gameImage, String startMusic, String gameMusic) {
        this.title = title;
        this.startImage = startImage;
        this.gameImage = gameImage;
        this.startMusic = startMusic;
        this.gameMusic = gameMusic;
    }
}
