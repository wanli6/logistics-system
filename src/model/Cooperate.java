package model;

public class Cooperate {
    private int l_id;
    private int t_id;

    public Cooperate() {
    }

    public Cooperate(int l_id, int t_id) {
        this.l_id = l_id;
        this.t_id = t_id;
    }

    public int getL_id() {
        return l_id;
    }

    public void setL_id(int l_id) {
        this.l_id = l_id;
    }

    public int getT_id() {
        return t_id;
    }

    public void setT_id(int t_id) {
        this.t_id = t_id;
    }

    @Override
    public String toString() {
        return "Cooperate{" +
                "l_id=" + l_id +
                ", t_id=" + t_id +
                '}';
    }
}