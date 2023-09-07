package budget.command;

import budget.AppContext;
import budget.service.ActionService;
import budget.state.MainMenuState;

public class GetBalanceCommand implements Command {
    @Override
    public void execute() {
        ActionService actionService = ActionService.getInstance();
        actionService.printBalance();
        AppContext.setState(new MainMenuState());
        AppContext.handle();
    }
}
