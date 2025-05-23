package com.example.demomaven.repository;

import com.example.demomaven.model.Component;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("test")
public class ComponentRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ComponentRepository componentRepository;

    @Test
    public void testFindByType() {
        // Тестовий сценарій 2: Перевірка фільтрації компонентів за типом
        
        // Підготовка тестових даних
        Component cpu = new Component("AMD Ryzen 5 3600", "CPU", 95, "6-core processor");
        Component gpu = new Component("NVIDIA RTX 3070", "GPU", 150, "Graphics card");
        Component ram = new Component("Corsair Vengeance", "RAM", 10, "16GB DDR4");
        
        entityManager.persist(cpu);
        entityManager.persist(gpu);
        entityManager.persist(ram);
        entityManager.flush();
        
        // Виконання тесту
        List<Component> cpuComponents = componentRepository.findByType("CPU");
        
        // Перевірка результатів
        assertEquals(1, cpuComponents.size());
        assertEquals("CPU", cpuComponents.get(0).getType());
        assertEquals("AMD Ryzen 5 3600", cpuComponents.get(0).getName());
    }
    
    @Test
    public void testSaveComponent() {
        // Тестовий сценарій 4: Перевірка додавання нового компонента
        
        // Підготовка тестових даних
        Component newComponent = new Component("NVIDIA RTX 4080", "GPU", 320, "High-end graphics card");
        
        // Виконання тесту
        Component savedComponent = componentRepository.save(newComponent);
        
        // Перевірка результатів
        assertNotNull(savedComponent.getId());
        assertEquals("NVIDIA RTX 4080", savedComponent.getName());
        assertEquals("GPU", savedComponent.getType());
        assertEquals(320, savedComponent.getPowerConsumption());
    }
    
    @Test
    public void testFindAll() {
        // Тестовий сценарій 1: Перевірка отримання списку всіх компонентів
        
        // Підготовка тестових даних
        Component cpu = new Component("Intel Core i7", "CPU", 125, "8-core processor");
        Component gpu = new Component("AMD Radeon RX 6800", "GPU", 250, "Graphics card");
        
        entityManager.persist(cpu);
        entityManager.persist(gpu);
        entityManager.flush();
        
        // Виконання тесту
        List<Component> components = componentRepository.findAll();
        
        // Перевірка результатів
        assertEquals(2, components.size());
    }
    
    @Test
    public void testFindAllById() {
        // Тестовий сценарій 3: Перевірка пошуку компонентів за ID для розрахунку потужності
        
        // Підготовка тестових даних
        Component cpu = new Component("Intel Core i5", "CPU", 65, "6-core processor");
        Component gpu = new Component("NVIDIA GTX 1660", "GPU", 120, "Graphics card");
        
        entityManager.persist(cpu);
        entityManager.persist(gpu);
        entityManager.flush();
        
        Long cpuId = cpu.getId();
        Long gpuId = gpu.getId();
        
        // Виконання тесту
        List<Component> components = componentRepository.findAllById(List.of(cpuId, gpuId));
        
        // Перевірка результатів
        assertEquals(2, components.size());
        assertEquals(65 + 120, components.stream().mapToInt(Component::getPowerConsumption).sum());
    }
}