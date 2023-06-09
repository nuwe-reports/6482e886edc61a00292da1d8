package com.example.demo.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="doctors")
public class Doctor extends Person {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
 
    public Doctor() {
        super();
    }
    public Doctor(String firstName, String lastName, int age, String email){
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
        if (!(o instanceof Doctor)) return false;
        Doctor doctor = (Doctor) o;
        return getId() == doctor.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
