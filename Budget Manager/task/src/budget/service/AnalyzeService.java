package budget.service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static budget.service.ActionService.THE_PURCHASE_LIST_IS_EMPTY;

public class AnalyzeService {
    private static AnalyzeService instance;
    private final ActionService actionService = ActionService.getInstance();

    public static synchronized AnalyzeService getInstance() {
        if (instance == null) {
            instance = new AnalyzeService();
        }
        return instance;
    }

    private AnalyzeService() {}

    public void sortAllPurchases() {
        if (!actionService.getAllPurchases().isEmpty()) {
            List<String> allPurchases = actionService.getAllPurchases();
            List<String> amounts = actionService.getCollectionOfAmounts(allPurchases);

            List<String> sortedPriceStrings = amounts.stream()
                    .sorted(Comparator.comparing(
                            s -> new BigDecimal(s.substring(1)),
                            Comparator.reverseOrder())
                    )
                    .collect(Collectors.toList());

            System.out.println(sortedPriceStrings);
        } else {
            System.out.println(THE_PURCHASE_LIST_IS_EMPTY);
        }
    }
    public void sortByType() {}
    public void sortCertainType() {}
}
