package com.ruleengine;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Create an example rule: "age > 30 AND salary > 50000"
        System.out.println("Creating Rule: 'age > 30 AND salary > 50000'");
        RuleEngine.Node rule = RuleEngine.createRule("age > 30 AND salary > 50000");

        // Sample data to evaluate the rule
        Map<String, Object> data1 = new HashMap<>();
        data1.put("age", 35);
        data1.put("salary", 60000);

        Map<String, Object> data2 = new HashMap<>();
        data2.put("age", 25);
        data2.put("salary", 40000);

        // Evaluating rule against data1 (age: 35, salary: 60000)` //////............./////////////////////////////
        System.out.println("Evaluating Rule for data1: {age: 35, salary: 60000}");
        boolean result1 = RuleEngine.evaluateRule(rule, data1);
        System.out.println("Result: " + result1);  // Should print true

        // Evaluating rule against data2 (age: 25, salary: 40000)
        System.out.println("Evaluating Rule for data2: {age: 25, salary: 40000}");
        boolean result2 = RuleEngine.evaluateRule(rule, data2);
        System.out.println("Result: " + result2);  // Should print false
    }
}
