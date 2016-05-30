package com.onlineinteract.model;

public class URL {
	String url;
	String nameOfTheService;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getNameOfTheService() {
		return nameOfTheService;
	}

	public void setNameOfTheService(String nameOfTheService) {
		this.nameOfTheService = nameOfTheService;
	}

	@Override
	public String toString() {
		return "URL [url=" + url + "]";
	}
}