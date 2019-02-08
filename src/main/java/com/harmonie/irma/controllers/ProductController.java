package com.harmonie.irma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harmonie.irma.models.Produit;
import com.harmonie.irma.repositories.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;

	@GetMapping
	public Iterable<Produit> findAll() {
		return productRepository.findAll();
	}
}
