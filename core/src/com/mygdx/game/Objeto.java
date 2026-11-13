package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import com.badlogic.gdx.math.MathUtils;



public abstract class Objeto {
	public static final int ANCHO_PANTALLA = 800;
	public static final int ALTO_JUGABLE = 480;
	public static final int TAMANIO = 64;
	
	//x al azar dentro de la pantalla (para los objetos que caen desde arriba)
	protected static float xAlAzar() {
		return MathUtils.random(0, ANCHO_PANTALLA - TAMANIO);
	}
	 
	// altura justo sobre el borde superior de la pantalla
	protected static float arriba() {
		return ALTO_JUGABLE;
	}
	
	private Rectangle area;
	private Texture imagen;
	private float velocidad;
	private boolean activo = true;
	
	public Objeto(Texture imagen, float x, float y, float velocidad) {
		this.imagen = imagen;
		this.velocidad = velocidad;
		this.area = new Rectangle(x, y, TAMANIO, TAMANIO);
	}
	
	public final void actualizar(float delta, Personaje p) {
		if(!activo) {
			return;
		}
		mover(delta);
		if(area.overlaps(p.getArea())){
			chocar(p);
			activo = false;
		}
		else if(fueraDePantalla()) {
			activo = false;
		}
	}
	
	public abstract void mover(float delta);
	public abstract void chocar(Personaje p);
	
	public void caer(float delta) {
		area.y -= velocidad * delta;
	}
	
	// mueve el objeto dx pixeles en horizontal y dy en vertical
	protected void desplazar(float dx, float dy) {
		area.x += dx;
		area.y += dy;
	}
	
	
	public boolean fueraDePantalla() {
        return area.y + area.height < 0
                || area.y > ALTO_JUGABLE + area.height
                || area.x + area.width < -area.width
                || area.x > ANCHO_PANTALLA + area.width;
	}
	
	public void dibujar(SpriteBatch batch) {
		batch.draw(imagen, area.x, area.y, area.width, area.height);
	}
	
	public Rectangle getArea() { return area; }
	public Texture getImagen() { return imagen; }
	public float getVelocidad() { return velocidad; }
	public boolean estaActivo() { return activo; }
	
}
