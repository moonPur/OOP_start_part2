package servise;

public interface NotificationService {
    /**
     * Отправляет уведомление пользователю.
     * @param message   текст сообщения
     * @param recipient контактные данные получателя (email или телефон)
     */
    void sendNotification(String message, String recipient);
}