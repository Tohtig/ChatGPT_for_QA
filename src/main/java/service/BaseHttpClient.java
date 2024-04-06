package service;

import io.restassured.config.RedirectConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseHttpClient {
    private final String JSON = "application/json";

    private final RestAssuredConfig config = RestAssuredConfig.newConfig()
            .sslConfig(new SSLConfig().relaxedHTTPSValidation())
            .redirect(new RedirectConfig().followRedirects(true));

    private final RequestSpecification requestSpec = given().config(config).header("Content-Type", JSON);

    protected ValidatableResponse doPostRequest(String uri, Object body) {
        return requestSpec.body(body).post(uri).then();
    }

    protected void doDeleteRequest(String uri, String accessToken) {
        requestSpec.auth().oauth2(accessToken).get(uri);
    }
}