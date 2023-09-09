package budget.command;

import budget.service.AnalyzeService;

import java.io.IOException;

public class SortByTypeCommand implements Command {
    @Override
    public void execute() throws IOException {
        AnalyzeService analyzeService = AnalyzeService.getInstance();
        analyzeService.sortByType();
    }
}
