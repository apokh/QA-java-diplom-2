import api.StepsUser;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojo.request.UserRequest;

import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;

public class UserUpdateDataUnauthorizedTest {
    public StepsUser steps = new StepsUser();
    public UserRequest user = steps.prepareSimpleUserDataToCreate();
    public UserRequest userNewData = steps.prepareSimpleUserDataToCreate();

    @Before
    public void prepareTestData() {
        steps.sendPostRequestCreateUser(user);
    }

    @Test
    @DisplayName("Запрос на обновление email пользователя (провальный) - код ответа: 401")
    @Description(" - только авторизованный пользователь может изменить email \n - код ответа: 401")
    public void updateUserEmailSuccessfullyTestStatusCode() {
        String newEmail = steps.randomString().toLowerCase() + "-" + userNewData.getEmail();
        steps.updateUserEmail(userNewData, newEmail);
        Response response = steps.sendPatchRequestUnauthorizedForUserDataUpdate(userNewData);
        steps.checkStatusCode(response, SC_UNAUTHORIZED);
    }

    @Test
    @DisplayName("Запрос на обновление email пользователя (провальный) - \"success\": false")
    @Description(" - только авторизованный пользователь может изменить email \n - \"success\": false")
    public void updateUserEmailSuccessfullyTestState() {
        String newEmail = steps.randomString().toLowerCase() + "-" + userNewData.getEmail();
        steps.updateUserEmail(userNewData, newEmail);
        Response response = steps.sendPatchRequestUnauthorizedForUserDataUpdate(userNewData);
        steps.checkElementEqualTo(response, "success", false);
    }

    @Test
    @DisplayName("Запрос на обновление email пользователя (провальный) - \"message\": \"You should be authorised\"")
    @Description(" - \"message\": \"You should be authorised\"")
    public void updateUserEmailSuccessfullyTestMessage() {
        String newEmail = steps.randomString().toLowerCase() + "-" + userNewData.getEmail();
        steps.updateUserEmail(userNewData, newEmail);
        Response response = steps.sendPatchRequestUnauthorizedForUserDataUpdate(userNewData);
        steps.checkElementEqualTo(response, "message", "You should be authorised");
    }

    @Test
    @DisplayName("Запрос на обновление password пользователя (провальный) - код ответа: 401")
    @Description(" - только авторизованный пользователь может изменить password \n - код ответа: 401")
    public void updateUserPasswordSuccessfullyTestStatusCode() {
        String newPassword = steps.randomString() + "-" + userNewData.getPassword();
        steps.updateUserPassword(userNewData, newPassword);
        Response response = steps.sendPatchRequestUnauthorizedForUserDataUpdate(userNewData);
        steps.checkStatusCode(response, SC_UNAUTHORIZED);
    }

    @Test
    @DisplayName("Запрос на обновление password пользователя (провальный) - \"success\": false")
    @Description(" - только авторизованный пользователь может изменить password \n - \"success\": false")
    public void updateUserPasswordSuccessfullyTestState() {
        String newPassword = steps.randomString() + "-" + userNewData.getPassword();
        steps.updateUserPassword(userNewData, newPassword);
        Response response = steps.sendPatchRequestUnauthorizedForUserDataUpdate(userNewData);
        steps.checkElementEqualTo(response, "success", false);
    }

    @Test
    @DisplayName("Запрос на обновление password пользователя (провальный) - необходима авторизация")
    @Description(" - только авторизованному пользователю можно изменить password \n" +
            " - пользователь не может авторизоваться с новым password")
    public void updateUserPasswordSuccessfullyTestData() {
        String newPassword = steps.randomString() + "-" + userNewData.getPassword();
        steps.updateUserPassword(userNewData, newPassword);
        steps.sendPatchRequestUnauthorizedForUserDataUpdate(userNewData);
        Response response = steps.sendPostRequestToLoginAsUser(userNewData);
        steps.checkStatusCode(response, SC_UNAUTHORIZED);
    }

    @Test
    @DisplayName("Запрос на обновление name пользователя (провальный) - код ответа: 401")
    @Description(" - только авторизованный пользователь может изменить name \n - код ответа: 401")
    public void updateUserNameSuccessfullyTestStatusCode() {
        String newName = userNewData.getName() + "-" + steps.randomString();
        steps.updateUserName(userNewData, newName);
        Response response = steps.sendPatchRequestUnauthorizedForUserDataUpdate(userNewData);
        steps.checkStatusCode(response, SC_UNAUTHORIZED);
    }

    @Test
    @DisplayName("Запрос на обновление name пользователя (провальный) - \"success\": false")
    @Description(" - только авторизованный пользователь может изменить name \n - \"success\": false")
    public void updateUserNameSuccessfullyTestState() {
        String newName = userNewData.getName() + "-" + steps.randomString();
        steps.updateUserName(userNewData, newName);
        Response response = steps.sendPatchRequestUnauthorizedForUserDataUpdate(userNewData);
        steps.checkElementEqualTo(response, "success", false);
    }

    @Test
    @DisplayName("Запрос на обновление name пользователя (провальный) - \"message\": \"You should be authorised\"")
    @Description(" - \"message\": \"You should be authorised\"")
    public void updateUserNameSuccessfullyTestMessage() {
        String newName = userNewData.getName() + "-" + steps.randomString();
        steps.updateUserName(userNewData, newName);
        Response response = steps.sendPatchRequestUnauthorizedForUserDataUpdate(userNewData);
        steps.checkElementEqualTo(response, "message", "You should be authorised");
    }

    @After
    public void clearTestData() {
        steps.sendDeleteRequestToDeleteUser(user);
    }
}