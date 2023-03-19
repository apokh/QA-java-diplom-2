package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.Assert;
import pojo.request.UserRequest;
import pojo.response.UserCreateSuccess;
import pojo.response.UserDataSuccess;

import static io.restassured.RestAssured.given;

public class StepsUser extends BaseSteps {
    private final String USER_CREATE_PATH = "/api/auth/register";
    private final String USER_DATA_PATH = "/api/auth/user";
    private final String USER_DELETE_PATH = "/api/auth/user";

    @Step
//    подготовить простые данные пользователя
    public UserRequest prepareSimpleUserDataToCreate() {
        return new UserRequest("test-data-auto-apokh-15@yandex.ru", "12345678", "auto-apokh-15");
    }

    @Step
//    подготовить специфические данные пользователя
    public UserRequest prepareSpecifyUserDataToCreate(String email, String password, String name) {
        return new UserRequest(email, password, name);
    }

    @Step
//    изменить email пользователя
    public UserRequest updateUserEmail(UserRequest user, String newEmail) {
        user.setEmail(newEmail);
        return user;
    }

    @Step
//    изменить password пользователя
    public UserRequest updateUserPassword(UserRequest user, String newPassword) {
        user.setPassword(newPassword);
        return user;
    }

    @Step
//    изменить name пользователя
    public UserRequest updateUserName(UserRequest user, String newName) {
        user.setName(newName);
        return user;
    }

    @Step
//    Выполнить POST-запрос на создание пользователя
    public Response sendPostRequestCreateUser(UserRequest user) {
        return given().spec(baseApi.baseSpec())
                .body(user)
                .when()
                .post(USER_CREATE_PATH);
    }

    @Step
//    Выполнить GET-запрос на получение данных пользователя
    public Response sendGetRequestForUserData(UserRequest user) {
        Response response = sendPostRequestToLoginAsUser(user);
        String userToken = response.as(UserCreateSuccess.class).getAccessToken().replaceAll("Bearer ","");
        return given().spec(baseApi.baseSpec())
                .auth()
                .oauth2(userToken)
                .get(USER_DATA_PATH);
    }

    @Step
//    Выполнить PATCH-запрос на обновление данных пользователя
    public Response sendPatchRequestForUserDataUpdate(UserRequest userOldData, UserRequest userNewData) {
        Response response = sendPostRequestToLoginAsUser(userOldData);
        String userToken = response.as(UserCreateSuccess.class).getAccessToken().replaceAll("Bearer ","");
        return given().spec(baseApi.baseSpec())
                .auth()
                .oauth2(userToken)
                .body(userNewData)
                .when()
                .patch(USER_DATA_PATH);
    }

    @Step
//    Выполнить неавторизованный PATCH-запрос на обновление данных пользователя
    public Response sendPatchRequestUnauthorizedForUserDataUpdate(UserRequest userNewData) {
        return given().spec(baseApi.baseSpec())
                .body(userNewData)
                .when()
                .patch(USER_DATA_PATH);
    }

    @Step
//    Выполнить DELETE-запрос на удаление пользователя
    public Response sendDeleteRequestToDeleteUser(UserRequest user) {
        Response response = sendPostRequestToLoginAsUser(user);
        String userToken = response.as(UserCreateSuccess.class).getAccessToken().replaceAll("Bearer ","");
        return given().spec(baseApi.baseSpec())
                .auth()
                .oauth2(userToken)
                .delete(USER_DELETE_PATH);
    }

    @Step
//    Сверить данные пользователя в запросе и ответе при создании
    public void checkResponseToCreateUserData(Response response, UserRequest user) {
        UserCreateSuccess responseData = response.as(UserCreateSuccess.class);
        Assert.assertEquals(user.getEmail(), responseData.getUser().getEmail());
        Assert.assertEquals(user.getName(), responseData.getUser().getName());
    }

    @Step
//    Сверить данные пользователя в запросе и ответе при обновлении
    public void checkResponseToUpdateUserData(Response response, UserRequest user) {
        UserDataSuccess responseData = response.as(UserDataSuccess.class);
        Assert.assertEquals(user.getEmail(), responseData.getUser().getEmail());
        Assert.assertEquals(user.getName(), responseData.getUser().getName());
    }
}
