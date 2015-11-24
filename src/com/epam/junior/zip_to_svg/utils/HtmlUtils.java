package com.epam.junior.zip_to_svg.utils;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.junior.zip_to_svg.domain.ZipRecordType;

public class HtmlUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HtmlUtils.class.getName());
	private static HashMap<String, String> stateColors = new HashMap<>();
	
	public static String printHtml(List<ZipRecordType> zips) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(appendHtmlHead());
		LOGGER.debug("Head part appended to output html.");
		
		for (ZipRecordType zip : zips) {
			String color = getColorForState(zip.getState());
			sb.append(zip.createCircleTag(color));
		}
		
		sb.append(appendHtmlFoot());
		LOGGER.debug("Foot part appended to output html.");
		
		return sb.toString();
	}

	private static String getColorForState(String state) {
		if (stateColors.containsKey(state)) {
			return stateColors.get(state);
		}
		
		String color = generateColor();
		stateColors.put(state, color);
		
		LOGGER.debug("Returning newly generated color for: " + state);
		return color;
	}

	private static String appendHtmlHead() {
		StringBuilder sb = new StringBuilder();
		sb.append("<html>" + System.lineSeparator());
		sb.append(printIdentation(1) + "<body>" + System.lineSeparator());
		sb.append(printIdentation(2) + "<svg width=\"1700\" height=\"800\">" + System.lineSeparator());
		return sb.toString();
	}
	
	private static String generateColor() {
		int r = generateInt0To256();
		int g = generateInt0To256();
		int b = generateInt0To256();
		
		return String.format("#%02x%02x%02x", r, g, b);
	}

	private static int generateInt0To256() {
		return (int) (Math.random() * 256);
	}
	
	private static String appendHtmlFoot() {
		StringBuilder sb = new StringBuilder();
		sb.append(printIdentation(2) + "</svg>" + System.lineSeparator());
		sb.append(printIdentation(1) + "</body>" + System.lineSeparator());
		sb.append("</html>" + System.lineSeparator());
		return sb.toString();
	}
	
	public static String printIdentation(int level) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < level * 4; i++) {
			sb.append(" ");
		}
		
		return sb.toString();
	}
}
