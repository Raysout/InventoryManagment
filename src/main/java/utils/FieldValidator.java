package utils;

import model.Product;

import java.util.Scanner;

public final class FieldValidator {
    public static void validatePositive(int number) throws IllegalArgumentException {
        if (number <= 0){
            throw new IllegalArgumentException();
        }
    }

    public static void validatePositive(double number) throws IllegalArgumentException {
        if (number <= 0){
            throw new IllegalArgumentException();
        }
    }

    public static void isNull(String field) throws RuntimeException{
        if (field == null){
            throw new RuntimeException();
        }
    }

    public static void isNull(Product product) throws RuntimeException{
        if (product == null){
            throw new RuntimeException();
        }
    }
}
