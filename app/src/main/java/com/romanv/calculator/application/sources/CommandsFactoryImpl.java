package com.romanv.calculator.application.sources;

import com.romanv.calculator.application.api.ActionButton;
import com.romanv.calculator.application.api.Command;
import com.romanv.calculator.application.api.CommandsFactory;
import com.romanv.calculator.application.api.Environment;
import com.romanv.calculator.application.api.InputBuffer;

class CommandsFactoryImpl implements CommandsFactory {

    CommandsFactoryImpl(Environment environment) {
        this.environment = environment;
    }

    @Override
    public Command createCommand(ActionButton pressedButton) {
        switch (pressedButton) {
            case One:   return createAppendCommand("1");
            case Two:   return createAppendCommand("2");
            case Three: return createAppendCommand("3");
            case Four:  return createAppendCommand("4");
            case Five:  return createAppendCommand("5");
            case Six:   return createAppendCommand("6");
            case Seven: return createAppendCommand("7");
            case Eight: return createAppendCommand("8");
            case Nine:  return createAppendCommand("9");
            case Zero:  return createAppendCommand("0");

            case Add:       return createAppendCommand(environment.getAdditionSymbol());
            case Subtract:  return createAppendCommand(environment.getSubtractionSymbol());
            case Multiply:  return createAppendCommand(environment.getMultiplicationSymbol());
            case Divide:    return createAppendCommand(environment.getDivisionSymbol());

            case Point: return createAppendCommand(environment.getPointSymbol());

            case Delete: return createDeleteCommand();
            case Clear: return createClearCommand();

            default:  return null;
        }
    }

    private Command createAppendCommand(String str) {
        return (inputBuffer) -> inputBuffer.append(str);
    }

    private Command createDeleteCommand() {
        return InputBuffer::removeLastElement;
    }

    private Command createClearCommand() {
        return InputBuffer::clear;
    }

    private Environment environment;

}
