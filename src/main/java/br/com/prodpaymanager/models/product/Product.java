package br.com.prodpaymanager.models.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String cEAN;
    private String xProd;
    private String qCom;
    private String vUnCom;
    private String vProd;
}
