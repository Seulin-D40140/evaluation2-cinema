package fr.fms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.fms.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
