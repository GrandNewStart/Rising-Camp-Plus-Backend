package src.models;

public class MobileClient extends Client {
    @Override
    public String toString() {
        String result = "=====================";
        result += "\n접속 경로: Mobile";
        result += "\n이름: " + this.name;
        result += "\n등급: " + this.rank.value;
        result += "\n결제 방식: " + this.discountType.description;
        return result;
    }
}
