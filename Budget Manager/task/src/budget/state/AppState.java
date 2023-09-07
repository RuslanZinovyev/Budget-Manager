package budget.state;

import java.io.IOException;

public interface AppState {
    void handle() throws IOException;
}
