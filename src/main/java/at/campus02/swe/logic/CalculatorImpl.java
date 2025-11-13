package at.campus02.swe.logic;


import at.campus02.swe.Calculator;
import at.campus02.swe.CalculatorException;

import java.util.Stack;

public class CalculatorImpl implements Calculator {

    private Stack<Double> stack_ = new Stack<Double>();

    @Override
    public double perform(Operation op) throws CalculatorException {

        switch (op) {
            case sin:
                return 0;
            case cos:
                return 0;
            default:
                double b = pop();
                double a = pop();
                switch (op) {
                    case mod:
                        return a % b;
                    case add:
                        return a + b;
                    case sub:
                        return a - b;
                    case div:
                        double c = a / b;
                        if (Double.isInfinite(c))
                            throw new CalculatorException("Division by zero");
                        return c;
                    case mul:
                        return a * b;
                    default:
                        throw new CalculatorException("Operator " + op + " unkown!");
                }
        }
    }

    @Override
    public double pop() throws CalculatorException {
        if (stack_.isEmpty())
            throw new CalculatorException();
        return stack_.pop();
    }

    @Override
    public void push(double v) {
        stack_.push(v);
    }

    @Override
    public void clear() {
        stack_.clear();
    }

}
