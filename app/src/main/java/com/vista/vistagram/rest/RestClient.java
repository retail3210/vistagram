package com.vista.vistagram.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.squareup.okhttp.OkHttpClient;
import com.vista.vistagram.App;
import com.vista.vistagram.Constants;

import java.lang.reflect.Type;
import java.util.Date;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 *
 */
public class RestClient {
    private static RestAdapter restAdapter;
    private static OkHttpClient httpClient;

    public static void init() {
        // build HTTP (OkHttp) client
        httpClient = new OkHttpClient();
        httpClient.interceptors().add(new SessionRequestInterceptor());

        // build REST (Retrofit) client
        restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.ENDPOINT)
                .setConverter(new GsonConverter(App.getGson()))
                .setLogLevel(RestAdapter.LogLevel.BASIC)
                .setClient(new OkClient(httpClient))
                .build();

    }

    public static OkHttpClient getHttpClient() {
        return httpClient;
    }

    public static RestAdapter getAdapter() {
        return restAdapter;
    }

}
