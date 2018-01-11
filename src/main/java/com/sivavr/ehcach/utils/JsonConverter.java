package com.sivavr.ehcach.utils;

import com.google.gson.Gson;

public final class JsonConverter {

	public static String toJson(Object object) {
		return new Gson().toJson(object);
	}

}
