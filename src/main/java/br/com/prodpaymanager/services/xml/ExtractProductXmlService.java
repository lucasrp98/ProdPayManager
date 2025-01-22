package br.com.prodpaymanager.services.xml;

import br.com.prodpaymanager.models.product.Product;
import br.com.prodpaymanager.repositories.xml.IExtractProductXmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExtractProductXmlService implements IExtractProductXmlService {

    @Autowired
    Product product;

    @Override
    public Object getProductXml(String xml) {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputStream is = new ByteArrayInputStream(xml.getBytes("UTF-8"));
            Document doc = builder.parse(is);
            doc.getDocumentElement().normalize();

            NodeList prodList = doc.getElementsByTagNameNS("http://www.portalfiscal.inf.br/nfe", "prod");
            List<Product> products = new ArrayList<>();

            for (int i = 0; i < prodList.getLength(); i++) {
                Element prodElement = (Element) prodList.item(i);
                String cEAN = prodElement.getElementsByTagNameNS("http://www.portalfiscal.inf.br/nfe", "cEAN").item(0).getTextContent();
                String xProd = prodElement.getElementsByTagNameNS("http://www.portalfiscal.inf.br/nfe", "xProd").item(0).getTextContent();
                String qCom = prodElement.getElementsByTagNameNS("http://www.portalfiscal.inf.br/nfe", "qCom").item(0).getTextContent();
                String vUnCom = prodElement.getElementsByTagNameNS("http://www.portalfiscal.inf.br/nfe", "vUnCom").item(0).getTextContent();
                String vProd = prodElement.getElementsByTagNameNS("http://www.portalfiscal.inf.br/nfe", "vProd").item(0).getTextContent();

                products.add(new Product(cEAN, xProd, qCom, vUnCom, vProd));
            }

            for (Product product : products) {
                System.out.println(product.getXProd());
            }
            return products;

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
