package se2.groupb.monopoly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import se2.groupb.monopoly.Monopoly;

public abstract class GameScreenAdapter extends ScreenAdapter implements Screen {

    Monopoly monopoly;

    public GameScreenAdapter(Monopoly monopoly) {
        this.monopoly = monopoly;
    }

    /****************** Methods ******************/

    protected static boolean isCorrectPosition(float userPosX, float userPosY, float xPosButton, float yPosButton, float buttonSizeX, float buttonSizeY, float yPosOffset) {
        return (userPosX > xPosButton && userPosX < xPosButton + buttonSizeX && userPosY > (yPosButton + yPosOffset) && userPosY < yPosButton + yPosOffset + buttonSizeY);
    }

    // purely for unit testing protected static method isCorrectPosition()
    public boolean testPosition(float userPosX, float userPosY, float xPosButton, float yPosButton, float buttonSizeX, float buttonSizeY, float yPosOffset) {
        return isCorrectPosition(userPosX, userPosY, xPosButton, yPosButton, buttonSizeX, buttonSizeY, yPosOffset);
    }

    /**
     * Method for making ImageButtons with relative ease
     *
     * @param texturePath
     * @param X           centered positioning of the button on the X axis ->
     *                    the width of the button is already calculated in the method just give it Gdx.graphics.getWidth() / x
     * @param Y
     * @param size        how big should the button be in the screen ->
     *                    use Gdx.graphics.getWidth() / x as size and give it to method, the rest will be done in the method
     *                    Button is as big as x of the screen size, height is automatically scaled to width
     * @return ImageButton that is positioned and scaled
     */
    public static ImageButton drawImageButton(String texturePath, float X, float Y, float size) {
        Texture buttonTexture = new Texture(Gdx.files.internal(texturePath));
        TextureRegion buttonTextureRegion = new TextureRegion(buttonTexture);
        TextureRegionDrawable buttonRegionDrawable = new TextureRegionDrawable(buttonTextureRegion);
        ImageButton button = new ImageButton(buttonRegionDrawable);
        float scale = size / buttonTexture.getWidth();
        button.getImage().setFillParent(true);
        button.getImage().setScale(scale, scale);
        button.setX(X - (((button.getWidth()*scale) / 2.0f)));
        button.setY(Y);
        return button;
    }

    // for testing protected static method drawImageButton()
    public ImageButton drawImageButtonTester(String texturePath, float X, float Y, float size) {
        return drawImageButton(texturePath, X, Y, size);
    }


    public abstract void switchScreenDelayed(final GameScreenAdapter screen, float delay);
}
