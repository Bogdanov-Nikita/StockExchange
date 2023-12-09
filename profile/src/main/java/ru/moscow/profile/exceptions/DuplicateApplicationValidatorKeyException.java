package ru.moscow.profile.exceptions;

/**
 * ApplicationValidator не должны иметь одинаковые ключи.
 * Каждому ApplicationValidator должен один к одному соответствовать элемент из перечисления.
 * По этому на всякий случай добавлен такой Exception, чтобы отличить от других ошибок
 */
public class DuplicateApplicationValidatorKeyException extends RuntimeException {
    /**
     * Исключение дублей ключей ApplicationValidator с базовым сообщением
     */
    public DuplicateApplicationValidatorKeyException() {
        super("ApplicationValidator should not have the same keys. "
            + "Each validator must match one to one an element from the enumeration");
    }
}
