package com.order.ms.OrderMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.order.ms.OrderMS.model.dto.OrderRequest;
import com.order.ms.OrderMS.model.dto.OrderResponse;
import com.order.ms.OrderMS.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public String placeOrder(@RequestBody OrderRequest orderRequest) throws IllegalAccessException {
		return orderService.placeOrder(orderRequest);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<OrderResponse> getOrderList(){
		
		return orderService.getAllOrder();
	}
	
	
}
