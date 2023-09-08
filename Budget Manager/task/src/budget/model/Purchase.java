package budget.model;

import budget.enumerator.CategoryEnum;

import java.math.BigDecimal;

public class Purchase {
    private CategoryEnum category;
    private String name;
    private BigDecimal price;

    public Purchase(CategoryEnum category, String name, BigDecimal price) {
        this.category = category;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s $%.2f", name, price);
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
