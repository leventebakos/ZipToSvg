package com.epam.junior.ziptosvg;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.junior.ziptosvg.domain.ZipRecordType;
import com.epam.junior.ziptosvg.utils.XmlParser;

public class ZipToSvg {

	private static final Logger LOGGER = LoggerFactory.getLogger(ZipToSvg.class.getName());

	public static void main(String[] args) {
		ZipToSvg zipToSvg = new ZipToSvg();

		try {
			LOGGER.debug("Starting to parse XML file.");
			List<ZipRecordType> zips = XmlParser.getZips(zipToSvg.getResource("zip-full.xml"));
			
			XmlParser.writeXml(zips);
			LOGGER.debug("Done.");
		} catch (JAXBException | XMLStreamException | IOException e) {
			LOGGER.error("Error during processing the XML file.");
		}
	}

	private InputStream getResource(String source) {
		return getClass().getClassLoader().getResourceAsStream(source);
	}
}
