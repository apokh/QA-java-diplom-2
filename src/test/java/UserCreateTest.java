import api.StepsUser;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import pojo.request.UserRequest;

import static org.apache.http.HttpStatus.*;

public class UserCreateTest {
    public StepsUser steps = new StepsUser();
    public UserRequest user = steps.prepareSimpleUserDataToCreate();

    @Test
    @DisplayName("Запрос на создание пользователя (успешный) - код ответа: 200")
    @Description(" - пользователя можно создать \n - код ответа: 200")
    public void createUserSuccessfullyTestStatusCode() {
        Response response = steps.sendPostRequestCreateUser(user);
        steps.checkStatusCode(response, SC_OK);
    }

    @Test
    @DisplayName("Запрос на создание пользователя (успешный) - \"success\": true")
    @Description(" - пользователя можно создать \n - ответ содержит \"success\": true")
    public void createUserSuccessfullyTestState() {
        Response response = steps.sendPostRequestCreateUser(user);
        steps.checkElementEqualTo(response, "success", true);
    }

    @Test
    @DisplayName("Запрос на создание пользователя (успешный) - данные пользователя соответствуют запросу")
    @Description(" - ответ содержит \"email\" со значением из запроса \n" +
                " - ответ содержит \"name\" со значением из запроса \n" +
                " - пользователя можно создать \n")
    public void createUserSuccessfullyTestUserData() {
        Response response = steps.sendPostRequestCreateUser(user);
        steps.checkResponseToCreateUserData(response, user);
    }

    @Test
    @DisplayName("Запрос на создание пользователя (провальный) - код ответа: 403")
    @Description(" - нельзя создать двух одинаковых пользователей \n - код ответа: 403")
    public void createUserFailureTestStatusCode() {
        steps.sendPostRequestCreateUser(user);
        Response response = steps.sendPostRequestCreateUser(user);
        steps.checkStatusCode(response, SC_FORBIDDEN);
    }

    @Test
    @DisplayName("Запрос на создание пользователя (провальный) - \"success\": false")
    @Description(" - нельзя создать двух одинаковых пользователей \n - ответ содержит \"success\": false")
    public void createUserFailureTestState() {
        steps.sendPostRequestCreateUser(user);
        Response response = steps.sendPostRequestCreateUser(user);
        steps.checkElementEqualTo(response, "success", false);
    }

    @Test
    @DisplayName("Запрос на создание пользователя (провальный) - \"message\": \"User already exists\"")
    @Description(" - нельзя создать двух одинаковых пользователей \n - ответ содержит \"message\": \"User already exists\"")
    public void createUserFailureTestMessage() {
        steps.sendPostRequestCreateUser(user);
        Response response = steps.sendPostRequestCreateUser(user);
        steps.checkElementEqualTo(response, "message", "User already exists");
    }


    @After
    public void clearTestData() {
        steps.sendDeleteRequestToDeleteUser(user);
    }
}
