package polymorphism.calculator.operations;

public interface Operation {
    void addOperand(int operand);
    int getResult();
    boolean isCompleted();
}