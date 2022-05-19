package se2.groupb.monopoly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Timer;

import se2.groupb.monopoly.CreateGameField;
import se2.groupb.monopoly.Monopoly;

public abstract class GameScreenAdapter implements Screen {

    Monopoly monopoly;

    public GameScreenAdapter(Monopoly monopoly) {
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

    protected static ImageButton drawImageButton(String texturePath){
        Texture buttonTexture = new Texture(Gdx.files.internal(texturePath));
        TextureRegion buttonTextureRegion = new TextureRegion(buttonTexture);
        TextureRegionDrawable buttonRegionDrawable = new TextureRegionDrawable(buttonTextureRegion);
        return new ImageButton(buttonRegionDrawable);

    }

    public abstract void switchScreenDelayed(final GameScreenAdapter screen, float delay);
}
