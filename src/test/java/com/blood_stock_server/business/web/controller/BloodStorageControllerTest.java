//package com.blood_stock_server.business.web.controller;
//
//import com.blood_stock_server.business.service.impl.BloodStorageServiceImpl;
//import com.blood_stock_server.model.BloodStorage;
//import com.blood_stock_server.web.controller.BloodStorageController;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(BloodStorageController.class)
//class BloodStorageControllerTest {
//    private final String BLOOD_STORAGE_URI = "/api/blood/storage";
//    @Autowired
//    private MockMvc mockMvc;
//    @MockBean
//    private BloodStorageServiceImpl service;
//    private List<BloodStorage> bloodStorages;
//    private BloodStorage bloodStorage;
//    private BloodStorage invalidBloodStorage;
//    private BloodStorage updatedBloodStorage;
//
//    private static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @BeforeEach
//    void init() {
//        bloodStorages = createListOfBloodStorages();
//        bloodStorage = createBloodStorage();
//        invalidBloodStorage = createInvalidBloodStorage();
//        updatedBloodStorage = createUpdatedBloodStorage();
//    }
//
//    @Test
//    void findAllBloodStorages_Success() throws Exception {
//        when(service.findAllBloodStorages()).thenReturn(bloodStorages);
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get(BLOOD_STORAGE_URI))
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("email1@gmail.com"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].phoneNumber").value("12345678"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].address").value("address1"))
//                .andExpect(status().isOk());
//        verify(service, times(1)).findAllBloodStorages();
//    }
//
//    @Test
//    void findBloodStorageById_Success() throws Exception {
//        when(service.findBloodStorageById(anyLong())).thenReturn(bloodStorage);
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get(BLOOD_STORAGE_URI + "/1"))
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("email1@gmail.com"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("12345678"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("address1"))
//                .andExpect(status().isOk());
//        verify(service, times(1)).findBloodStorageById(anyLong());
//    }
//
//    @Test
//    void saveBloodStockStorage_Success() throws Exception {
//        when(service.saveBloodStorage(bloodStorage)).thenReturn(bloodStorage);
//        mockMvc.perform(MockMvcRequestBuilders
//                        .post(BLOOD_STORAGE_URI)
//                        .content(asJsonString(bloodStorage))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("email1@gmail.com"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("12345678"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("address1"));
//        verify(service, times(1)).saveBloodStorage(bloodStorage);
//    }
//
//    @Test
//    void updateBloodStorage_Success() throws Exception {
//        when(service.updateBloodStorage(bloodStorage, 1L)).thenReturn(updatedBloodStorage);
//        mockMvc.perform(MockMvcRequestBuilders
//                        .put(BLOOD_STORAGE_URI + "/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(bloodStorage))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("email111@gmail.com"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("11111111"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("address111"));
//        verify(service, times(1)).updateBloodStorage(bloodStorage, 1L);
//    }
//
//    @Test
//    void updateBloodStorage_InvalidData_BadRequest() throws Exception {
//        when(service.saveBloodStorage(invalidBloodStorage)).thenReturn(invalidBloodStorage);
//        mockMvc.perform(MockMvcRequestBuilders
//                        .put(BLOOD_STORAGE_URI + "/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(invalidBloodStorage))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest());
//        verify(service, times(0)).saveBloodStorage(invalidBloodStorage);
//    }
//
//    private List<BloodStorage> createListOfBloodStorages() {
//        List<BloodStorage> bloodStorages = new ArrayList<>();
//        bloodStorages.add(createBloodStorage());
//        return bloodStorages;
//    }
//
//    private BloodStorage createBloodStorage() {
//        BloodStorage bloodStorage = new BloodStorage();
//        bloodStorage.setId(1L);
//        bloodStorage.setEmail("email1@gmail.com");
//        bloodStorage.setPhoneNumber("12345678");
//        bloodStorage.setAddress("address1");
//
//        return bloodStorage;
//    }
//
//    private BloodStorage createUpdatedBloodStorage() {
//        BloodStorage bloodStorage = new BloodStorage();
//        bloodStorage.setId(1L);
//        bloodStorage.setEmail("email111@gmail.com");
//        bloodStorage.setPhoneNumber("11111111");
//        bloodStorage.setAddress("address111");
//
//        return bloodStorage;
//    }
//
//    private BloodStorage createInvalidBloodStorage() {
//        BloodStorage bloodStorage = new BloodStorage();
//        bloodStorage.setId(1L);
//        bloodStorage.setAddress("address111");
//
//        return bloodStorage;
//    }
//}
