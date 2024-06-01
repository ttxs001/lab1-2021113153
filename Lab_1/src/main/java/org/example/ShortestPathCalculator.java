package org.example;

import java.util.*;

// 计算最短路径的类
public class ShortestPathCalculator {
    private DirectedGraph graph;

    // 构造方法
    public ShortestPathCalculator(DirectedGraph graph) {
        this.graph = graph;
    }

    // 计算最短路径的方法
    public String calcShortestPath(String word1, String word2) {
        if (!graph.getGraph().containsKey(word1) || !graph.getGraph().containsKey(word2)) {
            return "No path between " + word1 + " and " + word2 + "!";
        }
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        PriorityQueue<String> nodes = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        for (String node : graph.getGraph().keySet()) {
            if (node.equals(word1)) {
                distances.put(node, 0);
            } else {
                distances.put(node, Integer.MAX_VALUE);
            }
            nodes.add(node);
        }
        while (!nodes.isEmpty()) {
            String closest = nodes.poll();
            if (closest.equals(word2)) {
                break;
            }
            for (Map.Entry<String, Integer> neighbor : graph.getGraph().get(closest).entrySet()) {
                int alt = distances.get(closest) + neighbor.getValue();
                if (alt < distances.get(neighbor.getKey())) {
                    distances.put(neighbor.getKey(), alt);
                    previous.put(neighbor.getKey(), closest);
                    nodes.add(neighbor.getKey());
                }
            }
        }
        List<String> path = new ArrayList<>();
        for (String at = word2; at != null; at = previous.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        if (path.size() == 1) {
            return "No path between " + word1 + " and " + word2 + "!";
        }
        return "Shortest path: " + String.join(" -> ", path);
    }
}

