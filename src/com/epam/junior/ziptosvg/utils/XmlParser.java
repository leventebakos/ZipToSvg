package com.epam.junior.ziptosvg.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.junior.ziptosvg.domain.ObjectFactory;
import com.epam.junior.ziptosvg.domain.ZipListType;
import com.epam.junior.ziptosvg.domain.ZipRecordType;

public class XmlParser {

	private static final Logger LOGGER = LoggerFactory.getLogger(XmlParser.class.getName());
	private static final XMLOutputFactory factory = XMLOutputFactory.newInstance();
	private static XMLStreamWriter writer;

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

	public static void writeXml(List<ZipRecordType> zips) throws XMLStreamException, IOException {
		writer = factory.createXMLStreamWriter(new FileWriter("zips.html"));

		writeHtmlBeginning();
		writeHtmlCircles(zips);
		writeHtmlEnding();

		writer.flush();
		writer.close();
	}

	private static void writeHtmlBeginning() throws XMLStreamException {
		writer.writeStartElement("html");
		writer.writeStartElement("body");
		writer.writeStartElement("svg");
		writer.writeAttribute("width", "1700");
		writer.writeAttribute("height", "800");
	}

	private static void writeHtmlCircles(List<ZipRecordType> zips) throws XMLStreamException {
		for (ZipRecordType zip : zips) {
			writeCircleElement(zip);
		}
	}

	private static void writeCircleElement(ZipRecordType zip) throws XMLStreamException {
		String color = HtmlUtils.getColorForState(zip.getState());
		writer.writeStartElement("circle");
		writer.writeAttribute("cy", zip.getCY());
		writer.writeAttribute("cx", zip.getCX());
		writer.writeAttribute("fill", color);
		writer.writeAttribute("r", "1");
		writer.writeEndElement();
	}

	private static void writeHtmlEnding() throws XMLStreamException {
		writer.writeEndElement();
		writer.writeEndElement();
		writer.writeEndElement();
	}

}
