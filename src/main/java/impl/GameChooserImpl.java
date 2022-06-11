package impl;

import api.Game;
import api.GameChooser;
import api.User;

import java.util.ArrayList;
import java.util.Scanner;

public class GameChooserImpl implements GameChooser {

    @Override
    public Game chooseGame(User user) {
        System.out.print("Выберете игру:\n * Угадать число\n * Загадать число\nВаш вариант: ");
        ArrayList<String> listOfGame = new ArrayList<>();
        listOfGame.add("Угадать число");
        listOfGame.add("Загадать число");
        Scanner scanner = new Scanner(System.in);
        String gameName;
        do {
            gameName = scanner.nextLine();
            if (listOfGame.contains(gameName)) {
                break;
            } else {
                System.out.println("Вы ввели не верное название игры, попробуйте снова: ");
            }
        } while (true);
        switch (gameName) {
            case "Угадать число": return new GuessNumberGame(user);
            case "Загадать число": return new ThinkOfNumberGame(user);
            default: return null;
        }
    }
}
