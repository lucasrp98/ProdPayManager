package br.com.prodpaymanager.models.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Product {
    private String cEAN;
    private String xProd;
    private String qCom;
    private String vUnCom;
    private String vProd;
}
