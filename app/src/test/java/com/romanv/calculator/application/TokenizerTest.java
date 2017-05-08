package com.romanv.calculator.application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import com.romanv.calculator.application.sources.Tokenizer;

/*

Done    1. No tokens
Done        1.1 Empty string
Done        1.2 Single space
Done        1.2 Several spaces

Done    2. One token
Done        2.1 Single digit
Done        2.2 Several digits
Done        2.3 Single symbol

Done    3. Several space separated tokens
Done        3.1 Several symbols
Done        3.2 Several single digit numbers
Done        3.3 Several multi digit numbers

Done    4. Several symbol separated tokens
Done        4.1 Several symbols
Done        4.2 Several single digit numbers
Done        4.3 Several multi digit numbers

 */

public class TokenizerTest {

    private Tokenizer tokenizer = new Tokenizer(new TestEnvironment());

    private void setString(String string) {
        tokenizer.setString(string);
    }

    private void expectTokens(String... tokens) {
        List<String> expected = Arrays.asList(tokens);
        List<String> actual = new ArrayList<>();

        tokenizer.forEachToken(actual::add);

        assertThat(actual, is(expected));
    }

    @Test
    public void No_tokens__Empty_string__1_1() throws Exception {
        setString("");
        expectTokens();
    }

    @Test
    public void No_tokens__Single_space__1_2() throws Exception {
        setString(" ");
        expectTokens();
    }

    @Test
    public void No_tokens__Several_spaces__1_3() throws Exception {
        setString("   ");
        expectTokens();
    }

    @Test
    public void One_token__Single_digit_number__2_1() throws Exception {
        setString("1");
        expectTokens("1");
    }

    @Test
    public void One_token__Multi_digit_number__2_2() throws Exception {
        setString("420");
        expectTokens("420");
    }

    @Test
    public void One_token__Single_symbol__2_3() throws Exception {
        setString("+");
        expectTokens("+");
    }

    @Test
    public void Space_separator__Several_symbols__3_1() throws Exception {
        setString("- + -- ++ +- -+");
        expectTokens("-", "+", "-", "-", "+", "+", "+", "-", "-", "+");
    }

    @Test
    public void Space_separator__Several_single_digit_numbers__3_2() throws Exception {
        setString("1 2");
        expectTokens("1", "2");
    }

    @Test
    public void Space_separator__Several_multi_digit_numbers__3_3() throws Exception {
        setString("123 42");
        expectTokens("123", "42");
    }

    @Test
    public void Symbol_separator__Several_symbols__4_1() throws Exception {
        setString("-+-");
        expectTokens("-", "+", "-");
    }

    @Test
    public void Symbol_separator__Several_single_digit_numbers__4_2() throws Exception {
        setString("1+2");
        expectTokens("1", "+", "2");
    }

    @Test
    public void Symbol_separator__Several_multi_digit_numbers__4_3() throws Exception {
        setString("123-42");
        expectTokens("123", "-", "42");
    }

}
