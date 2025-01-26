package br.com.prodpaymanager.repositories.product;

import br.com.prodpaymanager.models.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    Optional<ProductEntity> findBycEAN(String cEAN);
}
