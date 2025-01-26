package br.com.prodpaymanager.models.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name = "product_seq", sequenceName = "product_seq", allocationSize = 1)
    private int id;
    @Column(name = "cean")
    private String cEAN;
    @Column(name = "xprod")
    private String xProd;
    @Column(name = "qcom")
    private int qCom;
    @Column(name = "vuncom")
    private String vUnCom;
    @Column(name = "vprod")
    private String vProd;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
