package src.models;

import src.impls.DiscountImpl;
import src.enums.DiscountType;
import src.enums.Rank;

public abstract class Client implements DiscountImpl {

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

    public Double getPrice(Double price) {
        switch (this.discountType) {
            case DISCOUNT -> {
                return price * (1 - this.rank.discountRate);
            }
            case BONUS -> {
                return price + price * this.rank.discountRate;
            }
        }
        return 0.0;
    }

    public boolean equals(Client client) {
        return this.name.hashCode() == client.name.hashCode();
    }

    public abstract String toString();

}
