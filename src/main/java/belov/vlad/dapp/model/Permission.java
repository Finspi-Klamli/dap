package belov.vlad.dapp.model;

public enum Permission {

    ADMIN_PERMISSION    ("admin"),
    USER_PERMISSION     ("user"),
    APPLICANT_PERMISSION("applicant");

    private final String permission;

    public String getPermission() {
        return permission;
    }

    Permission(String permission) {
        this.permission = permission;
    }
}
