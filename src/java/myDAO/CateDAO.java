/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myDAO;

import basicObject.Categories;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import mylib.DBUtils;

/**
 *
 * @author Thien Do
 */
public class CateDAO {

    public static ArrayList<Categories> getCates() {
        Connection cn = null;
        ArrayList<Categories> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select CateID,CateName\n"
                        + "from Categories";
                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int cateid = rs.getInt("CateID");
                        String catename = rs.getString("CateName");
                        Categories p = new Categories(cateid, catename);
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
       public static boolean insertCate(String newName) {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String query = "insert into Categories(CateName) values (?)";
                pst = cn.prepareStatement(query);
                pst.setString(1, newName);
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

    public static boolean updateCate(Categories p) {
        boolean check = false;
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update Categories \n"
                        + "set CateName=?\n"
                        + "where CateID=?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, p.getCateName().trim());
                pst.setInt(2, p.getCateId());
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

}
