package org.bel.models;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bel.apiHelper.StatusEnumOrder;

import java.time.OffsetDateTime;

public class Order {

    private Integer id;
    private Long petId;
    private Integer quantity;
    private String shipDate;
    private StatusEnumOrder status;
    private Boolean complete;

    public Order() {
    }

    public Order(Pet pet) {
        id = Integer.valueOf(RandomStringUtils.randomNumeric(4));
        petId = pet.getId();
        quantity = 1;
        shipDate = OffsetDateTime.now().toString();
        status = StatusEnumOrder.placed;
        complete = true;
    }

    public Order(Long petIdValue) {
        id = Integer.valueOf(RandomStringUtils.randomNumeric(4));
        petId = petIdValue;
        quantity = 1;
        shipDate = OffsetDateTime.now().toString();
        status = StatusEnumOrder.placed;
        complete = true;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public StatusEnumOrder getStatus() {
        return status;
    }

    public void setStatus(StatusEnumOrder status) {
        this.status = status;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("petId", petId).append("quantity", quantity).append("shipDate", shipDate).append("status", status).append("complete", complete).toString();
    }
}