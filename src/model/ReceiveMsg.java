package model;

public class ReceiveMsg {
    private int tid;
    private String num;
    private String startTime;
    private String data;
    private String id;
    private String name;
    private String address;

    public ReceiveMsg(){

    }

    public ReceiveMsg(int tid, String num, String startTime, String data, String id, String name, String address) {
        this.tid = tid;
        this.num = num;
        this.startTime = startTime;
        this.data = data;
        this.id = id;
        this.name = name;
        this.address = address;
    }
}