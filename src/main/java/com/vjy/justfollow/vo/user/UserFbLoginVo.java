package com.vjy.justfollow.vo.user;

public class UserFbLoginVo {


	private String fbId ;

	private String fbToken ;

	private int statusCode;


	public UserFbLoginVo() {
	}

	public UserFbLoginVo(String fbId, String fbToken) {
		this.fbId = fbId;
		this.fbToken = fbToken;
	}

	public UserFbLoginVo(int statusCode, String fbId) {
		super();

		this.statusCode = statusCode;
		this.fbId = fbId;
	}


	public String getFbToken() {
		return fbToken;
	}

	public void setFbToken(String fbToken) {
		this.fbToken = fbToken;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getFbId() {
		return fbId;
	}

	public void setFbId(String fbId) {
		this.fbId = fbId;
	}
}
