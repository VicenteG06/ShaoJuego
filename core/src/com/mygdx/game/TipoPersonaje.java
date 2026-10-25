package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public abstract class TipoPersonaje {
	
	public static final int ANCHO_PANTALLA = 800;
    public static final int ALTO_JUGABLE = 450;
    private String nombre;
    private Texture imagen;
    private int metaHambre;
    private float velocidad;

    protected TipoPersonaje(String nombre, Texture imagen, int metaHambre, float velocidad) {
    	
    	
        this.nombre = nombre;
        
        this.imagen = imagen;
        this.metaHambre = metaHambre;
        
        this.velocidad = velocidad;
        
        
    }

    // movimiento solo izquierda y derecha
    public void mover(Rectangle area, float delta) {
        float paso = velocidad * delta;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            area.x -= paso;
        }
        
        
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            area.x += paso;
        }
        limitarHorizontal(area);
    }

    protected void limitarHorizontal(Rectangle area) {
        if(area.x < 0){
            area.x = 0;
        }
        
        if(area.x> ANCHO_PANTALLA - area.width){
            area.x = ANCHO_PANTALLA - area.width;
        }
    }

    
    public String getNombre() { return nombre; }
    public Texture getImagen() { return imagen; }
    public int getMetaHambre() { return metaHambre; }
    public float getVelocidad() { return velocidad; }
}
