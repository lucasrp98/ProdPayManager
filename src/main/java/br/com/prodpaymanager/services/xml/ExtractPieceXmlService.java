package br.com.prodpaymanager.services.xml;

import br.com.prodpaymanager.dto.piece.PieceCreationDTO;
import br.com.prodpaymanager.interfaces.xml.IExtractPieceXmlService;
import br.com.prodpaymanager.models.buy.BuyEntity;
import br.com.prodpaymanager.models.piece.PieceEntity;
import br.com.prodpaymanager.models.piece_buy.PieceBuyEntity;
import br.com.prodpaymanager.repositories.buy.BuyRepository;
import br.com.prodpaymanager.services.piece.CreatePieceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExtractPieceXmlService implements IExtractPieceXmlService {

    @Autowired
    CreatePieceService createPieceService;

    @Autowired
    BuyRepository buyRepository;

    @Override
    public Integer getPieceXml(String xml) {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputStream is = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
            Document doc = builder.parse(is);
            doc.getDocumentElement().normalize();

            NodeList prodList = doc.getElementsByTagName("prod");
            List<PieceBuyEntity> pieceBuyEntities = new ArrayList<>();

            BuyEntity buy = new BuyEntity();

            for (int i = 0; i < prodList.getLength(); i++) {
                Element prodElement = (Element) prodList.item(i);
                String cEAN = prodElement.getElementsByTagName("cEAN").item(0).getTextContent();
                String xProd = prodElement.getElementsByTagName("xProd").item(0).getTextContent();
                int qCom = Integer.parseInt(prodElement.getElementsByTagName("qCom").item(0).getTextContent());
                double vUnCom = Double.parseDouble(prodElement.getElementsByTagName("vUnCom").item(0).getTextContent());

                PieceCreationDTO pieceDTO = new PieceCreationDTO(cEAN, xProd, qCom, vUnCom);
                PieceEntity pieceEntity = createPieceService.saveProduct(pieceDTO);

                PieceBuyEntity pieceBuyEntity = new PieceBuyEntity();
                pieceBuyEntity.setBuy(buy);
                pieceBuyEntity.setPiece(pieceEntity);
                pieceBuyEntity.setQCom(qCom);

                pieceBuyEntities.add(pieceBuyEntity);
            }

            buy.setPieceBuyEntities(pieceBuyEntities);
            buyRepository.save(buy);

            return buy.getId();

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }
}
