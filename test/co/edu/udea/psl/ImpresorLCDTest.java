/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.psl;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jpoh97
 */
public class ImpresorLCDTest {


    /**
     * Test of process method, of class ImpresorLCD.
     */
    @Test
    public void testProcess() throws Exception {
        System.out.println("process");
        String command = "1,234";
        int spaceBetweenDigits = 2;
        ImpresorLCD instance = new ImpresorLCD();
        instance.process(command, spaceBetweenDigits);
    }

    /**
     * Test of isNumeric method, of class ImpresorLCD.
     */
    @Test
    public void testIsNumeric() throws Exception {
        System.out.println("isNumeric");
        String myString = "12345";
        ImpresorLCD instance = new ImpresorLCD();
        boolean expResult = true;
        boolean result = instance.isNumeric(myString);
        assertEquals(expResult, result);
        if (result != expResult) {
            fail("The test case is a prototype.");
        }
    }

}
