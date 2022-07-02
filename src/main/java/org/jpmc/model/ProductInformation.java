package org.jpmc.model;

import org.jpmc.enums.OperationEnum;
import org.jpmc.enums.PriceUnitEnum;
import org.jpmc.enums.StatusEnum;

public class ProductInformation {
    private String productType;
    private Double price;
    private PriceUnitEnum priceUnitEnum;
    private Integer quantity;
    private OperationEnum adjustmentOperation;
    private StatusEnum messageStatus = StatusEnum.PENDING;

    public  ProductInformation(){ }
    public ProductInformation(String productType, Double price,PriceUnitEnum priceUnitEnum, Integer quantity, OperationEnum adjustmentOperation) {
        this.productType = productType;
        this.price = price;
        this.priceUnitEnum = priceUnitEnum;
        this.quantity = quantity;
        this.adjustmentOperation = adjustmentOperation;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Double getPrice() {
        return price;
    }

    public ProductInformation setPrice(Double price) {
        this.price = price;
        return this;
    }

    public PriceUnitEnum getPriceUnitEnum() {
        return priceUnitEnum;
    }

    public void setPriceUnitEnum(PriceUnitEnum priceUnitEnum) {
        this.priceUnitEnum = priceUnitEnum;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public OperationEnum getAdjustmentOperation() {
        return adjustmentOperation;
    }

    public void setAdjustmentOperation(OperationEnum adjustmentOperation) {
        this.adjustmentOperation = adjustmentOperation;
    }

    public StatusEnum getMessageStatus() {
        return messageStatus;
    }

    public ProductInformation setMessageStatus(StatusEnum messageStatus) {
        this.messageStatus = messageStatus;
        return this;
    }
}
