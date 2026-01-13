package com.nebula.dao;
import com.nebula.model.Skill;
import com.nebula.model.User;
import com.nebula.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import java.util.List;

public class SkillDAO {
    // Fetch all skills from skills table

    public List<Skill> getAllSkills() {
        List<Skill> skills = new ArrayList<>();

        String sql = "SELECT * FROM skills";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Skill skill = new Skill();
                skill.setSkillId(rs.getInt("skill_id"));
                skill.setSkillName(rs.getString("skill_name"));
                skill.setCategory(rs.getString("category"));
                skill.setDescription(rs.getString("description"));
                skill.setCreatedAt(rs.getTimestamp("created_at"));

                skills.add(skill);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return skills;
    }
    public List<Skill> getRecommendedSkills(User user) {

        List<Skill> skills = new ArrayList<>();

        String branch = user.getBranch();
        int year = user.getYear();

        String goal = user.getCareerGoal();

        if (goal != null) {
            goal = goal.toLowerCase();
        } else {
            goal = "";
        }

        String sql = "";

        if ("CSE".equalsIgnoreCase(branch) && year <= 2) {
            sql = "SELECT * FROM skills WHERE skill_name IN ('Java', 'DSA', 'SQL')";
        }
        else if ("CSE".equalsIgnoreCase(branch) && year >= 3) {
            sql = "SELECT * FROM skills WHERE skill_name IN ('System Design', 'Aptitude', 'OS')";
        }

        if (goal.contains("web")) {
            sql = "SELECT * FROM skills WHERE skill_name IN ('HTML', 'CSS', 'JavaScript')";
        }

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Skill skill = new Skill();
                skill.setSkillId(rs.getInt("skill_id"));
                skill.setSkillName(rs.getString("skill_name"));
                skill.setCategory(rs.getString("category"));
                skill.setDescription(rs.getString("description"));
                skills.add(skill);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return skills;
    }
    // API: Goal-based recommendations using role_skills
    public List<Skill> getRecommendations(int userId) {

        List<Skill> list = new ArrayList<>();

        String sql =
                "SELECT s.skill_id, s.skill_name, s.category, rs.priority " +
                        "FROM users u " +
                        "JOIN role_skills rs ON u.role_id = rs.role_id " +
                        "JOIN skills s ON rs.skill_id = s.skill_id " +
                        "WHERE u.user_id = ? " +
                        "AND s.skill_id NOT IN ( " +
                        "    SELECT skill_id FROM user_skills WHERE user_id = ? " +
                        ") " +
                        "ORDER BY rs.priority";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Skill skill = new Skill();
                skill.setSkillId(rs.getInt("skill_id"));
                skill.setSkillName(rs.getString("skill_name"));
                skill.setCategory(rs.getString("category"));
                skill.setPriority(rs.getInt("priority"));

                list.add(skill);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    // API: Skill Gap Analysis
    public Map<String, Object> getSkillGap(int userId) {

        Map<String, Object> result = new HashMap<>();

        String roleSql = "SELECT role_id FROM users WHERE user_id = ?";
        String totalSql = "SELECT COUNT(*) FROM role_skills WHERE role_id = ?";
        String addedSql =
                "SELECT COUNT(*) " +
                        "FROM user_skills us " +
                        "JOIN role_skills rs ON us.skill_id = rs.skill_id " +
                        "WHERE us.user_id = ? AND rs.role_id = ?";

        try (Connection con = DBUtil.getConnection()) {

            // 1️⃣ Get role_id
            PreparedStatement ps1 = con.prepareStatement(roleSql);
            ps1.setInt(1, userId);
            ResultSet rs1 = ps1.executeQuery();
            if (!rs1.next()) return result;

            int roleId = rs1.getInt(1);

            // 2️⃣ Total required skills
            PreparedStatement ps2 = con.prepareStatement(totalSql);
            ps2.setInt(1, roleId);
            ResultSet rs2 = ps2.executeQuery();
            rs2.next();
            int totalRequired = rs2.getInt(1);

            // 3️⃣ Skills already added
            PreparedStatement ps3 = con.prepareStatement(addedSql);
            ps3.setInt(1, userId);
            ps3.setInt(2, roleId);
            ResultSet rs3 = ps3.executeQuery();
            rs3.next();
            int added = rs3.getInt(1);

            result.put("totalRequired", totalRequired);
            result.put("skillsAdded", added);
            result.put("skillsMissing", totalRequired - added);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


}