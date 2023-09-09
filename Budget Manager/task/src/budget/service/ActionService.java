package budget.service;

import budget.AppContext;
import budget.enumerator.CategoryEnum;
import budget.model.Purchase;
import budget.state.MainMenuState;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

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

    private final List<Purchase> allPurchases = new ArrayList<>();
    private final List<Purchase> allFood = new ArrayList<>();
    private final List<Purchase> allClothes = new ArrayList<>();
    private final List<Purchase> allEntertainment = new ArrayList<>();
    private final List<Purchase> allOther = new ArrayList<>();
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

    public void addFood() throws IOException {
        System.out.println(ENTER_PURCHASE_NAME);
        String name = reader.readLine();
        System.out.println(ENTER_PRICE);
        String price = reader.readLine();

        allFood.add(new Purchase(CategoryEnum.FOOD, name, new BigDecimal(price)));
        allPurchases.addAll(allFood);
        System.out.println(PURCHASE_WAS_ADDED);
    }

    public void addClothes() throws IOException {
        System.out.println(ENTER_PURCHASE_NAME);
        String name = reader.readLine();
        System.out.println(ENTER_PRICE);
        String price = reader.readLine();

        allClothes.add(new Purchase(CategoryEnum.CLOTHES, name, new BigDecimal(price)));
        allPurchases.addAll(allClothes);
        System.out.println(PURCHASE_WAS_ADDED);
    }

    public void addEntertainment() throws IOException {
        System.out.println(ENTER_PURCHASE_NAME);
        String name = reader.readLine();
        System.out.println(ENTER_PRICE);
        String price = reader.readLine();

        allEntertainment.add(new Purchase(CategoryEnum.ENTERTAINMENT, name, new BigDecimal(price)));
        allPurchases.addAll(allEntertainment);
        System.out.println(PURCHASE_WAS_ADDED);
    }

    public void addOther() throws IOException {
        System.out.println(ENTER_PURCHASE_NAME);
        String name = reader.readLine();
        System.out.println(ENTER_PRICE);
        String price = reader.readLine();

        allOther.add(new Purchase(CategoryEnum.OTHER, name, new BigDecimal(price)));
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
            allPurchases.forEach(System.out::println);
            System.out.println(TOTAL_SUM + String.format("%.2f", totalSum.doubleValue()) + "\n");
        }
    }

    public void printListOfFood() {
        if (allFood.isEmpty()) {
            System.out.println(FOOD);
            System.out.println(THE_PURCHASE_LIST_IS_EMPTY);
        } else {
            BigDecimal totalSum = getSumOfAllAmounts(allFood);
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
            BigDecimal totalSum = getSumOfAllAmounts(allClothes);
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
            BigDecimal totalSum = getSumOfAllAmounts(allEntertainment);
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
            BigDecimal totalSum = getSumOfAllAmounts(allOther);
            System.out.println(OTHER);
            allOther.forEach(System.out::println);
            System.out.println(TOTAL_SUM + totalSum + "\n");
        }
    }

    public void printBalance() {
        if (!allFood.isEmpty()) {
            BigDecimal foodExpenses = getSumOfAllAmounts(allFood);
            balance = balance.subtract(foodExpenses);
        }
        if (!allClothes.isEmpty()) {
            BigDecimal clothesExpenses = getSumOfAllAmounts(allClothes);
            balance = balance.subtract(clothesExpenses);
        }
        if (!allEntertainment.isEmpty()) {
            BigDecimal entertainmentExpenses = getSumOfAllAmounts(allEntertainment);
            balance = balance.subtract(entertainmentExpenses);
        }
        if (!allOther.isEmpty()) {
            BigDecimal otherExpenses = getSumOfAllAmounts(allOther);
            balance = balance.subtract(otherExpenses);
        }
        System.out.println(BALANCE + balance);
    }

    public void exitApplication() {
        System.out.println(BYE);
        System.exit(0);
    }

    public List<Purchase> getAllPurchases() {
        return allPurchases;
    }

    public List<Purchase> getAllFood() {
        return allFood;
    }

    public List<Purchase> getAllClothes() {
        return allClothes;
    }

    public List<Purchase> getAllEntertainment() {
        return allEntertainment;
    }

    public List<Purchase> getAllOther() {
        return allOther;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getSumOfAllAmounts(List<Purchase> allPurchases) {
        return allPurchases.stream()
                .map(Purchase::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getTotalSumOfAllProducts() {
        return getSumOfAllAmounts(allFood)
                .add(getSumOfAllAmounts(allClothes))
                .add(getSumOfAllAmounts(allEntertainment))
                .add(getSumOfAllAmounts(allOther));
    }
}
