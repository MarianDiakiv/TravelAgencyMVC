package com.marian.domain;

import java.math.BigDecimal;

public class RoomRequest {
    private int number;
    private BigDecimal price;
    private String typeRoom;

    public RoomRequest() {
    }



    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTypeRoom() {
        return typeRoom;
    }

    public void setTypeRoom(String typeRoom) {
        this.typeRoom = typeRoom;
    }
}
