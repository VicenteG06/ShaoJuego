package com.mygdx.game;
 
import java.util.ArrayList;
import java.util.List;
 
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
 
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
 
 
public class ShaoJuego extends ApplicationAdapter {
	private OrthographicCamera camera;
	
	private SpriteBatch batch;
	private BitmapFont font;
 
	private Personaje personaje ;
//  private Tarro tarro;
	
	private Lluvia lluvia ;
 
	
	
	// personajes imagenes
	private Texture imgChiikawa;
	private Texture imgHachiware;
	private Texture imgUsagi ;
 
	//orden de los personajes y en cual vamos
	private List<TipoPersonaje> secuencia;
	private int indiceActual= 0;
	private boolean gano= false;
 
	@Override
	public void create() {
		font=new BitmapFont(); // use libGDX's default Arial font
 
		// .........-----------------------------------------------------------------------------------------------
		// cuando este chiikawa.png, hachiware.png y usagi.png cambiar nombres aqui
		imgChiikawa =new Texture(Gdx.files.internal("cubi.png"));
		imgHachiware =new Texture(Gdx.files.internal("bucket.png"));
		imgUsagi = new Texture(Gdx.files.internal("cubi.png"));
 
		
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
 
	@Override
	public void render(){
		ScreenUtils.clear(0, 0, 0.2f, 1);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
 
		font.draw(batch, personaje.getTipo().getNombre() + " - Hambre: "
				+ personaje.getHambre() + "/" + personaje.getMetaHambre(), 5, 475);
		font.draw(batch, "Vidas : " + personaje.getVidas(), 720, 475);
 
		//si ya ganó, el juego se detiene
		if (!personaje.estaHerido() && !gano) {
			personaje.actualizarMovimiento();
			lluvia.actualizarMovimiento(personaje);
			revisarCambioDePersonaje();
		}
 
		personaje.dibujar(batch);
		lluvia.actualizarDibujoLluvia(batch);
 
		if (gano){
			font.draw(batch, "GANASTE", 360, 260);
		}
 
		batch.end();
	}
 
	@Override
	public void dispose() {
		personaje.destruir();
		lluvia.destruir();
		imgChiikawa.dispose(); //---------------------------------------------------------------------------------
		imgHachiware.dispose();
		imgUsagi.dispose();      
		batch.dispose();
		font.dispose();
	}
}