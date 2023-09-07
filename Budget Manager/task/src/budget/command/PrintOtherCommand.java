package budget.command;

import budget.AppContext;
import budget.service.ActionService;
import budget.state.ListCategoryMenuState;

import java.io.IOException;

public class PrintOtherCommand implements Command {
    @Override
    public void execute() throws IOException {
        ActionService actionService = ActionService.getInstance();
        actionService.printListOfOthers();
        AppContext.setState(new ListCategoryMenuState());
        AppContext.handle();
    }
}
