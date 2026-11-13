package com.mygdx.game;
 
import java.util.ArrayList;
import java.util.List;
 
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
 
import com.badlogic.gdx.graphics.OrthographicCamera;
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

	// anuncio temporal en pantalla
	private static final float DURACION_ANUNCIO = 2.5f;
	private String anuncio = null;
	private float tiempoAnuncio = 0;
	
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

	//CLIENTE del builder
	//elige los builders y le pide al director que construya cada nivel
	private void crearSecuencias() {
		secuencias = new ArrayList<Secuencia>();

		List<SecuenciaBuilder> builders = new ArrayList<SecuenciaBuilder>();
		builders.add(new NivelChiikawaBuilder());
		builders.add(new NivelHachiwareBuilder());
		builders.add(new NivelUsagiBuilder());

		for (SecuenciaBuilder builder : builders) {
			DirectorNiveles director = new DirectorNiveles(builder);
			secuencias.add(director.construirNivel());
		}
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
			mostrarAnuncio(siguiente.getPersonaje().getMensajeInicio()) ;
		}else {
			gano = true;
			lluvia.reiniciar();
		}
	}
 
	//muestra un texto en pantalla por unos segundos (si el personaje no tiene anuncio, no hace nada)
	private void mostrarAnuncio(String texto) {
		if (texto == null) {
			return;
		}
		anuncio = texto;
		tiempoAnuncio = DURACION_ANUNCIO;
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
		
		if (gano) {
			
			batch.draw(GestorRecursos.getInstancia().getTextura(GestorRecursos.IMG_VICTORIA), 0, 0, Objeto.ANCHO_PANTALLA, Objeto.ALTO_JUGABLE);
			dibujarCentrado("GANASTE!", 80);
			dibujarCentrado("Presiona R para jugar de nuevo", 45);
		} else {
			batch.draw(GestorRecursos.getInstancia().getTextura(GestorRecursos.IMG_FONDO), 0, 0, Objeto.ANCHO_PANTALLA, Objeto.ALTO_JUGABLE);

			personaje.dibujar(batch);
			lluvia.dibujar(batch);

			font.draw(batch, personaje.getTipo().getNombre() + " - Hambre: "
					+ personaje.getHambre() + "/" + personaje.getMetaHambre(), 8, 472);
			font.draw(batch, "Vidas : " + personaje.getVidas(), 680, 472);

			float delta = Gdx.graphics.getDeltaTime();
			//si perdió, el juego se detiene
			if (!personaje.estaHerido() && !perdio) {
				personaje.actualizarMovimiento();
				lluvia.actualizar(delta, personaje);
				revisarCambioDePersonaje();
				if (personaje.estaMuerto()) {
					perdio = true;
					lluvia.reiniciar();
				}
			}			
			// anuncio temporal: se dibuja mientras quede tiempo
			if (tiempoAnuncio > 0) {
				tiempoAnuncio -= delta;
				dibujarCentrado(anuncio, 300);
			}
			
			if (perdio) {
				dibujarCentrado("PERDISTE", 260);
				dibujarCentrado("Presiona R para reintentar", 225);
			}
		}

		if ((gano || perdio) && Gdx.input.isKeyJustPressed(Input.Keys.R)) {
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
