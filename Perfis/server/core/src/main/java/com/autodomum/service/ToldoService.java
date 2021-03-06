package com.autodomum.service;

import com.autodomum.aplicacao.notificacao.Notificacao;
import com.autodomum.aplicacao.queue.AutodomumQueue;
import com.autodomum.comandos.ComandoToldo;
import com.autodomum.dao.ToldoDao;
import com.autodomum.modelo.HistoricoToldo;
import com.autodomum.modelo.Toldo;

import java.util.List;

/**
 * @author sabrina on 27/05/16.
 */
public class ToldoService {

    private final ToldoDao toldoDao;
    private final AutodomumQueue queue;

    public ToldoService(ToldoDao toldoDao, AutodomumQueue queue) {
        this.toldoDao = toldoDao;
        this.queue = queue;
    }

    public List<HistoricoToldo> historicoToldo(Toldo toldo) {
        return this.toldoDao.historico(toldo);
    }

    public Integer criarHistorico(HistoricoToldo historicoToldo) {
    	if (historicoToldo.isEstendido()) {
    		Notificacao.sendNotificaco("O toldo foi estendido!");
    	} else {
    		Notificacao.sendNotificaco("O toldo foi recolhido!");
    	}
    	
        return toldoDao.criarHistorico(historicoToldo);
    }

    public void enviarComando(ComandoToldo comando) {
    	if (comando.getEstendido()) {
    		Notificacao.sendNotificaco("O toldo foi estendido!");
    	} else {
    		Notificacao.sendNotificaco("O toldo foi recolhido!");
    	}
        queue.send(comando);
    }
}
