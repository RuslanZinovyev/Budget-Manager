package budget.model;

import budget.enumerator.CategoryEnum;

import java.math.BigDecimal;
import java.util.Objects;

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

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return category == purchase.category && Objects.equals(name, purchase.name) && Objects.equals(price, purchase.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, name, price);
    }
}
