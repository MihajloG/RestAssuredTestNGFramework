package com.spotify.ouath2.api;

import static com.spotify.ouath2.api.RouteURL.API;
import static com.spotify.ouath2.api.RouteURL.TOKEN;
import static com.spotify.ouath2.api.SpecBuilder.getAccoutnRequestSpec;
import static com.spotify.ouath2.api.SpecBuilder.getRequestSpec;
import static com.spotify.ouath2.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

import com.spotify.ouath2.pojo.Playlist;

import java.util.HashMap;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * @author Mihajlo Gorunovic (m.gorunovic@levi9.com)
 * @since $(Date)
 */
public class ApplicationApiResources {

	public static Response post(String path, String token, Object requestPlaylist){
		return given(getRequestSpec()).
			body(requestPlaylist).auth().oauth2(token).
			when().
			post(path).
			then().spec(getResponseSpec()).extract().
			response();

	}
		public static Response postAccount(HashMap<String, String> formParams){
			return  given(getAccoutnRequestSpec()).
				formParams(formParams).
				when().post(API + TOKEN).
				then().spec(getResponseSpec()).extract().response();
		}
	public static Response get(String path, String token){
	return given(getRequestSpec()).
			auth().oauth2(token).
			when().
			get(path).
			then().log().all().spec(getResponseSpec()).extract().response();
	}
	public static Response put(String path, String token, Object requestPlaylist){
		return given(getRequestSpec()).body(requestPlaylist).
			auth().oauth2(token).
			when().
			put(path).
			then().spec(getResponseSpec()).extract().response();
	}

}
