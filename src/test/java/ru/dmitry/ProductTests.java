package ru.dmitry;

import org.testng.annotations.Test;
import ru.dmitry.endpoints.CityEndpoint;
import ru.dmitry.endpoints.ProductEndpoint;

public class ProductTests {

    @Test(description = "Проверка что в городе 'Алматы' вес всех продуктов в наличии > 0")
    public static void checkAvailable() {
        System.out.println("Получение Id 'Алматы'");
        int cityId = CityEndpoint.getInstance().getCityIdByName("Алматы");
        System.out.println("Проверка веса продуктов");
        ProductEndpoint.getInstance().getAvailableStatus(cityId);
    }
}
