package budget.command;

import budget.AppContext;
import budget.service.AnalyzeService;
import budget.state.AnalyzeMenuState;

import java.io.IOException;

public class SortByOtherTypeCommand implements Command {
    @Override
    public void execute() throws IOException {
        AnalyzeService analyzeService = AnalyzeService.getInstance();
        analyzeService.sortOtherType();
        AppContext.setState(new AnalyzeMenuState());
        AppContext.handle();
    }
}
