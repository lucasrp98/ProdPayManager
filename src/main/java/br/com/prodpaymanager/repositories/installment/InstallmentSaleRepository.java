package br.com.prodpaymanager.repositories.installment;

import br.com.prodpaymanager.models.installment.InstallmentSaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstallmentSaleRepository extends JpaRepository<InstallmentSaleEntity, Integer> {
}
