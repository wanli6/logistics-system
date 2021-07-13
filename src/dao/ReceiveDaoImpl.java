package dao;

public class ReceiveDaoImpl {
    private static ReceiveDaoImpl receiveDao;
    static {
        receiveDao = new ReceiveDaoImpl();
    }

    private ReceiveDaoImpl(){
    }
    public static ReceiveDaoImpl getInstance(){
        return receiveDao;
    }
}