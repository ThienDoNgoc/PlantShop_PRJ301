/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dto;

import basicObject.Account;
import basicObject.Plant;
import java.util.ArrayList;
import myDAO.AccountDAO;
import myDAO.PlantDao;

/**
 *
 * @author Thien Do
 */
public class testConnection {

    public static void main(String[] args) {
//        Account acc = AccountDAO.getAccount("test@gmail.com","test");
////        System.out.println(acc.getFullname());
//        if(acc != null){
////            System.out.println(acc.getAccID());
//            if (acc.getRole()==1)
//                System.out.println("i am an admin");
//            else 
//                System.out.println("i am a user");
//        }
//        else System.out.println("login fail");
        ArrayList<Plant> plant = new ArrayList<>();
        plant = PlantDao.getPlants("mon", "byname");
        for (Plant plant1 : plant) {
            System.out.println(plant1 + " ");
        }
//        if(AccountDAO.insertAccount("thiendo1@gmail.com","thiendo1","Thiendongoc1","123456",0,1))
//            System.out.println("added new account");
//        else System.out.println("insert new account fail");
    }
}
