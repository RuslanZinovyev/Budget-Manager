package budget.command;

import budget.AppContext;
import budget.service.ActionService;
import budget.state.CategoryMenuState;

import java.io.IOException;

public class AddEntertainmentCommand implements Command {
    @Override
    public void execute() throws IOException {
        ActionService actionService = ActionService.getInstance();
        actionService.addEntertainment();
        AppContext.setState(new CategoryMenuState());
        AppContext.handle();
    }
}
