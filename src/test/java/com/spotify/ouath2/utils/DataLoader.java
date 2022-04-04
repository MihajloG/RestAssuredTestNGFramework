package com.spotify.ouath2.utils;

import java.util.Properties;

/**
 * @author Mihajlo Gorunovic (m.gorunovic@levi9.com)
 * @since $(Date)
 */
public class DataLoader {
	private final Properties properties;
	private static DataLoader dataLoader;

	private DataLoader(){
		properties = PropertyUtils.propertyLoader("src/test/resources/data.properties");
	}
	public static DataLoader getInstance(){
		if(dataLoader == null){
			dataLoader = new DataLoader();
		}
		return dataLoader;
	}
			public String getPlayListID(){
				String prop = properties.getProperty("get_playlist_id");
				if(prop !=null) return prop;
				else  throw new RuntimeException("Property get_playlist_id is not specified in the config.properties file");
			}
	public String getUpdatePlayListID(){
		String prop = properties.getProperty("update_playlist_id");
		if(prop !=null) return prop;
		else  throw new RuntimeException("Property update_playlist_id is not specified in the config.properties file");
	}
}
