package br.com.prodpaymanager.repositories.buy;

import br.com.prodpaymanager.models.buy.BuyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BuyRepository extends JpaRepository<BuyEntity, Integer> {
}

