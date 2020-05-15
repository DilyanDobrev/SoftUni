package com.softuni.xmlproductshop.utils;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface XmlParser {

    <T> T unmarshalFromFile(String path, Class<T> tClass) throws JAXBException, FileNotFoundException;

    <T> void marshalToFile(String path, T rootDto) throws JAXBException, IOException;
}
