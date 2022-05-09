package com.kgc.froglm;

import com.kgc.froglm.parser.Node;
import com.kgc.froglm.parser.Parser;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String code = "x = 22*33-4\nf(x)=2*x\ny = f(22)\nf(33)";

        ArrayList<Node> nodes = Parser.parse(code);

        System.out.println(code);
        System.out.println();
        for (Node n : nodes) {
            System.out.println(n);
        }
    }
}
