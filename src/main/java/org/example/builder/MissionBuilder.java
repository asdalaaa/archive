package org.example.builder;

import org.example.model.*;

import java.util.*;

public class MissionBuilder implements Builder {
    private Mission mission = new Mission();

    @Override
    public void setMissionId(String missionId) {
        mission.setMissionId(Objects.requireNonNullElse(missionId, "Данные не предоставлены"));
    }

    @Override
    public void setDate(String date) {
        mission.setDate(Objects.requireNonNullElse(date, "Данные не предоставлены"));
    }

    @Override
    public void setLocation(String location) {
        mission.setLocation(Objects.requireNonNullElse(location, "Данные не предоставлены"));
    }

    @Override
    public void setOutcome(String outcome) {
        mission.setOutcome(Objects.requireNonNullElse(outcome, "Данные не предоставлены"));
    }

    @Override
    public void setDamageCost(Integer damageCost) {
        mission.setDamageCost(Objects.requireNonNullElse(damageCost, -1));
    }

    @Override
    public void setCurse(Curse curse) {
        Curse curseTmp = new Curse();
        curseTmp.setName("Данные не предоставлены");
        curseTmp.setThreatLevel("Данные не предоставлены");
        mission.setCurse(Objects.requireNonNullElse(curse, curseTmp));
    }

    @Override
    public void setSorcerers(List<Sorcerer> sorcerers) {
        mission.setSorcerers(Objects.requireNonNullElse(sorcerers, new ArrayList<>()));
    }

    @Override
    public void setTechniques(List<Technique> techniques) {
        mission.setTechniques(Objects.requireNonNullElse(techniques, new ArrayList<>()));
    }

    @Override
    public void setEconomicAssessment(EconomicAssessment economicAssessment) {
        EconomicAssessment economicAssessmentTmp = new EconomicAssessment();
        if (economicAssessment != null) {
            economicAssessmentTmp.setInfrastructureDamage(Objects.requireNonNullElse(economicAssessment.getInfrastructureDamage(), -1));
            economicAssessmentTmp.setInsuranceCovered(economicAssessment.getInsuranceCovered());
            economicAssessmentTmp.setTransportDamage(Objects.requireNonNullElse(economicAssessment.getTransportDamage(), -1));
            economicAssessmentTmp.setTotalDamageCost(Objects.requireNonNullElse(economicAssessment.getTotalDamageCost(), -1));
            economicAssessmentTmp.setRecoveryEstimateDays(Objects.requireNonNullElse(economicAssessment.getRecoveryEstimateDays(), -1));
            economicAssessmentTmp.setCommercialDamage(Objects.requireNonNullElse(economicAssessment.getCommercialDamage(), -1));;
        }
        mission.setEconomicAssessment(economicAssessmentTmp);
    }

    @Override
    public void setCivilianImpact(CivilianImpact civilianImpact) {
        CivilianImpact civilianImpactTmp = new CivilianImpact();
        if (civilianImpact != null) {
            civilianImpactTmp.setEvacuated(Objects.requireNonNullElse(civilianImpact.getEvacuated(), -1));
            civilianImpactTmp.setInjured(Objects.requireNonNullElse(civilianImpact.getInjured(), -1));
            civilianImpactTmp.setMissing(Objects.requireNonNullElse(civilianImpact.getMissing(), -1));
            civilianImpactTmp.setPublicExposureRisk(Objects.requireNonNullElse(civilianImpact.getPublicExposureRisk(), "Данные не предоставлены"));
        }
        mission.setCivilianImpact(civilianImpactTmp);
    }

    @Override
    public void setEnemyActivity(EnemyActivity enemyActivity) {
        EnemyActivity enemyActivityTmp = new EnemyActivity();
        if (enemyActivity != null) {
            enemyActivityTmp.setAttackPatterns(Objects.requireNonNullElse(enemyActivity.getAttackPatterns(), new ArrayList<>()));
            enemyActivityTmp.setBehaviorType(Objects.requireNonNullElse(enemyActivity.getBehaviorType(), "Данные не предоставлены"));
            enemyActivityTmp.setCountermeasuresUsed(Objects.requireNonNullElse(enemyActivity.getCountermeasuresUsed(), new ArrayList<>() ));
            enemyActivityTmp.setMobility(Objects.requireNonNullElse(enemyActivity.getMobility(), "Данные не предоставлены" ));
            enemyActivityTmp.setEscalationRisk(Objects.requireNonNullElse(enemyActivity.getEscalationRisk(), "Данные не предоставлены"));
            enemyActivityTmp.setTargetPriority(Objects.requireNonNullElse(enemyActivity.getTargetPriority(), "Данные не предоставлены"));
        }
        mission.setEnemyActivity(enemyActivityTmp);
    }

    @Override
    public void setEnvironmentConditions(EnvironmentConditions environmentConditions) {
        EnvironmentConditions environmentConditionsTmp = new EnvironmentConditions();
        if (environmentConditions != null) {
            environmentConditionsTmp.setCursedEnergyDensity(Objects.requireNonNullElse(environmentConditions.getCursedEnergyDensity(), -1));
            environmentConditionsTmp.setVisibility(Objects.requireNonNullElse(environmentConditions.getVisibility(), "Данные не предоставлены"));
            environmentConditionsTmp.setWeather(Objects.requireNonNullElse(environmentConditions.getWeather(), "Данные не предоставлены"));
            environmentConditionsTmp.setTimeOfDay(Objects.requireNonNullElse(environmentConditions.getTimeOfDay(), "Данные не предоставлены"));
        }
        mission.setEnvironmentConditions(environmentConditionsTmp);
    }

    @Override
    public void setOperationTimeline(List<OperationTimeline> operationTimeline) {
        mission.setOperationTimeline(Objects.requireNonNullElse(operationTimeline, new ArrayList<>()));
    }

    @Override
    public void setOtherFields(Map<String, Object> otherFields) {
        mission.setOtherFields(Objects.requireNonNullElse(otherFields, new HashMap<>()));
    }

    @Override
    public Mission getResult() {
        return mission;
    }
}
