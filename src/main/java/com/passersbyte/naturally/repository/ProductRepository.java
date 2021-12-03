package com.passersbyte.naturally.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.passersbyte.naturally.model.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

}
