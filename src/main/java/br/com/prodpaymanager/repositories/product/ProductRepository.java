package br.com.prodpaymanager.repositories.product;


import br.com.prodpaymanager.models.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

}
