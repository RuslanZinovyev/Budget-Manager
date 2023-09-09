package budget.command;

import budget.AppContext;
import budget.state.SortTypeMenuState;

import java.io.IOException;

public class SortCertainTypeCommand implements Command {
    @Override
    public void execute() throws IOException {
        AppContext.setState(new SortTypeMenuState());
        AppContext.handle();
    }
}
