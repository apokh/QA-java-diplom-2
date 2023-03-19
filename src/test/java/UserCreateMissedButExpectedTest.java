import api.StepsUser;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import pojo.request.UserRequest;

import static org.apache.http.HttpStatus.SC_FORBIDDEN;

public class UserCreateMissedButExpectedTest {
    public StepsUser steps = new StepsUser();

    @Test
    @DisplayName("Запрос на создание пользователя (провальный) - код ответа: 403")
    @Description(" - поле \"email\" обязательно \n - код ответа: 403")
    public void createUserFailureByEmailIsNullTestStatusCode() {
        UserRequest user = steps.prepareSpecifyUserDataToCreate(null, "1234", "Mr.Null");
        Response response = steps.sendPostRequestCreateUser(user);
        steps.checkStatusCode(response, SC_FORBIDDEN);
    }

    @Test
    @DisplayName("Запрос на создание пользователя (провальный) - \"success\": false")
    @Description(" - поле \"email\" обязательно \n - \"success\": false")
    public void createUserFailureByEmailIsNullTestState() {
        UserRequest user = steps.prepareSpecifyUserDataToCreate(null, "1234", "Mr.Null");
        Response response = steps.sendPostRequestCreateUser(user);
        steps.checkElementEqualTo(response, "success", false);
    }

    @Test
    @DisplayName("Запрос на создание пользователя (провальный) - \"message\"")
    @Description(" - поле \"email\" обязательно \n - \"message\": \"Email, password and name are required fields\"")
    public void createUserFailureByEmailIsNullTestMessage() {
        UserRequest user = steps.prepareSpecifyUserDataToCreate(null, "1234", "Mr.Null");
        Response response = steps.sendPostRequestCreateUser(user);
        steps.checkElementEqualTo(response, "message", "Email, password and name are required fields");
    }



    @Test
    @DisplayName("Запрос на создание пользователя (провальный) - код ответа: 403")
    @Description(" - поле \"email\" обязательно \n - код ответа: 403")
    public void createUserFailureByEmailIsEmptyTestStatusCode() {
        UserRequest user = steps.prepareSpecifyUserDataToCreate("", "1234", "Mr.Null");
        Response response = steps.sendPostRequestCreateUser(user);
        steps.checkStatusCode(response, SC_FORBIDDEN);
    }

    @Test
    @DisplayName("Запрос на создание пользователя (провальный) - \"success\": false")
    @Description(" - поле \"email\" обязательно \n - \"success\": false")
    public void createUserFailureByEmailIsEmptyTestState() {
        UserRequest user = steps.prepareSpecifyUserDataToCreate("", "1234", "Mr.Null");
        Response response = steps.sendPostRequestCreateUser(user);
        steps.checkElementEqualTo(response, "success", false);
    }

    @Test
    @DisplayName("Запрос на создание пользователя (провальный) - \"message\"")
    @Description(" - поле \"email\" обязательно \n - \"message\": \"Email, password and name are required fields\"")
    public void createUserFailureByEmailIsEmptyTestMessage() {
        UserRequest user = steps.prepareSpecifyUserDataToCreate("", "1234", "Mr.Null");
        Response response = steps.sendPostRequestCreateUser(user);
        steps.checkElementEqualTo(response, "message", "Email, password and name are required fields");
    }

    @Test
    @DisplayName("Запрос на создание пользователя (провальный) - код ответа: 403")
    @Description(" - поле \"password\" обязательно \n - код ответа: 403")
    public void createUserFailureByPasswordIsNullTestStatusCode() {
        UserRequest user = steps.prepareSpecifyUserDataToCreate("null@ya.ru", null, "Mr.Null");
        Response response = steps.sendPostRequestCreateUser(user);
        steps.checkStatusCode(response, SC_FORBIDDEN);
    }

    @Test
    @DisplayName("Запрос на создание пользователя (провальный) - \"success\": false")
    @Description(" - поле \"password\" обязательно \n - \"success\": false")
    public void createUserFailureByPasswordIsNullTestState() {
        UserRequest user = steps.prepareSpecifyUserDataToCreate("null@ya.ru", null, "Mr.Null");
        Response response = steps.sendPostRequestCreateUser(user);
        steps.checkElementEqualTo(response, "success", false);
    }

    @Test
    @DisplayName("Запрос на создание пользователя (провальный) - \"message\"")
    @Description(" - поле \"password\" обязательно \n - \"message\": \"Email, password and name are required fields\"")
    public void createUserFailureByPasswordIsNullTestMessage() {
        UserRequest user = steps.prepareSpecifyUserDataToCreate("null@ya.ru", null, "Mr.Null");
        Response response = steps.sendPostRequestCreateUser(user);
        steps.checkElementEqualTo(response, "message", "Email, password and name are required fields");
    }



    @Test
    @DisplayName("Запрос на создание пользователя (провальный) - код ответа: 403")
    @Description(" - поле \"password\" обязательно \n - код ответа: 403")
    public void createUserFailureByPasswordIsEmptyTestStatusCode() {
        UserRequest user = steps.prepareSpecifyUserDataToCreate("null@ya.ru", "", "Mr.Null");
        Response response = steps.sendPostRequestCreateUser(user);
        steps.checkStatusCode(response, SC_FORBIDDEN);
    }

    @Test
    @DisplayName("Запрос на создание пользователя (провальный) - \"success\": false")
    @Description(" - поле \"password\" обязательно \n - \"success\": false")
    public void createUserFailureByPasswordIsEmptyTestState() {
        UserRequest user = steps.prepareSpecifyUserDataToCreate("null@ya.ru", "", "Mr.Null");
        Response response = steps.sendPostRequestCreateUser(user);
        steps.checkElementEqualTo(response, "success", false);
    }

    @Test
    @DisplayName("Запрос на создание пользователя (провальный) - \"message\"")
    @Description(" - поле \"password\" обязательно \n - \"message\": \"Email, password and name are required fields\"")
    public void createUserFailureByPasswordIsEmptyTestMessage() {
        UserRequest user = steps.prepareSpecifyUserDataToCreate("null@ya.ru", "", "Mr.Null");
        Response response = steps.sendPostRequestCreateUser(user);
        steps.checkElementEqualTo(response, "message", "Email, password and name are required fields");
    }

    @Test
    @DisplayName("Запрос на создание пользователя (провальный) - код ответа: 403")
    @Description(" - поле \"name\" обязательно \n - код ответа: 403")
    public void createUserFailureByNameIsNullTestStatusCode() {
        UserRequest user = steps.prepareSpecifyUserDataToCreate("null@ya.ru", "null", null);
        Response response = steps.sendPostRequestCreateUser(user);
        steps.checkStatusCode(response, SC_FORBIDDEN);
    }

    @Test
    @DisplayName("Запрос на создание пользователя (провальный) - \"success\": false")
    @Description(" - поле \"name\" обязательно \n - \"success\": false")
    public void createUserFailureByNameIsNullTestState() {
        UserRequest user = steps.prepareSpecifyUserDataToCreate("null@ya.ru", "null", null);
        Response response = steps.sendPostRequestCreateUser(user);
        steps.checkElementEqualTo(response, "success", false);
    }

    @Test
    @DisplayName("Запрос на создание пользователя (провальный) - \"message\"")
    @Description(" - поле \"name\" обязательно \n - \"message\": \"Email, password and name are required fields\"")
    public void createUserFailureByNameIsNullTestMessage() {
        UserRequest user = steps.prepareSpecifyUserDataToCreate("null@ya.ru", "null", null);
        Response response = steps.sendPostRequestCreateUser(user);
        steps.checkElementEqualTo(response, "message", "Email, password and name are required fields");
    }



    @Test
    @DisplayName("Запрос на создание пользователя (провальный) - код ответа: 403")
    @Description(" - поле \"name\" обязательно \n - код ответа: 403")
    public void createUserFailureByNameIsEmptyTestStatusCode() {
        UserRequest user = steps.prepareSpecifyUserDataToCreate("null@ya.ru", "null", "");
        Response response = steps.sendPostRequestCreateUser(user);
        steps.checkStatusCode(response, SC_FORBIDDEN);
    }

    @Test
    @DisplayName("Запрос на создание пользователя (провальный) - \"success\": false")
    @Description(" - поле \"name\" обязательно \n - \"success\": false")
    public void createUserFailureByNameIsEmptyTestState() {
        UserRequest user = steps.prepareSpecifyUserDataToCreate("null@ya.ru", "null", "");
        Response response = steps.sendPostRequestCreateUser(user);
        steps.checkElementEqualTo(response, "success", false);
    }

    @Test
    @DisplayName("Запрос на создание пользователя (провальный) - \"message\"")
    @Description(" - поле \"name\" обязательно \n - \"message\": \"Email, password and name are required fields\"")
    public void createUserFailureByNameIsEmptyTestMessage() {
        UserRequest user = steps.prepareSpecifyUserDataToCreate("null@ya.ru", "null", "");
        Response response = steps.sendPostRequestCreateUser(user);
        steps.checkElementEqualTo(response, "message", "Email, password and name are required fields");
    }
}
