package impl;

import api.Registrator;
import api.User;
import java.util.Scanner;

public class RegistratorImpl implements Registrator {

  @Override
  public User registerUser() {
    System.out.print("Привет, представься: ");
    Scanner scanner = new Scanner(System.in);
    // TODO Добавить проверку имени и спрашивать его до тех пор, пока не введут корректно
    //  Валидность имени: состоит только из букв и из одного слова
    //  Если пользователь вводит имя с маленькой буквы - перевести её в верхний регистр
    String name = scanner.nextLine();
    return new UserImpl(name);
  }
}
