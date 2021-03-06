package se2.groupb.monopoly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

import se2.groupb.monopoly.Monopoly;

public class WinningScreen extends GameScreenAdapter{

    private BitmapFont font;
    private GlyphLayout waitingText;
    private Environment environment;
    private float buttonsize;
    private ArrayList<Integer> sums;
    private ArrayList<String> placement;

    private Stage stage;
    private ImageButton end;
    SpriteBatch spriteBatch;

    private String text = "Und die Sieger sind:";
    private String first;
    private String second;
    private String third;
    private String fourth;
    private String with = " with:"; //Sonarcloud


    public WinningScreen(Monopoly monopoly, ArrayList<Integer> sums, ArrayList<String> placement) {
        super(monopoly);
        this.sums = sums;
        this.placement = placement;
    }

    @Override
    public void show() {
        stage = new Stage();
        end = drawImageButton("images/MenuButtons/arrow.png", Gdx.graphics.getWidth()-100f, 50,buttonsize/4);

        end.addListener(event -> {
            if(Gdx.input.justTouched()){
                Gdx.app.exit();
            }return true;
        });
        stage.addActor(end);

        font = new BitmapFont();
        spriteBatch = new SpriteBatch();
        font.getData().setScale(7f);
        waitingText = new GlyphLayout(font, text);

        /**
         * instead of closing the App go to Main Menu
         */
        InputBackProcessor inputProcessor = new InputBackProcessor(monopoly);
        inputProcessor.backToMainMenuProcessor();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        buttonsize = (float) (Gdx.graphics.getWidth()/3D);
        monopoly.getBatch().begin();
        spriteBatch.begin();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        first = "Sieger: " + placement.get(0) + with + sums.get(0);
        second = "Zweiter: " + placement.get(1) + with + sums.get(1);
        third = "Dritter: " + placement.get(2) + with + sums.get(2);
        fourth = "Vierter: " + placement.get(3) + with + sums.get(3);

        font.setColor(Color.BLACK);
        font.draw(spriteBatch, text, (float) (Gdx.graphics.getWidth()/3.75), (float) (Gdx.graphics.getHeight()/2)+250f);
        font.draw(spriteBatch, first, (float) (Gdx.graphics.getWidth()/3.75),(float) (Gdx.graphics.getHeight()/2)+150f);
        font.draw(spriteBatch, second, (float) (Gdx.graphics.getWidth()/3.75), (float) (Gdx.graphics.getHeight()/2)+50f);
        font.draw(spriteBatch, third, (float) (Gdx.graphics.getWidth()/3.75),(float) (Gdx.graphics.getHeight()/2)-50f);
        font.draw(spriteBatch, fourth, (float) (Gdx.graphics.getWidth()/3.75), (float) (Gdx.graphics.getHeight()/2)-150f);

        spriteBatch.end();
        monopoly.getBatch().end();
    }

    @Override
    public void dispose() {
        super.dispose();
        font.dispose();
        spriteBatch.dispose();
        stage.dispose();
    }

    //Because GameScreenAdapter requires it
    /** If Sonarcloud won't accept the Comment */
    public void switchScreenDelayed(GameScreenAdapter screen, float delay) {

    }

}
