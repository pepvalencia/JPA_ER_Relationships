package com.passersbyte.naturally.model;

import java.util.Objects;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Item {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private float discount;
	
	private float sellValue;  // product value - discount

	@JsonBackReference
	@ManyToOne(targetEntity=Cart.class, fetch = FetchType.LAZY) 
	@JoinColumn(name="cart_id", nullable=false) 
    private Cart cart;
	
	@OneToOne(targetEntity=Product.class) // No cascade because products can exists without items
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable=true) // not null if an item must have a product linked
    private Product product;
	
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

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
