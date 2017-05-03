package ru.javaLessonTask.calculator;

import java.io.Serializable;

public class StoreOperationNode implements Serializable {
    private String user;
    private Double arg1;
    private Double arg2;
    private String operation;
    private Double result;


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public StoreOperationNode(Double arg1, Double arg2, String operation, Double result) {
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.operation = operation;
        this.result = result;
    }

    public StoreOperationNode(Double arg1) {
        this.arg1 = arg1;
    }

    public Double getArg1() {
        return arg1;
    }

    public void setArg1(Double arg1) {
        this.arg1 = arg1;
    }

    public Double getArg2() {
        return arg2;
    }

    public void setArg2(Double arg2) {
        this.arg2 = arg2;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StoreOperationNode that = (StoreOperationNode) o;

        if (arg1 != null ? !arg1.equals(that.arg1) : that.arg1 != null) return false;
        if (arg2 != null ? !arg2.equals(that.arg2) : that.arg2 != null) return false;
        return operation != null ? operation.equals(that.operation) : that.operation == null;

    }

}
