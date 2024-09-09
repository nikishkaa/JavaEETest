package org.example.javaeedemo.dao;

import org.example.javaeedemo.model.Role;
import org.example.javaeedemo.model.User;
import org.example.javaeedemo.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDaoImpl implements RoleDao {
    @Override
    public Role findRoleById(int id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Role role = null;


        try (Connection conn = DBUtils.getConnection()) {
            pstmt = conn.prepareStatement("SELECT id, name, description FROM roles where id = ?");
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                role = new Role(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return role;
    }

    @Override
    public Role findRoleByUser(User user) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Role role = null;


        try (Connection conn = DBUtils.getConnection()) {
            pstmt = conn.prepareStatement("SELECT id, name, description FROM roles where id = ?");
            pstmt.setInt(1, user.getRoleId().getId());

            rs = pstmt.executeQuery();
            if (rs.next()) {
                role = new Role(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return role;
    }
}
