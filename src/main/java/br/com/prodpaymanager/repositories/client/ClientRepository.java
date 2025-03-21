package br.com.prodpaymanager.repositories.client;

import br.com.prodpaymanager.models.client.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {
    Optional<ClientEntity> findByCpf(String cpf);
}
