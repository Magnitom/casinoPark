package app;

import api.Game;
import api.GameChooser;
import api.Registrator;
import api.User;
import impl.GameChooserImpl;
import impl.RegistratorImpl;

public class Application {

  public static void main(String[] args) {
    Registrator registrator = new RegistratorImpl();
    GameChooser gameChooser = new GameChooserImpl();
    User user = registrator.registerUser();
    do {
      Game game = gameChooser.chooseGame(user);
      game.play();
      System.out.println(user.getName() + ", у тебя на счете " + user.getMoney());
      // TODO Продумать систему выхода из казино
    } while (isUserAvailableToPlay(user));
  }

  private static boolean isUserAvailableToPlay(User user) {
    // TODO Сделать проверку, что пользователь может играть в казино.
    //  В случае, если у пользователя закончились деньги, предложить пополнить
    return true;
  }

}
