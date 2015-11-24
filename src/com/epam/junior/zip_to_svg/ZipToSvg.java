package com.epam.junior.zip_to_svg;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.junior.zip_to_svg.domain.ZipRecordType;
import com.epam.junior.zip_to_svg.utils.HtmlUtils;
import com.epam.junior.zip_to_svg.utils.XmlParser;

public class ZipToSvg {

	private static final Logger LOGGER = LoggerFactory.getLogger(ZipToSvg.class.getName());

	public static void main(String[] args) throws IOException {
		ZipToSvg zipToSvg = new ZipToSvg();

		try {
			LOGGER.debug("Starting to parse XML file.");
			List<ZipRecordType> zips = XmlParser.getZips(zipToSvg.getResource("zip-full.xml"));

			FileWriter writer = new FileWriter("zips.html");
			writer.write(HtmlUtils.printHtml(zips));
			writer.flush();
			writer.close();
			LOGGER.debug("Done.");
		} catch (JAXBException | XMLStreamException e) {
			LOGGER.error("Error during processing the XML file.");
		}
	}

	private InputStream getResource(String source) {
		return getClass().getClassLoader().getResourceAsStream(source);
	}
}
