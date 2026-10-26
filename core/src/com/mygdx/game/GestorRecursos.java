package com.mygdx.game;
 
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;


public class GestorRecursos {
	public static final String IMG_CHIIKAWA = "chiikawa.png";
	public static final String IMG_HACHIWARE = "hachiware.png";
	public static final String IMG_USAGI = "usagi.png";
    public static final String IMG_COMIDA = "empanada.png";
    public static final String IMG_BASURA = "basura.png";
    public static final String IMG_GOBLIN = "goblin1.png"; // CAMBIAR DESPUES
    public static final String IMG_ESTRELLA_BUENA = "estrella_buena.png"; //CAMBIAR DESPUES
    public static final String IMG_ESTRELLA_MALA  = "estrella_mala.png"; //CAMBIAR DESPUES
    
    public static final String IMG_FONDO = "fondo.png";
	
    public static final String SONIDO_HERIDO = "hurt.ogg";
    public static final String MUSICA_FONDO = "rain.mp3";
    
    
    private static GestorRecursos instancia; // La unica instancia
    
    private HashMap<String, Texture> texturas;
    private HashMap<String, Sound> sonidos;
    private HashMap<String, Music> musicas;
    
    // Constructor privado para que solo haya UNA instancia de esta clase
    private GestorRecursos() {
    	texturas = new HashMap<String, Texture>();
    	sonidos = new HashMap<String, Sound>();
    	musicas = new HashMap<String, Music>();
    }
    public static GestorRecursos getInstancia() {
    	if(instancia == null) {
    		instancia = new GestorRecursos();
    	}
    	return instancia;
    }
    
    public Texture getTextura(String archivo) {
    	Texture textura = texturas.get(archivo);
    	// Si es la primera vez que se intenta acceder al archivo, se guarda en el mapa
    	if (textura == null) {
            textura = new Texture(Gdx.files.internal(archivo));
            texturas.put(archivo, textura);
        }
    	return textura;
    }
    public Sound getSonido(String archivo) {
    	Sound sonido = sonidos.get(archivo);
    	// Si es la primera vez que se intenta acceder al archivo, se guarda en el mapa
        if (sonido == null) {
            sonido = Gdx.audio.newSound(Gdx.files.internal(archivo));
            sonidos.put(archivo, sonido);
        }
    	return sonido;
    }
    public Music getMusica(String archivo) {
    	Music musica = musicas.get(archivo);
    	// Si es la primera vez que se intenta acceder al archivo, se guarda en el mapa
        if (musica == null) {
            musica = Gdx.audio.newMusic(Gdx.files.internal(archivo));
            musicas.put(archivo, musica);
        }
    	return musica;
    }
    
    public void liberar() {
        for (Texture t : texturas.values()) t.dispose();
        for (Sound s : sonidos.values()) s.dispose();
        for (Music m : musicas.values()) m.dispose();
        texturas.clear();
        sonidos.clear();
        musicas.clear();
        instancia = null;
    }
}
