/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myDAO;

import basicObject.Plant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import mylib.DBUtils;

/**
 *
 * @author Thien Do
 */
public class PlantDao {

    public static ArrayList<Plant> getPlants(String keyword, String searchby) {
        ArrayList<Plant> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null && searchby != null) {
                String sql = "select PID,PName,price,imgPath,description,status,Plants.CateID as 'CateID',CateName\n"
                        + "from Plants join Categories on Plants.CateID=Categories.CateID\n";
                if (searchby.equalsIgnoreCase("byname")) {
                    sql = sql + "where Plants.PName like ?";
                } else {
                    sql = sql + "where CateName like ?";
                }
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + keyword + "%");
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int id = rs.getInt("PID");
                        String name = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgpath = rs.getString("imgPath");
                        String description = rs.getString("description");
                        int status = rs.getInt("status");
                        int cateid = rs.getInt("CateID");
                        String catename = rs.getString("CateName");
                        Plant plant = new Plant(id, name, price, imgpath, description, status, cateid, catename);
                        list.add(plant);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static Plant getPlant(int pid) {
        Plant p = new Plant();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select PID,PName,price,imgPath,description,status,Plants.CateID as cateID,CateName\n"
                        + "from Plants, Categories\n"
                        + "where Plants.CateID=Categories.CateID and PID =?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, pid);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    pid = rs.getInt("PID");
                    String pname = rs.getString("PName");
                    int price = rs.getInt("price");
                    String imgPath = rs.getString("imgPath");
                    String des = rs.getString("description");
                    int status = rs.getInt("status");
                    int cateid = rs.getInt("cateID");
                    String cateName = rs.getString("CateName");
                    p = new Plant(pid, pname, price, imgPath, des, status, cateid, cateName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return p;
    }

    public static ArrayList<Plant> getPlants() {
        Connection cn = null;
        ArrayList<Plant> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select PID,PName,price,imgPath,description,status,CateID\n"
                        + "from Plants";
                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int pid = rs.getInt("PID");
                        String pname = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgpath = rs.getString("imgPath");
                        String des = rs.getString("description");
                        int status = rs.getInt("status");
                        int cateid = rs.getInt("CateID");
                        Plant p = new Plant(pid, pname, price, imgpath, des, status, cateid, "");
                        list.add(p);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    public static boolean updatePlant(Plant p) {
        boolean check = false;
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update Plants \n"
                        + "set PName=?,price=?,imgPath=?,description=?,status=?,CateID=? \n"
                        + "where PID= ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, p.getName().trim());
                pst.setInt(2, p.getPrice());
                pst.setString(3, p.getImgpath().trim());
                pst.setString(4, p.getDescription().trim());
                pst.setInt(5, p.getStatus());
                pst.setInt(6, p.getCateid());
                pst.setInt(7, p.getId());
                check = pst.executeUpdate() > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return check;
    }
    
    
    
        public static boolean insertPlant(String newName, int newprice,String newPath, String newDes,int newStatus,int newCateId) {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String query = "insert into Plants(PName,price,imgPath,description,status,cateID) values (?,?,?,?,?,?)";
                pst = cn.prepareStatement(query);
                pst.setString(1, newName);
                pst.setInt(2, newprice);
                pst.setString(3, newPath);
                pst.setString(4, newDes);
                pst.setInt(5, newStatus);
                pst.setInt(6, newCateId);

                int row = pst.executeUpdate();

                if (row > 0) {
                    return true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

}
