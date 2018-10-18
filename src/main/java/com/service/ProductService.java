package com.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pogo.Price;
import com.pogo.Product;
import com.pogo.ProductInfo;

@Service
public class ProductService {

	RestTemplate restTemplate = new RestTemplate();

	public ProductInfo getProduct(int id) {

		 Product[] prodcuts = restTemplate.getForObject("http://localhost:8080/productinfo", Product[].class);
		 
		ProductInfo p = new ProductInfo();
		Product pro = null;
		for (Product a:prodcuts) {
			if(a.getId()==id) {
				pro=a;
			}
			}

		if (pro!=null) {
			p.setId(pro.getId());
			p.setName(pro.getName());
		}
		else {
			return null;
		}
         Price price = restTemplate.getForObject("http://localhost:8081/productpriceinfo/"+id, Price.class);
		if (price!=null) {
			p.setPrice(price.getPrice());
			p.setQuantity(price.getQuantity());
         }
		else {
			return null;
		}
         return p;
         }
	
	public ArrayList<ProductInfo> getPriceProduct(double price) {
		
		Price[] b = restTemplate.getForObject("http://localhost:8081/productpriceinfo", Price[].class);
		ArrayList<ProductInfo> productInfo = new ArrayList<ProductInfo>();
		ArrayList<Price> pr = new ArrayList<Price>();
		for (Price a :b) {
			if(a.getPrice()>=price) {
			pr.add(a);
			}
		}
		
		Product[] products = restTemplate.getForObject("http://localhost:8080/productinfo", Product[].class);
		
		for (Product a : products) {
			for (Price c : pr) {
				if (a.getId()== c.getId()) {
					ProductInfo proInfo = new ProductInfo();
					proInfo.setId(a.getId());
					proInfo.setName(a.getName());
					proInfo.setPrice(c.getPrice());
					proInfo.setQuantity(c.getQuantity());
					
					productInfo.add(proInfo);
				}
			}
		}
		return productInfo;
	
		}
		
	}


