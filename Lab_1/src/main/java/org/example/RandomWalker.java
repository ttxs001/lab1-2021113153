package org.example;
import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
// 随机游走的类
public class RandomWalker {
    private DirectedGraph graph;

    // 构造方法
    public RandomWalker(DirectedGraph graph) {
        this.graph = graph;
    }

    // 随机游走的方法
    public String randomWalk() {
        DirectedGraph graph_walk = new DirectedGraph();

        Random random = new Random();
        List<String> nodes = new ArrayList<>(graph.getGraph().keySet());
        String current = nodes.get(random.nextInt(nodes.size()));
        Set<String> visitedEdges = new HashSet<>();
        StringBuilder walk = new StringBuilder(current);

        while (true) {
            Map<String, Integer> neighbors = graph.getGraph().get(current);
            if (neighbors.isEmpty()) {
                break;
            }
            List<String> edges = new ArrayList<>(neighbors.keySet());
            String next = edges.get(random.nextInt(edges.size()));

            graph_walk.addEdge(current, next);

            String edge = current + "->" + next;
            if (visitedEdges.contains(edge)) {
                break;
            }
            visitedEdges.add(edge);
            walk.append(" ").append(next);
            current = next;
        }
        graph_walk.showGraphVisual();

        // 要保存的字符串
        String content = walk.toString();

        // 文件路径
        String filePath = "output.txt";

        // 使用try-with-resources语句自动关闭资源
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
            System.out.println("文件保存成功！");
        } catch (IOException e) {
            System.err.println("保存文件时发生错误：" + e.getMessage());
        }

        return walk.toString();
    }

//    public String RWgraph() {
//        return this.graph;
//    }
}
