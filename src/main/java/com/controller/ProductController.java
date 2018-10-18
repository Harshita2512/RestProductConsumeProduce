package com.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pogo.Product;
import com.pogo.ProductInfo;
import com.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value ="/productapi/{id}", method =RequestMethod.GET, produces= "application/json")
	public ResponseEntity<ProductInfo> getStock(@PathVariable int id){
        
		ResponseEntity<ProductInfo> response = null;
		
		try {
			ProductInfo product = productService.getProduct(id);
			if(product!=null) {
				response =  new ResponseEntity<ProductInfo>(product, HttpStatus.OK);
			}else {
				response = new ResponseEntity<ProductInfo>(HttpStatus.NOT_FOUND);
			}
		}
		 catch(Exception e) {
				response = new ResponseEntity<ProductInfo>(HttpStatus.INTERNAL_SERVER_ERROR);
		 }
		return response;
	}
	
	@RequestMapping(value ="/productapi/price/{price}", method =RequestMethod.GET, produces= "application/json")
	public ResponseEntity<ArrayList<ProductInfo>> getAllProductInfo (@PathVariable double price){
		
		ResponseEntity<ArrayList<ProductInfo>> response = null;
		
		try {
			ArrayList<ProductInfo> product = productService.getPriceProduct(price);
			if(product!=null && product.size() > 0) {
				response =  new ResponseEntity<ArrayList<ProductInfo>>(product, HttpStatus.OK);
			}else {
				response = new ResponseEntity<ArrayList<ProductInfo>>(HttpStatus.NOT_FOUND);
			}
		}
		 catch(Exception e) {
				response = new ResponseEntity<ArrayList<ProductInfo>>(HttpStatus.INTERNAL_SERVER_ERROR);
		 }
		return response;
		}
		
	}


