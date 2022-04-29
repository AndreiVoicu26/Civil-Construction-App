package cca;

public class Card {
    private String cardNumber;
    private String expirationDate;
    private String CVC;

    public Card (String cardNumber, String expirationDate, String CVC) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.CVC = CVC;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getCVC() {
        return CVC;
    }
}
