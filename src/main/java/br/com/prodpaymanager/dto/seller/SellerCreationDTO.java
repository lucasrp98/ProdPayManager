package br.com.prodpaymanager.dto.seller;

import br.com.prodpaymanager.models.seller.SellerEntity;

public class SellerCreationDTO {
    private String name;

    private String cpf;

    private double comissionSeller;

    public SellerEntity toSellerEntity(){
        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setName(this.name);
        sellerEntity.setCpf(this.cpf);
        sellerEntity.setComissionSeller(this.comissionSeller);
        return sellerEntity;
    }
}
