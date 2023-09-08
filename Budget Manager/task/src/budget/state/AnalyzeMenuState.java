package budget.state;

import budget.AppContext;
import budget.command.Command;
import budget.command.SortAllPurchasesCommand;
import budget.command.SortByTypeCommand;
import budget.command.SortCertainTypeCommand;
import budget.enumerator.AnalyzeEnum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static budget.AppRunner.ANALYZE_MENU;

public class AnalyzeMenuState implements AppState {
    private final boolean isRunning;
    private final BufferedReader reader;

    public AnalyzeMenuState() {
        this.isRunning = true;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void handle() throws IOException {
        System.out.println();
        System.out.println(ANALYZE_MENU);
        while (isRunning) {
            AnalyzeEnum analyzeNumber = AnalyzeEnum.fromAnalyzeNumber(reader.readLine());

            switch (analyzeNumber) {
                case SORT_ALL_PURCHASES -> {
                    Command sortAllPurchasesCommand = new SortAllPurchasesCommand();
                    sortAllPurchasesCommand.execute();
                }
                case SORT_BY_TYPE -> {
                    Command sortByTypeCommand = new SortByTypeCommand();
                    sortByTypeCommand.execute();
                }
                case SORT_CERTAIN_TYPE -> {
                    Command sortCertainTypeCommand = new SortCertainTypeCommand();
                    sortCertainTypeCommand.execute();
                }
                case BACK -> {
                    AppContext.setState(new MainMenuState());
                    AppContext.handle();
                }
            }
        }
    }
}
