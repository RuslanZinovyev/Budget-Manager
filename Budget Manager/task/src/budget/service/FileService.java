package budget.service;

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
                for (String foodPurchase : actionService.getAllFood()) {
                    writer.println(foodPurchase);
                }
            }

            if (!actionService.getAllClothes().isEmpty()) {
                writer.println("Clothes:");
                for (String clothesPurchase : actionService.getAllClothes()) {
                    writer.println(clothesPurchase);
                }
            }

            if (!actionService.getAllEntertainment().isEmpty()) {
                writer.println("Entertainment:");
                for (String entertainmentPurchase : actionService.getAllEntertainment()) {
                    writer.println(entertainmentPurchase);
                }
            }

            if (!actionService.getAllOther().isEmpty()) {
                writer.println("Other:");
                for (String otherPurchase : actionService.getAllOther()) {
                    writer.println(otherPurchase);
                }
            }

            if (!actionService.getAllPurchases().isEmpty()) {
                writer.println("All:");
                for (String purchase : actionService.getAllPurchases()) {
                    writer.println(purchase);
                }
            }

            System.out.println(PURCHASES_WERE_SAVED);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the file.");
        }
    }

    public void loadFile() {
        String currentCategory = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = reader.readLine()) != null) {

                if (line.startsWith("Balance:")) {
                    String balanceStr = line.substring(9);
                    actionService.setBalance(new BigDecimal(balanceStr).setScale(2, RoundingMode.HALF_UP));
                    continue;
                }

                if (line.endsWith(":")) {
                    currentCategory = line.substring(0, line.length() - 1);
                    continue;
                }

                switch (currentCategory) {
                    case "Food" -> actionService.getAllFood().add(line);
                    case "Clothes" -> actionService.getAllClothes().add(line);
                    case "Entertainment" -> actionService.getAllEntertainment().add(line);
                    case "Other" -> actionService.getAllOther().add(line);
                    case "All" -> actionService.getAllPurchases().add(line);
                }
            }

            System.out.println(PURCHASES_WERE_LOADED);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
        }
    }
}
