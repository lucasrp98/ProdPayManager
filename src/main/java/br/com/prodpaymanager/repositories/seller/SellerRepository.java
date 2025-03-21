package br.com.prodpaymanager.repositories.seller;

import br.com.prodpaymanager.models.seller.SellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<SellerEntity, Integer> {
    Optional<SellerEntity> findByCpf(String cpf);

}
