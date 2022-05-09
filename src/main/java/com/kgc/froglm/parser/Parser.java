package com.kgc.froglm.parser;

import java.util.ArrayList;

public class Parser {
    private static final String FUNCTION_REGEX = "[a-zA-Z_0-9]*\\([a-zA-Z_0-9,]*\\)=[a-zA-Z_0-9\\*\\-\\+/]*";
    private static final String FUNCTION_CALL = "[a-zA-Z_0-9]*\\([a-zA-Z_0-9,]*\\)";
    private static final String VARIABLE_REGEX = "[a-zA-Z_0-9]*=[a-zA-Z_0-9\\*\\-\\+/]*";
    private static final String SPACE_REGEX = "\\s+";

    public static ArrayList<Node> parse(String code) {
        ArrayList<Node> nodes = new ArrayList<>();
        String[] lines = code.split("\\r?\\n");
        for (String line : lines) {
            String lineWithoutSpaces = line.replaceAll(SPACE_REGEX, "");
            if (!lineWithoutSpaces.isEmpty()) {
                nodes.add(parseLine(lineWithoutSpaces));
            }
        }
        return nodes;
    }

    public static Node parseLine(String line) {
        if (line.matches(FUNCTION_REGEX)) {
            return parseFunction(line);
        } else if (line.matches(VARIABLE_REGEX)) {
            return parseVariable(line);
        } else if (line.matches(FUNCTION_CALL)) {
            return parseFunctionCall(line);
        }
        return null;
    }

    //Example: f(56)
    public static Node parseFunctionCall(String line) {
        int bracketIndex = line.indexOf("(");
        Node[] arguments = parseArguments(line.substring(bracketIndex));

        Node node = new Node(Node.NodeType.FUNCTION_CALL, 1 + arguments.length);
        node.data[0] = line.substring(0, bracketIndex);
        System.arraycopy(arguments, 0, node.data, 1, arguments.length);

        return node;
    }

    //Example: f(x) = 2 * x
    public static Node parseFunction(String function) {
        String[] functionSplit = function.split("=");

        int bracketIndex = functionSplit[0].indexOf("(");

        Node[] arguments = parseArguments(functionSplit[0].substring(bracketIndex));

        Node node = new Node(Node.NodeType.FUNCTION, 2 + arguments.length);
        node.data[0] = functionSplit[0].substring(0, bracketIndex);
        node.data[1] = parseMathExpression(functionSplit[1]);
        System.arraycopy(arguments, 0, node.data, 2, arguments.length);
        return node;
    }

    //Example: "x = 435"
    public static Node parseVariable(String variable) {
        Node node = new Node(Node.NodeType.VARIABLE, 2);
        String[] variableSplit = variable.split("=");
        node.data[0] = variableSplit[0];
        node.data[1] = parseMathExpression(variableSplit[1]);
        return node;
    }


    //Example: "(x, y, name, xd)"
    public static Node[] parseArguments(String arguments) {
        String[] argumentsArray = arguments.substring(1, arguments.length() - 1).split(",");
        Node[] nodes = new Node[argumentsArray.length];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(Node.NodeType.ARGUMENT, 1);
            nodes[i].data[0] = argumentsArray[i];
        }
        return nodes;
    }

    public static Node parseMathExpression(String expression) {
        return null;
    }
}
