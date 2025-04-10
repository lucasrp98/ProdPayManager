package br.com.prodpaymanager.services.sale;


import br.com.prodpaymanager.dto.sale.SaleCreationDTO;
import br.com.prodpaymanager.exceptions.ClientExceptions;
import br.com.prodpaymanager.exceptions.ProductExceptions;
import br.com.prodpaymanager.exceptions.SaleExceptions;
import br.com.prodpaymanager.exceptions.SellerExceptions;
import br.com.prodpaymanager.models.client.ClientEntity;
import br.com.prodpaymanager.models.product.ProductEntity;
import br.com.prodpaymanager.models.sale.SaleEntity;
import br.com.prodpaymanager.models.seller.SellerEntity;
import br.com.prodpaymanager.repositories.client.ClientRepository;
import br.com.prodpaymanager.repositories.product.ProductRepository;
import br.com.prodpaymanager.repositories.sale.SaleRepository;
import br.com.prodpaymanager.repositories.seller.SellerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CreateSaleService {

    @Autowired
    SaleRepository saleRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ProductRepository productRepository;

    public SaleEntity creteSale(SaleCreationDTO saleCreationDTO) {
        ClientEntity clientEntity = toCheckClient(saleCreationDTO.getClientID());

        SellerEntity sellerEntity = toCheckSeller(saleCreationDTO.getSellerID());

        List<Integer> productIds = new ArrayList<>(saleCreationDTO.getProductsWithQuantity().keySet());

        List<ProductEntity> produtos = toCheckProduct(productIds, saleCreationDTO);

        updateProductStock(produtos);

        SaleEntity saleEntity = saleCreationDTO.toSaleEntity(clientEntity, sellerEntity, produtos);

        Optional<SaleEntity> existingSaleOpt = this.saleRepository.findById(saleEntity.getId());

        if (existingSaleOpt.isPresent()){
            throw new SaleExceptions.SaleFoundException();
        }

        sellerEntity.getSales().add(saleEntity);

        saleEntity.setSellerEntity(sellerEntity);

        return this.saleRepository.save(saleEntity);
    }

    private ClientEntity toCheckClient(Integer idClient) {
        return this.clientRepository.findById(idClient)
                .orElseThrow(() -> new ClientExceptions.ClientNotFoundException());
    }

    private SellerEntity toCheckSeller(Integer idSeller) {
        return this.sellerRepository.findById(idSeller)
                .orElseThrow(() -> new SellerExceptions.SellerNotFoundException());
    }

    private List<ProductEntity> toCheckProduct(List<Integer> list_products, SaleCreationDTO saleCreationDTO) {
        List<ProductEntity> produtos = productRepository.findAllById(list_products);

        if (produtos.size() != list_products.size()) {
            throw new ProductExceptions.ProductNotFoundException();
        }
        for (ProductEntity productEntity : produtos) {
            int quantityUsed = saleCreationDTO.getProductsWithQuantity().get(productEntity.getId());

            if (productEntity.getQuantProduct() < quantityUsed) {
                throw new IllegalArgumentException("Estoque insuficiente para a peça ID: " + productEntity.getId());
            }

            productEntity.setQuantityUsed(quantityUsed);
        }

        return produtos;
    }

    @Transactional
    public void updateProductStock(List<ProductEntity> list_products) {
        for (ProductEntity product : list_products) {
            product.setQuantProduct(product.getQuantProduct() - product.getQuantityUsed());
        }
        productRepository.saveAll(list_products);
    }
}
