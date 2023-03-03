package E1;

import java.rmi.Naming;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Implementación del cliente para RMI
 */
public class Cliente {

    private double valorCapitalUsuario;
    private double valorInteresUsuario;
    private double valorPlazoUsuario;
    private Input input = new Input();
    private boolean flag = true;
    private Logger logger = Logger.getLogger(Cliente.class.getName());

    // Crea nueva instancia de Cliente
    public Cliente() {
        try {
            /*
             * Accedemos a objeto remoto: La clase Naming provee de los métodos para
             * almacenar y obtener referencias a los OR que estan en el registro remoto de
             * objetos.
             *
             * El método lookup retorna una referencia al stub del OR.
             *
             * Con lo anterior ya tenemos creado un "puente" para enviar datos al método
             * remoto y hacer el calculo.
             */
            InterfaceRemota objetoRemoto = (InterfaceRemota) Naming.lookup("//192.168.255.19/ObjetoRemoto");

            while (flag) {
                // Capturamos los datos del usuario
                capturarDatosUsuario();
                validateData(valorCapitalUsuario, valorInteresUsuario, valorPlazoUsuario);
                DecimalFormat df = new DecimalFormat("#.##");
                String stringCapitalUsuario = df.format(valorCapitalUsuario);
                String stringInteresUsuario = df.format(valorInteresUsuario);

                String resultado = df.format(objetoRemoto.cuotaMensual(valorCapitalUsuario, valorInteresUsuario,
                        valorPlazoUsuario));

                logger.log(Level.INFO, "Resultado Usuario:\nCuota Mensual: {0} pesos\n\n", resultado);

                // Pruebas
                logger.info("Resultado prueba:\n");

                // Calculo 1
                resultado = df.format(objetoRemoto.cuotaMensual(valorCapitalUsuario, valorInteresUsuario, 3.00));
                logger.log(Level.INFO, "Plazo: 3 años\nCapital: {0} pesos\nInteres: {1}%\nCuota Mensual: {2} pesos\n",
                        new String[] { stringCapitalUsuario, stringInteresUsuario, resultado });

                // Calculo 2
                resultado = df.format(objetoRemoto.cuotaMensual(valorCapitalUsuario, valorInteresUsuario, 5.00));
                logger.log(Level.INFO, "Plazo: 5 años\nCapital: {0} pesos\nInteres: {1}%\nCuota Mensual: {2} pesos\n",
                        new String[] { stringCapitalUsuario, stringInteresUsuario, resultado });

                // Calculo 3
                resultado = df.format(objetoRemoto.cuotaMensual(valorCapitalUsuario, valorInteresUsuario, 7.00));
                logger.log(Level.INFO, "Plazo: 7 años\nCapital: {0} pesos\nInteres: {1}%\nCuota Mensual: {2} pesos\n",
                        new String[] { stringCapitalUsuario, stringInteresUsuario, resultado });

                // Damos opción al usuario para cerrar aplicación
                String continueValue = input.isStr("Desea continuar? [y / n]: ");
                if (continueValue.equals("n"))
                    flag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void capturarDatosUsuario() {
        valorCapitalUsuario = input.isDob("Ingresa la Capital: ");
        valorInteresUsuario = input.isDob("Ingresa el Interes: ");
        valorPlazoUsuario = input.isDob("Ingresa el Plazo: ");
    }

    private void validateData(double capital, double interes, double plazo) {
        if (capital < 10000)
            throw new IllegalArgumentException("❌ VALOR de CAPITAL ingresado INCORRECTO.");
        if (interes < 6 || interes > 9)
            throw new IllegalArgumentException("❌ INTERES DEBE SER ENTRE EL 6% Y EL 9%");
        if (plazo != 3 && plazo != 5 && plazo != 7)
            throw new IllegalArgumentException("❌ SOLO PLAZOS DE 3, 5 Y 7 AÑOS");
    }

    // Main
    public static void main(String[] args) throws InterruptedException {
        new Cliente();
    }
}
