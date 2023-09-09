package budget;

import budget.state.MainMenuState;

public class AppRunner {
    private AppRunner() {}

    public static final String ACTION_MENU = """
            Choose your action:
            1) Add income
            2) Add purchase
            3) Show list of purchases
            4) Balance
            5) Save
            6) Load
            7) Analyze
            0) Exit
            """;
    public static final String CATEGORY_MENU = """
            Choose the type of purchase
            1) Food
            2) Clothes
            3) Entertainment
            4) Other
            5) Back
            """;

    public static final String SORT_CATEGORY_MENU = """
            Choose the type of purchase
            1) Food
            2) Clothes
            3) Entertainment
            4) Other
            """;

    public static final String ALL_CATEGORY_MENU = """
            Choose the type of purchases
            1) Food
            2) Clothes
            3) Entertainment
            4) Other
            5) All
            6) Back
            """;

    public static final String ANALYZE_MENU = """
            How do you want to sort?
            1) Sort all purchases
            2) Sort by type
            3) Sort certain type
            4) Back
            """;

    public static void runApplication() {
        AppContext.setState(new MainMenuState());
        AppContext.handle();
    }
}
