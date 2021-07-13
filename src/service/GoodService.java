package service;

import dao.ConsignorDaoImpl;
import dao.GoodsDaoImpl;
import dao.LogisticsDaoImpl;
import dao.TrucksDaoImpl;
import model.Consignor;
import model.Goods;
import util.ShowMessageUtil;

import java.util.ArrayList;
import java.util.List;

public class GoodService {
    private static GoodService goodService;
    static {
        goodService = new GoodService();
    }
    public static GoodService getInstance(){return goodService;}
    private ConsignorDaoImpl consignorDao;
    private TrucksDaoImpl trucksDao;
    private LogisticsDaoImpl logisticsDao;
    private GoodsDaoImpl goodsDao;
    private GoodService(){
        consignorDao = ConsignorDaoImpl.getInstance();
        trucksDao = TrucksDaoImpl.getInstance();
        goodsDao = GoodsDaoImpl.getInstance();
        logisticsDao = LogisticsDaoImpl.getInstance();
    }

    public Goods queryGoodsNoTruckByLidGid(int l_id,int g_id){
        List<Goods> list = queryGoodsListNoTruck(l_id);
        for (Goods g :list){
            if(g.getId() == g_id){
                return g;
            }
        }
        return null;
    }

    public List<Goods> queryGoodsListNoTruck(int l_id){
        List<Consignor> listCon = consignorDao.queryConsignorsByLid(l_id);
        List<Goods> goodsRes = new ArrayList<>();
        for(Consignor c : listCon){
            List<Goods> listG = goodsDao.queryGoodsByCon(c.getId());
            for(Goods g : listG){
                if(g.getT_id()==0){
                    goodsRes.add(g);
                }
            }
        }
        return goodsRes;
    }

    public Object[][] queryGoodsNoTruck(int l_id){
        List<Goods> goodsRes = goodService.queryGoodsListNoTruck(l_id);
        Object[][] obj = new Object[goodsRes.size()][8];
        int i = 0;
        for (Goods g : goodsRes) {
            obj[i][0] = g.getId();
            obj[i][1] = g.getName();
            obj[i][2] = g.getType();
            obj[i][3] = g.getWeight();
            obj[i][4] = g.getReceiveTel();
            obj[i][5] = g.getCost();
            obj[i][6] = g.getC_id();
            obj[i][7] = g.getT_id();
            i++;
        }
        return obj;
    }

    public Object[][] queryGoodsById(int c_id){
        List<Goods> list = goodsDao.queryGoodsByCon(c_id);
        Object[][] obj = new Object[list.size()][8];
        int i = 0;
        for (Goods g : list) {
            obj[i][0] = g.getId();
            obj[i][1] = g.getName();
            obj[i][2] = g.getType();
            obj[i][3] = g.getWeight();
            obj[i][4] = g.getReceiveTel();
            obj[i][5] = g.getCost();
            obj[i][6] = g.getC_id();
            obj[i][7] = g.getT_id();
            i++;
        }
        return obj;
    }

    public boolean addGoods(int id,String name,String type
            ,int weight, String receiveTel,int cost,int c_id,int t_id){
        Goods goods = new Goods(id,name,type,weight,receiveTel,cost,c_id,t_id);
        if(goodsDao.insertGoods(goods)){
            ShowMessageUtil.Message("添加成功！");
            return true;
        }else {
            ShowMessageUtil.Message("添加失败！");
            return false;
        }
    }

    public boolean updGoods(int id,String name,String type
            ,int weight, String receiveTel,int cost,int c_id,int t_id){
        Goods goods = new Goods(id,name,type,weight,receiveTel,cost,c_id,t_id);
        if(goodsDao.updateGoods(goods)){
            ShowMessageUtil.Message("更新成功！");
            return true;
        }else {
            ShowMessageUtil.Message("更新失败!");
            return false;
        }
    }
}