package com.vista.vistagram.rest.apis;

import com.vista.vistagram.models.User;
import com.vista.vistagram.rest.RestCallback;
import com.vista.vistagram.rest.RestClient;
import com.vista.vistagram.rest.results.TokenResult;

import retrofit.http.POST;

/**
 *
 */
public interface AuthService {
    static AuthService create() {
        return RestClient.getAdapter().create(AuthService.class);
    }

    @POST("/auth/refresh")
    void refresh(RestCallback<TokenResult> cb);

    @POST("/auth/login")
    void login(String email, String pw, RestCallback<TokenResult> cb);

    @POST("/auth/login/facebook")
    void login(String facebookId, RestCallback<TokenResult> cb);

    @POST("/auth/register")
    void register(User user, RestCallback<TokenResult> cb);
}
