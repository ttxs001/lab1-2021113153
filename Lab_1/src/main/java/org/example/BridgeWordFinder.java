package org.example;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// R5修改

// R4修改

// 桥接词查询的类
public class BridgeWordFinder {
    private DirectedGraph graph;

    // 构造方法
    public BridgeWordFinder(DirectedGraph graph) {
        this.graph = graph;
    }

    // 查询桥接词的方法
    public String queryBridgeWords(String word1, String word2) {
        if (!graph.getGraph().containsKey(word1) || !graph.getGraph().containsKey(word2)) {
            return "No word1 or word2 in the graph!";
        }
        Set<String> bridgeWords = new HashSet<>();
        Map<String, Integer> edgesFromWord1 = graph.getGraph().get(word1);
        for (String intermediate : edgesFromWord1.keySet()) {
            if (graph.getGraph().getOrDefault(intermediate, Map.of()).containsKey(word2)) {
                bridgeWords.add(intermediate);
            }
        }
        if (bridgeWords.isEmpty()) {
            return "No bridge words from " + word1 + " to " + word2 + "!";
        }
        return "The bridge words from " + word1 + " to " + word2 + " are: " + String.join(", ", bridgeWords) + ".";
    }

}
