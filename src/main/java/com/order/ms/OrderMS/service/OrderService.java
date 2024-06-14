package com.order.ms.OrderMS.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.order.ms.OrderMS.model.ProductOrder;
import com.order.ms.OrderMS.model.dto.InventoryResponse;
import com.order.ms.OrderMS.model.dto.OrderRequest;
import com.order.ms.OrderMS.model.dto.OrderResponse;
import com.order.ms.OrderMS.repository.OrderRepository;

@Service
public class OrderService {

	private final Logger log = LoggerFactory.getLogger(OrderService.class);
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	public WebClient.Builder webClient;
	
	
	public String placeOrder(OrderRequest orderRequest) throws IllegalAccessException {
		
		String msg = null;
		
		ProductOrder order = new ProductOrder();
		order.setOrderNumber(UUID.randomUUID().toString());
		order.setPrice(orderRequest.getPrice());
		order.setQuantity(orderRequest.getQuantity());
		
		List<String> itemCodeList = new ArrayList<>();
		itemCodeList.add("iPhone14");
		itemCodeList.add("iPhone14-red");
		
		
		//call Inventary service to check if the product is available.
		InventoryResponse[] resultList = webClient.build().get().uri("http://inventory-ms/api/inventory", 
				uriParam -> uriParam.queryParam("itemCode", itemCodeList).build())
				.retrieve().bodyToMono(InventoryResponse[].class).block();
		
		Boolean result = Arrays.stream(resultList).allMatch(inventory-> inventory.getItemAvailableFlag());
		
		if(result) {
			orderRepository.save(order);
			msg = "Order placed successfully!!!";
			log.info(msg);
		}
		else {
			throw new IllegalAccessException("Product is not available...");
		}
		
		return msg;
	}
	
	public List<OrderResponse> getAllOrder(){
		List<ProductOrder> orderList = orderRepository.findAll();
		
		return orderList.stream().map(this:: mapToOrderResponse).toList();
	}
	
	private OrderResponse mapToOrderResponse(ProductOrder order) {
		OrderResponse response = new OrderResponse();
		response.setId(order.getId());
		response.setOrderNumber(order.getOrderNumber());
		response.setPrice(order.getPrice());
		response.setQuantity(order.getQuantity());
		
		return response;
		
	}
}
