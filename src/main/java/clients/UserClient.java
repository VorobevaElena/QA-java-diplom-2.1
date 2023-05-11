package clients;

import entity.*;
import api.Api;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static configuration.Configuration.getBaseSpec;
import static io.restassured.RestAssured.given;

public class UserClient extends Api {
    @Step("Создание пользователя")
    public ValidatableResponse createUser(User user) {
        return given()
                .spec(getBaseSpec())
                .body(user)
                .post(API_CREATE_USER)
                .then()
                .log().all();
    }
    @Step("Удаление пользователя")
    public void deleteUser(String accessToken){
        given()
                .spec(getBaseSpec())
                .auth().oauth2(accessToken)
                .delete(API_INFO)
                .then()
                .log().all();
    }
    @Step("Логин пользователя")
    public ValidatableResponse loginUser(Login login){
        return given()
                .spec(getBaseSpec())
                .body(login)
                .post(API_AUTHORIZATION_USER)
                .then()
                .log().all();
    }
    @Step("Изменение данных пользователя")
    public ValidatableResponse updateUserLogin(String accessToken, User user){
        return given()
                .spec(getBaseSpec())
                .auth().oauth2(accessToken)
                .body(user)
                .patch(API_INFO)
                .then()
                .log().all();
    }
    @Step("Выход пользователя")
    public ValidatableResponse updateUserLogout(User user){
        return given()
                .spec(getBaseSpec())
                .body(user)
                .patch(API_INFO)
                .then()
                .log().all();
    }
}