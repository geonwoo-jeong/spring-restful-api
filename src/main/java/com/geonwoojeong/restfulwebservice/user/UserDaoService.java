package com.geonwoojeong.restfulwebservice.user;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDaoService {
  private static List<User> users = new ArrayList<>();

  private static int usersCount = 3;

  static {
    users.add(new User(1, "Kenneth", new Date()));
    users.add(new User(1, "Alice", new Date()));
    users.add(new User(1, "Elena", new Date()));
  }

  public List<User> findAll() {
    return users;
  }

  public User save(User user) {
    if (user.getId() == null) {
      user.setId(++usersCount);
    }
    users.add(user);
  }

  public User findOne(int id) {
    for (User user: users) {
      if (user.getId() == id) {
        return user;
      }
    }
    return null;
  }
}
