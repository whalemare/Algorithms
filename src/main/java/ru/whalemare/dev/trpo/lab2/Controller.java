package ru.whalemare.dev.trpo.lab2;

public interface Controller {
    void onRender(Renderable renderable);

    void onClickAddHead(int value);

    void onClickAddLast(int value);

    void onClickRemoveHead(int value);

    void onClickRemoveLast(int value);

    interface Renderable {
        void render(String main, String additional);
    }
}
