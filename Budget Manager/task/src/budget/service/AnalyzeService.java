package budget.service;

import budget.AppContext;
import budget.model.Purchase;
import budget.state.AnalyzeMenuState;

import java.math.BigDecimal;
import java.util.*;

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

    private AnalyzeService() {
    }

    public void sortAllPurchases() {
        if (!actionService.getAllPurchases().isEmpty()) {
            System.out.println("All:");
            actionService.getAllPurchases().stream()
                    .sorted(Comparator.comparing(Purchase::getPrice).reversed())
                    .distinct()
                    .forEach(System.out::println);
            System.out.println("Total: " + actionService.getTotalSumOfAllProducts());
        } else {
            System.out.println(THE_PURCHASE_LIST_IS_EMPTY);
        }
    }

    public void sortByType() {
        BigDecimal foodSum = actionService.getSumOfAllAmounts(actionService.getAllFood());
        BigDecimal entertainmentSum = actionService.getSumOfAllAmounts(actionService.getAllEntertainment());
        BigDecimal clothesSum = actionService.getSumOfAllAmounts(actionService.getAllClothes());
        BigDecimal otherSum = actionService.getSumOfAllAmounts(actionService.getAllOther());

        Map<String, BigDecimal> categorySum = new HashMap<>();
        categorySum.put("Food", foodSum);
        categorySum.put("Entertainment", entertainmentSum);
        categorySum.put("Clothes", clothesSum);
        categorySum.put("Other", otherSum);

        List<Map.Entry<String, BigDecimal>> sortedSums = new ArrayList<>(categorySum.entrySet());
        sortedSums.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        for (Map.Entry<String, BigDecimal> entry : sortedSums) {
            System.out.println(entry.getKey() + " - $" + entry.getValue());
        }

        AppContext.setState(new AnalyzeMenuState());
        AppContext.handle();
    }

    public void sortFoodType() {
        if (!actionService.getAllFood().isEmpty()) {
            System.out.println("Food:");
            actionService.getAllFood().stream()
                    .sorted(Comparator.comparing(Purchase::getPrice).reversed())
                    .forEach(System.out::println);
            System.out.println("Total: " + actionService.getSumOfAllAmounts(actionService.getAllFood()));
        } else {
            System.out.println(THE_PURCHASE_LIST_IS_EMPTY);
        }

        AppContext.setState(new AnalyzeMenuState());
        AppContext.handle();
    }

    public void sortClothesType() {
        if (!actionService.getAllClothes().isEmpty()) {
            System.out.println("Clothes:");
            actionService.getAllClothes().stream()
                    .sorted(Comparator.comparing(Purchase::getPrice).reversed())
                    .forEach(System.out::println);
            System.out.println("Total: " + actionService.getAllClothes());
        } else {
            System.out.println(THE_PURCHASE_LIST_IS_EMPTY);
        }

        AppContext.setState(new AnalyzeMenuState());
        AppContext.handle();
    }

    public void sortEntertainmentType() {
        if (!actionService.getAllEntertainment().isEmpty()) {
            System.out.println("Entertainment:");
            actionService.getAllEntertainment().stream()
                    .sorted(Comparator.comparing(Purchase::getPrice).reversed())
                    .forEach(System.out::println);
            System.out.println("Total: " + actionService.getAllEntertainment());
        } else {
            System.out.println(THE_PURCHASE_LIST_IS_EMPTY);
        }

        AppContext.setState(new AnalyzeMenuState());
        AppContext.handle();
    }

    public void sortOtherType() {
        if (!actionService.getAllOther().isEmpty()) {
            System.out.println("Other:");
            actionService.getAllOther().stream()
                    .sorted(Comparator.comparing(Purchase::getPrice).reversed())
                    .forEach(System.out::println);
            System.out.println("Total: " + actionService.getAllOther());
        } else {
            System.out.println(THE_PURCHASE_LIST_IS_EMPTY);
        }

        AppContext.setState(new AnalyzeMenuState());
        AppContext.handle();
    }
}
