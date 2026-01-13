package com.nebula.model;
import java.sql.Timestamp;
public class Skill {
    private int skillId;
    private String skillName;
    private String category;
    private String description;
    private Timestamp createdAt;
    private int priority;
    public Skill(){
    }
    public int getSkillId(){
        return skillId;
    }
    public void setSkillId(int skillId){
        this.skillId=skillId;
    }
    public String getSkillName(){
        return skillName;
    }
    public void setSkillName(String skillName){
        this.skillName=skillName;
    }
    public String getCategory(){
        return category;
    }
    public void setCategory(String category){
        this.category= category;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description=description;
    }
    public Timestamp getCreatedAt(){
        return createdAt;
    }
    public void setCreatedAt(Timestamp createdAt){
        this.createdAt=createdAt;
    }
    private String importance;


    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

}
