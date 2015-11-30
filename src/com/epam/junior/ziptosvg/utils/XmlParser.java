package com.epam.junior.ziptosvg.utils;

import java.io.InputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.junior.ziptosvg.domain.ObjectFactory;
import com.epam.junior.ziptosvg.domain.ZipListType;
import com.epam.junior.ziptosvg.domain.ZipRecordType;

public class XmlParser {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(XmlParser.class.getName());

	public static List<ZipRecordType> getZips(InputStream xml) throws JAXBException, XMLStreamException {
		JAXBContext jc = JAXBContext.newInstance(ObjectFactory.class);

		Unmarshaller unmarshaller = jc.createUnmarshaller();
		XMLInputFactory xmlif = XMLInputFactory.newInstance();
		XMLEventReader xmler = xmlif.createXMLEventReader(xml);
		
		LOGGER.debug("Starting to unmarshal XML file.");
		ZipListType obj = (ZipListType) unmarshaller.unmarshal(xmler, ZipListType.class).getValue();
		LOGGER.debug("XML file unmarshalled.");
		return obj.getRecord();
	}

}
