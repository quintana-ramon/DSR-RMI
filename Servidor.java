package E1;

import java.rmi.Naming;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Servidor para el ejemplo RMI
 */
public class Servidor {

    // Crea nueva instancia de Servidor RMI
    public Servidor() {
        try {
            // Se indica a rmiregistry donde están las clases.
            System.setProperty("java.rmi.server.codebase", "192.168.255.19");

            // Se realiza publicación del objeto remoto
            InterfaceRemota objetoRemoto = new ObjetoRemoto();
            Naming.rebind("//192.168.255.19/ObjetoRemoto", objetoRemoto);
            Logger.getLogger(Servidor.class.getName()).log(Level.INFO, "⚡ Servidor iniciado...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Main
    public static void main(String[] args) {
        new Servidor();
    }
}
