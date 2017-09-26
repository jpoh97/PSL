package co.edu.udea.psl;

import co.edu.udea.psl.exception.SuperException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class with operations
 * 
 * @author Juan Pablo Ospina Herrera - jpoh97@gmail.com
 * @since 1.8
 * @version 1.0
 * 
 */

public class ImpresorLCD {

    // Declare variables
    private final int[] fixedPoint1;
    private final int[] fixedPoint2;
    private final int[] fixedPoint3;
    private final int[] fixedPoint4;
    private final int[] fixedPoint5;
    private String[][] matrixPrint;         // Main matrix with LCD numbers

    private final String VERTICAL_CHARACTER = "|";
    private final String HORIZONTAL_CHARACTER = "-";
    private final String POSITION_X = "X";
    private final String POSITION_Y = "Y";

    private int size;

    private int columnDigit;
    private int totalRows;
    private int totalColumns;

    // Output object
    private final PrintWriter out = new PrintWriter(System.out);

    // Builder
    public ImpresorLCD() {
        this.fixedPoint1 = new int[2];
        this.fixedPoint2 = new int[2];
        this.fixedPoint3 = new int[2];
        this.fixedPoint4 = new int[2];
        this.fixedPoint5 = new int[2];
    }
   
    /**
     * Method to add a line to the matrixPrint
     * 
     * @param point the fixed point
     * @param fixedPos the fixed position
     * @param character 
     */
    private void addLine(int[] point, String fixedPos, String character) {
        int value;
        if (fixedPos.equalsIgnoreCase(POSITION_X)) {
            for (int y = 1; y <= size; y++) {
                value = point[1] + y;
                matrixPrint[point[0]][value] = character;
            }
        } else {
            for (int i = 1; i <= size; i++) {
                value = point[0] + i;
                matrixPrint[value][point[1]] = character;
            }
        }
    }

    /**
     * Method in charge of a segment to the matrixPrint
     * 
     * @param segment 
     */
    private void addSegment(int segment) {

        switch (segment) {
            case 1:
                // Vertical, rigth, up
                addLine(fixedPoint1, POSITION_Y, VERTICAL_CHARACTER);
                break;
            case 2:
                // Vertical, rigth, down
                addLine(fixedPoint2, POSITION_Y, VERTICAL_CHARACTER);
                break;
            case 3:
                // Vertical, left, up
                addLine(fixedPoint5, POSITION_Y, VERTICAL_CHARACTER);
                break;
            case 4:
                // Vertical, left, down
                addLine(fixedPoint4, POSITION_Y, VERTICAL_CHARACTER);
                break;
            case 5:
                // Horizontal, up
                addLine(fixedPoint1, POSITION_X, HORIZONTAL_CHARACTER);
                break;
            case 6:
                // Horizontal, medium
                addLine(fixedPoint2, POSITION_X, HORIZONTAL_CHARACTER);
                break;
            case 7:
                // Horizontal, down
                addLine(fixedPoint3, POSITION_X, HORIZONTAL_CHARACTER);
                break;
            default:
                break;
        }
    }

    /**
     * Method in charge of defining the segments that make up a digit and 
     * from the segments add the representation of the digit to the matrix
     * 
     * @param num Number to represent 
     */
    private void addDigit(int num) {

        List<Integer> segList = new ArrayList<>();

            // For each number, I specify their segments
        switch (num) {
            case 1:
                segList.addAll(Arrays.asList(3, 4));
                break;
            case 2:
                segList.addAll(Arrays.asList(5, 3, 6, 2, 7));
                break;
            case 3:
                segList.addAll(Arrays.asList(5, 3, 6, 4, 7));
                break;
            case 4:
                segList.addAll(Arrays.asList(1, 6, 3, 4));
                break;
            case 5:
                segList.addAll(Arrays.asList(5, 1, 6, 4, 7));
                break;
            case 6:
                segList.addAll(Arrays.asList(5, 1, 6, 2, 7, 4));
                break;
            case 7:
                segList.addAll(Arrays.asList(5, 3, 4));
                break;
            case 8:
                segList.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
                break;
            case 9:
                segList.addAll(Arrays.asList(1, 3, 4, 5, 6, 7));
                break;
            case 0:
                segList.addAll(Arrays.asList(1, 2, 3, 4, 5, 7));
                break;
            default:
                break;
        }

        // For each segment, call addSegment to print it
        segList.forEach((segment) -> {
            addSegment(segment);
        });
        
    }

    /**
     * Method for printing a number
     * 
     * @param numPrint Input number to print
     * @param space space between digits
     * @throws SuperException Own exceptions
     */
    private void printNum(String numPrint, int space) throws SuperException {
        int pivotX = 0; // Pivot position X
        char[] digits;

        // Calculate the number of rows each digit
        totalRows = (2 * size) + 3;

        // Calculate the column number of each digit
        columnDigit = size + 2;

        // Calculates the total columns of the array in which the digits will be stored
        totalColumns = (columnDigit * numPrint.length())
                + (space * numPrint.length());

        // Create matrix to store the number to be printed
        this.matrixPrint = new String[totalRows][totalColumns];

        // Create the array of digits
        digits = numPrint.toCharArray();

        // Initialize empty Matrix
        for (int i = 0; i < totalRows; i++) {
            Arrays.fill(matrixPrint[i], " ");
        }
        
        for (char digit : digits) {
            
            // Validate number
            if (!Character.isDigit(digit)) {
                throw new SuperException("Caracter " + digit
                        + " no es un digito");
            }
            
            // Calculate fixed points
            fixedPoint1[0] = 0;
            fixedPoint1[1] = 0 + pivotX;

            fixedPoint2[0] = (totalRows / 2);
            fixedPoint2[1] = 0 + pivotX;

            fixedPoint3[0] = (totalRows - 1);
            fixedPoint3[1] = 0 + pivotX;

            fixedPoint4[0] = (columnDigit - 1);
            fixedPoint4[1] = (totalRows / 2) + pivotX;

            fixedPoint5[0] = 0;
            fixedPoint5[1] = (columnDigit - 1) + pivotX;

            pivotX = pivotX + columnDigit + space;

            // Add each digit
            addDigit((int) (digit - '0'));
        }

        // Print full matrix with segments
        for (int i = 0; i < totalRows; i++) {
            for (int j = 0; j < totalColumns; j++) {
                out.print(matrixPrint[i][j]);
            }
            out.println();
            out.flush();
        }
    }

    /**
     * Method for processing the entry containing the size of the segment of 
     * the digits and the digits to be printed
     * 
     * @param command Input command from the user. Contain the size of the 
     * segment of the digit and the number to be printed
     * @param spaceBetweenDigits Space between digits
     * @throws SuperException Own exceptions
     */
    public void process(String command, int spaceBetweenDigits) throws SuperException {

        String[] parameters;

        // Validate correct parameters
        if (!command.contains(",")) {
            throw new IllegalArgumentException("Cadena " + command
                    + " no contiene parametros (caracter ,)");
        }

        // Split the input String
        parameters = command.split(",");

        // Validate length
        if (parameters.length != 2) {
            throw new SuperException("Cadena " + command
                    + " debe tener 2 parametros");
        }

        // Validate number format
        if (isNumeric(parameters[0])) {
            size = Integer.parseInt(parameters[0]);

            // Validate size between 1 and 10
            if (size < 1 || size > 10) {
                throw new SuperException("El parametro size [" + size
                        + "] debe estar entre 1 y 10");
            }
        } else {
            throw new SuperException("Parametro Size [" + parameters[0]
                    + "] no es un numero");
        }

        // Call method for print as LCD number
        printNum(parameters[1], spaceBetweenDigits);

    }

   /**
    * Method in charge of validating if a string is numeric
    * 
    * @param myString String to validate
    * @return boolean True is numeric, False isn't numeric
    * @throws SuperException Own exceptions
    */
    public boolean isNumeric(String myString) throws SuperException {
        try {
            Integer.parseInt(myString);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

}
