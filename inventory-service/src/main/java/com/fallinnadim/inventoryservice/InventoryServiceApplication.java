package com.fallinnadim.inventoryservice;

import com.fallinnadim.inventoryservice.model.Inventory;
import com.fallinnadim.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
		return args -> {
			Inventory inventory = new Inventory();
			inventory.setSkuCode("iphone 14 pro");
			inventory.setQuantity(0);

			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("iphone 15 pro");
			inventory1.setQuantity(99);
			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory1);
		};
	}

}
