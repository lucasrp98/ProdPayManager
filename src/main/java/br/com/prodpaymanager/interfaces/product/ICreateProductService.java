package br.com.prodpaymanager.interfaces.product;

import br.com.prodpaymanager.dto.product.ProductCreationDTO;
import br.com.prodpaymanager.models.product.ProductEntity;

public interface ICreateProductService {
    ProductEntity saveProduct(ProductCreationDTO productCreationDTO);
}
