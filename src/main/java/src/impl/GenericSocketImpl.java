package src.impl;

import java.io.FileInputStream;
import java.io.InputStream;

import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;

import src.Main;

import src.utils.GenericSocket;

public abstract class GenericSocketImpl implements GenericSocket {
    public GenericSocketImpl() {
        super();
    }

    public SSLContext setupSecureContext() throws GenericSocketException {
        SSLContext sc = null;

        try(
            InputStream is = new FileInputStream("./maat-certs.jks");
        ) {
            /* Inicialización del almacén de claves y protocolos */
            KeyStore ks = null;
            KeyManagerFactory kmf = null;
            
            ks = KeyStore.getInstance("JKS");
            Main.log.trace("SC - Lectura de keyStore completa");
            
            ks.load(is, "00110011".toCharArray());
            Main.log.trace("SC - KeyStore abierto y leido");

            kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            Main.log.trace("SC - KeyManagerFactory creado");
            kmf.init(ks, "ssii-pai3".toCharArray());
            Main.log.trace("SC - Accedido al conjunto de claves");
            /* Definición del contexto del SSL */

            sc = SSLContext.getInstance("SSLv3");
            Main.log.trace("SC - Iniciado el contexto de SSL");
            sc.init(kmf.getKeyManagers(), null, null);
            Main.log.trace("SC - Inicializado el sistema de SSL");
        } catch(Exception e) {
            Main.log.warn(e.getMessage());
            throw new GenericSocketException(e.getMessage());
        }

        return sc;
    }
}
