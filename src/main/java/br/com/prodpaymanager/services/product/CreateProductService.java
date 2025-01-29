package br.com.prodpaymanager.services.product;

import br.com.prodpaymanager.dto.product.ProductCreationDTO;
import br.com.prodpaymanager.models.product.ProductEntity;
import br.com.prodpaymanager.repositories.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateProductService{

    @Autowired
    private ProductRepository productRepository;

    public ProductEntity saveProduct(ProductCreationDTO productCreationDTO) {

        ProductEntity productEntity = productCreationDTO.toProductEntity();

        this.productRepository
                    .findBycEAN(productEntity.getCEAN())
                    .ifPresent((product) -> {
                        toAddAmount(product, productEntity.getQCom());

                    });
            return this.productRepository.save(productEntity);
    }

    public void toAddAmount (ProductEntity product, int additionalAmount){
        int currentAmount = product.getQCom();
        product.setQCom(currentAmount + additionalAmount);
    }
}
