package br.com.prodpaymanager.services.client;

import br.com.prodpaymanager.dto.client.ClientCreationDTO;
import br.com.prodpaymanager.exceptions.ClientExceptions;
import br.com.prodpaymanager.models.client.ClientEntity;
import br.com.prodpaymanager.repositories.client.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreteClientService {

    @Autowired
    ClientRepository clientRepository;

    public void creteClient(ClientCreationDTO clientCreationDTO){
        ClientEntity clientEntity = clientCreationDTO.toClientEntity();
        this.clientRepository
                .findByCpf(clientEntity.getCpf())
                .ifPresent((cliente) -> {
                    throw new ClientExceptions.ClientFoundException();
                });
        this.clientRepository.save(clientEntity);
    }
}
