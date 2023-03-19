package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojo.request.UserRequest;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class BaseSteps {
    public BaseApi baseApi = new BaseApi();
    private final String USER_LOGIN_PATH = "/api/auth/login";

    @Step
//    Выполнить POST-запрос на авторизацию пользователем
    public Response sendPostRequestToLoginAsUser(UserRequest user) {
        return given().spec(baseApi.baseSpec())
                .body(user)
                .when()
                .post(USER_LOGIN_PATH);
    }

    @Step
//    Сгенерировать случайную строку
    public String randomString() {
        return new String("Some" + new Random().nextInt(999));
    }

    @Step
//    Проверить код ответа сервера
    public void checkStatusCode(Response response, int statusCode) {
        response.then().assertThat()
                .statusCode(statusCode);
    }

    @Step
//    Проверить, что элемент не пустой
    public void checkElementNotNullValue(Response response, String elementName) {
        response.then().assertThat()
                .body(elementName, notNullValue());
    }

    @Step
//    Проверить значение элемента
    public void checkElementEqualTo(Response response, String elementPath, String valueExpected) {
        response.then()
                .assertThat().body(elementPath, equalTo(valueExpected));
    }

    @Step
//    Проверить значение элемента
    public void checkElementEqualTo(Response response, String elementPath, Boolean valueExpected) {
        response.then()
                .assertThat().body(elementPath, equalTo(valueExpected));
    }
}