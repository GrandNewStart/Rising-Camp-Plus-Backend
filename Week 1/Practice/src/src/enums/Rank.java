package src.enums;

public enum Rank {
    BRONZE("BRONZE", 0.1),
    SILVER("SILVER", 0.2),
    GOLD("GOLD", 0.3);

    public final String value;
    public final Double discountRate;

    Rank(String value, Double discountRate) {
        this.value = value;
        this.discountRate = discountRate;
    }

}
