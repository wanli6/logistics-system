package model;

public class Contract {
    private int l_id;
    private int c_id;
    private String date;
    private int money;

    public Contract() {
    }

    public Contract(int l_id, int c_id, String date, int money) {
        this.l_id = l_id;
        this.c_id = c_id;
        this.date = date;
        this.money = money;
    }

    public int getL_id() {
        return l_id;
    }

    public void setL_id(int l_id) {
        this.l_id = l_id;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "l_id=" + l_id +
                ", c_id=" + c_id +
                ", date='" + date + '\'' +
                ", money=" + money +
                '}';
    }
}