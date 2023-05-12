package lv.venta.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.models.Product;
import lv.venta.repo.IProductRepo;
import lv.venta.services.ICRUDProductService;
import lv.venta.services.IFilteringProductService;

@Service
public class ProductServiceImplWithDB implements ICRUDProductService, IFilteringProductService {

	@Autowired
	private IProductRepo productRepo;
	
	@Override
	public ArrayList<Product> filterByPriceLess(float price) {
		return productRepo.findByPriceLessThan(price);
	}

	@Override
	public ArrayList<Product> retrieveAllProducts() {
		// TODO Auto-generated method stub
		return (ArrayList<Product>) productRepo.findAll();
	}

	@Override
	public Product retrieveOneProductById(int id) throws Exception {
		// TODO Auto-generated method stub
		return productRepo.findById(id).get();
	}

	@Override
	public Product retrieveOneProductByTitle(String title) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product insertProductByParams(String title, float price, String description, int quantity) {
		Product temp = productRepo.save(new Product(title, price, description, quantity));
		return temp;
	}

	@Override
	public Product updateProductByParams(int id, String title, float price, String description, int quantity)
			throws Exception {
		
		if (productRepo.existsById(id)) { //pārbaudam vai eksistē sads produkts datu bāzē
			Product updatedPr = productRepo.findById(id).get(); //pie findById vienmer vajag vēl get()
			updatedPr.setTitle(title);
			updatedPr.setDescription(description);
			updatedPr.setPrice(price);
			updatedPr.setQuantity(quantity);
			return productRepo.save(updatedPr);
		}
		else {
			throw new Exception("Wrong id");
		}
	}

	@Override
	public void deleteProductById(int id) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
