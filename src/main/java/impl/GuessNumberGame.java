package impl;

import api.Game;
import api.User;

import java.util.Random;
import java.util.Scanner;

public class GuessNumberGame extends Game {

    public GuessNumberGame(User user) {
        super(user);
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        long bet = makeBet();
        System.out.println("Загадано число, требуется угадать за 5 попыток...");
        Random random = new Random();
        int numberToGuess = random.nextInt(100);
//        int currentTry = 0;
        while (true) {
            if (currentTry == COUNT_OF_TRIES) {
                System.out.println("Проигрыш! Вы исчерпали количество попыток. Было загадано число - " + numberToGuess);
                user.reduceMoney(bet);
                break;
            }
            System.out.print("Введите свой вариант: ");
            int enteredNumber = scanner.nextInt();
            if (enteredNumber > numberToGuess) {
                System.out.println("Неправильно! Введеное число меньше...");
            } else if (enteredNumber < numberToGuess) {
                System.out.println("Неправильно! Введеное число больше...");
            } else {
                System.out.println("Поздравляю!\nЧисло угадано\nВы выиграли " + bet + " рублей");
                if (currentTry == 0){
                    System.out.println("Вы отгадали с первого раза, вам начислен двойной выйгрыш!");
                    user.increaseMoney(bet);
                }
                user.increaseMoney(bet);
                break;
            }
            currentTry++;
        }
    }
}