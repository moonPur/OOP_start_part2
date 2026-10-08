package model;


/**
 * Подписка для бизнес-клиентов. Расчет стоимости зависит от количества рабочих мест.
 */
public class CorporateSubscription extends Subscription {
    private final int activeLicensesCount; // Новое уникальное поле наследника

    public CorporateSubscription(String ownerName, double baseMonthlyPrice, String planName, int activeLicensesCount) {
        super(ownerName, baseMonthlyPrice, planName);
        this.activeLicensesCount = Math.max(1, activeLicensesCount); // Валидация: минимум 1 лицензия
    }

    /**
     * Переопределение метода расчета стоимости с учетом оптового коэффициента лицензий.
     */
    @Override
    public double calculateBillingPrice() {
        final double planModifier = switch (getPlanName()) {
            case "BASIC" -> 1.0;
            case "STANDARD" -> 1.30; // Для корпораций тарифы чуть дешевле за счет объема
            case "PREMIUM" -> 1.70;
            default -> 1.0;
        };

        // Цена за одну лицензию умножается на их общее количество
        double finalPrice = (getBaseMonthlyPrice() * planModifier) * activeLicensesCount;
        return Math.round(finalPrice * 100.0) / 100.0;
    }

    public int getActiveLicensesCount() {
        return activeLicensesCount;
    }
}
