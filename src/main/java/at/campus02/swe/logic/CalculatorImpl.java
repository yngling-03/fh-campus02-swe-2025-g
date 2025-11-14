package at.campus02.swe.logic;


import at.campus02.swe.Calculator;
import at.campus02.swe.CalculatorException;

import java.util.Stack;

public class CalculatorImpl implements Calculator {

    private Stack<Double> stack_ = new Stack<>();

    @Override
    public double perform(Operation op) throws CalculatorException {

        switch (op) {
            case dotproduct:
                int vectorSize = (int) pop();
                if (vectorSize == 0) {
                    throw new CalculatorException("Not Null");
                }
                double[] bArray = new double[vectorSize];
                double[] aArray = new double[vectorSize];
                double result = 0;
                for (int i = vectorSize - 1; i >= 0; i--) {
                    bArray[i] = pop();
                }
                for (int i = vectorSize - 1; i >= 0; i--) {
                    aArray[i] = pop();
                }
                for (int i = 0; i < vectorSize; i++) {
                    result += bArray[i] * aArray[i];
                }
                return result;

            case sin:
                double inputSin = pop();
                return Math.sin(inputSin);
            case cos:
                double inputCos = pop();
                return Math.cos(inputCos);
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
