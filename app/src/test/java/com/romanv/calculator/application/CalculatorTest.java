package com.romanv.calculator.application;

import org.junit.Test;
import static org.junit.Assert.*;

import com.romanv.calculator.application.sources.Calculator;

/*

Done    1. UnaryExpression

Done    2. Binary expression
Done        2.1 Addition
Done        2.2 Subtraction
Done            2.2.1 Positive result
Done            2.2.2 Negative result
Done        2.3 Multiplication
Done        2.4 Division
Done            2.4.1 Integer result
Done            2.4.2 Fractional result

Done    3. Binary reduce
Done        3.1 Chained Addition
Done        3.2 Chained Subtraction
Done            3.2.1 Positive result
Done            3.2.2 Negative result
Done        3.3 Chained Multiplication
Done        3.4 Chained Division
Done            3.4.1 Integer result
Done            3.4.2 Fractional result

Done    4. Precedence
Done        4.1 Subtraction after Addition
Done        4.2 Subtraction before Addition
Done        4.1 Multiplication after Addition
Done        4.2 Division before Addition

 */

public class CalculatorTest {

    private Calculator calculator = new Calculator(new TestEnvironment());

    @Test
    public void UnaryExpression__1() throws Exception {
        assertEquals(123.0, calculator.calculate("123"), 0.0);
    }

    @Test
    public void BinaryExpression__Addition__2_1() throws Exception {
        assertEquals(128.0, calculator.calculate("123 + 5"), 0.0);
    }

    @Test
    public void BinaryExpression__Subtraction__Positive__2_2_1() throws Exception {
        assertEquals(4.0, calculator.calculate("7 - 3"), 0.0);
    }

    @Test
    public void BinaryExpression__Subtraction__Negative__2_2_2() throws Exception {
        assertEquals(-4.0, calculator.calculate("3 - 7"), 0.0);
    }

    @Test
    public void BinaryExpression__Multiplication__2_3_1() throws Exception {
        assertEquals(420.0, calculator.calculate("42 * 10"), 0.0);
    }

    @Test
    public void BinaryExpression__Division__Integer__2_4_1() throws Exception {
        assertEquals(2.0, calculator.calculate("4 / 2"), 0.0);
    }

    @Test
    public void BinaryExpression__Division__Fractional__2_4_2() throws Exception {
        assertEquals(0.5, calculator.calculate("2 / 4"), 0.0);
    }

    @Test
    public void BinaryReduce__Addition__3_1() throws Exception {
        assertEquals(6.0, calculator.calculate("1 + 2 + 3"), 0.0);
    }

    @Test
    public void BinaryReduce__Subtraction__Positive__3_2_1() throws Exception {
        assertEquals(34.0, calculator.calculate("45 - 10 - 1"), 0.0);
    }

    @Test
    public void BinaryReduce__Subtraction__Negative__3_2_1() throws Exception {
        assertEquals(-2.0, calculator.calculate("2 - 3 - 1"), 0.0);
    }

    @Test
    public void BinaryReduce__Multiplication__3_3() throws Exception {
        assertEquals(240.0, calculator.calculate("3 * 40 * 2"), 0.0);
    }

    @Test
    public void BinaryReduce__Division__Integer__3_4_1() throws Exception {
        assertEquals(15.0, calculator.calculate("120 / 2 / 4"), 0.0);
    }

    @Test
    public void BinaryReduce__Division__Fractional__3_4_2() throws Exception {
        assertEquals(0.052, calculator.calculate("10.4 / 2 / 100"), 0.00000001);
    }

    @Test
    public void Precedence__Subtraction_after_addition__4_1() throws Exception {
        assertEquals(1.0, calculator.calculate("1 + 2 - 2"), 0.0);
    }

    @Test
    public void Precedence__Subtraction_before_addition__4_2() throws Exception {
        assertEquals(1.0, calculator.calculate("1 - 2 + 2"), 0.0);
    }

    @Test
    public void Precedence__Multiplication_after_addition__4_3() throws Exception {
        assertEquals(5.0, calculator.calculate("1 + 2 * 2"), 0.0);
    }

    @Test
    public void Precedence__Multiplication_before_addition__4_4() throws Exception {
        assertEquals(4.0, calculator.calculate("1 * 2 + 2"), 0.0);
    }

}
