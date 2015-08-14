package service;

import org.springframework.stereotype.Service;
import persistence.model.Product;
import persistence.model.ProductRepo;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

/**
 * Created by daniel.copaciu on 8/13/2015.
 */
@Service
public class XMLServiceImpl implements XMLService {

    @Override
    public String objectToXml(Object o) throws JAXBException {
        StringWriter result = new StringWriter();
        JAXBContext jaxbContext = JAXBContext.newInstance(ProductRepo.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(o, result);
        return result.toString();
    }

    @Override
    public Object xmlToObject(String xml) {
        return null;
    }
}
