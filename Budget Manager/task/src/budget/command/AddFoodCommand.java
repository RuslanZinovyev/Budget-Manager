package budget.command;

import budget.AppContext;
import budget.service.ActionService;
import budget.state.CategoryMenuState;

import java.io.IOException;

public class AddFoodCommand implements Command {
    @Override
    public void execute() throws IOException {
        ActionService actionService = ActionService.getInstance();
        actionService.addFood();
        AppContext.setState(new CategoryMenuState());
        AppContext.handle();
    }
}
