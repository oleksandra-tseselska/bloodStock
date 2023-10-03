//package com.blood_stock_server.business.web.controller;
//
//import com.blood_stock_server.business.service.impl.BloodGroupServiceImpl;
//import com.blood_stock_server.model.BloodGroup;
//import com.blood_stock_server.web.controller.BloodGroupController;
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
//@WebMvcTest(BloodGroupController.class)
//class BloodGroupControllerTest {
//    private final String BLOOD_GROUP_URI = "/api/blood/group";
//    @Autowired
//    private MockMvc mockMvc;
//    @MockBean
//    private BloodGroupServiceImpl service;
//    private List<BloodGroup> bloodGroups;
//    private BloodGroup bloodGroup;
//
//    @BeforeEach
//    void init() {
//        bloodGroups = createListOfBloodGroups();
//        bloodGroup = createBloodGroup();
//    }
//
//    @Test
//    void findAllBloodGroups_Success() throws Exception {
//        when(service.findAllBloodGroups()).thenReturn(bloodGroups);
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get(BLOOD_GROUP_URI))
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].group").value("0-"))
//                .andExpect(status().isOk());
//        verify(service, times(1)).findAllBloodGroups();
//    }
//
//
//    @Test
//    void findBloodGroupById_Success() throws Exception {
//        when(service.findBloodGroupById(anyLong())).thenReturn(bloodGroup);
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get(BLOOD_GROUP_URI + "/1"))
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.group").value("0-"))
//                .andExpect(status().isOk());
//        verify(service, times(1)).findBloodGroupById(anyLong());
//    }
//
//    private List<BloodGroup> createListOfBloodGroups() {
//        List<BloodGroup> bloodGroups = new ArrayList<>();
//        bloodGroups.add(createBloodGroup());
//        return bloodGroups;
//    }
//
//    private BloodGroup createBloodGroup() {
//        BloodGroup bloodGroup = new BloodGroup();
//        bloodGroup.setId(1L);
//        bloodGroup.setGroup("0-");
//
//        return bloodGroup;
//    }
//}
