package com.example.demomaven.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ComponentTest {

    @Test
    public void testComponentCreation() {
        // Тестовий сценарій: Перевірка створення компонента
        Component component = new Component("AMD Ryzen 5 3600", "CPU", 95, "6-core processor");
        
        assertNotNull(component);
        assertEquals("AMD Ryzen 5 3600", component.getName());
        assertEquals("CPU", component.getType());
        assertEquals(95, component.getPowerConsumption());
        assertEquals("6-core processor", component.getDescription());
        assertNull(component.getId()); // ID має бути null до збереження в БД
    }

    @Test
    public void testComponentEquality() {
        // Тестовий сценарій: Перевірка рівності компонентів
        Component component1 = new Component("NVIDIA RTX 3070", "GPU", 220, "Graphics card");
        component1.setId(1L);
        Component component2 = new Component("NVIDIA RTX 3070", "GPU", 220, "Graphics card");
        component2.setId(1L);
        Component component3 = new Component("NVIDIA RTX 3070", "GPU", 220, "Graphics card");
        component3.setId(2L);
        
        assertEquals(component1, component2); // Компоненти з однаковими полями мають бути рівні
        assertNotEquals(component1, component3); // Різні ID - різні компоненти
    }

    @Test
    public void testComponentToString() {
        // Тестовий сценарій: Перевірка методу toString()
        Component component = new Component("Intel Core i7", "CPU", 125, "8-core processor");
        component.setId(1L);
        String toString = component.toString();
        
        assertTrue(toString.contains("id=1"));
        assertTrue(toString.contains("name=Intel Core i7"));
        assertTrue(toString.contains("type=CPU"));
        assertTrue(toString.contains("powerConsumption=125"));
        assertTrue(toString.contains("description=8-core processor"));
    }
}