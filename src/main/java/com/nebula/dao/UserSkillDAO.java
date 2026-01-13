package com.nebula.dao;
import com.nebula.model.Skill;
import com.nebula.model.UserSkill;
import com.nebula.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class UserSkillDAO {
    public boolean skillAlreadyAdded(int userId, int skillId) {

        String sql = "SELECT 1 FROM user_skills WHERE user_id=? AND skill_id=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, skillId);

            return ps.executeQuery().next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Assign a skill to a user
    public boolean addUserSkill(UserSkill userSkill) {

        String sql = "INSERT INTO user_skills (user_id, skill_id, level, target_completion_date, status) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userSkill.getUserId());
            ps.setInt(2, userSkill.getSkillId());
            ps.setString(3, userSkill.getLevel());
            ps.setDate(4, userSkill.getTargetCompletionDate());
            ps.setString(5, userSkill.getStatus());

           ps.executeUpdate();
            return true;

        } catch (SQLIntegrityConstraintViolationException e) {
            // Duplicate skill selected
            return false;

        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }

    // Fetch skills selected by a specific user
    public List<UserSkill> getUserSkillsByUserId(int userId) {

        List<UserSkill> userSkills = new ArrayList<>();

        String sql = "SELECT * FROM user_skills WHERE user_id = ?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    UserSkill us = new UserSkill();
                    us.setUserSkillId(rs.getInt("user_skill_id"));
                    us.setUserId(rs.getInt("user_id"));
                    us.setSkillId(rs.getInt("skill_id"));
                    us.setLevel(rs.getString("level"));
                    us.setTargetCompletionDate(rs.getDate("target_completion_date"));
                    us.setStatus(rs.getString("status"));
                    us.setCreatedAt(rs.getTimestamp("created_at"));
                    us.setUpdatedAt(rs.getTimestamp("updated_at"));

                    userSkills.add(us);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return userSkills;
    }
    public List<UserSkill> getUserSkillsWithDetails(int userId) {

        List<UserSkill> list = new ArrayList<>();

        String sql =
                "SELECT us.user_skill_id, us.level, us.status, " +
                        "s.skill_name, s.category " +
                        "FROM user_skills us " +
                        "JOIN skills s ON us.skill_id = s.skill_id " +
                        "WHERE us.user_id = ?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UserSkill us = new UserSkill();
                us.setUserSkillId(rs.getInt("user_skill_id"));
                us.setLevel(rs.getString("level"));
                us.setStatus(rs.getString("status"));

                // extra display fields
                us.setSkillName(rs.getString("skill_name"));
                us.setCategory(rs.getString("category"));

                list.add(us);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }



        return list;
    }
    public void updateSkillStatus(int userSkillId, String status) {

        String sql = "UPDATE user_skills SET status = ? WHERE user_skill_id = ?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, userSkillId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void removeUserSkill(int userSkillId) {

        String sql = "DELETE FROM user_skills WHERE user_skill_id = ?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userSkillId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Map<String, Integer> getSkillStats(int userId) {

        Map<String, Integer> stats = new HashMap<>();

        String totalSql =
                "SELECT COUNT(*) FROM user_skills WHERE user_id = ?";
        String completedSql =
                "SELECT COUNT(*) FROM user_skills WHERE user_id = ? AND status = 'Completed'";
        String progressSql =
                "SELECT COUNT(*) FROM user_skills WHERE user_id = ? AND status = 'In Progress'";

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

}
