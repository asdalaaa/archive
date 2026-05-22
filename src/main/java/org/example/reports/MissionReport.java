package org.example.reports;

import org.example.model.Mission;

public class MissionReport implements Report {

    @Override
    public String getReport(Mission mission) {
        StringBuilder sb = new StringBuilder();

        sb.append("===========\n");
        sb.append("Миссия: ").append(mission.getMissionId()).append("\n");
        sb.append("Дата: ").append(mission.getDate()).append("\n");
        sb.append("Локация: ").append(mission.getLocation()).append("\n");
        sb.append("Исход: ").append(mission.getOutcome()).append("\n");
        sb.append("Ущерб: ").append(mission.getDamageCost()).append("\n");

        sb.append("\n===========\n");
        sb.append("Проклятие: \n");
        sb.append(mission.getCurse().getInfo()).append("\n");

        return sb.toString();
    }
}
