package ru.whalemare.dev.trpo.lab2;

public interface Controller<Item> {
    void onRender(Renderable renderable);

    void onClickAddHead(Item value);

    void onClickAddLast(Item value);

    void onClickRemoveHead(Item value);

    void onClickRemoveLast(Item value);

    interface Renderable {
        void render(String main, String additional);
    }
}
