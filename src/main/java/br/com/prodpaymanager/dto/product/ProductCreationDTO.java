package br.com.prodpaymanager.dto.product;

import br.com.prodpaymanager.models.piece.PieceEntity;
import br.com.prodpaymanager.models.product.ProductEntity;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductCreationDTO {

    private String nameProduct;

    private double valueProduct;

    private Map<Integer, Integer> piecesWithQuantity;

    public ProductEntity toProductEntity(List<PieceEntity> pieceEntities, double custPieces){
        ProductEntity productEntity = new ProductEntity();
        productEntity.setNameProduct(this.nameProduct);
        productEntity.setValueProduct(this.valueProduct);
        productEntity.setCustProduct(custPieces);
        productEntity.setPieces(pieceEntities);

        return productEntity;
    }
}
