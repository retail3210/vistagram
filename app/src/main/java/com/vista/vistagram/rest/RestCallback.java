package com.vista.vistagram.rest;

import android.widget.Toast;

import com.splunk.mint.Mint;
import com.splunk.mint.MintLogLevel;
import com.vista.vistagram.App;
import com.vista.vistagram.R;
import com.vista.vistagram.rest.apis.AuthService;
import com.vista.vistagram.rest.results.Result;
import com.vista.vistagram.rest.results.TokenResult;

import java.util.HashMap;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.RetrofitError.Kind;
import retrofit.client.Response;

/**
 *
 */
public interface RestCallback<T extends Result> extends Callback<T> {

    void done(T result);

    default void success(T result, Response response) {
        done(result);
    }

    default void failure(RetrofitError error) {
        HashMap<String, Object> data = new HashMap<>();
        int statusCode = error.getResponse().getStatus();

        switch (error.getKind()) {

            case CONVERSION:
            case HTTP:

                // log when failed
                data.put("url", error.getUrl());
                data.put("status", "HTTP " + statusCode);
                data.put("body", error.getBody().toString());
                Mint.logEvent("Request failed - Conversion Error", MintLogLevel.Error, data);

                // notify error to user using user-readable messages
                Result result = error.getKind() == Kind.HTTP ?
                        (Result) error.getBodyAs(Result.class) : null;

                Toast.makeText(App.getContext(),
                        Errors.getErrorMessage(statusCode, result),
                        Toast.LENGTH_LONG).show();
                break;

            // Connectivity Issue
            case NETWORK:
                Toast.makeText(App.getContext(), R.string.error_network, Toast.LENGTH_LONG).show();
                break;

            case UNEXPECTED:
                // additional data
                data.put("url", error.getUrl());
                data.put("status", "HTTP " + statusCode);
                data.put("body", error.getBody().toString());

                Mint.logExceptionMap(data, error);
        }
    }
}
