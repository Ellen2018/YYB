package com.ellen.yyb.helper;

import com.google.gson.Gson;

public class GsonHelper {

    private static Gson gson = new Gson();

    public static Gson getGsonInstance(){
        return gson;
    }

}
