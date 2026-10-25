package com.mygdx.game;
 
import java.util.ArrayList;
import java.util.List;
 
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
 
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Input;

 
 
public class ShaoJuego extends ApplicationAdapter {
	private OrthographicCamera camera;
 
	private SpriteBatch batch;
	private BitmapFont font;
	private GlyphLayout medidorTexto;   // NUEVO: mide el ancho de un texto para centrarlo
 
	private Personaje personaje ;
 
	private Lluvia lluvia ;
 
	private List<Secuencia> secuencias; // Para pasar entre chiikawa, hachiware y usagi
	private int indiceActual = 0;
	private boolean gano = false;
	private boolean perdio = false;
 
	@Override
	public void create() {
		font = new BitmapFont();   // fuente Arial por defecto de libGDX
		medidorTexto = new GlyphLayout();
	 
		crearSecuencias();
		Secuencia primera = secuencias.get(0);
	 
		GestorRecursos recurso = GestorRecursos.getInstancia();
		personaje = new Personaje(primera.getPersonaje(), recurso.getSonido(GestorRecursos.SONIDO_HERIDO));
		personaje.crear();
	 
		lluvia = new Lluvia(primera);
		lluvia.crear();
	 
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Objeto.ANCHO_PANTALLA, Objeto.ALTO_JUGABLE);
		batch = new SpriteBatch();
	   }

	// Se crean las secuencias del juego usando el builder
	private void crearSecuencias() {
		secuencias = new ArrayList<Secuencia>();
	 
		// Se usa a chiikawa y solo se genera comida y basura
		secuencias.add(new Builder().personaje(new Chiikawa()).probComida(3).probBasura(1).intervalo(0.8f).velocidadCaida(250).build());
	 
		// Se usa a hachiware y caen goblins y comida
		secuencias.add(new Builder().personaje(new Hachiware()).probComida(3).probGoblin(1).intervalo(0.7f).velocidadCaida(270).build());
	 
		// Se usa a usagi y caen estrellas buenas y malas
		secuencias.add(new Builder().personaje(new Usagi()).probComida(3).probEstrellaBuena(0.5f).probEstrellaMala(1.5f).intervalo(0.5f).velocidadCaida(300).build());
	   }
	 
//cuando el personaje actual se llena,se pasa al siguiente
//STRATEGY
	private void revisarCambioDePersonaje(){
		if (!personaje.estaLleno()){
			return;
		}
 
		boolean quedanPersonajes = indiceActual < secuencias.size()-1;
 
		if (quedanPersonajes){
			indiceActual++;
			Secuencia siguiente = secuencias.get(indiceActual);
			personaje.setTipo(siguiente.getPersonaje());
			lluvia.setSecuencia(siguiente);
		}else {
			gano = true;
			lluvia.reiniciar();
		}
	}
 
	private void reiniciarJuego() {
		indiceActual = 0;
		gano = false;
		perdio = false;
		personaje.reiniciar(secuencias.get(0).getPersonaje());
		lluvia.setSecuencia(secuencias.get(0));
	}
 
	
	private void dibujarCentrado(String texto, float y) {
		medidorTexto.setText(font, texto);
		float x = (800 - medidorTexto.width) / 2;
		font.draw(batch, texto, x, y);
	}
 
	@Override
	public void render(){
		ScreenUtils.clear(0, 0, 0.2f, 1);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
 
		batch.draw(GestorRecursos.getInstancia().getTextura(GestorRecursos.IMG_FONDO), 0, 0, Objeto.ANCHO_PANTALLA, Objeto.ALTO_JUGABLE); 
		
		personaje.dibujar(batch);
		lluvia.dibujar(batch);
 
		
		font.draw(batch, personaje.getTipo().getNombre() + " - Hambre: "
				+ personaje.getHambre() + "/" + personaje.getMetaHambre(), 8, 472);
		font.draw(batch, "Vidas : " + personaje.getVidas(), 680, 472);
 
		float delta = Gdx.graphics.getDeltaTime();
		//si ya ganó o perdió, el juego se detiene
		if (!personaje.estaHerido() && !gano && !perdio) {
			personaje.actualizarMovimiento();
			lluvia.actualizar(delta, personaje);
			revisarCambioDePersonaje();
			if(personaje.estaMuerto()) {
				perdio = true;
				lluvia.reiniciar();
			}
		}

		if (gano){
			dibujarCentrado("GANASTE", 260);
			dibujarCentrado("Presiona R para jugar de nuevo", 225);
		}
		if(perdio) {
			dibujarCentrado("PERDISTE", 260);
			dibujarCentrado("Presiona R para reintentar", 225);
		}
		if((gano || perdio) && Gdx.input.isKeyJustPressed(Input.Keys.R)){
			reiniciarJuego();
		}
 
		batch.end();
	}
 
	@Override
    public void dispose() {
        lluvia.destruir();
        batch.dispose();
        font.dispose();
        GestorRecursos.getInstancia().liberar();
    }
}
