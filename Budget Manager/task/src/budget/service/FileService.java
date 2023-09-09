package budget.service;

import budget.enumerator.CategoryEnum;
import budget.model.Purchase;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class FileService {
    private static final String FILE_NAME = "purchases.txt";
    public static final String PURCHASES_WERE_LOADED = "\nPurchases were loaded!";
    public static final String PURCHASES_WERE_SAVED = "\nPurchases were saved!";
    private static FileService instance;
    private final ActionService actionService = ActionService.getInstance();

    public static synchronized FileService getInstance() {
        if (instance == null) {
            instance = new FileService();
        }
        return instance;
    }

    private FileService() {
    }

    public void saveFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {

            if (actionService.getBalance() != null) {
                writer.println("Balance: " + actionService.getBalance());
            }

            if (!actionService.getAllFood().isEmpty()) {
                writer.println("Food:");
                for (Purchase foodPurchase : actionService.getAllFood()) {
                    writer.println(foodPurchase.toString());
                }
            }

            if (!actionService.getAllClothes().isEmpty()) {
                writer.println("Clothes:");
                for (Purchase clothesPurchase : actionService.getAllClothes()) {
                    writer.println(clothesPurchase.toString());
                }
            }

            if (!actionService.getAllEntertainment().isEmpty()) {
                writer.println("Entertainment:");
                for (Purchase entertainmentPurchase : actionService.getAllEntertainment()) {
                    writer.println(entertainmentPurchase.toString());
                }
            }

            if (!actionService.getAllOther().isEmpty()) {
                writer.println("Other:");
                for (Purchase otherPurchase : actionService.getAllOther()) {
                    writer.println(otherPurchase.toString());
                }
            }

            if (!actionService.getAllPurchases().isEmpty()) {
                writer.println("All:");
                for (Purchase purchase : actionService.getAllPurchases()) {
                    writer.println(purchase.toString());
                }
            }

            System.out.println(PURCHASES_WERE_SAVED);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the file.");
        }
    }

    public void loadFile() {
        CategoryEnum currentCategory = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = reader.readLine()) != null) {

                if (line.startsWith("Balance:")) {
                    String balanceStr = line.substring(9);
                    actionService.setBalance(new BigDecimal(balanceStr).setScale(2, RoundingMode.HALF_UP));
                    continue;
                }

                if (line.endsWith(":")) {
                    currentCategory = CategoryEnum.valueOf(line.substring(0, line.length() - 1).toUpperCase());
                    continue;
                }

                switch (currentCategory) {
                    case FOOD -> actionService.getAllFood().add(parseFromLine(CategoryEnum.FOOD, line));
                    case CLOTHES -> actionService.getAllClothes().add(parseFromLine(CategoryEnum.CLOTHES, line));
                    case ENTERTAINMENT ->
                            actionService.getAllEntertainment().add(parseFromLine(CategoryEnum.ENTERTAINMENT, line));
                    case OTHER -> actionService.getAllOther().add(parseFromLine(CategoryEnum.OTHER, line));
                    case ALL -> actionService.getAllPurchases().add(parseFromLine(CategoryEnum.ALL, line));
                }
            }
            System.out.println(PURCHASES_WERE_LOADED);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
        }
    }

    private Purchase parseFromLine(CategoryEnum category, String line) {
        int index = line.lastIndexOf(" $");

        if (index == -1) {
            return null;
        }

        String name = line.substring(0, index).trim();
        BigDecimal price = new BigDecimal(line.substring(index + 2).trim());

        if (category.equals(CategoryEnum.FOOD))
            return new Purchase(CategoryEnum.FOOD, name, price);
        else if (category.equals(CategoryEnum.CLOTHES))
            return new Purchase(CategoryEnum.CLOTHES, name, price);
        else if (category.equals(CategoryEnum.ENTERTAINMENT))
            return new Purchase(CategoryEnum.ENTERTAINMENT, name, price);
        else if (category.equals(CategoryEnum.OTHER))
            return new Purchase(CategoryEnum.OTHER, name, price);

        return new Purchase(CategoryEnum.ALL, name, price);
    }
}
