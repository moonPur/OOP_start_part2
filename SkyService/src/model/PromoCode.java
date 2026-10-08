package model;

public class PromoCode {
    private final String code;
    private double discountPercent;

    public PromoCode(String code, double discountPercent) {
        this.code = code.toUpperCase();
        setDiscountPercent(discountPercent);
    }

    public String getCode() { return code; }
    public double getDiscountPercent() { return discountPercent; }

    public void setDiscountPercent(double discountPercent) {
        if (discountPercent >= 0 && discountPercent <= 100) {
            this.discountPercent = discountPercent;
        } else {
            this.discountPercent = 0;
        }
    }
}
