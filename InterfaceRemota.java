package E1;

import java.rmi.Remote;
import java.rmi.RemoteException;

/*
 * Interface que define contrato con métodos para llamada remota
 */
public interface InterfaceRemota extends Remote {

    // Este método define el contrato que debe seguir el objeto remoto para el paso
    // de parametros al servidor
    public double cuotaMensual(double capital, double interes, double plazo) throws RemoteException;
}