package br.com.prodpaymanager.repositories.payment;

import br.com.prodpaymanager.models.payment.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Integer> {

}
