package budget.state;

import budget.AppContext;
import budget.command.*;
import budget.enumerator.CategoryEnum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static budget.AppRunner.CATEGORY_MENU;

public class CategoryMenuState implements AppState {
    private final boolean isRunning;
    private final BufferedReader reader;

    public CategoryMenuState() {
        this.isRunning = true;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void handle() throws IOException {
        System.out.println();
        System.out.println(CATEGORY_MENU);

        while (isRunning) {
            CategoryEnum categoryNumber = CategoryEnum.fromCategoryNumber(reader.readLine());

            switch (categoryNumber) {
                case FOOD -> {
                    Command addFoodCommand = new AddFoodCommand();
                    addFoodCommand.execute();
                }
                case CLOTHES -> {
                    Command allClothesCommand = new AddClothesCommand();
                    allClothesCommand.execute();
                }
                case ENTERTAINMENT -> {
                    Command allEntertainmentCommand = new AddEntertainmentCommand();
                    allEntertainmentCommand.execute();
                }
                case OTHER -> {
                    Command allOtherCommand = new AddOtherCommand();
                    allOtherCommand.execute();
                }
                case BACK -> {
                    AppContext.setState(new MainMenuState());
                    AppContext.handle();
                }
            }
        }
    }
}
