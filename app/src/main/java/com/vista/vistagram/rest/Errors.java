package com.vista.vistagram.rest;

import com.vista.vistagram.App;
import com.vista.vistagram.R;
import com.vista.vistagram.rest.results.Result;

import java.util.HashMap;

/**
 * Predefined network / server error messeages.
 */
public class Errors {

    private static final HashMap<Integer, Integer> ERROR_MSGS = new HashMap<>();
    static {
        // TODO: 여기에 에러들을 추가하시오.
        ERROR_MSGS.put(500, R.string.error_internal_server);
        ERROR_MSGS.put(404, R.string.error_not_found);
    }

    private static String getOrDefault(int code) {
        if (ERROR_MSGS.containsKey(code))
            return App.getContext().getResources().getString(ERROR_MSGS.get(code));
        else return App.getContext().getResources().getString(ERROR_MSGS.get(500));
    }

    public static String getErrorMessage(int httpStatus, Result result) {
        if (httpStatus == 500 && result != null && ERROR_MSGS.containsKey(result.code))
            return getOrDefault(result.code);
        return getOrDefault(httpStatus);
    }
}
