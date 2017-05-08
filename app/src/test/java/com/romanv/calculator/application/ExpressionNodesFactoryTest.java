package com.romanv.calculator.application;

import org.junit.Test;
import static org.junit.Assert.*;

import com.romanv.calculator.application.api.ExpressionNode;
import com.romanv.calculator.application.api.ExpressionNodeType;
import com.romanv.calculator.application.sources.ExpressionNodesFactory;
import com.romanv.calculator.application.sources.UnexpectedTokenException;

/*

Done    1. Incorrect token
Done    2. Number
Done        2.1 Single digit
Done        2.2 Several digits
Done        2.3 With floating point
Done    3. Operator
Done        3.1 Addition
Done        3.2 Subtraction
Done        3.3 Multiplication
Done        3.4 Division

 */

public class ExpressionNodesFactoryTest {

    private ExpressionNodesFactory factory = new ExpressionNodesFactory(new TestEnvironment());

    @Test(expected = UnexpectedTokenException.class)
    public void Incorrect_token__1() throws Exception {
        factory.createNode("hello");
    }

    @Test
    public void Number__Single_digit__2_1() throws Exception {
        ExpressionNode node = factory.createNode("1");
        assertEquals(ExpressionNodeType.Value, node.getType());
        assertEquals(1.0, node.evaluate(), 0.0);
    }

    @Test
    public void Number__Several_digits__2_2() throws Exception {
        ExpressionNode node = factory.createNode("123");
        assertEquals(ExpressionNodeType.Value, node.getType());
        assertEquals(123.0, node.evaluate(), 0.0);
    }

    @Test
    public void Number__With_floating_point__2_3() throws Exception {
        ExpressionNode node = factory.createNode("12.03");
        assertEquals(ExpressionNodeType.Value, node.getType());
        assertEquals(12.03, node.evaluate(), 0.0);
    }

    @Test
    public void Operator__Addition__3_1() throws Exception {
        ExpressionNode node = factory.createNode("+");
        assertEquals(ExpressionNodeType.Addition, node.getType());
    }

    @Test
    public void Operator__Subtraction__3_2() throws Exception {
        ExpressionNode node = factory.createNode("-");
        assertEquals(ExpressionNodeType.Subtraction, node.getType());
    }

    @Test
    public void Operator__Multiplication__3_3() throws Exception {
        ExpressionNode node = factory.createNode("*");
        assertEquals(ExpressionNodeType.Multiplication, node.getType());
    }

    @Test
    public void Operator__Division__3_4() throws Exception {
        ExpressionNode node = factory.createNode("/");
        assertEquals(ExpressionNodeType.Division, node.getType());
    }

}
