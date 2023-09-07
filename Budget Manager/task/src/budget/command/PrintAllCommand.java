package budget.command;

import budget.AppContext;
import budget.service.ActionService;
import budget.state.ListCategoryMenuState;

public class PrintAllCommand implements Command {
    @Override
    public void execute() {
        ActionService actionService = ActionService.getInstance();
        actionService.printListOfAll();
        AppContext.setState(new ListCategoryMenuState());
        AppContext.handle();
    }
}
