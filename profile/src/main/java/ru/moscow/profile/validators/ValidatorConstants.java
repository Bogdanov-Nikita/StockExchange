package ru.moscow.profile.validators;

public final class ValidatorConstants {

    public static final class PhonePatternValidation {
        public static final String MESSAGE = "Not correct phone";
        public static final String VALUE = "^7\\d{10}$";
    }

    public static final class PassportPatternValidation {
        public static final String MESSAGE = "Not correct passport number";
        public static final String VALUE = "^\\d{4} \\d{7}$";
    }

    public static final class EmailValidation {
        public static final String MESSAGE = "Not correct e-mail";
    }

}
