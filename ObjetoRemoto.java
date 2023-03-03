package E1;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * UnicastRemoteObject se utiliza para la exportación de un objeto remoto con el
 * Protocolo de método remoto Java (JRMP) y la obtención de un stub que se
 * comunica con el objeto remoto.
 *
 * La comunicación solo puede darse a traves del contrato establecido por medio
 * de una interfaz, por tal razon la clase implementa a InterfaceRemota
 */
public class ObjetoRemoto extends UnicastRemoteObject implements InterfaceRemota {

    private static final long serialVersionUID = 1L;

    // Construye una instancia de ObjetoRemoto
    public ObjetoRemoto() throws RemoteException {
        super();
    }

    // Implementacion del método remoto

    // Calcula la cuota mensual de un prestamo dado capital, interes anual y plazo
    // anual
    public double cuotaMensual(double capital, double interes, double plazo) {
        double resultadoCuotaMensual = 0;
        try {
            Logger.getLogger(ObjetoRemoto.class.getName()).log(Level.INFO, "✅ calculando cuota...");
            double plazoMes = plazo / 12.00;
            double interesMes = interes / 12.00;
            resultadoCuotaMensual = (capital * interes) / (100.00 * (1 - (Math.pow(interesMes / 100.00, plazoMes))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultadoCuotaMensual;
    }
}
