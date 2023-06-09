
package com.example.demo;

import static org.mockito.ArgumentMatchers.any;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import java.time.LocalDateTime;
import java.time.format.*;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.example.demo.controllers.*;
import com.example.demo.repositories.*;
import com.example.demo.entities.*;
import com.fasterxml.jackson.databind.ObjectMapper;



/** TODO
 * Implement all the unit test in its corresponding class.
 * Make sure to be as exhaustive as possible. Coverage is checked ;)
 */

@WebMvcTest(DoctorController.class)
class DoctorControllerUnitTest{

    @MockBean
    private DoctorRepository doctorRepository;

    @Autowired 
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private DoctorController doctorController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllDoctors() {

        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("Manel", "Samba", 30, "manel@gmail.com"));
        doctors.add(new Doctor("Núria", "Boreas", 35, "nuria@yahoo.com"));

        when(doctorRepository.findAll()).thenReturn(doctors);

        // Act
        ResponseEntity<List<Doctor>> response = doctorController.getAllDoctors();

        // Assert
        assertEquals("Status code",HttpStatus.OK, response.getStatusCode());
        assertEquals("Doctors found",doctors, response.getBody());
        verify(doctorRepository, times(1)).findAll();
    }

    @Test
    public void testGetAllDoctors_NoDoctors() {

        doctorRepository.deleteAll();

        when(doctorRepository.findAll()).thenReturn(new ArrayList<>());


        ResponseEntity<List<Doctor>> response = doctorController.getAllDoctors();


        assertEquals("Status Code", HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals("No doctors", null, response.getBody());
        verify(doctorRepository, times(1)).findAll();
    }

    @Test
    public void testCreateDoctor() {

        Doctor doctor = new Doctor("Robert", "Crus", 30, "robert@gmail.com");


        ResponseEntity<Doctor> response = doctorController.createDoctor(doctor);


        assertEquals("Status Code",HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Doctor created", doctor, response.getBody());
        verify(doctorRepository, times(1)).save(doctor);
    }

    @Test
    public void testGetDoctorById() {

        long id = 1L;
        Doctor doctor = new Doctor("Manel", "Boreas", 30, "manel@gmail.com");

        when(doctorRepository.findById(id)).thenReturn(Optional.of(doctor));


        ResponseEntity<Doctor> response = doctorController.getDoctorById(id);


        assertEquals("Status Code", HttpStatus.OK, response.getStatusCode());
        assertEquals("Doctor object found", doctor, response.getBody());
        verify(doctorRepository, times(1)).findById(id);
    }

    @Test
    public void testGetDoctorById_DoctorNotFound() {

        long id = 1L;

        when(doctorRepository.findById(id)).thenReturn(Optional.empty());


        ResponseEntity<Doctor> response = doctorController.getDoctorById(id);


        assertEquals("Status Code", HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Doctor not found", null, response.getBody());
        verify(doctorRepository, times(1)).findById(id);
    }


    @Test
    public void testDeleteDoctor() {

        long id = 1L;
        Doctor doctor = new Doctor("Manel", "Boreas", 30, "manel@gmail.com");

        when(doctorRepository.findById(id)).thenReturn(Optional.of(doctor));


        ResponseEntity<HttpStatus> response = doctorController.deleteDoctor(id);


        assertEquals("Status Code",HttpStatus.OK, response.getStatusCode());
        verify(doctorRepository, times(1)).findById(id);
        verify(doctorRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDeleteDoctor_DoctorNotFound() {

        long id = 1L;

        when(doctorRepository.findById(id)).thenReturn(Optional.empty());


        ResponseEntity<HttpStatus> response = doctorController.deleteDoctor(id);


        assertEquals("Status Code", HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(doctorRepository, times(1)).findById(id);
        verify(doctorRepository, times(0)).deleteById(id);
    }

    @Test
    public void testDeleteAllDoctors() {
        // Act
        ResponseEntity<HttpStatus> response = doctorController.deleteAllDoctors();

        // Assert
        assertEquals("Status Code", HttpStatus.OK, response.getStatusCode());
        verify(doctorRepository, times(1)).deleteAll();
    }




}


@WebMvcTest(PatientController.class)
class PatientControllerUnitTest{

    @InjectMocks
    private PatientController patientController;
    @MockBean
    private PatientRepository patientRepository;

    @Autowired 
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;



    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllPatients() {

        List<Patient> patients = Arrays.asList(
                new Patient("Robert", "Crus", 30, "robert@gmail.com"),
                new Patient("Nuria", "Smith", 25, "nuria@yahoo.com")
        );

        patientRepository.saveAll(patients);

        Patient patient = new Patient("Manel", "Boreas", 30, "manel@gmail.com");

        patientRepository.save(patient);

        Mockito.when(patientRepository.findAll()).thenReturn(patients);


        ResponseEntity<List<Patient>> response = patientController.getAllPatients();


        assertEquals("Status Code", HttpStatus.OK, response.getStatusCode());
        assertEquals("Patients size", patients.size(), response.getBody().size());
    }

    @Test
    public void testGetPatientById() {

        long patientId = 1L;
        Patient patient = new Patient("Manel", "Boreas", 30, "manel@gmail.com");
        patient.setId(patientId);

        Mockito.when(patientRepository.findById(patientId)).thenReturn(Optional.of(patient));


        ResponseEntity<Patient> response = patientController.getPatientById(patientId);


        assertEquals("Status Code",HttpStatus.OK, response.getStatusCode());
        assertEquals("Patient", patient, response.getBody());
    }

    @Test
    public void testCreatePatient() {

        Patient patient = new Patient("Manel", "Boreas", 30, "manel@gmail.com");


        ResponseEntity<Patient> response = patientController.createPatient(patient);


        assertEquals("Status Code",HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Patient", patient, response.getBody());

        Mockito.verify(patientRepository, Mockito.times(1)).save(patient);
    }

    @Test
    public void testDeletePatient() {

        long patientId = 1L;
        Patient patient = new Patient("Manel", "Boreas", 30, "manel@gmail.com");
        patient.setId(patientId);

        Mockito.when(patientRepository.findById(patientId)).thenReturn(Optional.of(patient));


        ResponseEntity<HttpStatus> response = patientController.deletePatient(patientId);


        assertEquals("Status Code",HttpStatus.OK, response.getStatusCode());

        Mockito.verify(patientRepository, Mockito.times(1)).deleteById(patientId);
    }

    @Test
    public void testDeleteAllPatients() {

        ResponseEntity<HttpStatus> response = patientController.deleteAllPatients();


        assertEquals("Status Code",HttpStatus.OK, response.getStatusCode());

        Mockito.verify(patientRepository, Mockito.times(1)).deleteAll();
    }



}

@WebMvcTest(RoomController.class)
class RoomControllerUnitTest{

    @MockBean
    private RoomRepository roomRepository;

    @Autowired 
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @InjectMocks
    private RoomController roomController;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(roomController).build();
    }

    @Test
    public void testGetAllRooms() throws Exception {
        Room room1 = new Room("Room 1");
        Room room2 = new Room("Room 2");
        List<Room> rooms = Arrays.asList(room1, room2);

        Mockito.when(roomRepository.findAll()).thenReturn(rooms);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/rooms"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].roomName", Matchers.is("Room 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].roomName", Matchers.is("Room 2")));
    }

    @Test
    public void testGetAllRooms_NoContent() throws Exception {
        List<Room> rooms = new ArrayList<>();

        Mockito.when(roomRepository.findAll()).thenReturn(rooms);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/rooms"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testGetRoomByRoomName() throws Exception {
        Room room = new Room("Room 1");

        Mockito.when(roomRepository.findByRoomName("Room 1")).thenReturn(Optional.of(room));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/rooms/{roomName}", "Room 1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.roomName", Matchers.is("Room 1")));
    }

    @Test
    public void testGetRoomByRoomName_NotFound() throws Exception {
        Mockito.when(roomRepository.findByRoomName("Room 1")).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/rooms/{roomName}", "Room 1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testCreateRoom() throws Exception {
        Room room = new Room("Room 1");
        Room savedRoom = new Room("Room 1");

        Mockito.when(roomRepository.save(Mockito.any(Room.class))).thenReturn(savedRoom);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/room")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"roomName\": \"Room 1\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.roomName", Matchers.is("Room 1")));
    }

    @Test
    public void testDeleteRoom() throws Exception {
        Room room = new Room("Room 1");

        Mockito.when(roomRepository.findByRoomName("Room 1")).thenReturn(Optional.of(room));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/rooms/{roomName}", "Room 1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteRoom_NotFound() throws Exception {
        Mockito.when(roomRepository.findByRoomName("Room 1")).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/rooms/{roomName}", "Room 1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testDeleteAllRooms() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/rooms"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
