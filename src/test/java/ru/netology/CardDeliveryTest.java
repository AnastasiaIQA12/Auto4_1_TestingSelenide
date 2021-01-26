package ru.netology;

import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {
    @Test
    void shouldSubmitForm() {
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Москва");
        SimpleDateFormat format=new SimpleDateFormat("dd.MM.yyyy");
        Calendar today=Calendar.getInstance();
        today.add(Calendar.DAY_OF_MONTH,3);
        String dayVisit=(String)(format.format(today.getTime()));
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL+"A"+Keys.DELETE);
        $("[data-test-id=date] input").setValue(dayVisit);
        $("[data-test-id=name] input").setValue("Елифанова Анастасия");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $(withText("Успешно!")).waitUntil(visible,15000);
    }

    @Test
    void shouldSubmitFormWithDropDownList() {
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Ка");
        $$(".menu-item").find(exactText("Казань")).click();
        SimpleDateFormat format=new SimpleDateFormat("dd.MM.yyyy");
        Calendar today=Calendar.getInstance();
        today.add(Calendar.DAY_OF_MONTH,3);
        String dayVisit=(String)(format.format(today.getTime()));
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL+"A"+Keys.DELETE);
        $("[data-test-id=date] input").setValue(dayVisit);
        $("[data-test-id=name] input").setValue("Елифанова Анастасия");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $(withText("Успешно!")).waitUntil(visible,15000);
    }

    @Test
    void shouldSubmitFormWithCalendar() {
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Казань");
        Calendar today=Calendar.getInstance();
        today.add(Calendar.DAY_OF_MONTH,7);
        int YEAR= today.get(Calendar.YEAR);
        String year= String.valueOf(YEAR);
        int MONTH=today.get(Calendar.MONTH);
        String[] month={"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
        int DAY=today.get(Calendar.DAY_OF_MONTH);
        String day=String.valueOf(DAY);
        $("[data-test-id=date] button").click();
        String calendarText;
        calendarText=$(".calendar__name").innerText();

        while (calendarText.contains(year)==false)
        {
            $("[data-step='12']").click();
            calendarText =$(".calendar__name").innerText();
        }

        while (calendarText.contains(month[MONTH])==false)
        {
            $("[data-step='1']").click();
            calendarText =$(".calendar__name").innerText();
        }

        ElementsCollection dayInCalendar=$$(".calendar__day");
        for (int i=0; i<35; i++){
            if (dayInCalendar.get(i).innerText().equals(day)){
                dayInCalendar.get(i).click();
                break;
            }
        }

        $("[data-test-id=name] input").setValue("Елифанова Анастасия");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $(withText("Успешно!")).waitUntil(visible,15000);
    }

}
