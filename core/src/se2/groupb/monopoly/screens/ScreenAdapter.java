package se2.groupb.monopoly.screens;

import com.badlogic.gdx.Screen;

import se2.groupb.monopoly.Monopoly;

public abstract class ScreenAdapter implements Screen {

    Monopoly monopoly;

    public ScreenAdapter(Monopoly monopoly) {
        this.monopoly = monopoly;
    }

    @Override
    abstract public void show();

    @Override
    abstract public void render(float delta);

    @Override
    abstract public void resize(int width, int height);

    @Override
    abstract public void pause();

    @Override
    abstract public void resume();

    @Override
    abstract public void hide();

    @Override
    abstract public void dispose();

    /****************** Methods ******************/

    protected static boolean isCorrectPosition(float userPosX, float userPosY, float xPosButton, float yPosButton, float buttonSizeX, float buttonSizeY, float yPosOffset) {
        return (userPosX > xPosButton && userPosX < xPosButton + buttonSizeX && userPosY > (yPosButton + yPosOffset) && userPosY < yPosButton + yPosOffset + buttonSizeY);
    }

    // purely for unit testing private static method isCorrectPosition()
    public boolean testPosition(float userPosX, float userPosY, float xPosButton, float yPosButton, float buttonSizeX, float buttonSizeY, float yPosOffset) {
        return isCorrectPosition(userPosX, userPosY, xPosButton, yPosButton, buttonSizeX, buttonSizeY, yPosOffset);
    }
}
