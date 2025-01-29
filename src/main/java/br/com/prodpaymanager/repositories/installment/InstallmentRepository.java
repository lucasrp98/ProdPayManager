package br.com.prodpaymanager.repositories.installment;

import br.com.prodpaymanager.models.installment.InstallmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstallmentRepository extends JpaRepository<InstallmentEntity, Integer> {

}
