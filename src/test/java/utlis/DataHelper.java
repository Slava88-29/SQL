package utlis;

import models.User;

public class DataHelper {

    private DataHelper() {}

    public static User getUser() {
        return new User("vasya", "qwerty123");
    }
}
