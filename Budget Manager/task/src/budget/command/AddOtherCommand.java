package budget.command;

import budget.AppContext;
import budget.service.ActionService;
import budget.state.CategoryMenuState;

import java.io.IOException;

public class AddOtherCommand implements Command {
    @Override
    public void execute() throws IOException {
        ActionService actionService = ActionService.getInstance();
        actionService.addOther();
        AppContext.setState(new CategoryMenuState());
        AppContext.handle();
    }
}
