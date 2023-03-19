import api.StepsUser;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojo.request.UserRequest;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;

public class UserLoginTest {
    public StepsUser steps = new StepsUser();
    public UserRequest user = steps.prepareSimpleUserDataToCreate();

    @Before
    public void prepareTestData() {
        steps.sendPostRequestCreateUser(user);
    }

    @Test
    @DisplayName("Запрос на авторизацию пользователем (успешный) - код ответа: 200")
    @Description(" - пользователь может авторизоваться в системе \n - код ответа: 200")
    public void loginUserSuccessfullyTestStatusCode() {
        Response response = steps.sendPostRequestToLoginAsUser(user);
        steps.checkStatusCode(response, SC_OK);
    }

    @Test
    @DisplayName("Запрос на авторизацию пользователем (успешный) - \"success\": true")
    @Description(" - пользователь может авторизоваться в системе \n - ответ содержит \"success\": true")
    public void loginUserSuccessfullyTestState() {
        Response response = steps.sendPostRequestToLoginAsUser(user);
        steps.checkElementEqualTo(response, "success", true);
    }

    @Test
    @DisplayName("Запрос на авторизацию пользователем (успешный) - данные пользователя соответствуют запросу")
    @Description(" - ответ содержит \"email\" со значением из запроса \n" +
            " - ответ содержит \"name\" со значением из запроса \n" +
            " - пользователь может авторизоваться в системе \n")
    public void loginUserSuccessfullyTestUserData() {
        Response response = steps.sendPostRequestToLoginAsUser(user);
        steps.checkResponseToCreateUserData(response, user);
    }

    @Test
    @DisplayName("Запрос на авторизацию пользователем (провальный) - код ответа: 401")
    @Description(" - несуществующий пользователь не может авторизоваться в системе \n - код ответа: 401")
    public void loginUnregisteredUserFailureTestStatusCode() {
        String randomString = steps.randomString();
        UserRequest userUnregistered = steps.prepareSpecifyUserDataToCreate("unregistered" + randomString + "@ya.ru", "12345678", "unregistered" + randomString);
        Response response = steps.sendPostRequestToLoginAsUser(userUnregistered);
        steps.checkStatusCode(response, SC_UNAUTHORIZED);
    }

    @Test
    @DisplayName("Запрос на авторизацию пользователем (провальный) - \"success\": false")
    @Description(" - несуществующий пользователь не может авторизоваться в системе \n - ответ содержит \"success\": false")
    public void loginUnregisteredUserFailureTestState() {
        String randomString = steps.randomString();
        UserRequest userUnregistered = steps.prepareSpecifyUserDataToCreate("unregistered" + randomString + "@ya.ru", "12345678", "unregistered" + randomString);
        Response response = steps.sendPostRequestToLoginAsUser(userUnregistered);
        steps.checkElementEqualTo(response, "success", false);
    }

    @Test
    @DisplayName("Запрос на авторизацию пользователем (провальный) - \"message\": \"email or password are incorrect\"")
    @Description(" - несуществующий пользователь не может авторизоваться в системе \n - ответ содержит \"message\": \"email or password are incorrect\"")
    public void createUserFailureTestMessage() {
        String randomString = steps.randomString();
        UserRequest userUnregistered = steps.prepareSpecifyUserDataToCreate("unregistered" + randomString + "@ya.ru", "12345678", "unregistered" + randomString);
        Response response = steps.sendPostRequestToLoginAsUser(userUnregistered);
        steps.checkElementEqualTo(response, "message", "email or password are incorrect");
    }

    @After
    public void clearTestData() {
        steps.sendDeleteRequestToDeleteUser(user);
    }
}
