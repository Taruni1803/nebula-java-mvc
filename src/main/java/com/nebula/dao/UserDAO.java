package com.nebula.dao;
import java.sql.ResultSet;

import com.nebula.model.User;
import com.nebula.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

public class UserDAO {

    public boolean registerUser(User user) {
        String sql = "INSERT INTO users(name, email, password, role, branch, year, career_goal) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword()); // later we hash
            ps.setString(4, user.getRole());
            ps.setString(5, user.getBranch());
            ps.setInt(6, user.getYear());
            ps.setString(7, user.getCareerGoal());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public User login(String email, String password) {
        String sql = "SELECT * FROM users WHERE email=? AND password=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                user.setBranch(rs.getString("branch"));
                user.setYear(rs.getInt("year"));
                user.setCareerGoal(rs.getString("career_goal"));
                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public void updateUserProfile(User user) {

        String sql = "UPDATE users SET branch = ?, year = ?, career_goal = ? WHERE user_id = ?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getBranch());
            ps.setInt(2, user.getYear());
            ps.setString(3, user.getCareerGoal());
            ps.setInt(4, user.getUserId());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public User getUserById(int userId) {

        User user = null;
        String sql = "SELECT * FROM users WHERE user_id = ?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                user.setBranch(rs.getString("branch"));
                user.setYear(rs.getInt("year"));
                user.setCareerGoal(rs.getString("career_goal"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
    public Map<String, Integer> getSkillStats(int userId) {
        Map<String, Integer> stats = new HashMap<>();

        String totalSql = "SELECT COUNT(*) FROM user_skills WHERE user_id=?";
        String completedSql = "SELECT COUNT(*) FROM user_skills WHERE user_id=? AND status='Completed'";
        String progressSql = "SELECT COUNT(*) FROM user_skills WHERE user_id=? AND status='In Progress'";

        try (Connection con = DBUtil.getConnection()) {

            PreparedStatement ps1 = con.prepareStatement(totalSql);
            ps1.setInt(1, userId);
            ResultSet rs1 = ps1.executeQuery();
            if (rs1.next()) stats.put("total", rs1.getInt(1));

            PreparedStatement ps2 = con.prepareStatement(completedSql);
            ps2.setInt(1, userId);
            ResultSet rs2 = ps2.executeQuery();
            if (rs2.next()) stats.put("completed", rs2.getInt(1));

            PreparedStatement ps3 = con.prepareStatement(progressSql);
            ps3.setInt(1, userId);
            ResultSet rs3 = ps3.executeQuery();
            if (rs3.next()) stats.put("progress", rs3.getInt(1));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return stats;
    }
    public void updateUserRole(int userId, int roleId) {

        String sql = "UPDATE users SET role_id=? WHERE user_id=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, roleId);
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
