package br.com.prodpaymanager.services.seller;

import br.com.prodpaymanager.dto.seller.SellerCreationDTO;
import br.com.prodpaymanager.models.seller.SellerEntity;
import br.com.prodpaymanager.repositories.seller.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateSellerService {

    @Autowired
    SellerRepository sellerRepository;

    public void creteSeller(SellerCreationDTO sellerCreationDTO){
        SellerEntity sellerEntity = sellerCreationDTO.toSellerEntity();
        this.sellerRepository
                .findByCpf(sellerEntity.getCpf())
                .ifPresent((seller) -> {

                });
        this.sellerRepository.save(sellerEntity);
    }
}
