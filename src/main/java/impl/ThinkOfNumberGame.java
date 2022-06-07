package impl;

import api.Game;
import api.User;
import java.util.Random;
import java.util.Scanner;

public class ThinkOfNumberGame implements Game {

  private static final int COUNT_OF_TRIES = 5;
  private int currentMaxNumber = 100;
  private int currentMinNumber = 0;

  private final User user;

  public ThinkOfNumberGame(User user) {
    this.user = user;
  }

  @Override
  public void play() {
    long bet = makeBet();
    // TODO После рефакторинга метода checkUserReady()
    //  убрать неиспользуемую переменную isUserReady
    String isUserReady = checkUserReady();
    Random random = new Random();
    int currentTry = 0;
    int numberToGuess = random.nextInt(100);
    if (guessUsersNumber(numberToGuess, currentTry)) {
      user.reduceMoney(bet);
    } else {
      user.increaseMoney(bet);
    }
  }

  private long makeBet() {
    System.out.println("Сделайте ставку...");
    Scanner scanner = new Scanner(System.in);
    // TODO Добавить проверку, чтобы ставка не превышала оставшейся суммы на счете
    return scanner.nextLong();
  }

  private boolean guessUsersNumber(int numberToGuess, int currentTry) {
    // TODO Количество попыток не учитывается
    //  Программа угадывает число больше, чем максимальное количество попыток
    if (checkCountOfTries(currentTry)) {
      System.out.println("Выигрыш! Программа исчерпала количество попыток...");
      return false;
    }
    System.out.print("Ваше число - " + numberToGuess + "?\n"
        + "Введите:\n Да\n Больше\n Меньше\nВаш ответ: ");
    Scanner scanner = new Scanner(System.in);
    String userAnswer = scanner.nextLine();
    if (userAnswer.equalsIgnoreCase("да")) {
      System.out.println("Проигрыш! Программа угадала ваше число...");
      return true;
    } else if (userAnswer.equalsIgnoreCase("больше")) {
      int newNumberToGuess = (currentMaxNumber - numberToGuess) / 2 + numberToGuess;
      currentMinNumber = numberToGuess;
      guessUsersNumber(newNumberToGuess, currentTry++);
    } else if (userAnswer.equalsIgnoreCase("меньше")) {
      int newNumberToGuess = numberToGuess - (numberToGuess - currentMinNumber) / 2;
      currentMaxNumber = numberToGuess;
      guessUsersNumber(newNumberToGuess, currentTry++);
    }
    return false;
  }

  private String checkUserReady() {
    System.out.print("Загадайте число от 0 до 100 и вбейте 'Загадал': ");
    Scanner scanner = new Scanner(System.in);
    // TODO Проверить, что ввёл пользователь.
    //  Если пользователь ввёл что-то кроме Загадал или ничего,
    //  то указать ему, что надо ввести Загадал и попросить заново.
    //  Метод должен ничего не возвращать
    return scanner.nextLine();
  }

  private boolean checkCountOfTries(int currentTry) {
    return currentTry == COUNT_OF_TRIES;
  }
}
