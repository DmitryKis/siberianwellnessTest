package ru.dmitry.endpoints;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import ru.dmitry.utils.RestUtils;

public class ProductEndpoint {

    private final String url = "https://kz.siberianwellness.com/api/v1/product";

    private static ProductEndpoint instance = new ProductEndpoint();

    private ProductEndpoint() {
    }

    public static ProductEndpoint getInstance() {
        return instance;
    }

    public String getDefaultParameters(int perPage, int currentPage) {
        return "?RegionId=22" +
                "&PerPage=" + perPage +
                "&CurrentPage=" + currentPage +
                "&LanguageId=9";
    }

    public String getParametersWithCityId(int perPage, int currentPage, int cityId) {
        return "?RegionId=22" +
                "&PerPage=" + perPage +
                "&CurrentPage=" + currentPage +
                "&LanguageId=9" +
                "&CityId=" + cityId;
    }

    public void getAvailableStatus(int cityId) {
        int pageCount = 1;
        int perPage = 300;
        int weights, items;
        Response response;
        do {
            response = RestUtils.httpGet(url + getParametersWithCityId(perPage, pageCount, cityId));
            response.then().statusCode(200);
            weights = response.getBody().jsonPath().getList("List.findAll { it.ProductSaldo.Volume > 0 }.Weight").size();
            items = response.getBody().jsonPath().getList("List").size();
            int countWeights = ((pageCount - 1) * perPage) + weights;
            int countItems = ((pageCount - 1) * perPage) + items;
            Assertions.assertEquals(countItems, countWeights,
                    "Не у всех продуктов в наличии вес > 0, из " + countItems + " подходят условию: " + countWeights);
            pageCount++;
        } while (!response.getBody().asString().contains("\"List\":null,"));
    }
}
