package com.example.demomaven.config;

import com.example.demomaven.model.Component;
import com.example.demomaven.model.CPU;
import com.example.demomaven.model.GPU;
import com.example.demomaven.repository.ComponentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(ComponentRepository componentRepository) {
        return args -> {
            // Clear existing data
            componentRepository.deleteAll();

            // CPUs - AMD Ryzen
            componentRepository.save(new CPU("AMD Ryzen 3 1300X", 65, "4-core, 4-thread desktop processor", 4, 4, 3.5, 3.7, "AM4", 32, 7));
            componentRepository.save(new CPU("AMD Ryzen 5 1600X", 95, "6-core, 12-thread desktop processor", 6, 12, 3.6, 4.0, "AM4", 32, 7));
            componentRepository.save(new CPU("AMD Ryzen 7 1800X", 95, "8-core, 16-thread desktop processor", 8, 16, 3.6, 4.0, "AM4", 32, 7));
            componentRepository.save(new CPU("AMD Ryzen 5 2600", 65, "6-core, 12-thread desktop processor", 6, 12, 3.4, 3.9, "AM4", 32, 12));
            componentRepository.save(new CPU("AMD Ryzen 7 2700X", 105, "8-core, 16-thread desktop processor", 8, 16, 3.7, 4.3, "AM4", 32, 12));
            componentRepository.save(new CPU("AMD Ryzen 5 3600", 65, "6-core, 12-thread desktop processor", 6, 12, 3.6, 4.2, "AM4", 32, 7));
            componentRepository.save(new CPU("AMD Ryzen 7 3700X", 65, "8-core, 16-thread desktop processor", 8, 16, 3.6, 4.4, "AM4", 32, 7));
            componentRepository.save(new CPU("AMD Ryzen 9 3900X", 105, "12-core, 24-thread desktop processor", 12, 24, 3.8, 4.6, "AM4", 64, 7));
            componentRepository.save(new CPU("AMD Ryzen 5 5600X", 65, "6-core, 12-thread desktop processor", 6, 12, 3.7, 4.6, "AM4", 32, 7));
            componentRepository.save(new CPU("AMD Ryzen 7 5800X", 105, "8-core, 16-thread desktop processor", 8, 16, 3.8, 4.7, "AM4", 32, 7));
            componentRepository.save(new CPU("AMD Ryzen 9 5950X", 105, "16-core, 32-thread desktop processor", 16, 32, 3.4, 4.9, "AM4", 64, 7));
            componentRepository.save(new CPU("AMD Ryzen 5 7600X", 105, "6-core, 12-thread desktop processor", 6, 12, 4.7, 5.3, "AM5", 32, 5));
            componentRepository.save(new CPU("AMD Ryzen 7 7700X", 105, "8-core, 16-thread desktop processor", 8, 16, 4.5, 5.4, "AM5", 32, 5));
            componentRepository.save(new CPU("AMD Ryzen 9 7950X", 170, "16-core, 32-thread desktop processor", 16, 32, 4.5, 5.7, "AM5", 64, 5));

            // CPUs - Intel
            componentRepository.save(new Component("Intel Core i5-8400", "CPU", 65, "6-core, 6-thread desktop processor"));
            componentRepository.save(new Component("Intel Core i7-8700K", "CPU", 95, "6-core, 12-thread desktop processor"));
            componentRepository.save(new Component("Intel Core i5-9600K", "CPU", 95, "6-core, 6-thread desktop processor"));
            componentRepository.save(new Component("Intel Core i7-9700K", "CPU", 95, "8-core, 8-thread desktop processor"));
            componentRepository.save(new Component("Intel Core i9-9900K", "CPU", 95, "8-core, 16-thread desktop processor"));
            componentRepository.save(new Component("Intel Core i5-10600K", "CPU", 125, "6-core, 12-thread desktop processor"));
            componentRepository.save(new Component("Intel Core i7-10700K", "CPU", 125, "8-core, 16-thread desktop processor"));
            componentRepository.save(new Component("Intel Core i9-10900K", "CPU", 125, "10-core, 20-thread desktop processor"));
            componentRepository.save(new Component("Intel Core i5-11600K", "CPU", 125, "6-core, 12-thread desktop processor"));
            componentRepository.save(new Component("Intel Core i7-11700K", "CPU", 125, "8-core, 16-thread desktop processor"));
            componentRepository.save(new Component("Intel Core i9-11900K", "CPU", 125, "8-core, 16-thread desktop processor"));
            componentRepository.save(new Component("Intel Core i5-12600K", "CPU", 125, "10-core, 16-thread desktop processor"));
            componentRepository.save(new Component("Intel Core i7-12700K", "CPU", 125, "12-core, 20-thread desktop processor"));
            componentRepository.save(new Component("Intel Core i9-12900K", "CPU", 125, "16-core, 24-thread desktop processor"));
            componentRepository.save(new Component("Intel Core i5-13600K", "CPU", 125, "14-core, 20-thread desktop processor"));
            componentRepository.save(new Component("Intel Core i7-13700K", "CPU", 125, "16-core, 24-thread desktop processor"));
            componentRepository.save(new Component("Intel Core i9-13900K", "CPU", 253, "24-core, 32-thread desktop processor"));

            // GPUs - AMD
            componentRepository.save(new GPU("AMD Radeon RX 480", 150, "8GB GDDR5 graphics card", 8, "GDDR5", 0, 0, "", 256));
            componentRepository.save(new GPU("AMD Radeon RX 580", 185, "8GB GDDR5 graphics card", 8, "GDDR5", 1500, 1750, "PCIe 3.0", 240));
            componentRepository.save(new GPU("AMD Radeon RX 590", 225, "8GB GDDR5 graphics card", 8, "GDDR5", 0, 0, "", 256));
            componentRepository.save(new GPU("AMD Radeon RX 5500 XT", 130, "8GB GDDR6 graphics card", 8, "GDDR6", 0, 0, "", 256));
            componentRepository.save(new GPU("AMD Radeon RX 5600 XT", 150, "6GB GDDR6 graphics card", 8, "GDDR6", 0, 0, "", 256));
            componentRepository.save(new GPU("AMD Radeon RX 5700 XT", 225, "8GB GDDR6 graphics card", 8, "GDDR6", 0, 0, "", 256));
            componentRepository.save(new GPU("AMD Radeon RX 6600", 132, "8GB GDDR6 graphics card", 8, "GDDR6", 0, 0, "", 256));
            componentRepository.save(new GPU("AMD Radeon RX 6600 XT", 160, "8GB GDDR6 graphics card", 8, "GDDR6", 0, 0, "", 256));
            componentRepository.save(new GPU("AMD Radeon RX 6700 XT", 230, "12GB GDDR6 graphics card", 8, "GDDR6", 0, 0, "", 256));
            componentRepository.save(new GPU("AMD Radeon RX 6800", 250, "16GB GDDR6 graphics card", 8, "GDDR6", 0, 0, "", 256));
            componentRepository.save(new GPU("AMD Radeon RX 6800 XT", 300, "16GB GDDR6 graphics card", 8, "GDDR6", 0, 0, "", 256));
            componentRepository.save(new GPU("AMD Radeon RX 6900 XT", 300, "16GB GDDR6 graphics card", 8, "GDDR6", 0, 0, "", 256));
            componentRepository.save(new GPU("AMD Radeon RX 7600", 165, "8GB GDDR6 graphics card", 8, "GDDR6", 0, 0, "", 256));
            componentRepository.save(new GPU("AMD Radeon RX 7700 XT", 245, "12GB GDDR6 graphics card", 8, "GDDR6", 0, 0, "", 256));
            componentRepository.save(new GPU("AMD Radeon RX 7800 XT", 263, "16GB GDDR6 graphics card", 8, "GDDR6", 0, 0, "", 256));
            componentRepository.save(new GPU("AMD Radeon RX 7900 XT", 315, "20GB GDDR6 graphics card", 8, "GDDR6", 0, 0, "", 256));
            componentRepository.save(new GPU("AMD Radeon RX 7900 XTX", 355, "24GB GDDR6 graphics card", 8, "GDDR6", 0, 0, "", 256));

            // GPUs - NVIDIA
            componentRepository.save(new Component("NVIDIA GeForce GTX 1060", "GPU", 120, "6GB GDDR5 graphics card"));
            componentRepository.save(new Component("NVIDIA GeForce GTX 1070", "GPU", 150, "8GB GDDR5 graphics card"));
            componentRepository.save(new Component("NVIDIA GeForce GTX 1070 Ti", "GPU", 180, "8GB GDDR5 graphics card"));
            componentRepository.save(new Component("NVIDIA GeForce GTX 1080", "GPU", 180, "8GB GDDR5X graphics card"));
            componentRepository.save(new Component("NVIDIA GeForce GTX 1080 Ti", "GPU", 250, "11GB GDDR5X graphics card"));
            componentRepository.save(new Component("NVIDIA GeForce RTX 2060", "GPU", 160, "6GB GDDR6 graphics card"));
            componentRepository.save(new Component("NVIDIA GeForce RTX 2070", "GPU", 175, "8GB GDDR6 graphics card"));
            componentRepository.save(new Component("NVIDIA GeForce RTX 2080", "GPU", 215, "8GB GDDR6 graphics card"));
            componentRepository.save(new Component("NVIDIA GeForce RTX 2080 Ti", "GPU", 250, "11GB GDDR6 graphics card"));
            componentRepository.save(new Component("NVIDIA GeForce RTX 3060", "GPU", 170, "12GB GDDR6 graphics card"));
            componentRepository.save(new Component("NVIDIA GeForce RTX 3070", "GPU", 220, "8GB GDDR6 graphics card"));
            componentRepository.save(new Component("NVIDIA GeForce RTX 3080", "GPU", 320, "10GB GDDR6X graphics card"));
            componentRepository.save(new Component("NVIDIA GeForce RTX 3090", "GPU", 350, "24GB GDDR6X graphics card"));
            componentRepository.save(new Component("NVIDIA GeForce RTX 4060", "GPU", 115, "8GB GDDR6 graphics card"));
            componentRepository.save(new Component("NVIDIA GeForce RTX 4070", "GPU", 200, "12GB GDDR6X graphics card"));
            componentRepository.save(new Component("NVIDIA GeForce RTX 4080", "GPU", 320, "16GB GDDR6X graphics card"));
            componentRepository.save(new Component("NVIDIA GeForce RTX 4090", "GPU", 450, "24GB GDDR6X graphics card"));

            // RAM
            componentRepository.save(new Component("Corsair Vengeance LPX 16GB", "RAM", 10, "2x8GB DDR4-3200MHz"));
            componentRepository.save(new Component("G.Skill Trident Z RGB 32GB", "RAM", 15, "2x16GB DDR4-3600MHz"));

            // Storage
            componentRepository.save(new Component("Samsung 970 EVO Plus 1TB", "Storage", 8, "NVMe M.2 SSD"));
            componentRepository.save(new Component("Western Digital Blue 2TB", "Storage", 6, "7200RPM HDD"));

            // Motherboards
            componentRepository.save(new Component("ASUS ROG Strix Z690-E Gaming", "Motherboard", 45, "Intel LGA 1700 ATX gaming motherboard"));
            componentRepository.save(new Component("MSI MPG B550 Gaming Edge WiFi", "Motherboard", 35, "AMD AM4 ATX gaming motherboard"));

            // Cooling
            componentRepository.save(new Component("NZXT Kraken X63", "Cooling", 30, "280mm AIO liquid CPU cooler"));
            componentRepository.save(new Component("Noctua NH-D15", "Cooling", 5, "Dual-tower CPU air cooler"));

            // Case Fans
            componentRepository.save(new Component("Corsair LL120 RGB (3-pack)", "Fan", 15, "120mm RGB case fans"));
            componentRepository.save(new Component("be quiet! Silent Wings 4", "Fan", 5, "140mm PWM case fan"));

            // Optical Drives
            componentRepository.save(new Component("ASUS DRW-24B1ST", "Optical", 25, "DVD-RW optical drive"));

            // Other peripherals
            componentRepository.save(new Component("RGB LED Strip", "Peripheral", 5, "Addressable RGB LED strip"));
            componentRepository.save(new Component("Internal Card Reader", "Peripheral", 3, "USB 3.0 internal card reader"));
        };
    }
}
