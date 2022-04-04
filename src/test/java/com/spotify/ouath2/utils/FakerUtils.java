package com.spotify.ouath2.utils;

import com.github.javafaker.Faker;

/**
 * @author Mihajlo Gorunovic (m.gorunovic@levi9.com)
 * @since $(Date)
 */
public class FakerUtils {

	public static String generateName(){
		Faker faker = new Faker();
		return "Playlist" + faker.regexify("[A-Za-z0-9 ,_-]{10}");

	}
	public static String generateDescritpion(){
		Faker faker = new Faker();
		return "Description" + faker.regexify("[A-Za-z0-9_@./#&+-]{50}");

	}

}
