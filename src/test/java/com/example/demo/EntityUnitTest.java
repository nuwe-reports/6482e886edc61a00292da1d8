package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import com.example.demo.entities.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@TestInstance(Lifecycle.PER_CLASS)
class EntityUnitTest {

	@Autowired
	private TestEntityManager entityManager;


    private Room r1 = new Room("Dermatology");




    /** TODO
     * Implement tests for each Entity class: Doctor, Patient, Room and Appointment.
     * Make sure you are as exhaustive as possible. Coverage is checked ;)
     */

    @DisplayName("Appointment Entity Getter Id Test")
    @Test
    public void testGetAppointmentId() {
        Appointment appointment = new Appointment();
        appointment.setId(1L);
        assertEquals(1L, appointment.getId());
    }

    @DisplayName("Appointment Entity Setter Id Test")
    @Test
    public void testSetAppointmentId() {
        Appointment appointment = new Appointment();
        appointment.setId(2L);
        assertEquals(2L, appointment.getId());
    }

    @DisplayName("Appointment Entity Getter StartsAt Test")
    @Test
    public void testGetStartsAt() {
        LocalDateTime startsAt = LocalDateTime.of(2023, 5, 27, 10, 0);
        Appointment appointment = new Appointment();
        appointment.setStartsAt(startsAt);
        assertEquals(startsAt, appointment.getStartsAt());
    }

    @DisplayName("Appointment Entity Setter StartsAt Test")
    @Test
    public void testSetStartsAt() {
        LocalDateTime startsAt = LocalDateTime.of(2023, 5, 28, 14, 30);
        Appointment appointment = new Appointment();
        appointment.setStartsAt(startsAt);
        assertEquals(startsAt, appointment.getStartsAt());
    }
    @DisplayName("Appointment Entity Getter FinishesAt Test")
    @Test
    public void testGetFinishesAt() {
        LocalDateTime finishesAt = LocalDateTime.of(2023, 5, 27, 11, 0);
        Appointment appointment = new Appointment();
        appointment.setFinishesAt(finishesAt);
        assertEquals(finishesAt, appointment.getFinishesAt());
    }

    @DisplayName("Appointment Entity Setter FinishesAt Test")
    @Test
    public void testSetFinishesAt() {
        LocalDateTime finishesAt = LocalDateTime.of(2023, 5, 28, 15, 0);
        Appointment appointment = new Appointment();
        appointment.setFinishesAt(finishesAt);
        assertEquals(finishesAt, appointment.getFinishesAt());
    }

    @DisplayName("Appointment Entity Getter Patient Test")
    @Test
    public void testGetPatient() {
        Patient patient = new Patient();
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        assertEquals(patient, appointment.getPatient());
    }

    @DisplayName("Appointment Entity Setter Patient Test")
    @Test
    public void testSetPatient() {
        Patient patient = new Patient();
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        assertEquals(patient, appointment.getPatient());
    }

    @DisplayName("Appointment Entity Getter Doctor Test")
    @Test
    public void testGetDoctor() {
        Doctor doctor = new Doctor();
        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        assertEquals(doctor, appointment.getDoctor());
    }

    @DisplayName("Appointment Entity Setter Doctor Test")
    @Test
    public void testSetDoctor() {
        Doctor doctor = new Doctor();
        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        assertEquals(doctor, appointment.getDoctor());
    }

    @DisplayName("Appointment Entity Getter Room Test")
    @Test
    public void testGetRoom() {
        Room room = new Room();
        Appointment appointment = new Appointment();
        appointment.setRoom(room);
        assertEquals(room, appointment.getRoom());
    }

    @DisplayName("Appointment Entity Setter Room Test")
    @Test
    public void testSetRoom() {
        Room room = new Room();
        Appointment appointment = new Appointment();
        appointment.setRoom(room);
        assertEquals(room, appointment.getRoom());
    }


    @DisplayName("Appointment overlap test")
    @Test
    public void testOverlaps() {
        LocalDateTime startsAt1 = LocalDateTime.of(2023, 1, 1, 10, 0);
        LocalDateTime finishesAt1 = LocalDateTime.of(2023, 1, 1, 12, 0);
        Appointment appointment1 = new Appointment(null, null, r1, startsAt1, finishesAt1);

        LocalDateTime startsAt2 = LocalDateTime.of(2023, 1, 1, 11, 0);
        LocalDateTime finishesAt2 = LocalDateTime.of(2023, 1, 1, 13, 0);
        Appointment appointment2 = new Appointment(null, null, r1, startsAt2, finishesAt2);

        assertTrue(appointment1.overlaps(appointment2));
        assertTrue(appointment2.overlaps(appointment1));
    }

    @DisplayName("Appointment not overlap test")
    @Test
    public void testNotOverlapping() {

        LocalDateTime startsAt1 = LocalDateTime.of(2023, 1, 1, 10, 0);
        LocalDateTime finishesAt1 = LocalDateTime.of(2023, 1, 1, 12, 0);
        Appointment appointment1 = new Appointment(null, null, r1, startsAt1, finishesAt1);

        LocalDateTime startsAt2 = LocalDateTime.of(2023, 1, 1, 12, 0);
        LocalDateTime finishesAt2 = LocalDateTime.of(2023, 1, 1, 14, 0);
        Appointment appointment2 = new Appointment(null, null, r1, startsAt2, finishesAt2);

        assertFalse(appointment1.overlaps(appointment2));
        assertFalse(appointment2.overlaps(appointment1));
    }

    @DisplayName("Doctor Entity Id Getter Test")
    @Test
    public void testGetDoctorId() {
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        assertEquals(1L, doctor.getId());
    }

    @DisplayName("Doctor Entity Id Setter Test")
    @Test
    public void testSetDoctorId() {
        Doctor doctor = new Doctor();
        doctor.setId(2L);
        assertEquals(2L, doctor.getId());
    }

    @DisplayName("Patient Entity Id Getter Test")
    @Test
    public void testGetId() {
        long expectedId = 123;
        Patient patient = new Patient();
        patient.setId(expectedId);

        long actualId = patient.getId();

        assertEquals(expectedId, actualId);
    }

    @DisplayName("Patient Entity Id Setter Test")
    @Test
    public void testSetId() {

        long expectedId = 456;
        Patient patient = new Patient();

        patient.setId(expectedId);
        long actualId = patient.getId();

        assertEquals(expectedId, actualId);
    }


    @DisplayName("Room Entity Getter Test")
    @Test
    public void testGetRoomName() {

        String expectedRoomName = "Room 1";
        Room room = new Room(expectedRoomName);

        String actualRoomName = room.getRoomName();

        assertEquals(expectedRoomName, actualRoomName);
    }


    @DisplayName("Person Entity Name Getter Test")
    @Test
    public void testGetFirstName() {

        String expectedFirstName = "Manel";
        Person person = new Person();
        person.setFirstName(expectedFirstName);

        String actualFirstName = person.getFirstName();

        assertEquals(expectedFirstName, actualFirstName);
    }


    @DisplayName("Person Entity Name Setter Test")
    @Test
    public void testSetFirstName() {

        String expectedFirstName = "Marta";
        Person person = new Person();

        person.setFirstName(expectedFirstName);
        String actualFirstName = person.getFirstName();

        assertEquals(expectedFirstName, actualFirstName);
    }

    @DisplayName("Person Entity LastName Getter Test")
    @Test
    public void testGetLastName() {

        String expectedLastName = "Rodriguez";
        Person person = new Person();
        person.setLastName(expectedLastName);

        String actualLastName = person.getLastName();

        assertEquals(expectedLastName, actualLastName);
    }

    @DisplayName("Person Entity LastName Setter Test")
    @Test
    public void testSetLastName() {

        String expectedLastName = "Verona";
        Person person = new Person();

        person.setLastName(expectedLastName);
        String actualLastName = person.getLastName();

        assertEquals(expectedLastName, actualLastName);
    }

    @DisplayName("Person Entity Age Getter Test")
    @Test
    public void testGetAge() {

        int expectedAge = 30;
        Person person = new Person();
        person.setAge(expectedAge);

        int actualAge = person.getAge();

        assertEquals(expectedAge, actualAge);
    }

    @DisplayName("Person Entity Age Setter Test")
    @Test
    public void testSetAge() {

        int expectedAge = 35;
        Person person = new Person();

        person.setAge(expectedAge);
        int actualAge = person.getAge();

        assertEquals(expectedAge, actualAge);
    }

    @DisplayName("Person Entity Email Getter Test")
    @Test
    public void testGetEmail() {

        String expectedEmail = "manel@gmail.com";
        Person person = new Person();
        person.setEmail(expectedEmail);

        String actualEmail = person.getEmail();

        assertEquals(expectedEmail, actualEmail);
    }

    @DisplayName("Person Entity Email Setter Test")
    @Test
    public void testSetEmail() {

        String expectedEmail = "marta@yahoo.com";
        Person person = new Person();

        person.setEmail(expectedEmail);
        String actualEmail = person.getEmail();

        assertEquals(expectedEmail, actualEmail);
    }


}
