package com.kgc.froglm.parser;

import java.util.Arrays;

public class Node {
    enum NodeType {
        FUNCTION,
        FUNCTION_CALL,
        VARIABLE,
        PLUS,
        MINUS,
        MULTIPLICATION,
        DIVISION,
        ARGUMENT
    }

    public Node(NodeType type, int dataSize) {
        this.type = type;
        data = new Object[dataSize];
    }

    public NodeType type;
    public Object[] data;

    @Override
    public String toString() {
        return "Node{" +
                "\ntype=" + type +
                ",\n data=" + Arrays.toString(data) +
                "\n}";
    }
}
