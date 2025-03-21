package br.com.prodpaymanager.interfaces.client;

import br.com.prodpaymanager.dto.client.ClientCreationDTO;
import br.com.prodpaymanager.dto.payment.PaymentCreationDTO;

public interface ICreateClientService {
    void saveClient(ClientCreationDTO clientCreationDTO);

}
