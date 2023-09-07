package budget.command;

import budget.AppContext;
import budget.service.ActionService;
import budget.state.ListCategoryMenuState;
import budget.state.MainMenuState;

import java.io.IOException;

import static budget.service.ActionService.THE_PURCHASE_LIST_IS_EMPTY;

public class ShowListOfPurchasesCommand implements Command {
    @Override
    public void execute() throws IOException {
        ActionService actionService = ActionService.getInstance();
        if (actionService.getAllPurchases().isEmpty()) {
            System.out.println(THE_PURCHASE_LIST_IS_EMPTY);
            AppContext.setState(new MainMenuState());
            AppContext.handle();
        } else {
            AppContext.setState(new ListCategoryMenuState());
            AppContext.handle();
        }
    }
}
