package br.com.prodpaymanager.models.seller;

import br.com.prodpaymanager.models.PeopleEntity;
import br.com.prodpaymanager.models.sale.SaleEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "seller")
public class SellerEntity extends PeopleEntity {

    private double comissionSeller;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = true)
    private SaleEntity saleEntity;
}
