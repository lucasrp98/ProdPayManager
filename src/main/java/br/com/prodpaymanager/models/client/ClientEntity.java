package br.com.prodpaymanager.models.client;

import br.com.prodpaymanager.models.PeopleEntity;
import br.com.prodpaymanager.models.sale.SaleEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "client")
public class ClientEntity extends PeopleEntity {

    @OneToMany(mappedBy = "clientEntity")
    @JsonManagedReference
    private List<SaleEntity> sales;
}
