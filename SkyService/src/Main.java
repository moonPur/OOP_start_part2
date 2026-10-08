import servise.NotificationService;
import servise.impl.SmsNotification;
import servise.impl.EmailNotification;
import model.CorporateSubscription;
import model.PromoCode;
import model.UserSubscription;
import model.Subscription;
import billing.Billing;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Инициализация сервисов системы ===\n");

        // 1. Создаем разные стратегии уведомлений
        NotificationService smsService = new SmsNotification();
        NotificationService emailService = new EmailNotification();

        // 2. Создаем первый биллинг с внедрением SMS-сервиса
        Billing smsBilling = new Billing(smsService);

        // Оформляем обычную подписку физ. лица
        Subscription userSub = new UserSubscription("Иван Петров", 300.0, "PREMIUM");
        smsBilling.processPayment(userSub, "+7-999-123-45-67");

        System.out.println();

        // 3. Создаем второй биллинг с внедрением Email-сервиса
        Billing emailBilling = new Billing(emailService);

        // Оформляем корпоративную подписку на 10 рабочих мест
        Subscription corpSub = new CorporateSubscription("ООО ТехноПром", 300.0, "STANDARD", 10);
        emailBilling.processPayment(corpSub, "finance@technoprom.ru");

        // 4. Вывод глобальной статистики (проверяем, что статика работает сквозь разные объекты Billing)
        Billing.printGlobalStats();
    }
}
