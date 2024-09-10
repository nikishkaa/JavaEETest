package org.example.javaeedemo.dao;

import org.example.javaeedemo.model.User;
import org.example.javaeedemo.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class UserDAOImpl implements UserDAO {

    public static final String Y = "Y";
    private final RoleDao roleDao = new RoleDaoImpl();

    @Override
    public Set<User> findAllUsers() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Set<User> users = new HashSet<>();

        try (Connection conn = DBUtils.getConnection()) {
            pstmt = conn.prepareStatement(String.format("SELECT * FROM users"));
            rs = pstmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.set_active(rs.getString(4).equals(Y));
                user.setPassword(rs.getString(5));

                int roleID = rs.getInt(6);
                user.setRoleId(roleDao.findRoleById(roleID));

                user.setCreatedTs(rs.getTimestamp(7));
                user.setUpdatedTs(rs.getTimestamp(8));

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("FindAll Exception", e);
        } finally {
            DBUtils.close(null, null, pstmt, rs);
        }


        return users;
    }

    @Override
    public boolean activate(User user) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try (Connection conn = DBUtils.getConnection()) {

            pstmt = conn.prepareStatement("UPDATE users SET  is_active = 'Y', updated_ts = CURRENT_TIMESTAMP WHERE users.id = ?;");
            pstmt.setString(1, user.getEmail());
            return pstmt.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Activation user problem", e);
        } finally {
            DBUtils.close(null, null, pstmt, rs);
        }

    }


    @Override
    public User findByEmail(String email) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try (Connection conn = DBUtils.getConnection()) {

            pstmt = conn.prepareStatement(String.format("SELECT * FROM users WHERE email = '%s'", email));
            rs = pstmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.set_active(rs.getString(4).equals(Y));
                user.setPassword(rs.getString(5));

                int roleID = rs.getInt(6);
                user.setRoleId(roleDao.findRoleById(roleID));

                user.setCreatedTs(rs.getTimestamp(7));
                user.setUpdatedTs(rs.getTimestamp(8));
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Bad connection", e);
        } finally {
            DBUtils.close(null, null, pstmt, rs);
        }


        return null;
    }

    @Override
    public boolean createUser(User user) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try (Connection conn = DBUtils.getConnection()) {

            pstmt = conn.prepareStatement(String.format("INSERT INTO users (name, email, password, role) VALUES ('%s', '%s', '%s', 3)",
                    user.getName(), user.getEmail(), user.getPassword()));

            return pstmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("creation user problem", e);
        } finally {
            DBUtils.close(null, null, pstmt, rs);
        }
    }


}
