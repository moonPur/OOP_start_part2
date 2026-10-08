package model;

public class UserSubscription extends Subscription {

    public UserSubscription(String ownerName, double baseMonthlyPrice) {
        super(ownerName, baseMonthlyPrice, "STANDARD"); // Вызов конструктора родителя
    }

    public UserSubscription(String ownerName, double baseMonthlyPrice, String planName) {
        super(ownerName, baseMonthlyPrice, planName);
    }

    /**
     * Переопределение метода родителя. Реализует логику тарифов физ.лиц.
     */
    @Override
    public double calculateBillingPrice() {
        final double planModifier = switch (getPlanName()) {
            case "BASIC" -> 1.0;
            case "STANDARD" -> 1.40;
            case "PREMIUM" -> 1.90;
            default -> 1.0;
        };

        double finalPrice = getBaseMonthlyPrice() * planModifier;
        return Math.round(finalPrice * 100.0) / 100.0;
    }
}
