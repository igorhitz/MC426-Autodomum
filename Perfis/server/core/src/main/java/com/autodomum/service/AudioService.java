package com.autodomum.service;

import java.util.List;
import java.util.Random;

import com.autodomum.aplicacao.queue.AutodomumQueue;
import com.autodomum.comandos.ComandoAudio;
import com.autodomum.dao.PreferenciasDao;
import com.autodomum.modelo.Preferencias;

public class AudioService {
	
	private final PreferenciasDao preferenciasDao;
	private final AutodomumQueue queue;
	private final Random random;

	public AudioService(PreferenciasDao preferenciasDao, AutodomumQueue queue) {
		this.preferenciasDao = preferenciasDao;
		this.queue = queue;
		random = new Random();
	}
	
	public void enviarComando(ComandoAudio comando) {
	    queue.send(comando);
	}
	
	public void cadastroArtista(Preferencias preferencia){
		preferenciasDao.criar(preferencia);
	}
	
	public String buscaArtista(String username) {
        List<String> list = preferenciasDao.BuscaDeArtistas(username);
        if (list.size() != 0) {
        	return list.get(random.nextInt(list.size()));
        } else {
        	return "0";
        }
       
    }

}
