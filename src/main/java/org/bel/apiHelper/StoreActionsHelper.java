package org.bel.apiHelper;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.bel.models.ApiResponse;
import org.bel.models.Order;

import static io.restassured.RestAssured.given;
import static org.bel.apiHelper.Endpoints.BASE_URL;
import static org.bel.apiHelper.Endpoints.STORE_ENDPOINT;

public class StoreActionsHelper {
    private RequestSpecification requestSpecification;
    private ResponseSpecification responseSpecification;
    private String message;

    public StoreActionsHelper() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL).build();

        responseSpecification = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                build();

    }

    public Order placeOrderForPet(Order newOrder) {
        return given(requestSpecification)
                .body(newOrder)
                .post(STORE_ENDPOINT + "/order")
                .then().spec(responseSpecification)
                .extract().body()
                .jsonPath().getObject("", Order.class);

    }

    public void deleteOrder(Integer orderId) {
        given(requestSpecification)
                .pathParam("orderId", orderId)
                .delete(STORE_ENDPOINT + "/order/{orderId}")
                .then().spec(responseSpecification);

    }

    public String isOrderExistMessage(Integer orderId) {
        Response response = given(requestSpecification)
                .pathParam("orderId", orderId)
                .delete(STORE_ENDPOINT + "/order/{orderId}")
                .then().extract().response();

        if (response.statusCode() == 200) {
            message = "Order is exist, statusCode = 200";

        } else if (response.statusCode() == 404) {
            ApiResponse resp = response.jsonPath().getObject("", ApiResponse.class);
            message = resp.getMessage();

        }
        return message;
    }

}
