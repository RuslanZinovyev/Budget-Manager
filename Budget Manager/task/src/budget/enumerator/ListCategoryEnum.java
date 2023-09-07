package budget.enumerator;

public enum ListCategoryEnum {
    FOOD("1"),
    CLOTHES("2"),
    ENTERTAINMENT("3"),
    OTHER("4"),
    ALL("5"),
    BACK("6");

    private final String listCategoryNumber;

    ListCategoryEnum(String listCategoryNumber) {
        this.listCategoryNumber = listCategoryNumber;
    }

    public String getListCategoryNumber() {
        return listCategoryNumber;
    }

    public static ListCategoryEnum fromCategoryNumber(String listCategoryNumber) {
        for (ListCategoryEnum listCategory : ListCategoryEnum.values()) {
            if (listCategory.getListCategoryNumber().equals(listCategoryNumber)) {
                return listCategory;
            }
        }
        throw new IllegalArgumentException("Invalid action number: " + listCategoryNumber);
    }
}
