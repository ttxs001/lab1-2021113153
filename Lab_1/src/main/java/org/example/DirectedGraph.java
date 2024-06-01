package org.example;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

import java.util.HashMap;
import java.util.Map;

// 有向图的类
public class DirectedGraph {
    // 存储有向图的数据结构，节点和边
    private Map<String, Map<String, Integer>> graph = new HashMap<>();

    // 添加边的方法
    public void addEdge(String from, String to) {
        // 如果图中没有这个节点，则初始化
        graph.putIfAbsent(from, new HashMap<>());
        // 获取节点的边
        Map<String, Integer> edges = graph.get(from);
        // 增加边的权重
        edges.put(to, edges.getOrDefault(to, 0) + 1);
    }

    // 返回图的数据结构
    public Map<String, Map<String, Integer>> getGraph() {
        return graph;
    }

    // 显示图的方法
    public void displayGraph() {
        for (Map.Entry<String, Map<String, Integer>> entry : graph.entrySet()) {
            for (Map.Entry<String, Integer> edge : entry.getValue().entrySet()) {
                System.out.println(entry.getKey() + " -> " + edge.getKey() + " (weight: " + edge.getValue() + ")");
            }
        }
    }

    // 可视化图的方法
    public void showGraphVisual() {
        // 创建一个名为 "Directed Graph" 的图形对象
        Graph graphVisual = new SingleGraph("Directed Graph");

        // 添加节点和边到可视化图中
        for (Map.Entry<String, Map<String, Integer>> entry : graph.entrySet()) {
            String from = entry.getKey();
            if (graphVisual.getNode(from) == null) {
                graphVisual.addNode(from).addAttribute("ui.label", from);
            }
            for (Map.Entry<String, Integer> edge : entry.getValue().entrySet()) {
                String to = edge.getKey();
                if (graphVisual.getNode(to) == null) {
                    graphVisual.addNode(to).addAttribute("ui.label", to);
                }
                String edgeId = from + "->" + to + "-" + edge.getValue();
                if (graphVisual.getEdge(edgeId) == null) {
                    graphVisual.addEdge(edgeId, from, to, true).addAttribute("ui.label", edge.getValue());
                }
            }
        }

        // 显示图
        graphVisual.display();
    }
}
