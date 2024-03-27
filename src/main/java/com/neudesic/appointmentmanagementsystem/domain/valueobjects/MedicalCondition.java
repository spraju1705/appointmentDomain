package com.neudesic.appointmentmanagementsystem.domain.valueobjects;

import java.util.Objects;
import java.util.UUID;

public class MedicalCondition {

    private final String disease;

    private final MedicalConditionStage medicalConditionStage;


    public String getDisease() {
        return disease;
    }

    public MedicalConditionStage getMedicalConditionStage() {
        return medicalConditionStage;
    }

    public MedicalCondition(String disease, MedicalConditionStage medicalConditionStage) {
        this.disease = disease;
        this.medicalConditionStage = medicalConditionStage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalCondition that = (MedicalCondition) o;
        return Objects.equals(disease, that.disease) && medicalConditionStage == that.medicalConditionStage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(disease, medicalConditionStage);
    }

    private Boolean isValidMedicalCondition(){
        return  (this.disease != null && this.medicalConditionStage !=null);
    }
}
