package model;

public class Trucks {
    private int id;
    private String num;
    private String driver1;
    private String driver2;
    private String startTime;
    private int weight;
    private int isFree;

    public Trucks() {
    }

    public Trucks(int id, String num, String driver1, String driver2, String startTime, int weight, int isFree) {
        this.id = id;
        this.num = num;
        this.driver1 = driver1;
        this.driver2 = driver2;
        this.startTime = startTime;
        this.weight = weight;
        this.isFree = isFree;
    }

    public int getIsFree() {
        return isFree;
    }

    public void setIsFree(int isFree) {
        this.isFree = isFree;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getDriver1() {
        return driver1;
    }

    public void setDriver1(String driver1) {
        this.driver1 = driver1;
    }

    public String getDriver2() {
        return driver2;
    }

    public void setDriver2(String driver2) {
        this.driver2 = driver2;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }



    @Override
    public String toString() {
        return "Trucks{" +
                "id='" + id + '\'' +
                ", num='" + num + '\'' +
                ", driver1='" + driver1 + '\'' +
                ", driver2='" + driver2 + '\'' +
                ", startTime='" + startTime + '\'' +
                ", weight='" + weight + '\'' +
                '}';
    }
}