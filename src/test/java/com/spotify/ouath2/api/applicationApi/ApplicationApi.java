package com.spotify.ouath2.api.applicationApi;

import static com.spotify.ouath2.api.RouteURL.PLAYLISTS;
import static com.spotify.ouath2.api.RouteURL.USERS;
import static com.spotify.ouath2.api.SpecBuilder.getRequestSpec;
import static com.spotify.ouath2.api.SpecBuilder.getResponseSpec;
import static com.spotify.ouath2.api.TokenManager.getToken;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.spotify.ouath2.api.ApplicationApiResources;
import com.spotify.ouath2.pojo.Playlist;
import com.spotify.ouath2.utils.ConfigLoader;

import io.restassured.response.Response;

/**
 * @author Mihajlo Gorunovic (m.gorunovic@levi9.com)
 * @since $(Date)
 */
public class ApplicationApi {

	public static Response post(Playlist requestPlaylist){
		return ApplicationApiResources.post(USERS + "/"+ ConfigLoader.getInstance().getUser() + PLAYLISTS, getToken(), requestPlaylist);


	}
	public static Response post(String token, Playlist requestPlaylist){
		return ApplicationApiResources.post(USERS + "/" + ConfigLoader.getInstance().getUser() + PLAYLISTS, token, requestPlaylist);


	}
	public static Response get(String playlistID){
		return  ApplicationApiResources.get(PLAYLISTS + "/" + playlistID, getToken());

	}
	public static Response put(Playlist requestPlaylist, String playlistID){
		return ApplicationApiResources.put(PLAYLISTS + "/" + playlistID, getToken(), requestPlaylist );

	}

}
