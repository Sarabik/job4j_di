package ru.job4j.di.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StartUI {

    @Autowired
    private final Store store;

    @Autowired
    private final ConsoleInput input;

    public StartUI(Store store, ConsoleInput input) {
        this.store = store;
        this.input = input;
    }

    public void add() {
        store.add(input.askStr("Add name:"));
    }

    public void print() {
        for (String value : store.getAll()) {
            System.out.println(value);
        }
    }
}