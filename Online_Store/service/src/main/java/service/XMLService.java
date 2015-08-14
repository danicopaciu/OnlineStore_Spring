package service;

import javax.xml.bind.JAXBException;

/**
 * Created by daniel.copaciu on 8/13/2015.
 */
public interface XMLService {

    public String objectToXml(Object o) throws JAXBException;
    public Object xmlToObject(String xml);
}
