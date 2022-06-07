package api;

public interface User {

  void increaseMoney(long amount);

  void reduceMoney(long amount);

  String getName();

  long getMoney();

}
