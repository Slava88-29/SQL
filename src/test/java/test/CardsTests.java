package test;

import models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.VerifyPage;
import sql.SqlQuery;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CardsTest {
    // спецификация нужна для того, чтобы переиспользовать настройки в разных запросах

    public User user = new User("vasya", "qwerty123");

    @BeforeEach
    void setup() {
        System.setProperty("chromeoptions.prefs", "profile.password_manager_leak_detection=false");
    }

    @Test
    @DisplayName("Успешный вход в систему")
    void shouldLoginAndVerify() {
        new LoginPage().loginIn(user);
        SqlQuery query = new SqlQuery();
        String code = query.getValidCode();
        new VerifyPage().verifyIn(code);

        DashboardPage dashboardPage = new DashboardPage();
    }

    @Test
    @DisplayName("Три неудачных попытки блокируют систему")
    void shouldBlockWhenThreeWrong() {
        LoginPage login = new LoginPage();
        User user = new User("vasya", "password1");
        login.loginIn(user).checkErrorNotification().clear();

        user.setPassword("password2");
        login.loginIn(user).checkErrorNotification().clear();

        user.setPassword("password3");
        login.loginIn(user).checkErrorNotification();

        login.checkDisable();
    }
}