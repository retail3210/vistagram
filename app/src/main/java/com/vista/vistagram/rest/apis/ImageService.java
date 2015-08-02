package com.vista.vistagram.rest.apis;

import com.vista.vistagram.App;
import com.vista.vistagram.models.Article;
import com.vista.vistagram.rest.RestClient;
import com.vista.vistagram.rest.results.ArticleList;
import com.vista.vistagram.rest.results.ImageResult;
import com.vista.vistagram.rest.results.Result;

import java.io.File;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.PATCH;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.mime.TypedFile;
import rx.Observable;

/**
 *
 */
public interface ImageService {

    static ImageService create() {
        return RestClient.getAdapter().create(ImageService.class);
    }

    @POST("/image")
    @Multipart
    Observable<ImageResult> upload(@Part("image") TypedFile image);
}
