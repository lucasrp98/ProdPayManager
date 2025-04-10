package br.com.prodpaymanager.dto.sale;

import br.com.prodpaymanager.models.client.ClientEntity;
import br.com.prodpaymanager.models.product.ProductEntity;
import br.com.prodpaymanager.models.sale.SaleEntity;
import br.com.prodpaymanager.models.seller.SellerEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SaleCreationDTO {
    private Date dateSale;

    private double valueSale;

    private double valueFreight;

    private Integer clientID;

    private Integer sellerID;

    private Map<Integer, Integer> productsWithQuantity;


    public SaleEntity toSaleEntity(ClientEntity clientEntity, SellerEntity sellerEntity, List<ProductEntity> products){
        SaleEntity saleEntity = new SaleEntity();
        saleEntity.setDateSale(this.dateSale);
        saleEntity.setValueSale(this.valueSale);
        saleEntity.setValueFreight(this.valueFreight);
        saleEntity.setClientEntity(clientEntity);
        saleEntity.setSellerEntity(sellerEntity);
        saleEntity.setProductEntityList(products);

        return saleEntity;
    }
}
