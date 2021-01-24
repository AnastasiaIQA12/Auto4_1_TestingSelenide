package ru.netology;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {
    @Test
    void shouldSubmitForm() {
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Москва");
        Date today=new Date();
        SimpleDateFormat format=new SimpleDateFormat("dd.MM.yyyy");
        Calendar c=Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH,4);
        String todayPlusThree=(String)(format.format(c.getTime()));
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL+"A"+Keys.DELETE);
        $("[data-test-id=date] input").setValue(todayPlusThree);
        $("[data-test-id=name] input").setValue("Елифанова Анастасия");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $(withText("Успешно!")).waitUntil(visible,15000);
    }

}
