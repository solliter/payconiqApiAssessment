package api.utils;

import io.restassured.RestAssured;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import jdk.jfr.Description;


public class RestAssuredConfiguration {
	@Description("Simple logs")
	public static RequestSpecification given() {
		return RestAssured.given().filters(new RequestLoggingFilter(), new ResponseLoggingFilter(), new ErrorLoggingFilter());
	}

}

