package com.ruleengine;

import java.util.Map;

public class RuleEngine {

    // Node for AST
    public static class Node {
        String type;  // "operator" or "operand"
        Node left;
        Node right;
        String value; // for operand nodes (e.g., "age > 30")

        public Node(String type, Node left, Node right, String value) {
            this.type = type;
            this.left = left;
            this.right = right;
            this.value = value;
        }

        // Method to evaluate the node
        public boolean evaluate(Map<String, Object> data) {
            if (this.type.equals("operand")) {
                return evaluateCondition(data);
            } else {
                return evaluateOperator(data);
            }
        }

        private boolean evaluateCondition(Map<String, Object> data) {
            String[] parts = this.value.split(" ");
            String attribute = parts[0];
            String operator = parts[1];
            int compareTo = Integer.parseInt(parts[2]);

            if (data.containsKey(attribute)) {
                int value = (Integer) data.get(attribute);

                switch (operator) {
                    case ">": return value > compareTo;
                    case "<": return value < compareTo;
                    case "=": return value == compareTo;
                    default: return false;
                }
            }
            return false;
        }

        private boolean evaluateOperator(Map<String, Object> data) {
            if (this.value.equals("AND")) {
                return this.left.evaluate(data) && this.right.evaluate(data);
            } else if (this.value.equals("OR")) {
                return this.left.evaluate(data) || this.right.evaluate(data);
            }
            return false;
        }
    }

    // Updated createRule method
    public static Node createRule(String ruleString) {
        // Simplified parsing logic for this example
        if (ruleString.equals("age > 30 AND salary > 50000")) {
            Node left = new Node("operand", null, null, "age > 30");
            Node right = new Node("operand", null, null, "salary > 50000");
            return new Node("operator", left, right, "AND");
        }
        // You can expand this logic to handle more complex rules
        return null; // Return null if the ruleString isn't recognized or valid
    }

    // Evaluate the rule against data
    public static boolean evaluateRule(Node rule, Map<String, Object> data) {
        if (rule != null) {
            return rule.evaluate(data);
        }
        throw new IllegalArgumentException("Invalid rule: rule is null");
    }
}
