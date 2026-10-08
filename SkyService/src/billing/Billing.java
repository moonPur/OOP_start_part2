package billing;
// Импорт интерфейс и абстрастный класс

import model.Subscription;
import servise.NotificationService;

/**
 * Класс бизнес-логики (Service Layer).
 * Демонстрирует паттерн Стратегия и Внедрение зависимостей (DI).
 */


public class Billing {
    // Глобальная статистика (статика)
    private static int totalSubscribersCount = 0;
    private static double totalRevenue = 0;

    // Ссылка на ИНТЕРФЕЙС. Класс Billing ничего не знает про Email или SMS конкретно!
    //private final NotificationService notificationService;
    private final servise.NotificationService notificationService;

    /**
     * Конструктор принимает ЛЮБУЮ реализацию интерфейса уведомлений.
     * Это ручное внедрение зависимости (Dependency Injection).
     */
    public Billing(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    /**
     * Обработка платежа. Работает с абстракцией Subscription благодаря полиморфизму.
     */
    public void processPayment(Subscription sub, String contact) {
        // Полиморфный вызов: Java сама поймет, у кого вызвать метод — у User или Corporate
        double finalPrice = sub.calculateBillingPrice();

        // Обновляем статические счетчики
        totalRevenue += finalPrice;
        totalSubscribersCount++;

        // Формируем текст уведомления
        String message = String.format("Счет оплачен. Тариф [%s]. Списано: %.2f руб.",
                sub.getPlanName(), finalPrice);

        // Отправляем уведомление через внедренный ранее сервис
        notificationService.sendNotification(message, contact);
    }

    public static void printGlobalStats() {
        System.out.println("\n--- ГЛОБАЛЬНЫЯ СТАТИСТИКА СИСТЕМЫ ---");
        System.out.println("Всего обработано подписок: " + totalSubscribersCount);
        System.out.println("Общая выручка компании: " + Math.round(totalRevenue * 100.0) / 100.0 + " руб.");
        System.out.println("-------------------------------------");
    }
}