package practice;

import java.util.ArrayList;
import java.util.List;

public class ParseTree {
    // x + (a+pi-xn)+eps
    List<String> genCalTree(String calExpression) {
        // 构造树
        Node node = parseNode(calExpression);
        ArrayList<String> result = new ArrayList<>();
        parseList(node, result);
        return result;
    }

    private void parseList(Node node, ArrayList<String> result) {
        if (node.value == null) {
            return;
        }
        result.add(node.value);
        parseList(node.left, result);
        parseList(node.right, result);
    }

    private Node parseNode(String calExpression) {
        if (calExpression.isEmpty()) {
            return null;
        }
        int level = 0;
        for (int i = calExpression.length() - 1; i >= 0; i--) {
            char c = calExpression.charAt(i);
            if (c == ')') {
                level++;
            } else if (c == '(') {
                level--;
            }
            if (level == 0) {
                if (c == '+' || c == '-') {
                    Node node = new Node(String.valueOf(c));
                    node.left = parseNode(calExpression.substring(0, i));
                    node.right = parseNode(calExpression.substring(i + 1));
                    return node;
                }
            }
        }
        if (calExpression.charAt(0) == '(' && calExpression.charAt(calExpression.length() - 1) == ')') {
            return parseNode(calExpression.substring(1, calExpression.length() - 1));
        }

        return new Node(calExpression);
    }

}

class Node {
    Node left;
    Node right;
    String value;


    public Node(String value) {
        this.value = value;
    }
}
