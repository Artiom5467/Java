package com.example.demomaven.service;

import com.example.demomaven.model.Component;
import com.example.demomaven.repository.ComponentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class PowerCalculationServiceIntegrationTest {

    @Autowired
    private PowerCalculationService powerCalculationService;

    @MockBean
    private ComponentRepository componentRepository;

    private Component cpuComponent;
    private Component gpuComponent;

    @BeforeEach
    public void setup() {
        // Підготовка тестових даних
        cpuComponent = new Component("AMD Ryzen 5 3600", "CPU", 95, "6-core processor");
        cpuComponent.setId(1L);
        gpuComponent = new Component("NVIDIA RTX 3070", "GPU", 150, "Graphics card");
        gpuComponent.setId(2L);
    }

    @Test
    public void testGetAllComponents() {
        // Тестовий сценарій 1: Перевірка отримання списку всіх компонентів
        List<Component> components = Arrays.asList(cpuComponent, gpuComponent);
        when(componentRepository.findAll()).thenReturn(components);

        List<Component> result = powerCalculationService.getAllComponents();

        assertEquals(2, result.size());
        assertEquals("AMD Ryzen 5 3600", result.get(0).getName());
        assertEquals("NVIDIA RTX 3070", result.get(1).getName());
    }

    @Test
    public void testGetComponentsByType() {
        // Тестовий сценарій 2: Перевірка фільтрації компонентів за типом
        List<Component> cpuComponents = Collections.singletonList(cpuComponent);
        when(componentRepository.findByType("CPU")).thenReturn(cpuComponents);

        List<Component> result = powerCalculationService.getComponentsByType("CPU");

        assertEquals(1, result.size());
        assertEquals("CPU", result.get(0).getType());
        assertEquals("AMD Ryzen 5 3600", result.get(0).getName());
    }

    @Test
    public void testCalculateTotalPowerConsumption() {
        // Тестовий сценарій 3: Перевірка розрахунку загального споживання енергії
        List<Component> selectedComponents = Arrays.asList(cpuComponent, gpuComponent);
        List<Long> componentIds = Arrays.asList(1L, 2L);

        when(componentRepository.findAllById(componentIds)).thenReturn(selectedComponents);

        int totalPower = powerCalculationService.calculateTotalPowerConsumption(componentIds);

        assertEquals(245, totalPower); // 95 + 150 = 245
    }

    @Test
    public void testCalculateRecommendedPowerSupply() {
        // Тестовий сценарій 3: Перевірка розрахунку рекомендованої потужності блоку живлення
        List<Component> selectedComponents = Arrays.asList(cpuComponent, gpuComponent);
        List<Long> componentIds = Arrays.asList(1L, 2L);

        when(componentRepository.findAllById(componentIds)).thenReturn(selectedComponents);

        int recommendedPower = powerCalculationService.calculateRecommendedPowerSupply(componentIds);

        assertEquals(294, recommendedPower); // (95 + 150) * 1.2 = 294
    }

    @Test
    public void testSaveComponent() {
        // Тестовий сценарій 4: Перевірка додавання нового компонента
        Component newComponent = new Component("NVIDIA RTX 4080", "GPU", 320, "High-end graphics card");
        Component savedComponent = new Component("NVIDIA RTX 4080", "GPU", 320, "High-end graphics card");
        savedComponent.setId(3L);

        when(componentRepository.save(any(Component.class))).thenReturn(savedComponent);

        Component result = powerCalculationService.saveComponent(newComponent);

        assertNotNull(result);
        assertEquals(3L, result.getId());
        assertEquals("NVIDIA RTX 4080", result.getName());
        assertEquals("GPU", result.getType());
        assertEquals(320, result.getPowerConsumption());
    }

    @Test
    public void testCalculatePowerWithEmptyList() {
        // Тестовий сценарій 5: Перевірка обробки некоректних даних при розрахунку
        List<Long> emptyList = Collections.emptyList();
        when(componentRepository.findAllById(emptyList)).thenReturn(Collections.emptyList());

        int totalPower = powerCalculationService.calculateTotalPowerConsumption(emptyList);
        int recommendedPower = powerCalculationService.calculateRecommendedPowerSupply(emptyList);

        assertEquals(0, totalPower);
        assertEquals(0, recommendedPower);
    }
}