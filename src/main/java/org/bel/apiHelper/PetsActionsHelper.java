package org.bel.apiHelper;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.bel.models.ApiResponse;
import org.bel.models.Pet;

import java.io.File;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.bel.apiHelper.Endpoints.BASE_URL;
import static org.bel.apiHelper.Endpoints.PET_ENDPOINT;

public class PetsActionsHelper {
    private RequestSpecification requestSpecification;
    private ResponseSpecification responseSpecification;
    private String message;

    public PetsActionsHelper() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL).build();

        responseSpecification = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                build();
    }

    public Pet addNewPet(Pet newPetBody) {
        return given(requestSpecification)
                .body(newPetBody)
                .post(PET_ENDPOINT)
                .then().spec(responseSpecification).log().all()
                .extract().body()
                .jsonPath().getObject("", Pet.class);

    }

    public Pet getPetById(Long petId) {
        return given(requestSpecification)
                .pathParam("petId", petId)
                .get(PET_ENDPOINT + "/{petId}")
                .then().spec(responseSpecification).log().all()
                .extract().body()
                .jsonPath().getObject("", Pet.class);

    }

    public void deletePet(Long petId) {
        given(requestSpecification)
                .pathParam("petId", petId)
                .delete(PET_ENDPOINT + "/{petId}")
                .then().spec(responseSpecification);
    }


    public List<Pet> findPetsByStatus(StatusEnumPet status) {
        return given(requestSpecification)
                .param("status", status)
                .get(PET_ENDPOINT + "/findByStatus")
                .then().spec(responseSpecification).log().all()
                .extract().body()
                .jsonPath().getList("", Pet.class);

    }


    public ApiResponse uploadImageForPet(Long petId, File image) {
        return given()
                .pathParam("petId", petId)
                .multiPart(image)
                .post(PET_ENDPOINT + "/{petId}/uploadImage")
                .then().spec(responseSpecification).log().all()
                .extract().body()
                .jsonPath().getObject("", ApiResponse.class);

    }

    public void uploadImageForPet(Pet pet, File image) {
        given(requestSpecification)
                .pathParam("petId", pet.getId())
                .multiPart(image)
                .post(PET_ENDPOINT + "/{petId}/uploadImage")
                .then().spec(responseSpecification).log().all()
                .extract().body()
                .jsonPath().getObject("", ApiResponse.class);

    }

    public String isPetExistMessage(Long petId) {
        Response response = given(requestSpecification)
                .pathParam("petId", petId)
                .get(PET_ENDPOINT + "/{petId}")
                .then().extract().response();

        if (response.statusCode() == 200) {
            message = "Pet is exist, statusCode = 200";

        } else if (response.statusCode() == 404) {
            ApiResponse resp = response.jsonPath().getObject("", ApiResponse.class);
            message = resp.getMessage();

        }
        return message;
    }
}






