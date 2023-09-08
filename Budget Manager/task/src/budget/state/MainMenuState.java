package budget.state;

import budget.AppContext;
import budget.command.*;
import budget.enumerator.ActionEnum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static budget.AppRunner.ACTION_MENU;

public class MainMenuState implements AppState {
    private boolean isRunning;
    private final BufferedReader reader;

    public MainMenuState() {
        this.isRunning = true;
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void handle() throws IOException {
        System.out.println();
        System.out.println(ACTION_MENU);

        while (isRunning) {
            ActionEnum actionNumber = ActionEnum.fromActionNumber(reader.readLine());

            switch (actionNumber) {
                case ADD_INCOME -> {
                    Command addIncomeCommand = new AddIncomeCommand();
                    addIncomeCommand.execute();
                }
                case ADD_PURCHASES -> {
                    AppContext.setState(new CategoryMenuState());
                    AppContext.handle();
                }
                case SHOW_LIST_OF_PURCHASES -> {
                    Command showListOfPurchasesCommand = new ShowListOfPurchasesCommand();
                    showListOfPurchasesCommand.execute();
                }
                case BALANCE -> {
                    Command getBalanceCommand = new GetBalanceCommand();
                    getBalanceCommand.execute();
                }
                case SAVE -> {
                    Command fileSaveCommand = new FileSaveCommand();
                    fileSaveCommand.execute();
                }
                case LOAD -> {
                    Command fileLoadCommand = new FileLoadCommand();
                    fileLoadCommand.execute();
                }
                case ANALYZE -> {
                    AppContext.setState(new AnalyzeMenuState());
                    AppContext.handle();
                }
                case EXIT_APPLICATION -> {
                    isRunning = false;
                    Command exitAppCommand = new ExitAppCommand();
                    exitAppCommand.execute();
                }
            }
        }
    }
}
