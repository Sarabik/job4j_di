package ru.job4j.di.tracker;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringDi {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("ru.job4j.di.tracker");
        context.refresh();
        StartUI ui = context.getBean(StartUI.class);
        ui.add();
        ui.add();
        ui.print();
    }
}
