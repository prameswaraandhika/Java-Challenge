	package com.challenge4.demo.service;


	import com.challenge4.demo.model.Product;
	import com.challenge4.demo.repositori.ProductRepository;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;

	import java.util.List;
	import java.util.Optional;
	import java.util.UUID;

	@Service
	public class ProductServiceImpl implements ProductService {
		@Autowired
		ProductRepository productRepository;
		@Override
		public List<Product> findAll() {
			return productRepository.findAll();
		}

		@Override
		public void create(Product product) {
			productRepository.save(product);
		}

		@Override
		public void delete(UUID id) {
			productRepository.deleteById(id);
		}

		@Override
		public void update(UUID id, String name) {
			Optional<Product> productOptional= productRepository.findById(id);
			if (productOptional.isPresent()) {
				Product productFound = productOptional.get();
				productFound.setName(name);
				productRepository.save(productFound);
			}
		}

	}
