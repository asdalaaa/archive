package org.example.builder;

import org.example.model.Mission;

public class Director {
    public Mission createMission(Builder missionBuilder, Mission mission) {
        missionBuilder.setMissionId(mission.getMissionId());
        missionBuilder.setCurse(mission.getCurse());
        missionBuilder.setCivilianImpact(mission.getCivilianImpact());
        missionBuilder.setDate(mission.getDate());
        missionBuilder.setDamageCost(mission.getDamageCost());
        missionBuilder.setEconomicAssessment(mission.getEconomicAssessment());
        missionBuilder.setLocation(mission.getLocation());
        missionBuilder.setEnemyActivity(mission.getEnemyActivity());
        missionBuilder.setOutcome(mission.getOutcome());
        missionBuilder.setSorcerers(mission.getSorcerers());
        missionBuilder.setEnvironmentConditions(mission.getEnvironmentConditions());
        missionBuilder.setTechniques(mission.getTechniques());
        missionBuilder.setOperationTimeline(mission.getOperationTimeline());
        missionBuilder.setOtherFields(mission.getOtherFields());
        return missionBuilder.getResult();
    }
}
