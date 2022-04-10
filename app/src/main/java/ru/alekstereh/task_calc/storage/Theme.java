package ru.alekstereh.task_calc.storage;

import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;

import ru.alekstereh.task_calc.R;

public enum Theme {

    //R.string.Theme.......... не распознаёт
    ONE(R.string.Theme.Task_Calc, R.string.theme_one, "THEME_ONE");
    //R.string.Theme.......... не распознаёт, и TWO
    TWO(R.string.Theme.Task_Calc.V2, "THEME_TWO"); //не вставляется автоматически key  как на строке выше

    private @StyleRes
    final int theme;

    private @StringRes
    final int title;
    private final String key;


    Theme(int theme, int title, String key) {
        this.theme = theme;
        this.title = title;
        this.key = key;
    }

    public int getTitle() {
        return title;
    }

    public String getKey() {
        return key;
    }

    public int getTheme() {
        return theme;
    }
}