package com.blood_stock_server.business.web.controller;

import com.blood_stock_server.business.service.impl.BloodInfoServiceImpl;
import com.blood_stock_server.model.BloodInfo;
import com.blood_stock_server.web.controller.BloodInfoController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BloodInfoController.class)
class BloodInfoControllerTest {
    private final String BLOOD_INFO_URI = "/api/blood/info/";
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BloodInfoServiceImpl service;
    private List<BloodInfo> bloodInfoList;
    private BloodInfo bloodInfo;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    void init() {
        bloodInfoList = createListOfBloodInfo();
        bloodInfo = createBloodInfo();
    }

    @Test
    void saveBloodInfo_Success() throws Exception {
        when(service.saveBloodInfo(bloodInfo)).thenReturn(bloodInfo);
        mockMvc.perform(MockMvcRequestBuilders
                        .post(BLOOD_INFO_URI)
                        .content(asJsonString(bloodInfo))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bloodGroupId").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bloodStorageId").value(2L));
        verify(service, times(1)).saveBloodInfo(bloodInfo);
    }

    @Test
    void findAllBloodInfoByBloodGroup_Success() throws Exception {
        String bloodGroup = "0-";
        List<BloodInfo> infoList = Arrays.asList(new BloodInfo(), new BloodInfo());
        when(service.findAllBloodInfoByBloodGroup(anyString())).thenReturn(infoList);
        mockMvc.perform(get(BLOOD_INFO_URI).param("bloodGroup", bloodGroup))
                .andExpect(status().isOk());
    }

    private List<BloodInfo> createListOfBloodInfo() {
        List<BloodInfo> bloodInfo = new ArrayList<>();
        bloodInfo.add(createBloodInfo());
        return bloodInfo;
    }

    private BloodInfo createBloodInfo() {
        BloodInfo bloodInfo = new BloodInfo();
        bloodInfo.setId(1L);
        bloodInfo.setBloodGroupId(1L);
        bloodInfo.setBloodStorageId(2L);

        return bloodInfo;
    }
}
