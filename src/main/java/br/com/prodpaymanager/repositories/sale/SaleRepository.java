package br.com.prodpaymanager.repositories.sale;

import br.com.prodpaymanager.models.sale.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<SaleEntity, Integer> {
}
