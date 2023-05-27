package belov.vlad.dapp.model;

public enum ApplicationStatus {
    AWAITING_CONFIRMATION("Ожидает подтверждения"),
    ACCEPTED("Принято"),
    REJECTED("Отклонено");
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    ApplicationStatus(String name) {
        this.name = name;
    }
}
