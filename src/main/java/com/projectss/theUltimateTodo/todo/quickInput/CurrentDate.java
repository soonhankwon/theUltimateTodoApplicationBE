package com.projectss.theUltimateTodo.todo.quickInput;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

@Component
public class CurrentDate {
    public String inputDate() {
        LocalDate currentDate = LocalDate.now();

        DateTimeFormatter DateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(DateFormat);

        String dayOfWeek = currentDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());

        String inputDate = "오늘은 " + formattedDate + "이고, " + dayOfWeek + "입니다. ";

        return inputDate;
    }
}
