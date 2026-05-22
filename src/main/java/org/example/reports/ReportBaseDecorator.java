package org.example.reports;

import org.example.model.Mission;

abstract class ReportBaseDecorator implements Report {
    private Report wrapper;
    public ReportBaseDecorator(Report wrapper) {
        this.wrapper = wrapper;
    }

    @Override
    public String getReport(Mission mission) {
        return wrapper.getReport(mission);
    }
}
