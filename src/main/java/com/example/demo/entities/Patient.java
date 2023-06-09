package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Objects;

@Entity
public class Patient extends Person{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    public Patient(){
        super();
    }

    public Patient(String firstName, String lastName, int age, String email){
        super(firstName, lastName, age, email);
    }

    public long getId(){
        return this.id;
    }
    public void setId(long id){
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient patient = (Patient) o;
        return getId() == patient.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
