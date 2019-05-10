package com.aggy.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
public class UserDao {
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1, "Kamil", LocalDate.now()));
        users.add(new User(2, "Monika", LocalDate.now()));
        users.add(new User(3, "Kulfon", LocalDate.now()));
    }

    private static int usersCount = 3;
    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if(user.getId() == null) {
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    public User find(Integer id) throws NoSuchElementException {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .get();
    }

    public User deleteById(int id) {
        Iterator<User> iterator = users.iterator();
        while(iterator.hasNext()) {
            User tempUser = iterator.next();
            if(tempUser.getId().equals(id)) {
                iterator.remove();
                return tempUser;
            }
        }
        return null;
    }
}
