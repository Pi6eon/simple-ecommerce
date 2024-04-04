package com.fallinnadim.orderservice.repository;

import com.fallinnadim.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
