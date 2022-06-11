package app;

import api.Game;
import api.GameChooser;
import api.Registrator;
import api.User;
import impl.GameChooserImpl;
import impl.RegistratorImpl;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Registrator registrator = new RegistratorImpl();
        GameChooser gameChooser = new GameChooserImpl();
        User user = registrator.registerUser();
        do {
            Game game = gameChooser.chooseGame(user);
            game.play();
            System.out.println(user.getName() + ", у тебя на счете " + user.getMoney());
            boolean gameOut = false;
            do {
                System.out.println("Хотите продолжить игру?(1 - да, 2 - нет)");
                String gameOver = new Scanner(System.in).nextLine();
                boolean gameCorrect = false;
                switch (gameOver) {
                    case "1":
                        gameCorrect = true;
                        break;
                    case "2": {
                        System.out.println("До свидания!");
                        gameOut = true;
                        gameCorrect = true;
                        break;
                    }
                    default:
                        System.out.println("Вы ввели не верное значение, попробуйте снова.");
                        break;
                }
                if (gameCorrect) break;
            } while (true);
            if (gameOut) break;
        } while (isUserAvailableToPlay(user));
    }

    private static boolean isUserAvailableToPlay(User user) {
        if (user.getMoney() <= 0) {
            System.out.println("У вас на счёте недостаточно средств, пополните счёт. До свидания!");
            return false;
        } else return true;
    }

}
