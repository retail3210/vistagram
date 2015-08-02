package com.vista.vistagram.rest.apis;

import com.vista.vistagram.models.Article;
import com.vista.vistagram.rest.RestClient;
import com.vista.vistagram.rest.results.ArticleList;
import com.vista.vistagram.rest.results.Result;

import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.PATCH;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 *
 */
public interface ArticleService {
    static ArticleService create() {
        return RestClient.getAdapter().create(ArticleService.class);
    }

    @GET("/article")
    Observable<ArticleList> getMainFeed(@Query("index") int index, @Query("size") int count);

    @POST("/article")
    Observable<ArticleList> write(@Body Article article);

    @GET("/article/{id}")
    Observable<Article> get(@Path("id") String articleId);

    @PATCH("/article/{id}")
    Observable<Result> edit(@Path("id") String articleId, @Body Article article);

    @DELETE("/article/{id}")
    Observable<Result> delete(@Path("id") String articleId);
}
