package se2.groupb.monopoly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

import se2.groupb.monopoly.CreateGameField;
import se2.groupb.monopoly.Monopoly;

public class WinningScreen extends GameScreenAdapter{

    private BitmapFont font;
    private GlyphLayout waitingText;
    private Environment environment;
    private float buttonsize;
    //private int[] sums;
    //private String[] placement;
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
    /*private String first = "Sieger: Player 1 with: 2000$";
    private String second = "Zweiter: Player 2 with: 1800$";
    private String third = "Dritter: Player 3 with: 1600$";
    private String fourth = "Vierter: Player 4 with: 100$";*/


    public WinningScreen(Monopoly monopoly, ArrayList<Integer> sums, ArrayList<String> placement) {
        super(monopoly);
        //environment = new Environment();
        //environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 1f, 1f, 1f, 1f));
        this.sums = sums;
        this.placement = placement;
    }

    @Override
    public void show() {
        stage = new Stage();
        end = drawImageButton("images/MenuButtons/arrow.png", Gdx.graphics.getWidth()-100, 50,buttonsize/4);

        end.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if(Gdx.input.justTouched()){
                    Gdx.app.exit();
                }return true;
            }
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

        first = "Sieger: " + placement.get(0) + " with:" + sums.get(0);
        second = "Zweiter: " + placement.get(1) + " with:" + sums.get(1);
        third = "Dritter: " + placement.get(2) + " with:" + sums.get(2);
        fourth = "Vierter: " + placement.get(3) + " with:" + sums.get(3);

        font.setColor(Color.BLACK);
        font.draw(spriteBatch, text, (float) (Gdx.graphics.getWidth()/3.75),(Gdx.graphics.getHeight()/2)+250);
        font.draw(spriteBatch, first, (float) (Gdx.graphics.getWidth()/3.75),(Gdx.graphics.getHeight()/2)+150);
        font.draw(spriteBatch, second, (float) (Gdx.graphics.getWidth()/3.75), (Gdx.graphics.getHeight()/2)+50);
        font.draw(spriteBatch, third, (float) (Gdx.graphics.getWidth()/3.75),(Gdx.graphics.getHeight()/2)-50);
        font.draw(spriteBatch, fourth, (float) (Gdx.graphics.getWidth()/3.75), (Gdx.graphics.getHeight()/2)-150);

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

    public void switchScreenDelayed(GameScreenAdapter screen, float delay) {

    }

}
