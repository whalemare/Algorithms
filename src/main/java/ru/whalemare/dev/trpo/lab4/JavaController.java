package ru.whalemare.dev.trpo.lab4;

import ru.whalemare.dev.trpo.lab1.LinkedDeque;
import ru.whalemare.dev.trpo.lab2.Controller;

import java.util.concurrent.Callable;

/**
 * @author Mikhail Medvedev
 * @since 2019
 */
public class JavaController implements Controller {
    private LinkedDeque<Integer> linkedDeque = new LinkedDeque<>();
    private Renderable renderable;


    private void render(int value) {
        renderable.render(
                linkedDeque.toString(),
                "Список пуст: " + safe(() -> linkedDeque.isEmpty()) + "\n" +
                        "Начальный элемент: " + safe(() -> linkedDeque.peekHead()) + "\n" +
                        "Конечный элемент: " + safe(() -> linkedDeque.peekLast()) + "\n" +
                        "Индекс элемента [" + value + "]: " + safe(() -> linkedDeque.indexOf(value))
        );
    }

    @Override
    public void onClickAddHead(int value) {
        linkedDeque.addHead(value);
        render(value);
    }

    @Override
    public void onClickAddLast(int value) {
        linkedDeque.addLast(value);
        render(value);
    }

    @Override
    public void onClickRemoveHead(int value) {
        linkedDeque.removeHead();
        render(value);
    }

    @Override
    public void onClickRemoveLast(int value) {
        linkedDeque.removeLast();
        render(value);
    }

    @Override
    public void onRender(Renderable renderable) {
        this.renderable = renderable;
    }

    public <V> V safe(Callable<V> callable) {
        try {
            return callable.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
