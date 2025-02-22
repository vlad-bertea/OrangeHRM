package utils;

public enum ReportsSelectionCriteria {
    EMPLOYEE_NAME("Employee Name"),
    GENDER("Gender");

    private final String label;

    public String getLabel() {
        return this.label;
    }

    private ReportsSelectionCriteria(String label) {
        this.label = label;
    }
}
