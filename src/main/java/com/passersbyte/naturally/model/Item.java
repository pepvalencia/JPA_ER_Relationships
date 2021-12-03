package com.passersbyte.naturally.model;

import java.util.Objects;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Item {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@JsonBackReference
	@ManyToOne(targetEntity=Cart.class, fetch = FetchType.LAZY)
	@JoinColumn(name="cart_id", nullable=false)//Optional
    private Cart cart;
	
	private float discount;
	
	private float sellValue;  // product value - discount
	
	
	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer itemId) {
		this.id = itemId;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public float getSellValue() {
		return sellValue;
	}

	public void setSellValue(float sellValue) {
		this.sellValue = sellValue;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cart)) {
            return false;
        }
        Item item = (Item) o;
        return getId() == item.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }



}
