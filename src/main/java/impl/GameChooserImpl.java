package impl;

import api.Game;
import api.GameChooser;
import api.User;

import java.util.ArrayList;
import java.util.Scanner;

public class GameChooserImpl implements GameChooser {

    @Override
    public Game chooseGame(User user) {
        System.out.print("Выберете игру, набрав её порядковый номер:\n 1. Угадать число\n 2. Загадать число\n 3. Поле чудес\n Ваш вариант: ");
        Scanner scanner = new Scanner(System.in);
//        String gameName;
//        do {
//            gameName = scanner.nextLine();
//            if (listOfGame.contains(gameName)) {
//                break;
//            } else {
//                System.out.println("Вы ввели не верное название игры, попробуйте снова: ");
//            }
//        } while (true);
        do {
            String gameChoose = scanner.nextLine();
            switch (gameChoose) {
                case "1":
                    return new GuessNumberGame(user);
                case "2":
                    return new ThinkOfNumberGame(user);
                case "3":
                    return new FieldOfMiracleGame(user);
                default:
                    System.out.println("Вы ввели не существующую игру, попробуйте снова: ");
            }
        } while (true);
    }
}
