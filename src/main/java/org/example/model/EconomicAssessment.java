package org.example.model;

import javax.persistence.Embeddable;

@Embeddable
public class EconomicAssessment {
    private Integer totalDamageCost;
    private Integer infrastructureDamage;
    private Integer commercialDamage;
    private Integer transportDamage;
    private Integer recoveryEstimateDays;
    private boolean insuranceCovered;

    public Integer getTotalDamageCost() {
        return totalDamageCost;
    }

    public void setTotalDamageCost(Integer totalDamageCost) {
        this.totalDamageCost = totalDamageCost;
    }

    public Integer getInfrastructureDamage() {
        return infrastructureDamage;
    }

    public void setInfrastructureDamage(Integer infrastructureDamage) {
        this.infrastructureDamage = infrastructureDamage;
    }

    public Integer getCommercialDamage() {
        return commercialDamage;
    }

    public void setCommercialDamage(Integer commercialDamage) {
        this.commercialDamage = commercialDamage;
    }

    public Integer getTransportDamage() {
        return transportDamage;
    }

    public void setTransportDamage(Integer transportDamage) {
        this.transportDamage = transportDamage;
    }

    public Integer getRecoveryEstimateDays() {
        return recoveryEstimateDays;
    }

    public void setRecoveryEstimateDays(Integer recoveryEstimateDays) {
        this.recoveryEstimateDays = recoveryEstimateDays;
    }

    public boolean getInsuranceCovered() {
        return insuranceCovered;
    }

    public void setInsuranceCovered(boolean insuranceCovered) {
        this.insuranceCovered = insuranceCovered;
    }
}