package impl;

import api.Registrator;
import api.User;

import java.util.Scanner;

public class RegistratorImpl implements Registrator {

    @Override
    public User registerUser() {
        System.out.print("Привет, представься: ");
        Scanner scanner = new Scanner(System.in);
        String name;
        do {
            name = scanner.nextLine();
            if (name.matches("^[A-Za-zА-Яа-я][A-Za-zА-Яа-я]+$")) {
                break;
            } else {
                System.out.println("Вы ввели не допустимое имя, попробуйте снова: ");
            }
        }
        while (true);
        name = name.replaceFirst(".", name.substring(0, 1).toUpperCase());
        System.out.println(name + ", добро пожаловать в казино!");
        return new UserImpl(name);
    }
}
