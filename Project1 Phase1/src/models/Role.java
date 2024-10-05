package models;

public enum Role {
    ADMIN("A"),
    STUDENT("S"),
    INSTRUCTOR("I");

    private final String code;

    Role(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
