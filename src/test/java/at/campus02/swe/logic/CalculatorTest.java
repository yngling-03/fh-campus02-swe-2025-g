package at.campus02.swe.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import at.campus02.swe.Calculator;
import at.campus02.swe.CalculatorException;
import at.campus02.swe.Calculator.Operation;

public class CalculatorTest {

    @Test
    public void testSimpleAddOperation() throws Exception {

        //setup
        Calculator calc = new CalculatorImpl();

        //execute
        calc.push(2.0);
        calc.push(3);
        double result = calc.perform(Operation.add);

        //verify
        assertEquals(5, result, 0);


    }

    @Test
    public void testSimpleMulOperation() throws Exception {

        Calculator calc = new CalculatorImpl();
        calc.push(2.0);
        calc.push(3);
        double result = calc.perform(Operation.mul);

        assertEquals(6, result, 0);

    }

    @Test
    public void testSimpleDivOperation() throws Exception {

        Calculator calc = new CalculatorImpl();
        calc.push(6.0);
        calc.push(2);
        double result = calc.perform(Operation.div);

        assertEquals(3, result, 0);

    }

    @Test
    public void testSimpleModOperation() throws Exception {

        Calculator calc = new CalculatorImpl();
        calc.push(7.0);
        calc.push(2);
        double result = calc.perform(Operation.mod);

        assertEquals(1, result, 0);

    }

    @Test
    public void testSinOperation() throws Exception {

        Calculator calc = new CalculatorImpl();
        calc.push(90);
        double result = calc.perform(Operation.sin);

        assertEquals(0.8939966636005579, result, 0);
    }

    @Test
    public void testCosOperation() throws Exception {

        Calculator calc = new CalculatorImpl();
        calc.push(90);
        double result = calc.perform(Operation.cos);

        assertEquals(-0.4480736161291701, result, 1e-12);

    }

    @Test(expected = CalculatorException.class)
    public void testPopOnEmptyStack() throws Exception {

        Calculator calc = new CalculatorImpl();
        calc.pop();

    }

    @Test
    public void testDivisionByZero() throws Exception {

        //Setup
        Calculator calc = new CalculatorImpl();
        try {
            calc.push(2);
            calc.push(0);
            calc.perform(Operation.div);

            fail("Exception expected");


        } catch (CalculatorException e) {
            assertEquals("Division by zero", e.getMessage());
            // e.getCause()
        }

    }

    @Test
    public void testDotproductTwoElements() throws Exception {

        // setup
        Calculator calc = new CalculatorImpl();

        // (1, 3) · (2, 4) = 1*2 + 3*4 = 14
        calc.push(1);
        calc.push(3);
        calc.push(2);
        calc.push(4);
        calc.push(2); // Anzahl Elemente pro Vektor

        // execute
        double result = calc.perform(Operation.dotproduct);

        // verify
        assertEquals(14, result, 0);
    }

    @Test
    public void testDotproductThreeElements() throws Exception {

        // setup
        Calculator calc = new CalculatorImpl();

        // (1, 2, 3) · (4, 5, 6) = 1*4 + 2*5 + 3*6 = 32
        calc.push(1);
        calc.push(2);
        calc.push(3);

        calc.push(4);
        calc.push(5);
        calc.push(6);

        calc.push(3); // Anzahl Elemente pro Vektor

        // execute
        double result = calc.perform(Operation.dotproduct);

        // verify
        assertEquals(32, result, 0.00000000000001);
    }

    @Test(expected = CalculatorException.class)
    public void testDotproductInvalidElementCount() throws Exception {

        // setup
        Calculator calc = new CalculatorImpl();

        // Wir pushen nur 2 Werte, aber geben als Anzahl 0 an → ungültig
        calc.push(1);
        calc.push(2);
        calc.push(0); // ungültige Anzahl Elemente pro Vektor

        // execute (soll Exception werfen)
        calc.perform(Operation.dotproduct);
    }


}