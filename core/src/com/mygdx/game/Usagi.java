package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Usagi extends TipoPersonaje {
	private static final float FACTOR_DIAGONAL = 0.7071f;

    public Usagi(Texture imagen) {
        super("Usagi", imagen, 40, 400);
    }

    
    
    
    @Override
    public void mover(Rectangle area, float delta) {
        int dx = 0;
        int dy = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            dx -= 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            dx += 1;
        }
        
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            dy += 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            dy -= 1;
        }

        float paso = getVelocidad() * delta;
        
        if (dx != 0 && dy != 0) {
            paso = paso * FACTOR_DIAGONAL;   
        }
        area.x += dx * paso;
        area.y += dy * paso;
        limitarHorizontal(area);
        if (area.y < 0) {
            area.y = 0;
        }
        
        if (area.y > ALTO_JUGABLE - area.height) {
            area.y = ALTO_JUGABLE - area.height;
        }
        
        
    }
}
