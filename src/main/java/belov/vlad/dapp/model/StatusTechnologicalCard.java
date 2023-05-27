package belov.vlad.dapp.model;

public enum StatusTechnologicalCard {
    ACTIVE("Активная"),
    OBSOLETE("Устаревшая"),
    AWAITING_CONFIRMATION("Ожидает подтверждения");

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    StatusTechnologicalCard(String name) {
        this.name = name;
    }
}
