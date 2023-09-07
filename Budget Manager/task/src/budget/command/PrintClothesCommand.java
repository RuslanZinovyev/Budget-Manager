package budget.command;

import budget.AppContext;
import budget.service.ActionService;
import budget.state.ListCategoryMenuState;

import java.io.IOException;

public class PrintClothesCommand implements Command {
    @Override
    public void execute() throws IOException {
        ActionService actionService = ActionService.getInstance();
        actionService.printListOfClothes();
        AppContext.setState(new ListCategoryMenuState());
        AppContext.handle();
    }
}
