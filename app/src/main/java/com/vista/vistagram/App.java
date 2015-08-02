package com.vista.vistagram;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.splunk.mint.Mint;
import com.vista.vistagram.rest.RestClient;
import com.vista.vistagram.rest.Session;
import com.vista.vistagram.rest.apis.ArticleService;
import com.vista.vistagram.rest.apis.AuthService;
import com.vista.vistagram.utils.ImageUrl;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * The abstraction of the app's entire lifecycle.
 * Initializes some modules.
 */
public class App extends Application {

    private static App context;
    private static Gson gson;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        initGson();
        RestClient.init();
        Session.init();

        Mint.disableNetworkMonitoring();
        Mint.initAndStartSession(this, Constants.MINT_KEY);
    }

    private static void initGson() {
        GsonBuilder builder = new GsonBuilder();

        // Register an adapter to manage the date types as long values
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonElement json, Type type, JsonDeserializationContext context)
                    throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        });
        gson = builder.create();
    }

    /**
     * @return The application context
     */
    public static App getContext() {
        return context;
    }


    /**
     * @return The GSON instance
     */
    public static Gson getGson() {
        return gson;
    }
}
