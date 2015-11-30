package com.epam.junior.ziptosvg.utils;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HtmlUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HtmlUtils.class.getName());
	private static HashMap<String, String> stateColors = new HashMap<>();

	public static String getColorForState(String state) {
		if (stateColors.containsKey(state)) {
			return stateColors.get(state);
		}
		
		String color = generateColor();
		stateColors.put(state, color);
		
		LOGGER.debug("Returning newly generated color for: " + state);
		return color;
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
}
