package posctn.posctn_api.enums;

public enum MenuCategoryEnum {

    FOOD("Makanan"),
    DRINK("Minuman");

    private final String label;

    MenuCategoryEnum(String name) {
        this.label = name;
    }

    public String getLabel() {
        return label;
    }
}
