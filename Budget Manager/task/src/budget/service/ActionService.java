package budget.service;

import budget.AppContext;
import budget.state.MainMenuState;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ActionService {
    public static final String ENTER_PURCHASE_NAME = "\nEnter purchase name:";
    public static final String ENTER_PRICE = "Enter its price:";
    public static final String PURCHASE_WAS_ADDED = "Purchase was added!";
    public static final String ENTER_INCOME = "\nEnter income:";
    public static final String INCOME_WAS_ADDED = "Income was added!";
    public static final String THE_PURCHASE_LIST_IS_EMPTY = "\nThe purchase list is empty!\n";
    public static final String FOOD = "Food:";
    public static final String TOTAL_SUM = "Total sum: $";
    public static final String ALL = "All:";
    public static final String CLOTHES = "Clothes:";
    public static final String ENTERTAINMENT = "Entertainment:";
    public static final String OTHER = "Other:";
    public static final String BALANCE = "\nBalance: $";
    public static final String BYE = "\nBye!";

    private static ActionService instance;

    private final List<String> allPurchases = new ArrayList<>();
    private final List<String> allFood = new ArrayList<>();
    private final List<String> allClothes = new ArrayList<>();
    private final List<String> allEntertainment = new ArrayList<>();
    private final List<String> allOther = new ArrayList<>();
    private BigDecimal balance = new BigDecimal(0).setScale(2, RoundingMode.HALF_UP);
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static synchronized ActionService getInstance() {
        if (instance == null) {
            instance = new ActionService();
        }
        return instance;
    }

    private ActionService() {
    }

    public void addIncome() throws IOException {
        System.out.println(ENTER_INCOME);
        String typedIncome = reader.readLine();
        balance = balance
                .add(BigDecimal.valueOf(Double.parseDouble(typedIncome)))
                .setScale(2, RoundingMode.HALF_UP);
        System.out.println(INCOME_WAS_ADDED);
    }

    public void addPurchase() throws IOException {
        System.out.println(ENTER_PURCHASE_NAME);
        String typedPurchaseName = reader.readLine();
        System.out.println(ENTER_PRICE);
        String typedPrice = reader.readLine();
        StringBuilder purchase = new StringBuilder();

        allPurchases.add(purchase
                .append(typedPurchaseName)
                .append(" $")
                .append(typedPrice)
                .toString());
        System.out.println(PURCHASE_WAS_ADDED);
    }

    public void addFood() throws IOException {
        System.out.println(ENTER_PURCHASE_NAME);
        String typedPurchaseName = reader.readLine();
        System.out.println(ENTER_PRICE);
        String typedPrice = reader.readLine();
        StringBuilder purchase = new StringBuilder();

        allFood.add(purchase
                .append(typedPurchaseName)
                .append(" $")
                .append(typedPrice)
                .toString());
        allPurchases.addAll(allFood);
        System.out.println(PURCHASE_WAS_ADDED);
    }

    public void addClothes() throws IOException {
        System.out.println(ENTER_PURCHASE_NAME);
        String typedPurchaseName = reader.readLine();
        System.out.println(ENTER_PRICE);
        String typedPrice = reader.readLine();
        StringBuilder purchase = new StringBuilder();

        allClothes.add(purchase
                .append(typedPurchaseName)
                .append(" $")
                .append(typedPrice)
                .toString());
        allPurchases.addAll(allClothes);
        System.out.println(PURCHASE_WAS_ADDED);
    }

    public void addEntertainment() throws IOException {
        System.out.println(ENTER_PURCHASE_NAME);
        String typedPurchaseName = reader.readLine();
        System.out.println(ENTER_PRICE);
        String typedPrice = reader.readLine();
        StringBuilder purchase = new StringBuilder();

        allEntertainment.add(purchase
                .append(typedPurchaseName)
                .append(" $")
                .append(typedPrice)
                .toString());
        allPurchases.addAll(allEntertainment);
        System.out.println(PURCHASE_WAS_ADDED);
    }

    public void addOther() throws IOException {
        System.out.println(ENTER_PURCHASE_NAME);
        String typedPurchaseName = reader.readLine();
        System.out.println(ENTER_PRICE);
        String typedPrice = reader.readLine();
        StringBuilder purchase = new StringBuilder();

        allOther.add(purchase.append(typedPurchaseName)
                .append(" $")
                .append(typedPrice)
                .toString());
        allPurchases.addAll(allOther);
        System.out.println(PURCHASE_WAS_ADDED);
    }

    public void printListOfAll() {
        if (allPurchases.isEmpty()) {
            System.out.println(THE_PURCHASE_LIST_IS_EMPTY);
            AppContext.setState(new MainMenuState());
            AppContext.handle();
        } else {
            BigDecimal totalSum = getTotalSumOfAllProducts();
            System.out.println(ALL);

            allPurchases.forEach(purchase -> {
                String[] parts = purchase.split(" ");
                String lastAmount = parts[parts.length - 1]; // Only consider the last amount in the string
                BigDecimal lastAmountAsBigDecimal = new BigDecimal(lastAmount.substring(1));
                String formattedLastAmount = "$" + String.format("%.2f", lastAmountAsBigDecimal);

                // Replace only the last occurrence of the lastAmount
                int lastIndex = purchase.lastIndexOf(lastAmount);
                String updatedPurchase = purchase.substring(0, lastIndex) + formattedLastAmount +
                        purchase.substring(lastIndex + lastAmount.length());

                System.out.println(updatedPurchase);
            });

            System.out.println(TOTAL_SUM + String.format("%.2f", totalSum.doubleValue()) + "\n");
        }
    }

    public void printListOfFood() {
        if (allFood.isEmpty()) {
            System.out.println(FOOD);
            System.out.println(THE_PURCHASE_LIST_IS_EMPTY);
        } else {
            List<String> amounts = getCollectionOfAmounts(allFood);
            BigDecimal totalSum = getSumOfAllAmounts(amounts);
            System.out.println(FOOD);
            allFood.forEach(System.out::println);
            System.out.println(TOTAL_SUM + totalSum + "\n");

        }
    }

    public void printListOfClothes() {
        if (allClothes.isEmpty()) {
            System.out.println(CLOTHES);
            System.out.println(THE_PURCHASE_LIST_IS_EMPTY);
        } else {
            List<String> amounts = getCollectionOfAmounts(allClothes);
            BigDecimal totalSum = getSumOfAllAmounts(amounts);
            System.out.println(CLOTHES);
            allClothes.forEach(System.out::println);
            System.out.println(TOTAL_SUM + totalSum + "\n");
        }
    }

    public void printListOfEntertainment() {
        if (allEntertainment.isEmpty()) {
            System.out.println(ENTERTAINMENT);
            System.out.println(THE_PURCHASE_LIST_IS_EMPTY);
        } else {
            List<String> amounts = getCollectionOfAmounts(allEntertainment);
            BigDecimal totalSum = getSumOfAllAmounts(amounts);
            System.out.println(ENTERTAINMENT);
            allEntertainment.forEach(System.out::println);
            System.out.println(TOTAL_SUM + totalSum + "\n");
        }
    }

    public void printListOfOthers() {
        if (allOther.isEmpty()) {
            System.out.println(OTHER);
            System.out.println(THE_PURCHASE_LIST_IS_EMPTY);
        } else {
            List<String> amounts = getCollectionOfAmounts(allOther);
            BigDecimal totalSum = getSumOfAllAmounts(amounts);
            System.out.println(OTHER);
            allOther.forEach(System.out::println);
            System.out.println(TOTAL_SUM + totalSum + "\n");
        }
    }

    public void printBalance() {
        if (!allFood.isEmpty()) {
            BigDecimal foodExpenses = getSumOfAllAmounts(getCollectionOfAmounts(allFood));
            balance = balance.subtract(foodExpenses);
        }
        if (!allClothes.isEmpty()) {
            BigDecimal clothesExpenses = getSumOfAllAmounts(getCollectionOfAmounts(allClothes));
            balance = balance.subtract(clothesExpenses);
        }
        if (!allEntertainment.isEmpty()) {
            BigDecimal entertainmentExpenses = getSumOfAllAmounts(getCollectionOfAmounts(allEntertainment));
            balance = balance.subtract(entertainmentExpenses);
        }
        if (!allOther.isEmpty()) {
            BigDecimal otherExpenses = getSumOfAllAmounts(getCollectionOfAmounts(allOther));
            balance = balance.subtract(otherExpenses);
        }
        System.out.println(BALANCE + balance);
    }

    public void exitApplication() {
        System.out.println(BYE);
        System.exit(0);
    }

    public List<String> getAllPurchases() {
        return allPurchases;
    }

    public List<String> getAllFood() {
        return allFood;
    }

    public List<String> getAllClothes() {
        return allClothes;
    }

    public List<String> getAllEntertainment() {
        return allEntertainment;
    }

    public List<String> getAllOther() {
        return allOther;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<String> getCollectionOfAmounts(List<String> allPurchases) {
        return allPurchases.stream()
                .map(line -> {
                    String[] parts = line.split(" ");
                    return parts[parts.length - 1];
                })
                .collect(Collectors.toList());
    }

    private BigDecimal getSumOfAllAmounts(List<String> allAmounts) {
        return allAmounts.stream()
                .map(purchase -> new BigDecimal(purchase.substring(1))
                        .setScale(2, RoundingMode.HALF_UP))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal getTotalSumOfAllProducts() {
        List<String> foodAmount = getCollectionOfAmounts(allFood);
        List<String> clothesAmount = getCollectionOfAmounts(allClothes);
        List<String> entertainmentAmount = getCollectionOfAmounts(allEntertainment);
        List<String> otherAmount = getCollectionOfAmounts(allOther);
        return getSumOfAllAmounts(foodAmount)
                .add(getSumOfAllAmounts(clothesAmount))
                .add(getSumOfAllAmounts(entertainmentAmount))
                .add(getSumOfAllAmounts(otherAmount));
    }
}
