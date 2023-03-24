package ru.dmitry.utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static int getIntByJsonPath(Response response, String path){
        JsonPath jsonPath = new JsonPath(response.getBody().asString());
        try {
            return jsonPath.getInt(path);
        } catch (NullPointerException e){
            return  -1;
        }
    }

    public static List<Integer> getListIntByJsonPath(Response response, String path){
        JsonPath jsonPath = new JsonPath(response.getBody().asString());
        try {
            return jsonPath.getList(path);
        } catch (NullPointerException e){
            return new ArrayList<>();
        }
    }
}
