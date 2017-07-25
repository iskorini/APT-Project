package it.unifi.gameutility;

/**
 * Created by iskor on 16/07/2017.
 */
public class Player {

    private String username;
    private String points;

    public Player(String username) {
        this.username = username;
        this.points = "0";
    }

    public String getUsername() {
        return username;
    }

    public String getPoints() {
        return points;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}
