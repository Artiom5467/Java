package com.example.demomaven.controller;

import com.example.demomaven.config.TestSecurityConfig;
import com.example.demomaven.model.Component;
import com.example.demomaven.service.PowerCalculationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Import;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PowerCalculatorController.class)
@Import(TestSecurityConfig.class)
public class PowerCalculatorControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PowerCalculationService powerCalculationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllComponents() throws Exception {
        Component component = new Component();
        component.setId(1L);
        component.setName("Test CPU");
        component.setType("CPU");
        component.setPowerConsumption(65);
        component.setDescription("Test Description");

        List<Component> components = Arrays.asList(component);

        when(powerCalculationService.getAllComponents()).thenReturn(components);

        mockMvc.perform(get("/api/power-calculator/components")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Test CPU"))
                .andExpect(jsonPath("$[0].type").value("CPU"));
    }

    @Test
    public void testCreateComponent() throws Exception {
        Component component = new Component();
        component.setName("New CPU");
        component.setType("CPU");
        component.setPowerConsumption(95);
        component.setDescription("New CPU Description");

        when(powerCalculationService.saveComponent(any(Component.class))).thenReturn(component);

        mockMvc.perform(post("/api/power-calculator/components")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(component)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New CPU"))
                .andExpect(jsonPath("$.type").value("CPU"));
    }

    @Test
    public void testCalculatePowerRequirements() throws Exception {
        List<Long> componentIds = Arrays.asList(1L, 2L);
        when(powerCalculationService.calculateTotalPowerConsumption(componentIds)).thenReturn(150);
        when(powerCalculationService.calculateRecommendedPowerSupply(componentIds)).thenReturn(300);

        mockMvc.perform(post("/api/power-calculator/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(componentIds)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPowerConsumption").value(150))
                .andExpect(jsonPath("$.recommendedPowerSupply").value(300));
    }

    @Test
    public void testCalculatePowerRequirementsWithEmptyList() throws Exception {
        List<Long> emptyList = Collections.emptyList();
        when(powerCalculationService.calculateTotalPowerConsumption(emptyList)).thenReturn(0);
        when(powerCalculationService.calculateRecommendedPowerSupply(emptyList)).thenReturn(0);

        mockMvc.perform(post("/api/power-calculator/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(emptyList)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPowerConsumption").value(0))
                .andExpect(jsonPath("$.recommendedPowerSupply").value(0));
    }

    @Test
    public void testGetComponentsByType() throws Exception {
        Component component = new Component();
        component.setId(1L);
        component.setName("Test GPU");
        component.setType("GPU");
        component.setPowerConsumption(250);
        component.setDescription("Test GPU Description");

        List<Component> components = Arrays.asList(component);

        when(powerCalculationService.getComponentsByType("GPU")).thenReturn(components);

        mockMvc.perform(get("/api/power-calculator/components/type/GPU")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Test GPU"))
                .andExpect(jsonPath("$[0].type").value("GPU"));
    }
}