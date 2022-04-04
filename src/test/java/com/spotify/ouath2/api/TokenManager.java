package com.spotify.ouath2.api;

import static com.spotify.ouath2.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

import com.spotify.ouath2.utils.ConfigLoader;

import java.time.Instant;
import java.util.HashMap;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * @author Mihajlo Gorunovic (m.gorunovic@levi9.com)
 * @since $(Date)
 */
public class TokenManager {
	private static String access_token;
	private static Instant expire_time;

	public synchronized static String getToken(){

		try{

			if(access_token == null || Instant.now().isAfter(expire_time)){

				System.out.println("Renewing a token.... ");
				Response response = renewToken();
				access_token = response.path("access_token");

				int expiryDurationInSeconds = response.path("expires_in");
				expire_time = Instant.now().plusSeconds(expiryDurationInSeconds - 300);
			} else {
				System.out.println("Token is good to use! ");
			}

		}
		catch(Exception e){
			throw new RuntimeException("Failed to get Token! ", e);

		}
			return access_token;

	}
	private static Response renewToken(){
		HashMap<String, String> formParams = new HashMap<String, String>();
		formParams.put("grant_type", ConfigLoader.getInstance().getGrantType());
		formParams.put("refresh_token", ConfigLoader.getInstance().getRefreshToken());
		formParams.put("client_id", ConfigLoader.getInstance().getClientId());
		formParams.put("client_secret", ConfigLoader.getInstance().getClientSecret());

		Response response = ApplicationApiResources.postAccount(formParams);

		if(response.statusCode() != 200){
			throw new RuntimeException("renewtoken failed");
		}
		return response;
	}

}
