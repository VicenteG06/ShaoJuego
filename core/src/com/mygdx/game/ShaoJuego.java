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
 
	// NUEVO: imagen de fondo
	private Texture imgFondo;
 
	// personajes imagenes
	private Texture imgChiikawa;
	private Texture imgHachiware;
	private Texture imgUsagi ;
 
	//orden de los personajes y en cual vamos
	private List<TipoPersonaje> secuencia;
	private int indiceActual= 0;
	private boolean gano= false;
	private boolean perdio = false;
 
	@Override
	public void create() {
		font=new BitmapFont(); // use libGDX's default Arial font
		font.getData().setScale(1.5f);//letra 1,5 veces más grande
		font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);   // NUEVO: bordes suaves
		medidorTexto = new GlyphLayout();
 
		//fondo e imágenes de los personajes
		imgFondo = new Texture(Gdx.files.internal("fondo.png"));
		imgChiikawa = new Texture(Gdx.files.internal("chiikawa.png"));
		imgHachiware = new Texture(Gdx.files.internal("hachiware.png"));
		imgUsagi = new Texture(Gdx.files.internal("usagi.png"));
 
		//orden en que aparecen los personajes
		secuencia= new ArrayList<TipoPersonaje>();
		secuencia.add(new Chiikawa(imgChiikawa));
		secuencia.add(new Hachiware(imgHachiware));
		secuencia.add(new Usagi(imgUsagi));
 
		Sound hurtSound = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
		personaje = new Personaje(secuencia.get(0), hurtSound);
 
		// load the drop sound effect and the rain background "music"
		Texture gota = new Texture(Gdx.files.internal("drop.png"));
		Texture gotaMala = new Texture(Gdx.files.internal("dropBad.png"));
 
		Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
 
		Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
		lluvia = new Lluvia(gota, gotaMala, dropSound, rainMusic);
 
		// camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();
 
		personaje.crear();
		lluvia.crear();
	}
 
 
//cuando el personaje actual se llena,se pasa al siguiente
//STRATEGY
	private void revisarCambioDePersonaje(){
		if (!personaje.estaLleno()){
			return;
		}
 
		boolean quedanPersonajes = indiceActual <secuencia.size()-1;
 
		if (quedanPersonajes){
			indiceActual++;
			personaje.setTipo(secuencia.get(indiceActual));
		}else {
			gano =true;
		}
	}
 
	private void reiniciarJuego() {
		indiceActual = 0;
		gano = false;
		perdio = false;
		personaje.reiniciar(secuencia.get(0));
		lluvia.reiniciar();
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
 
		batch.draw(imgFondo, 0, 0, 800, 480);   
		
		
		font.draw(batch, personaje.getTipo().getNombre() + " - Hambre: "
				+ personaje.getHambre() + "/" + personaje.getMetaHambre(), 8, 472);
		font.draw(batch, "Vidas : " + personaje.getVidas(), 680, 472);
 
		//si ya ganó o perdió, el juego se detiene
		if (!personaje.estaHerido() && !gano && !perdio) {
			personaje.actualizarMovimiento();
			lluvia.actualizarMovimiento(personaje);
			revisarCambioDePersonaje();
			if(personaje.estaMuerto()) {
				perdio = true;
			}
		}
 
		personaje.dibujar(batch);
		lluvia.actualizarDibujoLluvia(batch);
 

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
		personaje.destruir();
		lluvia.destruir();
		imgFondo.dispose();
		imgChiikawa.dispose();
		imgHachiware.dispose();
		imgUsagi.dispose();
		batch.dispose();
		font.dispose();
	}
}
