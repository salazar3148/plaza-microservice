package com.pragma.powerup.plazamicroservice.configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Constants {

    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static final List<String> ORDER_STATUSES = Collections.unmodifiableList(
            Arrays.asList("PENDING", "IN PREPARATION", "DONE")
    );

    public static final Long ADMIN_ROLE_ID = 1L;
    public static final Long CUSTOMER_ROLE_ID = 2L;
    public static final Long OWNER_ROLE_ID = 3L;
    public static final Long EMPLOYEE_ROLE_ID = 4L;
    public static final String RESPONSE_MESSAGE_KEY = "message";
    public static final String PLAZA_CREATED_MESSAGE = "Restaurant created successfully";
    public static final String DISH_CREATED_MESSAGE = "Dish created successfully";
    public static final String ORDER_CREATED_MESSAGE = "Order successfully created in pending status";
    public static final String INVALID_STATE_TRANSITION_MESSAGE = "Invalid state transition attempted";
    public static final String ORDER_IS_NOT_IN_PENDING_STATUS_MESSAGE= "the order must be in pending status to do this action";
    public static final String CUSTOMER_WITH_PENDING_ORDER_MESSAGE = "A pending, in preparation, or completed order already exists for this customer. Please resolve the existing order before creating a new one";
    public static final String EMPLOYEE_NOT_ASSIGNED_TO_RESTAURANT_MESSAGE = "Employee is not assigned to any restaurant.";
    public static final String VERIFICATION_CODE_MISMATCH_MESSAGE = "The verification code provided does not match the authentication code.";
    public static final String DISH_UPDATED_MESSAGE = "Dish updated successfully";
    public static final String STATUS_DISH_UPDATED_MESSAGE = "status dish updated successfully";
    public static final String EMPLOYEE_ASSIGMENT_SUCCESSFULLY_MESSAGE = "employee successfully assigned to the order";
    public static final String USER_ORDER_NOTIFY_MESSAGE = "the user has been notified and the verification code has been sent.";
    public static final String ORDER_CANCELED_MESSAGE = "order cancelled successfully";
    public static final String ORDER_DELIVERED_MESSAGE = "order delivered successfully";
    public static final String ROLE_ASSIGNMENT_MESSAGE = "role assignment is not allowed";
    public static final String RESPONSE_ERROR_MESSAGE_KEY = "error";
    public static final String NO_DATA_FOUND_MESSAGE = "No data found for the requested petition";
    public static final String PLAZA_ALREADY_EXISTS_MESSAGE = "A Plaza already exists with the NIT number provided";
    public static final String USER_NO_ACCESS_MESSAGE = "User does not have permission to access";
    public static final String MAIL_ALREADY_EXISTS_MESSAGE = "A person with that mail already exists";
    public static final String PERSON_NOT_FOUND_MESSAGE = "No person found with the id provided";
    public static final String ROLE_NOT_FOUND_MESSAGE = "No role found with the id provided";
    public static final String CATEGORY_NOT_FOUND_MESSAGE = "No category found with the id provided";

    public static final String ORDER_NOT_FOUND_MESSAGE = "No order found with the id provided";
    public static final String DISH_NOT_FOUND_MESSAGE = "No dish found with the id provided";
    public static final String PLAZA_NOT_FOUND_MESSAGE = "No restaurant found with the id provided";
    public static final String UNAUTHORIZED_RESTAURANT_ACCESS_MESSAGE = "you are not allowed to modify data in this restaurant";
    public static final String UNAUTHORIZED_ORDER_ACCESS_MESSAGE = "you are not allowed to modify data in this order";
    public static final String ROLE_NOT_ALLOWED_MESSAGE = "No permission granted to create users with this role";
    public static final String USER_ALREADY_EXISTS_MESSAGE = "A user already exists with the role provided";
    public static final String USER_NOT_FOUND_MESSAGE = "No user found with the role provided";
    public static final String SWAGGER_TITLE_MESSAGE = "Plaza API Pragma Power Up";
    public static final String SWAGGER_DESCRIPTION_MESSAGE = "Plaza microservice";
    public static final String SWAGGER_VERSION_MESSAGE = "1.0.0";
    public static final String SWAGGER_LICENSE_NAME_MESSAGE = "Apache 2.0";
    public static final String SWAGGER_LICENSE_URL_MESSAGE = "http://springdoc.org";
    public static final String SWAGGER_TERMS_OF_SERVICE_MESSAGE = "http://swagger.io/terms/";
}
