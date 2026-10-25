package com.mygdx.game;
 
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
 
//CONTEXTO DE STRATEGY
//tipopersonaje cambia entre chiikawa, hachiware y usagi (setTipo)


public class Personaje {
	private TipoPersonaje tipo ;//STRATEGY
	private Rectangle area;      
	private Sound sonidoHerido;
	private int vidas = 3;
	private int satisfecho = 0;       
	private boolean herido= false;
	private int tiempoHeridoMax= 50;
	private int tiempoHerido;
 
	public Personaje(TipoPersonaje tipoInicial,Sound sonidoHerido){
		this.tipo= tipoInicial ;
		this.sonidoHerido= sonidoHerido;
	}
 
	public void crear(){
		area = new Rectangle();
		
		area.x = 800 / 2 - 64 / 2;
		area.y = 20;
		
		area.width = 64;
		area.height = 64;
	}
 


	public void setTipo(TipoPersonaje nuevoTipo) { //-----------STRATEGY
		this.tipo = nuevoTipo;
		this.satisfecho = 0;      // la barra se reinicia en cada personaje
		area.y = 20;          // vuelve al suelo por si usagi estaba arriba
	}
 
	public TipoPersonaje getTipo(){ return tipo;}
 

 
	public void comer(){//lo que pasa al chocar con las gotas ----------
		if (satisfecho <tipo.getMetaHambre()){
			satisfecho++ ;
		}
	}
 
	public void daniar(int dano){
		if(vidas > 0) {vidas-= dano;}
		herido = true;
		tiempoHerido =tiempoHeridoMax;
		sonidoHerido.play();
		
	}
	
	public void ganarVida() {
		if(vidas < 3) {
			vidas++;
		}
	}
	
	public void reiniciar(TipoPersonaje tipoInicial) {
		this.tipo = tipoInicial;
		vidas = 3;
		satisfecho = 0;
		herido = false;
		tiempoHerido = 0;
		area.x = 800 / 2 - 64 / 2;
		area.y = 20;
	}
	
	
	public void actualizarMovimiento() {
		tipo.mover(area, Gdx.graphics.getDeltaTime()); //cada personaje se mueve segun el metodo definidio en cada una de las clases
	}
 
	public void dibujar(SpriteBatch batch){
		
		if (!herido){ //la imagen la da el tipo actual
			batch.draw(tipo.getImagen(),area.x, area.y);
			
		} else {
			batch.draw(tipo.getImagen(),area.x, area.y+ MathUtils.random(-5, 5));
			tiempoHerido--;
			
			if (tiempoHerido <=+0) {
				herido = false;
			}
			
			
		}
	}
 
	public void destruir(){ sonidoHerido.dispose(); }
 
	public boolean estaMuerto() { return vidas <= 0; }
 
	public Rectangle getArea(){ return area; }
 
	public int getVidas(){ return vidas; }
 
	public int getHambre(){ return satisfecho; }
	public int getMetaHambre() {return tipo.getMetaHambre(); }
 
	public boolean estaLleno() { return satisfecho>= tipo.getMetaHambre();}
 
	public boolean estaHerido() {return herido; }
}
 