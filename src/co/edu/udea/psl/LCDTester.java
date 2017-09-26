package co.edu.udea.psl;

import co.edu.udea.psl.exception.SuperException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class. Execute operations from ImpresorLCD
 * 
 * @author Juan Pablo Ospina Herrera - jpoh97@gmail.com
 * @since 1.8
 * @version 1.0
 * 
 */

public class LCDTester {
    
    // Input and output variables per console
    private static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static final PrintWriter out = new PrintWriter(System.out);

    // String indicating the end of the program
    private static final String CADENA_FINAL = "0,0";

    /**
     * Main method. Read input and call operations from ImpresorLCD
     * 
     * @param jpoh97 Arguments per console
     * @throws SuperException I handle my own exceptions
     */
    public static void main(String... jpoh97) throws SuperException {

        int spaceBetweenDigits = 0;
        List<String> listCommands = new ArrayList<>();
        String input;
        ImpresorLCD impresorLCD;

        try {
            
            impresorLCD = new ImpresorLCD();
            
            out.print("Espacio entre Digitos (0 a 5): ");
            out.flush();
            input = in.readLine();

            // Check numeric input
            if (impresorLCD.isNumeric(input)) {

                spaceBetweenDigits = Integer.parseInt(input);
                // Check space between 0 and 5
                if (spaceBetweenDigits < 0 || spaceBetweenDigits > 5) {
                    throw new SuperException("El espacio entre "
                            + "digitos debe estar entre 0 y 5");
                }

            } else {
                throw new SuperException("Cadena " + input
                        + " no es un entero");
            }

            out.print("Entrada: ");
            out.flush();
            
            // Validate each input and save in listCommands
            while (!(input = in.readLine()).equalsIgnoreCase(CADENA_FINAL)
                    && !input.trim().equals("")) {
                listCommands.add(input);           
                out.print("Entrada: ");
                out.flush();                
            }
            
            // For each command in listCommands, call operations
            for(String myCommand : listCommands) {
                try {
                    impresorLCD.process(myCommand, spaceBetweenDigits);
                } catch(SuperException e) {
                    throw new SuperException("Error", e);
                }
            }           

            // Close BufferedReader object
            in.close();
            
        } catch (IOException e) {
            throw new SuperException("Error en la entrada", e);
        } finally {
            // Close PrinWriter object
            out.close();
        }
    }
    
}
