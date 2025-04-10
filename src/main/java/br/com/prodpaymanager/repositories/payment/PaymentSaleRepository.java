package br.com.prodpaymanager.repositories.payment;

import br.com.prodpaymanager.models.payment.PaymentSaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentSaleRepository extends JpaRepository<PaymentSaleEntity, Integer> {
}
