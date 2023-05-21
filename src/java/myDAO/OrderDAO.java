/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myDAO;

import basicObject.Order;
import basicObject.OrderDetails;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import mylib.DBUtils;

/**
 *
 * @author Thien Do
 */
public class OrderDAO {

    public static ArrayList<Order> getOrders(String email) throws Exception {
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select OrderID,OrdDate,shipdate,Orders.status as 'status',Accounts.accID\n"
                    + "from Accounts, Orders\n"
                    + "where Accounts.accID = Orders.AccID and Accounts.status=1 and Accounts.email = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    int orderId = rs.getInt("OrderID");
                    String ordDate = rs.getString("OrdDate");
                    String shipDate = rs.getString("shipdate");
                    int status = rs.getInt("status");
                    int accId = rs.getInt("accID");
                    Order o = new Order(orderId, ordDate, shipDate, status, accId);
                    list.add(o);
                }
            }
            cn.close();
        }
        return list;
    }

    public static void cancelOrder(int oid) {
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update Orders\n"
                        + "set status=3\n"
                        + "where OrderID =?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, oid);
                pst.executeUpdate();
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

    }

    public static void reOrder(int oid) {
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update Orders\n"
                        + "set status=1,OrdDate=?\n"
                        + "where OrderID =?";
                PreparedStatement pst = cn.prepareStatement(sql);
                Date d = new Date(System.currentTimeMillis());
                pst.setDate(1, d);
                pst.setInt(2, oid);
                pst.executeUpdate();
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

    }

    public static void updateOrder(int oid, int status) {
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update Orders\n"
                        + "set status=? \n"
                        + "where OrderID=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, status);
                pst.setInt(2, oid);
                pst.executeUpdate();
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

    }

    public static ArrayList<OrderDetails> getOrderDetail(int orderID) {
        Connection cn = null;
        ArrayList<OrderDetails> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select DetailId,OrderID,PID,PName,price,imgPath,quantity\n"
                        + "from OrderDetails, Plants\n"
                        + "where OrderID=? and OrderDetails.FID=Plants.PID";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, orderID);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int detailID = rs.getInt("DetailId");
                        int PlantID = rs.getInt("PID");
                        String PlantName = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgPath = rs.getString("imgPath");
                        int quantity = rs.getInt("quantity");
                        OrderDetails orderdetail = new OrderDetails(detailID, orderID, PlantID, PlantName, price, imgPath, quantity);
                        list.add(orderdetail);
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

    public static ArrayList<Order> orderByStatus(int stt) {
        Connection cn = null;
        ArrayList<Order> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select OrderID,OrdDate,shipdate,status\n"
                        + "from Orders\n"
                        + "where status=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, stt);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int orderid = rs.getInt("OrderID");
                        String orddate = rs.getString("OrdDate");
                        String shipdate = rs.getString("shipdate");
                        int status = rs.getInt("status");
                        Order account = new Order(orderid, orddate, shipdate, status, 0);
                        list.add(account);
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

    public static boolean insertOrder(String email, HashMap<String, Integer> cart) {
        Connection cn = null;
        boolean result = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                int accid = 0, orderid = 0;
                cn.setAutoCommit(false);
                String sql = "select accID from Accounts where Accounts.email=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    accid = rs.getInt("accID");
                }
                //insert a new order
                System.out.println("accountid:" + accid);
                Date d = new Date(System.currentTimeMillis());
                System.out.println("order date:" + d);
                sql = "insert Orders(OrdDate,status,AccID) values(?,?,?)";
                pst = cn.prepareStatement(sql);
                pst.setDate(1, d);
                pst.setInt(2, 1);
                pst.setInt(3, accid);
                pst.executeUpdate();
                sql = "select top 1 orderID from Orders order by orderId desc";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();

                if (rs != null && rs.next()) {
                    orderid = rs.getInt("orderID");
                }
                //insert order detail
                System.out.println("orderid" + orderid);
                Set<String> pids = cart.keySet();
                for (String pid : pids) {
                    sql = "insert OrderDetails values(?,?,?)";
                    pst = cn.prepareStatement(sql);
                    pst.setInt(1, orderid);
                    pst.setInt(2, Integer.parseInt(pid.trim()));
                    pst.setInt(3, cart.get(pid));
                    pst.executeUpdate();
                    cn.commit();
                    cn.setAutoCommit(true);
                }
                return true;
            } else {
                System.out.println("k chen order dc");
            }
        } catch (Exception e) {
            try {
                if (cn != null) {
                    cn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            result = false;
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }
    }

    public static ArrayList<Order> getOrders() {
        Connection cn = null;
        ArrayList<Order> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select OrderID,OrdDate,shipdate,status,AccID\n"
                        + "from orders";
                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int orderid = rs.getInt("OrderID");
                        String orddate = rs.getString("OrdDate");
                        String shipdate = rs.getString("shipdate");
                        int status = rs.getInt("status");
                        int accid = rs.getInt("AccID");
                        Order account = new Order(orderid, orddate, shipdate, status, accid);
                        list.add(account);
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

    public static ArrayList<Order> getOrder(int keyword) {
        Connection cn = null;
        ArrayList<Order> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select OrderID,OrdDate,shipdate,status,AccID\n"
                        + "from orders\n"
                        + "where AccID=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, keyword);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int orderid = rs.getInt("OrderID");
                        String orddate = rs.getString("OrdDate");
                        String shipdate = rs.getString("shipdate");
                        int status = rs.getInt("status");
                        int accid = rs.getInt("AccID");
                        Order account = new Order(orderid, orddate, shipdate, status, accid);
                        list.add(account);
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
}
