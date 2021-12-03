package com.passersbyte.naturally.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private float total;
	private String name;
	
	@JsonManagedReference
	@OneToMany(orphanRemoval=true,targetEntity=Item.class,cascade = CascadeType.ALL , fetch = FetchType.LAZY, mappedBy = "cart")
	private List<Item> items = new ArrayList<>();
	
	public void addItem(Item item){
		   items.add(item);
		   item.setCart(this);
		}

	public void removeItem(Item item){
		items.remove(item);
		item.setCart(null);
	}
	 
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer cartId) {
		this.id = cartId;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cart)) {
            return false;
        }
        Cart cart = (Cart) o;
        return getId() == cart.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
