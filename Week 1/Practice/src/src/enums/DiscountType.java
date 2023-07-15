package src.enums;

public enum DiscountType {
    BONUS("보너스 결제 방식"),
    DISCOUNT("할인 결제 방식");

    public final String description;

    DiscountType(String description) {
        this.description = description;
    }

}
