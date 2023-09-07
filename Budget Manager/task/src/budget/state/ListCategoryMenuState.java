package budget.state;

import budget.AppContext;
import budget.command.*;
import budget.enumerator.ListCategoryEnum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static budget.AppRunner.ALL_CATEGORY_MENU;

public class ListCategoryMenuState implements AppState {

    private final boolean isRunning;
    private final BufferedReader reader;

    public ListCategoryMenuState() {
        this.isRunning = true;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void handle() throws IOException {
        System.out.println();
        System.out.println(ALL_CATEGORY_MENU);

        while (isRunning) {
            ListCategoryEnum categoryNumber = ListCategoryEnum.fromCategoryNumber(reader.readLine());

            switch (categoryNumber) {
                case FOOD -> {
                    Command printFoodCommand = new PrintFoodCommand();
                    printFoodCommand.execute();
                }
                case CLOTHES -> {
                    Command printClothesCommand = new PrintClothesCommand();
                    printClothesCommand.execute();
                }
                case ENTERTAINMENT -> {
                    Command printEntertainmentCommand = new PrintEntertainmentCommand();
                    printEntertainmentCommand.execute();
                }
                case OTHER -> {
                    Command printOtherCommand = new PrintOtherCommand();
                    printOtherCommand.execute();
                }
                case ALL -> {
                    Command printAllCommand = new PrintAllCommand();
                    printAllCommand.execute();
                }
                case BACK -> {
                    AppContext.setState(new MainMenuState());
                    AppContext.handle();
                }
            }
        }
    }
}
