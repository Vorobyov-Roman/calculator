package com.romanv.calculator.application.sources;

import com.romanv.calculator.application.api.Command;
import com.romanv.calculator.application.api.CommandsFactory;
import com.romanv.calculator.application.api.Environment;
import com.romanv.calculator.application.api.Application;
import com.romanv.calculator.application.api.Callback;

public class ApplicationImpl implements Application {

    public ApplicationImpl(Environment environment) {
        this.commandsFactory = new CommandsFactoryImpl(environment);

        inputBuffer = new InputBufferImpl();
        outputBuffer = new OutputBuffer();
        calculator = new Calculator(environment);

        inputBuffer.subscribeToInputChange(calculator::onInputChanged);
        calculator.subscribeToOutputChange(outputBuffer::onOutputChanged);
    }

    @Override
    public void execute(Command command) {
        try {
            command.execute(inputBuffer);
        }
        catch (UnexpectedTokenException e) {
            calculator.reset();
        }
    }

    public void subscribeToInputChange(Callback<String> callback) {
        inputBuffer.subscribeToInputChange(callback);
    }

    public void subscribeToOutputChange(Callback<String> callback) {
        outputBuffer.subscribeToOutputChange(callback);
    }

    public CommandsFactory getCommandsFactory() {
        return commandsFactory;
    }

    private CommandsFactory commandsFactory;

    private InputBufferImpl inputBuffer;
    private Calculator calculator;
    private OutputBuffer outputBuffer;

}
