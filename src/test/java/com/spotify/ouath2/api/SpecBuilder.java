package com.spotify.ouath2.api;

import static com.spotify.ouath2.api.RouteURL.BASE_PATH;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

/**
 * @author Mihajlo Gorunovic (m.gorunovic@levi9.com)
 * @since $(Date)
 */
public class SpecBuilder {

	public static RequestSpecification getRequestSpec() {
		return new RequestSpecBuilder().
			setBaseUri("https://api.spotify.com").
			setBasePath(BASE_PATH).setContentType(ContentType.JSON).
			log(LogDetail.ALL).build();

	}
	public static RequestSpecification getAccoutnRequestSpec() {
		return new RequestSpecBuilder().
			setBaseUri("https://accounts.spotify.com").setContentType(ContentType.URLENC).
			log(LogDetail.ALL).build();

	}
	public static ResponseSpecification getResponseSpec(){

		return new ResponseSpecBuilder().log(LogDetail.ALL).build();

	}
}


