package budget.command;

import budget.service.ActionService;

public class ExitAppCommand implements Command {
    @Override
    public void execute() {
        ActionService actionService = ActionService.getInstance();
        actionService.exitApplication();
    }
}
