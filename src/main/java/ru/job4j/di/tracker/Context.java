package ru.job4j.di.tracker;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Context {

    /*Карта с объектами, в которй мы будем хранить проинициализированные объекты*/
    private final Map<String, Object> els = new HashMap<>();

    /*Метод регистрации классов*/
    public void reg(Class<?> cl) {

        /*Получаем все конструкторы класса. Если их больше 1,
        то мы не знаем как загружать этот класс и кидаем исключение*/
        Constructor<?>[] constructors = cl.getDeclaredConstructors();
        if (constructors.length > 1) {
            throw new IllegalStateException("Class has multiple constructors : " + cl.getCanonicalName());
        }

        /*Когда мы нашли конструктор, мы собираем аргументы этого конструктора и
        ищем уже зарегистрированные объекты, чтобы внедрить их в конструктор.*/
        Constructor<?> con = constructors[0];
        List<Object> args = new ArrayList<>();
        for (Class<?> arg : con.getParameterTypes()) {
            if (!els.containsKey(arg.getCanonicalName())) {
                throw new IllegalStateException("Object doesn't found in context : " + arg.getCanonicalName());
            }
            args.add(els.get(arg.getCanonicalName()));
        }
        /*Создание объекта и добавление его в карту*/
        try {
            els.put(cl.getCanonicalName(), con.newInstance(args.toArray()));
        } catch (Exception e) {
            throw new IllegalStateException("Can't create an instance of : " + cl.getCanonicalName(), e);
        }
    }

    /*Метод возвращает проинициализированный объект*/
    public <T> T get(Class<T> inst) {
        return (T) els.get(inst.getCanonicalName());
    }
}
