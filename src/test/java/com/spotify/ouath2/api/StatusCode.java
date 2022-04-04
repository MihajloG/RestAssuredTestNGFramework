package com.spotify.ouath2.api;

/**
 * @author Mihajlo Gorunovic (m.gorunovic@levi9.com)
 * @since $(Date)
 */
public enum StatusCode {
	CODE_200(200, ""),
	CODE201(201, ""),
	CODE400(400, "Missing required field: name"),
	CODE401(401, "Invalid access token");

	private final int code;
	private final String msg;

	private StatusCode(int code, final String msg) {
		this.code = code;
		this.msg = msg;
	}
	public int getCode(){
		return code;
	}
	public String getMsg(){
		return  msg;
	}
}
