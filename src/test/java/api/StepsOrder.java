package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.Assert;
import pojo.request.OrderRequest;
import pojo.request.UserRequest;
import pojo.response.Ingredient;
import pojo.response.IngredientsResponse;
import pojo.response.OrdersByAllResponse;
import pojo.response.UserCreateSuccess;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class StepsOrder extends BaseSteps {
    private final String INGREDIENTS_GET_PATH = "/api/ingredients";
    private final String ORDERS_PATH = "/api/orders";

    @Step
//    Выполнить GET-запрос на получение списка ингредиентов
    public Ingredient[] getListOfIngredients() {
        IngredientsResponse ingredientsResponse = given().spec(baseApi.baseSpec())
                .get(INGREDIENTS_GET_PATH).as(IngredientsResponse.class);
        return ingredientsResponse.getData();
    }

    @Step
//    Выполнить POST-запрос на создание заказа
    public Response sendPostRequestToCreateOrder(OrderRequest orderRequest) {
        return given().spec(baseApi.baseSpec())
                .body(orderRequest)
                .when()
                .post(ORDERS_PATH);
    }

    @Step
//    Выполнить POST-запрос на создание заказа не забыв представиться
    public Response sendPostAuthorizedRequestToCreateOrder(UserRequest user, OrderRequest orderRequest) {
        Response response = sendPostRequestToLoginAsUser(user);
        String userToken = response.as(UserCreateSuccess.class).getAccessToken().replaceAll("Bearer ","");
        return given().spec(baseApi.baseSpec())
                .auth()
                .oauth2(userToken)
                .body(orderRequest)
                .when()
                .post(ORDERS_PATH);
    }

    @Step
//    Выполнить GET-запрос на получение списка заказов
    public Response sendGetRequestForOrdersByUser() {
        return given().spec(baseApi.baseSpec())
                .get(ORDERS_PATH);
    }

    @Step
//    Выполнить GET-запрос на получение списка заказов
    public Response sendGetAuthorizedRequestForOrdersByUser(UserRequest user) {
        Response response = sendPostRequestToLoginAsUser(user);
        String userToken = response.as(UserCreateSuccess.class).getAccessToken().replaceAll("Bearer ","");
        return given().spec(baseApi.baseSpec())
                .auth()
                .oauth2(userToken)
                .get(ORDERS_PATH);
    }

    @Step
//    Собрать рандомный бургер по количеству ингредиентов
    public OrderRequest createOwnUniqueBurgerByCount(Ingredient[] ingredients, int count) {
        List<String> ingredientsToOrder = new ArrayList<>();
        for(int i = count; i > 0; i--) {
            Integer index = new Random().nextInt(ingredients.length);
            ingredientsToOrder.add(ingredients[index].get_id());
        }
        OrderRequest orderRequest = new OrderRequest(ingredientsToOrder);
        return orderRequest;
    }

    @Step
//    Собрать бургер без ингредиентов
    public OrderRequest createOwnUniqueBurgerByCount(int count) {
        List<String> ingredientsToOrder = new ArrayList<>();
        OrderRequest orderRequest = new OrderRequest(ingredientsToOrder);
        return orderRequest;
    }

    @Step
//    Собрать плохой бургер из ингредиента с кривым хэшем
    public OrderRequest createBadBurger() {
        String badHash = randomString();
        List<String> ingredientsToOrder = new ArrayList<>();
        ingredientsToOrder.add(badHash);
        OrderRequest orderRequest = new OrderRequest(ingredientsToOrder);
        return orderRequest;
    }

    @Step
//    Проверить, что в списке заказов есть хотя бы один заказ
    public void checkOrdersListNotEmpty(Response response) {
        OrdersByAllResponse responseBodyObject = response.as(OrdersByAllResponse.class);
        Assert.assertTrue(responseBodyObject.getOrders().length > 0);
    }
}
