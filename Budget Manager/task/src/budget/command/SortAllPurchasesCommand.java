package budget.command;

import budget.service.ActionService;
import budget.service.AnalyzeService;

import java.io.IOException;

public class SortAllPurchasesCommand implements Command {
    @Override
    public void execute() throws IOException {
        AnalyzeService analyzeService = AnalyzeService.getInstance();
        analyzeService.sortAllPurchases();
    }
}
