package com.geekbrains.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Turret {
    private GameScreen gameScreen;

    private TextureRegion texture;
    private int cellX, cellY;
    private float angle;
    private byte[][] pressedSquares;
    private int pressedX = 0;
    private int pressedY = 0;
    private Vector2 monsterPosition;
    private Vector2 nullVector;


    public Turret(GameScreen gameScreen, TextureAtlas atlas) {
        this.nullVector = new Vector2(1,0);
        this.monsterPosition = new Vector2(cellX,cellY);
        pressedSquares = new byte[16][9];
        this.gameScreen = gameScreen;
        this.texture = new TextureRegion(atlas.findRegion("turrets"), 0, 0, 80, 80);
        this.cellX = 8;
        this.cellY = 4;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 9; j++) {
                pressedSquares[i][j]=0;
            }
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, cellX * 80, cellY * 80, 40, 40, 80, 80, 1, 1, angle);
    }

    public void update(float dt) {

        monsterPosition.set(gameScreen.getMonster().getPosition());
        monsterPosition.sub(cellX,cellY);
        monsterPosition.nor();

        // получаю вектор, направленный в самое сердце мостра, размер не важен нам
        // arctg(y - противоположная сторона, x - ближняя сторона) нам даст угол
       // System.out.println(monsterPosition);
       // nullVector.set(monsterPosition.x,monsterPosition.y);


    //angle = (float) Math.acos((monsterPosition.x*nullVector.x+monsterPosition.y+nullVector.y) / (monsterPosition.len() * nullVector.len()));
      angle = monsterPosition.angle();
        System.out.println(monsterPosition.len());
        System.out.println(nullVector.len());

    System.out.println(angle);

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 9; j++) {
                if (pressedSquares[i][j]==2){
                    this.cellX = i;
                    this.cellY = j;
                    pressedSquares[i][j]=0;
                }
            }
        }

        if (Gdx.input.justTouched()) {
            pressedX = Gdx.input.getX() / 80;
            pressedY = (720 - Gdx.input.getY()) / 80;
            pressedSquares[pressedX][pressedY]++;
        }
    }
}
