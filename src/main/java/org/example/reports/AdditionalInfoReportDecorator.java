package org.example.reports;

import org.example.model.Mission;
import org.example.model.Sorcerer;
import org.example.model.Technique;

import java.util.List;

public class AdditionalInfoReportDecorator extends ReportBaseDecorator {

    public AdditionalInfoReportDecorator(Report wrapper) {
        super(wrapper);
    }

    @Override
    public String getReport(Mission mission) {

        String wrapperReport = super.getReport(mission);
        StringBuilder report = new StringBuilder();
        report.append(wrapperReport);

        report.append("\n");
        report.append("Маги:\n");

        List<Sorcerer> sorcerers = mission.getSorcerers();
        if (sorcerers != null) {
            for (Sorcerer sorcerer : sorcerers) {
                report.append(String.format("Имя: %s\n",
                        sorcerer.getName() != null ? sorcerer.getName() : ""));
                report.append(String.format("Ранг: %s\n",
                        sorcerer.getRank() != null ? sorcerer.getRank() : ""));
                report.append("\n");
            }
        }

        report.append("Техники:\n");

        List<Technique> techniques = mission.getTechniques();
        if (techniques != null) {
            for (Technique technique : techniques) {
                report.append(String.format("Название: %s\n",
                        technique.getName() != null ? technique.getName() : ""));
                report.append(String.format("Тип: %s\n",
                        technique.getType() != null ? technique.getType() : ""));
                report.append(String.format("Владелец: %s\n",
                        technique.getOwner() != null ? technique.getOwner() : ""));
                if (technique.getDamage() != null) {
                    report.append(String.format("Урон: %d\n", technique.getDamage()));
                }
                report.append("\n");
            }
        }

        return report.toString();
    }
}