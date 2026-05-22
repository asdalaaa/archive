package org.example.builder;

import org.example.model.*;

import java.util.List;
import java.util.Map;

public interface Builder {
    void setMissionId(String missionId);
    void setDate(String date);
    void setLocation(String location);
    void setOutcome(String outcome);
    void setDamageCost(Integer damageCost);
    void setCurse(Curse curse);
    void setSorcerers(List<Sorcerer> sorcerers);
    void setTechniques(List<Technique> techniques);
    void setEconomicAssessment(EconomicAssessment economicAssessment);
    void setCivilianImpact(CivilianImpact civilianImpact);
    void setEnemyActivity(EnemyActivity enemyActivity);
    void setEnvironmentConditions(EnvironmentConditions environmentConditions);
    void setOperationTimeline(List<OperationTimeline> operationTimeline);
    void setOtherFields(Map<String, Object> otherFields);
    Mission getResult();
}
