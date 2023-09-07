package budget.command;

import budget.AppContext;
import budget.service.FileService;
import budget.state.MainMenuState;

import java.io.IOException;

public class FileLoadCommand implements Command {
    @Override
    public void execute() throws IOException {
        FileService fileService = FileService.getInstance();
        fileService.loadFile();
        AppContext.setState(new MainMenuState());
        AppContext.handle();
    }
}
