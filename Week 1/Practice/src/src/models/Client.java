package src.models;

import src.interfaces.Discount;
import src.enums.DiscountType;
import src.enums.Rank;

public abstract class Client implements Discount {

    protected String name;
    protected Rank rank;
    protected DiscountType discountType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    public Integer getPrice(Integer price) {
        switch (this.discountType) {
            case DISCOUNT -> {
                double p = price * (1 - this.rank.discountRate);
                System.out.printf("결제 금액: %d\n", (int) p);
                return (int) p;
            }
            case BONUS -> {
                double bonus = price * this.rank.discountRate;
                System.out.printf("보너스: %d\n", (int) bonus);
                System.out.printf("결제 금액: %d\n", price);
                return price;
            }
        }
        return price;
    }

    public boolean equals(Client client) {
        return this.name.hashCode() == client.name.hashCode();
    }

    public abstract String toString();

}
