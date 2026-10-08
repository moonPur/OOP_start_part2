package model;

/**
 * Абстрактный базовый класс для всех типов подписок.
 * Задает общую структуру, но делегирует расчет стоимости наследникам.
 */
public abstract class Subscription {
    private final String ownerName;
    private final double baseMonthlyPrice;
    private final String planName; // "BASIC", "STANDARD", "PREMIUM"

    public Subscription(String ownerName, double baseMonthlyPrice, String planName) {
        this.ownerName = ownerName;
        this.baseMonthlyPrice = baseMonthlyPrice;
        this.planName = planName.toUpperCase();
    }

    /**
     * Абстрактный метод расчета стоимости.
     * Каждый тип подписки обязан реализовать его по-своему.
     */
    public abstract double calculateBillingPrice();

    // Геттеры, общие для всех наследников
    public String getOwnerName() { return ownerName; }
    public double getBaseMonthlyPrice() { return baseMonthlyPrice; }
    public String getPlanName() { return planName; }
}