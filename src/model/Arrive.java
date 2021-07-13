package model;

public class Arrive {
    private int t_id;
    private int r_id;
    private String date;

    public Arrive() {
    }

    public Arrive(int t_id, int r_id, String date) {
        this.t_id = t_id;
        this.r_id = r_id;
        this.date = date;
    }

    public int getT_id() {
        return t_id;
    }

    public void setT_id(int t_id) {
        this.t_id = t_id;
    }

    public int getR_id() {
        return r_id;
    }

    public void setR_id(int r_id) {
        this.r_id = r_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Arrive{" +
                "t_id=" + t_id +
                ", r_id=" + r_id +
                ", date='" + date + '\'' +
                '}';
    }
}