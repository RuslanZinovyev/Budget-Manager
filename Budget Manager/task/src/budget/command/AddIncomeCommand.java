package budget.command;

import budget.AppContext;
import budget.service.ActionService;
import budget.state.MainMenuState;

import java.io.IOException;

public class AddIncomeCommand implements Command {
    @Override
    public void execute() throws IOException {
        ActionService actionService = ActionService.getInstance();
        actionService.addIncome();
        AppContext.setState(new MainMenuState());
        AppContext.handle();
    }
}
