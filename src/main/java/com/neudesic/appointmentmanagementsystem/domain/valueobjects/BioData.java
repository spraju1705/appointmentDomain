package com.neudesic.appointmentmanagementsystem.domain.valueobjects;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class BioData {

    private  final String name;

    private final LocalDate dateOfBirth;

    private final String address;

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public BioData(String name, LocalDate dateOfBirth, String address) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BioData bioData = (BioData) o;
        return Objects.equals(name, bioData.name) && Objects.equals(dateOfBirth, bioData.dateOfBirth) && Objects.equals(address, bioData.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dateOfBirth, address);
    }

    public Boolean isValidBioData(){
        return (this.address!=null && this.name!=null && this.dateOfBirth!=null && this.dateOfBirth.isBefore(LocalDate.now()));
    }


}
