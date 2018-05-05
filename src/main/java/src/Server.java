package src;

import org.json.simple.JSONObject;

import src.impl.ServerSocketImpl;

import src.utils.GenericSocket.GenericSocketException;

public class Server implements Runnable {

  @Override
	public void run() {
        // Ejecución del servidor
        ServerSocketImpl serverSocket = null;
        Main.log.debug("Inicio de servidor");
        try {
            serverSocket = new ServerSocketImpl(4430);

            while(true) {
                serverSocket.awaitConnection();
                JSONObject message = serverSocket.readLineFromSocket();
                // Poner aqui restricciones necesarias
                // ...
                // y guardado en BD
                // ...
                // Si todo sale bien, enviar esto:
                JSONObject result = new JSONObject();
                result.put("status", "Success");
                System.out.println(result.toJSONString());
                serverSocket.writeLineToSocket(result.toJSONString());
                serverSocket.closeClientSocket();
            }
        } catch(GenericSocketException gse) {
            gse.printStackTrace();
        } finally {
            try {
                if(serverSocket != null) {
                    serverSocket.closeSocket();
                }
            } catch(GenericSocketException gse) {
                // Algo muy malo debe estar pasando, emitimos en pantalla el stack
                Main.log.warn(gse.getMessage());
            }
        }
	}

}
