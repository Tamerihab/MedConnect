package com.med.MedConnect.Model.Donation;

public enum DonationType {
    MONETARY("Monetary"),
    ITEM("Item");

    private final String value;

    // Constructor
    DonationType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    // Method to get DonationType from String value
    public static DonationType fromString(String value) {
        for (DonationType type : DonationType.values()) {
            if (type.getValue().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unexpected value: " + value);
    }
}
