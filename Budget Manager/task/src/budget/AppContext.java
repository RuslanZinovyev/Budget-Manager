package budget;

import budget.state.AppState;

import java.io.IOException;

public class AppContext {
    private static AppState currentState;

    public static void setState(AppState newState) {
        currentState = newState;
    }

    public static void handle() {
        if (currentState != null) {
            try {
                currentState.handle();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
