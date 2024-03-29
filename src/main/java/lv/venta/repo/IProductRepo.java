package lv.venta.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import lv.venta.models.Product;

public interface IProductRepo extends CrudRepository<Product, Integer>{
	// SELECT * FROM product_table WHERE price < var;
	ArrayList<Product> findByPriceLessThan(float var);
	
	// TODO atlasa pēc nosaukuma
	// SELECT * FROM product_table WHERE title='<var>';
	ArrayList<Product> findByTitleIgnoreCase(String var);
	
	//TODO atlasa tos produktus kuru daudzums ir lielāks par 10 bet cena mazāka par 4e
	// SELECT * FROM product_table WHERE quantity > varQ and price < varP;
	ArrayList<Product> findByQuantityGreaterThanAndPriceLessThan(int varQ, float varP);

	// SELECT * FROM product_table WHERE quantity < '<quantity>'
	ArrayList<Product> findByQuantityLessThan(int quantity);
	
	
	//@Query(nativeQuery = countBy)
	//ArrayList<Product> funcNameDoesNotMatter();
	
}
