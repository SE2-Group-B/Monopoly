package se2.groupb.monopoly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

import se2.groupb.monopoly.Monopoly;

public class MinigameScreen extends GameScreenAdapter{

    private Environment environment;
    private int counter;

    private Stage stage;
    private ImageButton clicker;
    private BitmapFont font;
    private SpriteBatch spriteBatch;
    private GlyphLayout waitingText;

    private String text = "Kleines Minispiel um den Pot der Spieler mit den meisten ";

    public MinigameScreen(Monopoly monopoly) {
        super(monopoly);

        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 1f, 1f, 1f, 1f));
    }

    @Override
    public void show() {
        stage = new Stage();
        clicker = drawImageButton("images/MenuButtons/clicker.png", Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()/2f, (float) (Gdx.graphics.getWidth()/3D));

        clicker.addListener(event -> {
            if(Gdx.input.justTouched()){
                counter++;
            }return true;
        });
        stage.addActor(clicker);

        font = new BitmapFont();
        spriteBatch = new SpriteBatch();
        font.getData().setScale(7f);
        waitingText = new GlyphLayout(font, text);
    }

    @Override
    public void switchScreenDelayed(GameScreenAdapter screen, float delay) {

    }
}
