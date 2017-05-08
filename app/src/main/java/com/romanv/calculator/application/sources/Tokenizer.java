package com.romanv.calculator.application.sources;

import com.romanv.calculator.application.api.Callback;
import com.romanv.calculator.application.api.Environment;

import java.util.StringTokenizer;

public class Tokenizer {

    public Tokenizer(Environment environment) {
        this.environment = environment;

        delimiters = " ";
        delimiters += environment.getAdditionSymbol();
        delimiters += environment.getSubtractionSymbol();
        delimiters += environment.getMultiplicationSymbol();
        delimiters += environment.getDivisionSymbol();
    }

    public void setString(String string) {
        this.string = string;
    }

    public void forEachToken(Callback<String> callback) {
        StringTokenizer tokenizer = new StringTokenizer(string, delimiters, true);

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();

            if (!token.equals(" ")) {
                callback.call(token);
            }
        }
    }

    private Environment environment;

    private String string;

    private String delimiters;

}
