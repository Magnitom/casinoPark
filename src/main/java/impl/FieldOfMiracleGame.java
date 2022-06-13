package impl;

import api.Game;
import api.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Function;

public class FieldOfMiracleGame extends Game {
    public FieldOfMiracleGame(User user) {
        super(user);
    }

    private ArrayList<String> listGuessLetter = new ArrayList<>();

    private static final double KOEF_TRIES = 1.5;

    @Override
    public void play() {
        long bet = makeBet();
        ArrayList<String> wordsList = initWords();
        int pickWord = new Random().nextInt(wordsList.size() - 1);
        String word = wordsList.get(pickWord);
        COUNT_OF_TRIES = (int)(word.length()/KOEF_TRIES);
        ArrayList<String> wordList = new ArrayList<String>(Arrays.asList(word.split("")));
        System.out.println("В этой игре нужно отгадать слово, предварительно отгадывая буквы.");
        System.out.println("В загаданном слове " + word.length() + " букв.");
        System.out.println("У вас есть " + COUNT_OF_TRIES + " попыток отгадать буквы и 1 попытка отгадать слово.");
        do {
            guessLetter(word);
            System.out.print("Слово с отгаданными буквами выглядит так: ");
            showWordPart(wordList);
            if (userReady()) {
                if (guessWord(word)) {
                    user.increaseMoney(bet);
                } else {
                    user.reduceMoney(bet);
                }
                break;
            }
        } while (!checkCountOfTries(currentTry));
        if (checkCountOfTries(currentTry)) {
            user.reduceMoney(bet);
            System.out.println("Проигрыш! Вы исчерпали количество попыток отгадать буквы. Загаданное слово было: " + word);
        }
    }

    private ArrayList<String> initWords() {
        ArrayList<String> listOfWords = new ArrayList<>();
        listOfWords.add("массив");
        listOfWords.add("переменная");
        listOfWords.add("интрефейс");
        listOfWords.add("полиморфизм");
        listOfWords.add("инкапсуляция");
        listOfWords.add("абстракция");
        listOfWords.add("наследование");
        listOfWords.add("исключения");
        listOfWords.add("конструктор");
        return listOfWords;
    }

    private void guessLetter(String word) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Отгадайте букву в слове: ");
        String letter = sc.nextLine();
        if (word.contains(letter)) {
            listGuessLetter.add(letter);
            System.out.print("Эта буква есть в слове. ");
        } else {
            currentTry++;
            System.out.print("Вы не отгадали букву. ");
        }
        System.out.println("У вас осталось " + (COUNT_OF_TRIES - currentTry) + " попыток отгадать буквы.");
    }

    private void showWordPart(ArrayList<String> wordList) {
        ArrayList<String> outListWord = new ArrayList<>(wordList.size());
        for (int i = 0; i < wordList.size(); i++) {
            if (listGuessLetter.contains(wordList.get(i))) {
                outListWord.add(wordList.get(i));
            } else outListWord.add("*");
        }
        for (String i :
                outListWord) {
            System.out.print(i);
        }
        System.out.println();
    }

    private boolean userReady() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Вы готовы отгадать слово?(1 - да, 2 - нет)");
        String wordReady = sc.nextLine();
        if (wordReady.equals("1") || wordReady.equals("2")) {
            if (wordReady.equals("1")) {
                return true;
            } else return false;
        } else {
            System.out.println("Вы ввели недопустимый символ, попробуйте снова.");
            return userReady();
        }
    }

    private boolean guessWord(String word) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Напишите загаданное слово:");
        String wordUser = sc.nextLine();
        if (wordUser.equals(word)) {
            System.out.println("Поздравялем, вы отгадали слово!");
            return true;
        } else {
            System.out.println("Вы не отгадали слово. Загаданное слово было: " + word);
            return false;
        }
    }
}
