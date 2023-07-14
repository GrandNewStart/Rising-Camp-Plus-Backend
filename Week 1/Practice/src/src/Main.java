package src;

import src.enums.DiscountType;
import src.enums.Rank;
import src.models.MobileClient;
import src.models.SaleService;
import src.models.WebClient;

public class Main {

    public static void main(String[] args) {

        SaleService coffee = new SaleService("Coffee", 5000.0);
        SaleService cake = new SaleService("Cake", 3600.0);

        MobileClient john = new MobileClient();
        john.setName("John");
        john.setRank(Rank.BRONZE);
        john.setDiscountType(DiscountType.DISCOUNT);

        WebClient bob = new WebClient();
        bob.setName("Bob");
        bob.setRank(Rank.GOLD);
        bob.setDiscountType(DiscountType.BONUS);

        System.out.println(john);
        System.out.println(bob);
        System.out.println("=====================");
    }

}
