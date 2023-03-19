import api.StepsUser;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojo.request.UserRequest;

import static org.apache.http.HttpStatus.SC_OK;

public class UserUpdateDataSuccessTest {
    public StepsUser steps = new StepsUser();
    public UserRequest user = steps.prepareSimpleUserDataToCreate();
    public UserRequest userNewData = steps.prepareSimpleUserDataToCreate();

    @Before
    public void prepareTestData() {
        steps.sendPostRequestCreateUser(user);
    }

    @Test
    @DisplayName("Запрос на обновление email пользователя (успешный) - код ответа: 200")
    @Description(" - пользователю можно изменить email \n - код ответа: 200")
    public void updateUserEmailSuccessfullyTestStatusCode() {
        String newEmail = steps.randomString().toLowerCase() + "-" + userNewData.getEmail();
        steps.updateUserEmail(userNewData, newEmail);
        Response response = steps.sendPatchRequestForUserDataUpdate(user, userNewData);
        steps.checkStatusCode(response, SC_OK);
    }

    @Test
    @DisplayName("Запрос на обновление email пользователя (успешный) - \"success\": true")
    @Description(" - пользователю можно изменить email \n - \"success\": true")
    public void updateUserEmailSuccessfullyTestState() {
        String newEmail = steps.randomString().toLowerCase() + "-" + userNewData.getEmail();
        steps.updateUserEmail(userNewData, newEmail);
        Response response = steps.sendPatchRequestForUserDataUpdate(user, userNewData);
        steps.checkElementEqualTo(response, "success", true);
    }

    @Test
    @DisplayName("Запрос на обновление email пользователя (успешный) - обновление успешно")
    @Description(" - ответ содержит \"email\" со значением из запроса \n" +
            " - ответ содержит \"name\" со значением из запроса \n" +
            " - пользователю можно изменить email \n")
    public void updateUserEmailSuccessfullyTestData() {
        String newEmail = steps.randomString().toLowerCase() + "-" + userNewData.getEmail();
        steps.updateUserEmail(userNewData, newEmail);
        Response response = steps.sendPatchRequestForUserDataUpdate(user, userNewData);
        steps.checkResponseToUpdateUserData(response, userNewData);
    }

    @Test
    @DisplayName("Запрос на обновление password пользователя (успешный) - код ответа: 200")
    @Description(" - пользователю можно изменить password \n - код ответа: 200")
    public void updateUserPasswordSuccessfullyTestStatusCode() {
        String newPassword = steps.randomString() + "-" + userNewData.getPassword();
        steps.updateUserPassword(userNewData, newPassword);
        Response response = steps.sendPatchRequestForUserDataUpdate(user, userNewData);
        steps.checkStatusCode(response, SC_OK);
    }

    @Test
    @DisplayName("Запрос на обновление password пользователя (успешный) - \"success\": true")
    @Description(" - пользователю можно изменить password \n - \"success\": true")
    public void updateUserPasswordSuccessfullyTestState() {
        String newPassword = steps.randomString() + "-" + userNewData.getPassword();
        steps.updateUserPassword(userNewData, newPassword);
        Response response = steps.sendPatchRequestForUserDataUpdate(user, userNewData);
        steps.checkElementEqualTo(response, "success", true);
    }

    @Test
    @DisplayName("Запрос на обновление password пользователя (успешный) - обновление успешно")
    @Description(" - пользователю можно изменить password \n" +
            " - пользователь успешно авторизовался с новым password")
    public void updateUserPasswordSuccessfullyTestData() {
        String newPassword = steps.randomString() + "-" + userNewData.getPassword();
        steps.updateUserPassword(userNewData, newPassword);
        steps.sendPatchRequestForUserDataUpdate(user, userNewData);
        Response response = steps.sendPostRequestToLoginAsUser(userNewData);
        steps.checkStatusCode(response, SC_OK);
    }

    @Test
    @DisplayName("Запрос на обновление name пользователя (успешный) - код ответа: 200")
    @Description(" - пользователю можно изменить name \n - код ответа: 200")
    public void updateUserNameSuccessfullyTestStatusCode() {
        String newName = userNewData.getName() + "-" + steps.randomString();
        steps.updateUserName(userNewData, newName);
        Response response = steps.sendPatchRequestForUserDataUpdate(user, userNewData);
        steps.checkStatusCode(response, SC_OK);
    }

    @Test
    @DisplayName("Запрос на обновление name пользователя (успешный) - \"success\": true")
    @Description(" - пользователю можно изменить name \n - \"success\": true")
    public void updateUserNameSuccessfullyTestState() {
        String newName = userNewData.getName() + "-" + steps.randomString();
        steps.updateUserName(userNewData, newName);
        Response response = steps.sendPatchRequestForUserDataUpdate(user, userNewData);
        steps.checkElementEqualTo(response, "success", true);
    }

    @Test
    @DisplayName("Запрос на обновление name пользователя (успешный) - обновление успешно")
    @Description(" - ответ содержит \"email\" со значением из запроса \n" +
            " - ответ содержит \"name\" со значением из запроса \n" +
            " - пользователю можно изменить name \n")
    public void updateUserNameSuccessfullyTestData() {
        String newName = userNewData.getName() + "-" + steps.randomString();
        steps.updateUserName(userNewData, newName);
        Response response = steps.sendPatchRequestForUserDataUpdate(user, userNewData);
        steps.checkResponseToUpdateUserData(response, userNewData);
    }

    @After
    public void clearTestData() {
        steps.sendDeleteRequestToDeleteUser(userNewData);
    }
}
