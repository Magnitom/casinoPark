package impl;

import api.Game;
import api.User;
import java.util.Random;
import java.util.Scanner;

public class GuessNumberGame implements Game {

  private static final int COUNT_OF_TRIES = 5;

  private final User user;

  public GuessNumberGame(User user) {
    this.user = user;
  }

  @Override
  public void play() {
    // TODO Выполнить рефакторинг метода, разбив на небольшие приватные методы
    System.out.println("Сделайте ставку...");
    Scanner scanner = new Scanner(System.in);
    // TODO Добавить проверку, чтобы ставка не превышала оставшейся суммы на счете
    long bet = scanner.nextLong();
    System.out.println("Загадано число, требуется угадать за 5 попыток...");
    Random random = new Random();
    int numberToGuess = random.nextInt(100);
    int currentTry = 0;
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
        // TODO Добавить бонус игроку в виде двойного выигрыша, если число угадано с 1 раза
        System.out.println("Поздравляю!\nЧисло угадано\nВы выиграли " + bet + " рублей");
        user.increaseMoney(bet);
        break;
      }
      currentTry++;
    }
  }

}
