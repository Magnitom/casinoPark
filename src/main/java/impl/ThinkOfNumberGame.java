package impl;

import api.Game;
import api.User;

import java.util.Random;
import java.util.Scanner;

public class ThinkOfNumberGame extends Game {

    private int currentMaxNumber = 100;
    private int currentMinNumber = 0;

    public ThinkOfNumberGame(User user) {
        super(user);
    }

    @Override
    public void play() {
        long bet = makeBet();
        checkUserReady();
        Random random = new Random();
//        int currentTry = 0;
        int numberToGuess = random.nextInt(100);
        if (guessUsersNumber(numberToGuess, currentTry)) {
            user.reduceMoney(bet);
        } else {
            user.increaseMoney(bet);
        }
    }

    private boolean guessUsersNumber(int numberToGuess, int currentTry) {
        if (checkCountOfTries(currentTry)) {
            System.out.println("Выигрыш! Программа исчерпала количество попыток...");
            return false;
        }
        System.out.print("Ваше число - " + numberToGuess + "?\n"
                + "Введите порядковый номер ответа:\n 1. Да\n 2. Больше\n 3. Меньше\nВаш ответ: ");
        Scanner scanner = new Scanner(System.in);
        int userAnswer = scanner.nextInt();
        if (userAnswer == 1) {
            System.out.println("Проигрыш! Программа угадала ваше число...");
            return true;
        } else if (userAnswer == 2) {
            int newNumberToGuess = (currentMaxNumber - numberToGuess) / 2 + numberToGuess;
            currentMinNumber = numberToGuess;
            currentTry++;
            guessUsersNumber(newNumberToGuess, currentTry);
        } else if (userAnswer == 3) {
            int newNumberToGuess = numberToGuess - (numberToGuess - currentMinNumber) / 2;
            currentMaxNumber = numberToGuess;
            currentTry++;
            guessUsersNumber(newNumberToGuess, currentTry);
        } else {
            System.out.println("Вы ввели недопустимый ответ, попробуйте снова: ");
            guessUsersNumber(numberToGuess, currentTry);
        }
        return false;
    }

    private void checkUserReady() {
        do {
            try {
                System.out.print("Загадайте число от 0 до 100 и вбейте '1': ");
                Scanner scanner = new Scanner(System.in);
                int guess;
                guess = scanner.nextInt();
                if (guess == 1) {
                    break;
                }
                System.out.println("Вы ввели не корректное значение, попробуйте снова:");
            } catch (Exception e) {
                System.out.println("Вы ввели не корректное значение, попробуйте снова");
            }
        } while (true);
    }
}
