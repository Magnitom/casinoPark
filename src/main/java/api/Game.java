package api;

import java.util.Scanner;

public abstract class Game {

    protected final User user;

    protected int COUNT_OF_TRIES = 5;

    protected int currentTry = 0;

    public Game(User user) {
        this.user = user;
    }

    public abstract void play();

    protected long makeBet() {
        System.out.println("Сделайте ставку...");
        Scanner scanner = new Scanner(System.in);
        String bet;
        long betLong;
        do {
            bet = scanner.nextLine();
            try {
                betLong = Long.parseLong(bet);
                if (betLong > user.getMoney()) {
                    System.out.println("Ставка превышает ваш остаток на счёте(" + user.getMoney() + "), повторите ставку");
                } else break;
            }
            catch (Exception e){
                System.out.println("Вы ввели не верный формат ставки, введите цифры:");
            }
        } while (true);
        return betLong;
    }

    protected boolean checkCountOfTries(int currentTry) {
        if (currentTry >= COUNT_OF_TRIES) {
            return true;
        } else return false;
    }
}
