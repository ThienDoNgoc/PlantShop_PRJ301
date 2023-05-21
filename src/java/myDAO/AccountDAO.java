/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myDAO;

import basicObject.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import mylib.DBUtils;

/**
 *
 * @author Thien Do
 */
public class AccountDAO {

    public static boolean updateProfile(Account acc) {
        boolean check = false;
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update Accounts \n"
                        + "set email=?,password=?,fullname=?,phone=? \n"
                        + "where accID= ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, acc.getEmail().trim());
                pst.setString(2, acc.getPassword().trim());
                pst.setString(3, acc.getFullname().trim());
                pst.setString(4, acc.getPhone().trim());
                pst.setInt(5, acc.getAccID());
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

    public static Account getAccount(String email, String password) {
        Connection cn = null;
        Account acc = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select accID,email,password,fullname,phone,status,role\n"
                        + "from Accounts \n"
                        + "where status=1 and email = ? and password = ? COLLATE Latin1_General_CS_As ";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, password);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int AccID = rs.getInt("accID");
                    String Email = rs.getString("email");
                    String Password = rs.getString("password");
                    String Fullname = rs.getString("fullname");
                    String Phone = rs.getString("phone");
                    int Status = rs.getInt("status");
                    int Role = rs.getInt("role");
                    acc = new Account(AccID, Email, Password, Fullname, Phone, Status, Role);

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
        return acc;
    }

    public static ArrayList<Account> getAccounts(String key) {
        Connection cn = null;
        Account acc = null;
        ArrayList<Account> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select accID,email,password,fullname,phone,status,role\n"
                        + "from Accounts \n"
                        + "where email like ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%"+key+"%");
                ResultSet rs = pst.executeQuery();
                while (rs != null && rs.next()) {
                    int AccID = rs.getInt("accID");
                    String Email = rs.getString("email");
                    String Password = rs.getString("password");
                    String Fullname = rs.getString("fullname");
                    String Phone = rs.getString("phone");
                    int Status = rs.getInt("status");
                    int Role = rs.getInt("role");
                    acc = new Account(AccID, Email, Password, Fullname, Phone, Status, Role);
                    list.add(acc);
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
        return list;
    }

    public static Account getAccountToChange(String email) {
        Connection cn = null;
        Account acc = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select accID,email,password,fullname,phone,status,role\n"
                        + "from Accounts \n"
                        + "where status=1 and email = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int AccID = rs.getInt("accID");
                    String Email = rs.getString("email");
                    String Password = rs.getString("password");
                    String Fullname = rs.getString("fullname");
                    String Phone = rs.getString("phone");
                    int Status = rs.getInt("status");
                    int Role = rs.getInt("role");
                    acc = new Account(AccID, Email, Password, Fullname, Phone, Status, Role);

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
        return acc;
    }

    public static Account getAccount(String token) {
        Connection cn = null;
        Account acc = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select accID,email,password,fullname,phone,status,role\n"
                        + "from Accounts \n"
                        + "where status=1 and token=? COLLATE Latin1_General_CS_As ";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, token);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int AccID = rs.getInt("accID");
                    String Email = rs.getString("email");
                    String Password = rs.getString("password");
                    String Fullname = rs.getString("fullname");
                    String Phone = rs.getString("phone");
                    int Status = rs.getInt("status");
                    int Role = rs.getInt("role");
                    acc = new Account(AccID, Email, Password, Fullname, Phone, Status, Role);

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
        return acc;
    }

    public static void updateToken(String token, String email) {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String query = "update Accounts \n"
                        + "set token=? \n"
                        + "where email = ?";
                pst = cn.prepareStatement(query);
                pst.setString(1, token);
                pst.setString(2, email);
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

    public static boolean insertAccount(String newEmail, String newPassword, String newFullname,
            String newPhone, int newStatus, int newRole) {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
//                pstm = cn.prepareStatement("SET IDENTITY_INSERT Accounts on");
//                pstm.execute();
                String query = "insert into Accounts(email,password,fullname,phone,status,role) values (?,?,?,?,?,?)";
                pst = cn.prepareStatement(query);
                pst.setString(1, newEmail);
                pst.setString(2, newPassword);
                pst.setString(3, newFullname);
                pst.setString(4, newPhone);
                pst.setInt(5, newStatus);
                pst.setInt(6, newRole);

                int row = pst.executeUpdate();

                if (row > 0) {
                    return true;
                }
//                pstm = cn.prepareStatement("SET IDENTITY_INSERT Accounts off");
//                pstm.execute();
//                

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

    public static ArrayList<Account> getAccounts() {
        Connection cn = null;
        ArrayList<Account> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select accID,email,fullname,status,phone,role\n"
                        + "from Accounts";
                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int accid = rs.getInt("accID");
                        String email = rs.getString("email");
                        String fullname = rs.getString("fullname");
                        int status = rs.getInt("status");
                        String phone = rs.getString("phone");
                        int role = rs.getInt("role");
                        Account account = new Account(accid, email, phone, fullname, phone, status, role);
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

    public static void updateAccountStatus(String email, int i) {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String query = "update Accounts \n"
                        + "set status=? \n"
                        + "where email=?";
                pst = cn.prepareStatement(query);
                pst.setInt(1, i);
                pst.setString(2, email);
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

}
