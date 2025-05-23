package com.example.demomaven.service;

import com.example.demomaven.model.Component;
import com.example.demomaven.repository.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Optional;

@Service
public class PowerCalculationService {

    private final ComponentRepository componentRepository;
    
    // Потужнвсть блоку живлення (+20%)
    private static final double SAFETY_MARGIN = 1.2;
    
    @Autowired
    public PowerCalculationService(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }
    
    /**
     * Розрахувати загальне споживання енергії вибраних компонентів
     * @param componentIds Список ID компонентів для розрахунку
     * @return Загальне споживання енергії у ватах
     */
    public int calculateTotalPowerConsumption(List<Long> componentIds) {
        return componentRepository.findAllById(componentIds).stream()
                .mapToInt(Component::getPowerConsumption)
                .sum();
    }
    
    /**
     * Розрахувати рекомендовану потужність блоку живлення на основі компонентів
     * @param componentIds Список ID компонентів для розрахунку
     * @return Рекомендована потужність блоку живлення із запасом надійності
     */
    public int calculateRecommendedPowerSupply(List<Long> componentIds) {
        int totalPower = calculateTotalPowerConsumption(componentIds);
        return (int) Math.ceil(totalPower * SAFETY_MARGIN);
    }
    
    /**
     * Отримати всі доступні компоненти
     * @return Список всіх компонентів
     */
    public List<Component> getAllComponents() {
        return componentRepository.findAll();
    }
    
    /**
     * Отримати компоненти за типом
     * @param type Тип компонента (CPU, GPU, тощо)
     * @return Список компонентів вказаного типу
     */
    public List<Component> getComponentsByType(String type) {
        return componentRepository.findByType(type);
    }
    
    /**
     * Зберегти новий компонент
     * @param component Компонент для збереження
     * @return Збережений компонент
     */
    public Component saveComponent(Component component) {
        return componentRepository.save(component);
    }

    /**
     * Перевірити чи існує компонент за ID
     * @param id ID компонента
     * @return true якщо компонент існує, false якщо ні
     */
    public boolean existsById(Long id) {
        return componentRepository.existsById(id);
    }

    /**
     * Отримати компонент за ID
     * @param id ID компонента
     * @return Optional з компонентом, якщо знайдено
     */
    public Optional<Component> getComponentById(Long id) {
        return componentRepository.findById(id);
    }

    /**
     * Видалити компонент за ID
     * @param id ID компонента для видалення
     */
    public void deleteComponent(Long id) {
        componentRepository.deleteById(id);
    }

    /**
     * Знайти компоненти за діапазоном потужності
     * @param minPower Мінімальна потужність
     * @param maxPower Максимальна потужність
     * @return Список компонентів у заданому діапазоні потужності
     */
    public List<Component> findComponentsByPowerRange(int minPower, int maxPower) {
        return componentRepository.findAll().stream()
                .filter(component -> {
                    int power = component.getPowerConsumption();
                    return power >= minPower && power <= maxPower;
                })
                .collect(java.util.stream.Collectors.toList());
    }

    /**
     * Отримати статистику використання компонентів
     * @return Мапа зі статистичними даними
     */
    public Map<String, Object> getUsageStatistics() {
        List<Component> allComponents = componentRepository.findAll();
        Map<String, Object> statistics = new HashMap<>();
        
        // Загальна кількість компонентів
        statistics.put("totalComponents", allComponents.size());
        
        // Статистика по типам компонентів
        Map<String, Long> componentsByType = allComponents.stream()
                .collect(java.util.stream.Collectors.groupingBy(
                    Component::getType,
                    java.util.stream.Collectors.counting()
                ));
        statistics.put("componentsByType", componentsByType);
        
        // Середнє споживання енергії
        double avgPowerConsumption = allComponents.stream()
                .mapToInt(Component::getPowerConsumption)
                .average()
                .orElse(0.0);
        statistics.put("averagePowerConsumption", avgPowerConsumption);
        
        return statistics;
    }

    /**
     * Отримати рекомендації щодо оптимізації енергоспоживання
     * @param configurationId ID конфігурації (опціонально)
     * @return Мапа з рекомендаціями
     */
    public Map<String, Object> getOptimizationRecommendations(Long configurationId) {
        Map<String, Object> recommendations = new HashMap<>();
        List<Component> components;
        
        if (configurationId != null) {
            // Якщо вказано ID конфігурації, отримуємо компоненти цієї конфігурації
            components = componentRepository.findAllById(java.util.Collections.singletonList(configurationId));
        } else {
            // Інакше аналізуємо всі компоненти
            components = componentRepository.findAll();
        }
        
        // Аналіз високого енергоспоживання
        List<Component> highPowerComponents = components.stream()
                .filter(c -> c.getPowerConsumption() > 100) // Поріг високого споживання
                .collect(java.util.stream.Collectors.toList());
        
        recommendations.put("highPowerComponents", highPowerComponents);
        
        // Загальні рекомендації
        List<String> generalRecommendations = new ArrayList<>();
        generalRecommendations.add("Розгляньте використання енергоефективних компонентів");
        generalRecommendations.add("Перевірте налаштування управління живленням");
        generalRecommendations.add("Оптимізуйте охолодження для кращої енергоефективності");
        
        recommendations.put("generalRecommendations", generalRecommendations);
        
        return recommendations;
    }
}