package org.example.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class EnemyActivity {

    private String behaviorType;
    private String targetPriority;

    @ElementCollection
    @CollectionTable(name = "enemy_attack_patterns",
            joinColumns = @JoinColumn(name = "mission_id", referencedColumnName = "missionId"))
    @Column(name = "pattern")
    private List<String> attackPatterns = new ArrayList<>();

    private String mobility;
    private String escalationRisk;

    @ElementCollection
    @CollectionTable(name = "enemy_countermeasures",
            joinColumns = @JoinColumn(name = "mission_id", referencedColumnName = "missionId"))
    @Column(name = "measure")
    private List<String> countermeasuresUsed = new ArrayList<>();

    // Геттеры и сеттеры
    public String getBehaviorType() {
        return behaviorType;
    }

    public void setBehaviorType(String behaviorType) {
        this.behaviorType = behaviorType;
    }

    public String getTargetPriority() {
        return targetPriority;
    }

    public void setTargetPriority(String targetPriority) {
        this.targetPriority = targetPriority;
    }

    public List<String> getAttackPatterns() {
        return attackPatterns;
    }

    public void setAttackPatterns(List<String> attackPatterns) {
        this.attackPatterns = attackPatterns;
    }

    public String getMobility() {
        return mobility;
    }

    public void setMobility(String mobility) {
        this.mobility = mobility;
    }

    public String getEscalationRisk() {
        return escalationRisk;
    }

    public void setEscalationRisk(String escalationRisk) {
        this.escalationRisk = escalationRisk;
    }

    public List<String> getCountermeasuresUsed() {
        return countermeasuresUsed;
    }

    public void setCountermeasuresUsed(List<String> countermeasuresUsed) {
        this.countermeasuresUsed = countermeasuresUsed;
    }
}