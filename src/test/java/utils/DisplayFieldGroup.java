package utils;

public enum DisplayFieldGroup {
    PERSONAL("Personal"),
    CONTACT_DETAILS("Contact Details");


    private final String label;

    public String getLabel() {
        return this.label;
    }

    private DisplayFieldGroup(String label) {
        this.label = label;
    }
}
