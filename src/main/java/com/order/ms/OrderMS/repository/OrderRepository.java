package com.order.ms.OrderMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.ms.OrderMS.model.ProductOrder;

public interface OrderRepository extends JpaRepository<ProductOrder, Long>{

}
