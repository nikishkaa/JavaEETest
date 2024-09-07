package org.example.javaeedemo.dao;

import org.example.javaeedemo.model.User;
import org.example.javaeedemo.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {

    public static final String Y = "Y";

    @Override
    public boolean activate(User user) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try (Connection conn = DBUtils.getConnection()) {

            pstmt = conn.prepareStatement("UPDATE users SET  is_active = 'Y', update_ts = CURRENT_TIMESTAMP WHERE users.id = ?;");
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
                user.setPassword(rs.getString(4));
                user.set_active(rs.getString(5).equals(Y));
                user.setCreatedTs(rs.getTimestamp(6));
                user.setUpdatedTs(rs.getTimestamp(7));
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

            pstmt = conn.prepareStatement(String.format("INSERT INTO users (name, email, password) VALUES ('%s', '%s', '%s')",
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
