package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;


public class Hachiware extends TipoPersonaje {
	public Hachiware() {
        super("Hachiware", GestorRecursos.getInstancia().getTextura(GestorRecursos.IMG_HACHIWARE), 25, 400);
    }
}
