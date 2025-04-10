package br.com.prodpaymanager.dto.client;

import br.com.prodpaymanager.models.client.ClientEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientCreationDTO {

    private String name;

    private String cpf;

    public ClientEntity toClientEntity(){
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setName(this.name);
        clientEntity.setCpf(this.cpf);
        return clientEntity;
    }
}
