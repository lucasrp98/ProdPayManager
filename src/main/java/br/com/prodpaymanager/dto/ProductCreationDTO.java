package br.com.prodpaymanager.dto;

import br.com.prodpaymanager.models.product.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductCreationDTO {
    private String cEAN;
    private String xProd;
    private int qCom;
    private String vUnCom;
    private String vProd;

    public ProductEntity toProductEntity() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setCEAN(this.getCEAN());
        productEntity.setXProd(this.getXProd());
        productEntity.setQCom(this.getQCom());
        productEntity.setVUnCom(this.getVUnCom());
        productEntity.setVProd(this.getVProd());
        return productEntity;
    }
}
