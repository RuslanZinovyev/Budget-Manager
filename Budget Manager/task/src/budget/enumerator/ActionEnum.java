package budget.enumerator;

public enum ActionEnum {
    ADD_INCOME("1"),
    ADD_PURCHASES("2"),
    SHOW_LIST_OF_PURCHASES("3"),
    BALANCE("4"),
    SAVE("5"),
    LOAD("6"),
    EXIT_APPLICATION("0");

    private final String actionNumber;

    ActionEnum(String actionNumber) {
        this.actionNumber = actionNumber;
    }

    public String getActionNumber() {
        return actionNumber;
    }

    public static ActionEnum fromActionNumber(String actionNumber) {
        for (ActionEnum action : ActionEnum.values()) {
            if (action.getActionNumber().equals(actionNumber)) {
                return action;
            }
        }
        throw new IllegalArgumentException("Invalid action number: " + actionNumber);
    }
}