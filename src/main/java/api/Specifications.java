package api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import test_conf.props.TestConfig;

public class Specifications {
        public static RequestSpecification requestSpec() {
            return new RequestSpecBuilder()
                    .setBaseUri(TestConfig.URI.Value)
                    .setContentType(ContentType.URLENC)
                    .build();
        }

        public static ResponseSpecification responseSpec200() {
            return new ResponseSpecBuilder()
                    .expectStatusCode(200)
                    .build();
        }

        public static void installSpecification(RequestSpecification requestSpec, ResponseSpecification responseSpec) {
            RestAssured.requestSpecification = requestSpec;
            RestAssured.responseSpecification = responseSpec;
        }
}
