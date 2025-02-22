package utils;

import net.datafaker.Faker;

import java.util.Random;

public class TestData {
    public static final String USER_NAME = "Admin";
    public static final String PASSWORD = "admin123";
    public static final String EMPLOYEE_FIRST_NAME = "EMPLOYEE_FIRST_NAME";
    public static final String EMPLOYEE_MIDDLE_NAME = "EMPLOYEE_MIDDLE_NAME";
    public static final String EMPLOYEE_LAST_NAME = "EMPLOYEE_LAST_NAME";
    public static final String EMPLOYEE_ID = "EMPLOYEE_ID";
    public static final String BIRTH_DATE = "1994-02-23";

    public static String generateTestData(String dataType) {

        Faker faker = new Faker();
        switch (dataType) {
            case EMPLOYEE_FIRST_NAME, EMPLOYEE_MIDDLE_NAME -> {
                return faker.name().firstName();
            }
            case EMPLOYEE_LAST_NAME -> {
                return faker.name().lastName();
            }
            default -> {
                Random random = new Random();
                return String.valueOf(random.nextInt(10_000, 99_999));
            }
        }
    }
}
