/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mylib;

import basicObject.Account;
import basicObject.Order;
import basicObject.Plant;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import myDAO.AccountDAO;
import myDAO.OrderDAO;
import myDAO.PlantDao;

/**
 *
 * @author Thien Do
 */
public class Tester {
    public static void main(String[] args) {
//        OrderDAO.updateOrder(18,2);
        ArrayList<Order> list = OrderDAO.getOrder(1);
              for (Order alist : list) {
            System.out.println(alist.toString()+" ");
            
        }
//        AccountDAO.updateAccountStatus("oke@gmail.com", 1);
//        ArrayList<Account> list = AccountDAO.getAccounts();
//        for (Account alist : list) {
//            System.out.println(alist.toString()+" ");
//            
//        }
//        System.out.println(PlantDao.getPlant(1));
//        try{
//            Connection cn = DBUtils.makeConnection();
//            if(cn!=null){
//                String s = "select PID,PName,price,imgPath,description,status,CateID \n" +
//                            "from Plants";
//                Statement st=cn.createStatement();
//                ResultSet kq = st.executeQuery(s);
//                if(kq!=null) {
//                    while(kq.next()){
//                        String id = kq.getString("PID");
//                        String name = kq.getString("PName");
//                        int price = kq.getInt("price");
//                        String path = kq.getString("imgPath");
//                        String des = kq.getString("description");
//                        int stt = kq.getInt("status");
//                        int caID = kq.getInt("CateID");
//                        System.out.println(id+","+name+","+price+","+path+","+des+","+stt+","+caID);
//                    }
//                }
//                cn.close();
//            }
//        } catch (Exception e) 
//        {
//            e.printStackTrace();
//        }
    }
}
