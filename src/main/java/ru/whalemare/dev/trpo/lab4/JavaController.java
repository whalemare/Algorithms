package ru.whalemare.dev.trpo.lab4;

import ru.whalemare.dev.trpo.lab1.HeaderLinkedList;
import ru.whalemare.dev.trpo.lab2.Controller;

import java.util.List;
import java.util.concurrent.Callable;

public class JavaController implements Controller<List<String>> {
    private HeaderLinkedList<String> linkedDeque = new HeaderLinkedList<>();
    private Renderable renderable;


    private void render(List<String> value) {
        renderable.render(
                linkedDeque.toString(),
                "Пустой список: " + safe(() -> linkedDeque.isEmpty()) + "\n" +
                        "Первый список: " + safe(() -> linkedDeque.peekHead()) + "\n" +
                        "Последний список: " + safe(() -> linkedDeque.peekLast()) + "\n" +
                        "Индекс списка [" + value + "]: " + safe(() -> linkedDeque.indexOf(value))
        );
    }

    @Override
    public void onClickAddHead(List<String> value) {
        linkedDeque.addHead(value);
        render(value);
    }

    @Override
    public void onClickAddLast(List<String> value) {
        linkedDeque.addLast(value);
        render(value);
    }

    @Override
    public void onClickRemoveHead(List<String> value) {
        linkedDeque.removeHead();
        render(value);
    }

    @Override
    public void onClickRemoveLast(List<String> value) {
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
