package ru.dmitry.endpoints;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import ru.dmitry.utils.JsonUtils;
import ru.dmitry.utils.RestUtils;

public class CityEndpoint {

    private final String url = "https://kz.siberianwellness.com/api/v1/city";
    private static CityEndpoint instance = new CityEndpoint();

    private CityEndpoint(){}

    public static CityEndpoint getInstance(){
        return instance;
    }
    public String getDefaultParameters(int perPage,int currentPage){
        return "?RegionId=22" +
                "&PerPage=" + perPage +
                "&CurrentPage=" + currentPage;
    }
    public int getCityIdByName(String cityName){
        int pageCount = 1;
        int cityId;
        Response response;
        do{
            response = RestUtils.httpGet(url + getDefaultParameters(20,pageCount));
            response.then().statusCode(200);
            cityId = JsonUtils.getIntByJsonPath(response,"List.find { it.Name.toLowerCase()=='" + cityName.toLowerCase() + "' }.Id");
            pageCount++;
        } while (cityId == -1 && !response.getBody().asString().contains("\"List\":null,"));
        Assertions.assertTrue(cityId > 0, "Город не найден");
        return cityId;
    }
}
