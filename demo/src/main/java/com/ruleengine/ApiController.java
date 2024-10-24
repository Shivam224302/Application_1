package com.ruleengine;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
public class ApiController {

    @PostMapping("/createRule")
    public RuleEngine.Node createRule(@RequestBody String ruleString) {
        return RuleEngine.createRule(ruleString);
    }

    @PostMapping("/evaluateRule")
    public boolean evaluateRule(@RequestBody Map<String, Object> data, @RequestParam String ruleId) {
        // Ideally, you would load the rule from the database using ruleId, here we assume a hardcoded rule for demo.
        RuleEngine.Node rule = RuleEngine.createRule("age > 30 AND salary > 50000");
        return RuleEngine.evaluateRule(rule, data);
    }
}
