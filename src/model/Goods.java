package model;

public class Goods {
    private int id;
    private String name;
    private String type;
    private int weight;
    private String receiveTel;
    private int cost;
    private int c_id;
    private int t_id;

    public Goods(){

    }

    public Goods(int id, String name, String type, int weight, String receiveTel, int cost, int c_id, int t_id) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.receiveTel = receiveTel;
        this.cost = cost;
        this.c_id = c_id;
        this.t_id = t_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getReceiveTel() {
        return receiveTel;
    }

    public void setReceiveTel(String receiveTel) {
        this.receiveTel = receiveTel;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public int getT_id() {
        return t_id;
    }

    public void setT_id(int t_id) {
        this.t_id = t_id;
    }

}