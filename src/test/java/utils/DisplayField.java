package utils;

public enum DisplayField {
    EMPLOYEE_LAST_NAME("Employee Last Name"),
    EMPLOYEE_FIRST_NAME("Employee First Name"),
    DATE_OF_BIRTH("Date of Birth"),
    GENDER("Gender"),
    EMPLOYEE_ID("Employee Id");


    private final String label;

    public String getLabel() {
        return this.label;
    }

    private DisplayField(String label) {
        this.label = label;
    }
}
