package belov.vlad.dapp.model;

public enum Permission {
    DEVELOPER_READ("developers:read"),
    DEVELOPER_WRITE("developers:write"),

    ADMIN_PERMISSION("admin"),
    USER_CREATE("user:create"),
    USER_READ("user:read"),
    USER_DELETE("user:delete"),
    USER_UPDATE("user:update");
    private final String permission;

    public String getPermission() {
        return permission;
    }

    Permission(String permission) {
        this.permission = permission;
    }
}
