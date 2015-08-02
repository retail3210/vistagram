package com.vista.vistagram.rest;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.vista.vistagram.App;
import com.vista.vistagram.Constants;
import com.vista.vistagram.rest.results.TokenResult;

import java.io.IOException;

/**
 * Copyright (C) 2015 Vista. All rights are reserved.
 *
 * Automatically loads access token to the request and
 * will refresh session and retry the request if the session is expired.
 */
public class SessionRequestInterceptor implements Interceptor {

    private static final String REFRESH_URL = Constants.ENDPOINT + "/auth/refresh";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (Session.isLoggedIn()) {
            // add access token
            request = request.newBuilder()
                    .addHeader("Access-Token", Session.getAccessToken())
                    .build();
        }

        Response response = chain.proceed(request);

        // is the session expired?
        if (response.code() == 401) {

            // get the token
            Request tokenRequest = new Request.Builder()
                    .url(REFRESH_URL)
                    .addHeader("Access-Token", Session.getAccessToken())
                    .addHeader("Refresh-Token", Session.getRefreshToken())
                    .build();

            Response tokenResponse = RestClient.getHttpClient().newCall(tokenRequest).execute();
            if (!tokenResponse.isSuccessful()) return response; // RestCallback will log error.

            // refresh the session
            TokenResult result = App.getGson()
                    .fromJson(tokenResponse.body().string(), TokenResult.class);
            Session.refreshSession(result.accessToken);

            // retry old request with new session
            Request newRequest = request.newBuilder()
                    .addHeader("Access-Token", Session.getAccessToken())
                    .build();

            // go with brand new fresh request
            return chain.proceed(newRequest);
        }

        // otherwise just go with it
        return response;
    }
}
