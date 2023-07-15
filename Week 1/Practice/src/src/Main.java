package src;

import src.enums.DiscountType;
import src.enums.Rank;
import src.models.Client;
import src.models.MobileClient;
import src.models.SaleService;
import src.models.WebClient;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // 세일 서비스 객체 생성
        SaleService service = new SaleService("Service", 10000);

        // 고객 객체 생성
        MobileClient john = new MobileClient();
        john.setName("John");
        john.setRank(Rank.GOLD);
        john.setDiscountType(DiscountType.DISCOUNT);

        WebClient bob = new WebClient();
        bob.setName("Bob");
        bob.setRank(Rank.BRONZE);
        bob.setDiscountType(DiscountType.BONUS);

        // 고객 리스트 생성, 추가
        Client[] clients = {john, bob};

        // 고객 정보 조회 & 가격 계산
        for (Client client : clients) {
            System.out.println(client);
            client.getPrice(service.getPrice());
        }
        System.out.println("=====================");
    }

}
