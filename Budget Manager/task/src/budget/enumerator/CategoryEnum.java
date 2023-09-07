package budget.enumerator;

public enum CategoryEnum {
    FOOD("1"),
    CLOTHES("2"),
    ENTERTAINMENT("3"),
    OTHER("4"),
    BACK("5");

    private final String categoryNumber;

    CategoryEnum(String categoryNumber) {
        this.categoryNumber = categoryNumber;
    }

    public String getCategoryNumber() {
        return categoryNumber;
    }

    public static CategoryEnum fromCategoryNumber(String categoryNumber) {
        for (CategoryEnum category : CategoryEnum.values()) {
            if (category.getCategoryNumber().equals(categoryNumber)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Invalid action number: " + categoryNumber);
    }
}
