package com.order.ms.OrderMS.model.dto;

public class InventoryResponse {

	private String itemCode;
    private Boolean itemAvailableFlag;
    
    
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public Boolean getItemAvailableFlag() {
		return itemAvailableFlag;
	}
	public void setItemAvailableFlag(Boolean itemAvailableFlag) {
		this.itemAvailableFlag = itemAvailableFlag;
	}
    
}
