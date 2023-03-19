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

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;

public class OrderByUserGetTest {
    public StepsOrder steps = new StepsOrder();
    public StepsUser stepsUser = new StepsUser();
    public UserRequest user = stepsUser.prepareSimpleUserDataToCreate();

    @Before
    public void prepareTestData() {
        stepsUser.sendPostRequestCreateUser(user);
    }

    @Test
    @DisplayName("Запрос на получение списка заказов (успешный) - код ответа: 200")
    @Description(" - авторизованный пользователь может просмотреть свои заказы \n - код ответа: 200")
    public void getOrdersByUserSuccessfullyTestStatusCode() {
        Response response = steps.sendGetAuthorizedRequestForOrdersByUser(user);
        steps.checkStatusCode(response, SC_OK);
    }

    @Test
    @DisplayName("Запрос на получение списка заказов (успешный) - \"success\": true")
    @Description(" - авторизованный пользователь может просмотреть свои заказы \n - \"success\": true")
    public void getOrdersByUserSuccessfullyTestState() {
        Response response = steps.sendGetAuthorizedRequestForOrdersByUser(user);
        steps.checkElementEqualTo(response, "success", true);
    }

    @Test
    @DisplayName("Запрос на получение списка заказов (успешный) - содержит заказы")
    @Description(" - авторизованный пользователь может просмотреть свои заказы \n - есть заказы в ответе")
    public void getOrdersByUserSuccessfullyTestHasOrders() {
        Ingredient[] ingredients = steps.getListOfIngredients();
        OrderRequest orderRequest = steps.createOwnUniqueBurgerByCount(ingredients, 3);
        steps.sendPostAuthorizedRequestToCreateOrder(user, orderRequest);
        Response response = steps.sendGetAuthorizedRequestForOrdersByUser(user);
        steps.checkOrdersListNotEmpty(response);
    }

    @Test
    @DisplayName("Запрос на получение списка заказов (провальный) - код ответа: 401")
    @Description(" - для просмотра заказов необходимо авторизоваться \n - код ответа: 401")
    public void getOrdersByUserFailureTestStatusCode() {
        Response response = steps.sendGetRequestForOrdersByUser();
        steps.checkStatusCode(response, SC_UNAUTHORIZED);
    }

    @After
    public void clearTestData() {
        stepsUser.sendDeleteRequestToDeleteUser(user);
    }
}
