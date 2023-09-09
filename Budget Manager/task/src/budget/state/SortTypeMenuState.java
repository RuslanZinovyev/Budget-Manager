package budget.state;

import budget.command.*;
import budget.enumerator.CategoryEnum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static budget.AppRunner.SORT_CATEGORY_MENU;

public class SortTypeMenuState implements AppState {
    private final boolean isRunning;
    private final BufferedReader reader;

    public SortTypeMenuState() {
        this.isRunning = true;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void handle() throws IOException {
        System.out.println();
        System.out.println(SORT_CATEGORY_MENU);

        while (isRunning) {
            CategoryEnum categoryNumber = CategoryEnum.fromCategoryNumber(reader.readLine());

            switch (categoryNumber) {
                case FOOD -> {
                    Command sortByFoodTypeCommand = new SortByFoodTypeCommand();
                    sortByFoodTypeCommand.execute();
                }
                case CLOTHES -> {
                    Command sortByClothesTypeCommand = new SortByClothesTypeCommand();
                    sortByClothesTypeCommand.execute();
                }
                case ENTERTAINMENT -> {
                    Command sortByEntertainmentTypeCommand = new SortByEntertainmentTypeCommand();
                    sortByEntertainmentTypeCommand.execute();
                }
                case OTHER -> {
                    Command sortByOtherTypeCommand = new SortByOtherTypeCommand();
                    sortByOtherTypeCommand.execute();
                }
            }
        }
    }
}
