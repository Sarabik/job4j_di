package ru.job4j.di.tracker;

public class StartUI {

    private final Store store;

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