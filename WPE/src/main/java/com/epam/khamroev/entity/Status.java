package com.epam.khamroev.entity;

public enum Status {
    ACTIVE(1),
    INACTIVE(2),
    IN_REGISTR(3);
    private final int statusId;

    Status(int id) {
        this.statusId = id;
    }

    public static Status getRoleById(int roleId) {
        return switch (roleId) {
            case 1 -> ACTIVE;
            case 2 -> INACTIVE;
            case 3 -> IN_REGISTR;
            default -> throw new EnumConstantNotPresentException(INACTIVE.getDeclaringClass(), INACTIVE.name());
        };
    }

    public int getRoleId() {
        return statusId;
    }
}

