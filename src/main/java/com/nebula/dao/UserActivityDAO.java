package com.nebula.dao;

import com.nebula.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

public class UserActivityDAO {

    public void logActivity(int userId, int skillId) {

        String sql =
                "INSERT INTO user_activity (user_id, skill_id, activity_date) " +
                        "VALUES (?, ?, ?)";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, skillId);
            ps.setDate(3, java.sql.Date.valueOf(LocalDate.now()));

            ps.executeUpdate();

        } catch (SQLException e) {
            // Duplicate entry = activity already logged today
            // We ignore it intentionally
            if (e.getErrorCode() != 1062) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Calculate current streak for a user
    public int getCurrentStreak(int userId) {

        String sql =
                "SELECT activity_date " +
                        "FROM user_activity " +
                        "WHERE user_id = ? " +
                        "ORDER BY activity_date DESC";

        Set<LocalDate> activityDays = new HashSet<>();

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                activityDays.add(rs.getDate("activity_date").toLocalDate());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 🔹 Calculate streak
        int streak = 0;
        LocalDate today = LocalDate.now();

        while (activityDays.contains(today)) {
            streak++;
            today = today.minusDays(1);
        }

        return streak;
    }

}
