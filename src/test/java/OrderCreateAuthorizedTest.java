import api.StepsOrder;
import api.StepsUser;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojo.request.OrderRequest;
import pojo.request.UserRequest;
import pojo.response.Ingredient;

import static org.apache.http.HttpStatus.*;

public class OrderCreateAuthorizedTest {
    public StepsOrder steps = new StepsOrder();
    public StepsUser stepsUser = new StepsUser();
    public UserRequest user = stepsUser.prepareSimpleUserDataToCreate();

    @Before
    public void prepareTestData() {
        stepsUser.sendPostRequestCreateUser(user);
    }

    @Test
    @DisplayName("Запрос на создание заказа (успешный) - код ответа: 200")
    @Description(" - заказ можно создать \n - код ответа: 200")
    public void createOrderSuccessfullyTestStatusCode() {
        Ingredient[] ingredients = steps.getListOfIngredients();
        OrderRequest orderRequest = steps.createOwnUniqueBurgerByCount(ingredients, 3);
        Response response = steps.sendPostAuthorizedRequestToCreateOrder(user, orderRequest);
        steps.checkStatusCode(response, SC_OK);
    }

    @Test
    @DisplayName("Запрос на создание заказа (успешный) - \"success\": true")
    @Description(" - заказ можно создать \n - \"success\": true")
    public void createOrderSuccessfullyTestState() {
        Ingredient[] ingredients = steps.getListOfIngredients();
        OrderRequest orderRequest = steps.createOwnUniqueBurgerByCount(ingredients, 3);
        Response response = steps.sendPostAuthorizedRequestToCreateOrder(user, orderRequest);
        steps.checkElementEqualTo(response, "success", true);
    }

    @Test
    @DisplayName("Запрос на создание заказа (успешный) - в ответе непустое name")
    @Description(" - заказ можно создать \n - в ответе непустое name")
    public void createOrderSuccessfullyHasName() {
        Ingredient[] ingredients = steps.getListOfIngredients();
        OrderRequest orderRequest = steps.createOwnUniqueBurgerByCount(ingredients, 3);
        Response response = steps.sendPostAuthorizedRequestToCreateOrder(user, orderRequest);
        steps.checkElementNotNullValue(response, "name");
    }

    @Test
    @DisplayName("Запрос на создание заказа (успешный) - в ответе непустое order")
    @Description(" - заказ можно создать \n - в ответе непустое order")
    public void createOrderSuccessfullyHasOrder() {
        Ingredient[] ingredients = steps.getListOfIngredients();
        OrderRequest orderRequest = steps.createOwnUniqueBurgerByCount(ingredients, 3);
        Response response = steps.sendPostAuthorizedRequestToCreateOrder(user, orderRequest);
        steps.checkElementNotNullValue(response, "order");
    }

    @Test
    @DisplayName("Запрос на создание заказа (провальный) - код ответа: 400")
    @Description(" - не бывает бургеров без ингредиентов \n - код ответа: 400")
    public void createOrderWithoutIngredientsFailureTestStatusCode() {
        OrderRequest orderRequest = steps.createOwnUniqueBurgerByCount(0);
        Response response = steps.sendPostAuthorizedRequestToCreateOrder(user, orderRequest);
        steps.checkStatusCode(response, SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("Запрос на создание заказа (провальный) - \"success\": false")
    @Description(" - не бывает бургеров без ингредиентов \n - \"success\": false")
    public void createOrderWithoutIngredientsFailureTestState() {
        OrderRequest orderRequest = steps.createOwnUniqueBurgerByCount(0);
        Response response = steps.sendPostAuthorizedRequestToCreateOrder(user, orderRequest);
        steps.checkElementEqualTo(response, "success", false);
    }

    @Test
    @DisplayName("Запрос на создание заказа (провальный) - \"message\": \"Ingredient ids must be provided\"")
    @Description(" - не бывает бургеров без ингредиентов \n - \"message\": \"Ingredient ids must be provided\"")
    public void createOrderWithoutIngredientsFailureTestMessage() {
        OrderRequest orderRequest = steps.createOwnUniqueBurgerByCount(0);
        Response response = steps.sendPostAuthorizedRequestToCreateOrder(user, orderRequest);
        steps.checkElementEqualTo(response, "message", "Ingredient ids must be provided");
    }

    @Test
    @DisplayName("Запрос на создание заказа (провальный) - код ответа: 500")
    @Description(" - ингредиент не найден \n - код ответа: 500")
    public void createOrderWithBadIngredientFailureTestStatusCode() {
        OrderRequest orderRequest = steps.createBadBurger();
        Response response = steps.sendPostAuthorizedRequestToCreateOrder(user, orderRequest);
        steps.checkStatusCode(response, SC_INTERNAL_SERVER_ERROR);
    }

    @After
    public void clearTestData() {
        stepsUser.sendDeleteRequestToDeleteUser(user);
    }
}
