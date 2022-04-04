package com.spotify.ouath2.tests;

import static com.spotify.ouath2.api.SpecBuilder.getRequestSpec;
import static com.spotify.ouath2.api.SpecBuilder.getResponseSpec;
import static com.spotify.ouath2.utils.FakerUtils.generateDescritpion;
import static com.spotify.ouath2.utils.FakerUtils.generateName;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.spotify.ouath2.api.StatusCode;
import com.spotify.ouath2.api.applicationApi.ApplicationApi;
import com.spotify.ouath2.pojo.Error;
import com.spotify.ouath2.pojo.Playlist;
import com.spotify.ouath2.utils.DataLoader;

import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

/**
 * @author Mihajlo Gorunovic (m.gorunovic@levi9.com)
 * @since $(Date)
 */
public class PlaylistTests extends BaseTest {



	@Description("this is description")
	@Test (description = "Should be able to create playlist")
	public void createPlaylist(){
		Playlist requestPlaylist =  playlistbuilder(generateName(),generateDescritpion(), false);
		Response response = ApplicationApi.post(requestPlaylist);

		assertStatusCode(response.statusCode(), 201);
		assertPlaylistEqual(response.as(Playlist.class), requestPlaylist);



	}

	@Test
	public void shouldBeAbleToFetchPlaylist(){
		Playlist requestPlaylist = playlistbuilder(generateName(),generateDescritpion(), false );
		Response response = ApplicationApi.get(DataLoader.getInstance().getPlayListID());

		assertStatusCode(response.statusCode(), 200);
		assertPlaylistEqual(response.as(Playlist.class), requestPlaylist);

	}
	@Test
	public void shouldBeAbleToUpdatePlaylist(){
		Playlist requestPlaylist = playlistbuilder(generateName(),generateDescritpion(), false );
		Response response = ApplicationApi.put(requestPlaylist, DataLoader.getInstance().getUpdatePlayListID());

		assertStatusCode(response.statusCode(), SC_OK);



	}

	@Test
	public void userShouldNotBeAbleTocreatePlaylistWithName(){
		Playlist requestPlaylist = playlistbuilder("", "New playlist description", false );
		Response response = ApplicationApi.post(requestPlaylist);
		Error error = response.as(Error.class);

		assertStatusCode(response.statusCode(), StatusCode.CODE400.getCode());
		assertError(response.as(Error.class), StatusCode.CODE400.getCode(), StatusCode.CODE400.getMsg());


	}
	@Test
	public void userShouldNotBeAbleTocreatePlaylistWithExpiredToken(){
		Playlist requestPlaylist = playlistbuilder("New Playlist", "New playlist description", false );
		String token = "123";
		Response response = ApplicationApi.post(token, requestPlaylist);

		assertStatusCode(response.statusCode(), SC_UNAUTHORIZED);
		assertError(response.as(Error.class), 401, "Invalid access token");

	}
	public Playlist playlistbuilder(String name, String description, boolean _public){
		return Playlist.builder().
			name(name).
			description(description).
			_public(_public).build();

	}
	public void assertPlaylistEqual(Playlist responsePlayList, Playlist requestPlaylist){
		assertThat(requestPlaylist.getName(), equalTo(requestPlaylist.getName()));
		assertThat(requestPlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
		assertThat(requestPlaylist.get_public(), equalTo(requestPlaylist.get_public()));
	}

	public void assertStatusCode(int actualStatusCode, int expectedStatusCode){
		assertThat(actualStatusCode, equalTo(expectedStatusCode));

	}
	public void assertError(Error responseError, int expectedStatusCode, String expectedMessage){
		assertThat(responseError.getError().getStatus(), equalTo(expectedStatusCode));
		assertThat(responseError.getError().getMessage(), equalTo(expectedMessage));

	}

}
