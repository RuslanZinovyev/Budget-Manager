package budget.command;

import budget.AppContext;
import budget.service.ActionService;
import budget.state.CategoryMenuState;

import java.io.IOException;

public class AddClothesCommand implements Command {
    @Override
    public void execute() throws IOException {
        ActionService actionService = ActionService.getInstance();
        actionService.addClothes();
        AppContext.setState(new CategoryMenuState());
        AppContext.handle();
    }
}
