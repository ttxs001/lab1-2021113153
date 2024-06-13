package org.example;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

public class ShortestPathCalculatorTest {
    private DirectedGraph graph;
    private ShortestPathCalculator calculator;

    @Before
    public void setUp() {
        // 创建一个测试用的有向图
        graph = new DirectedGraph();
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "D");

        calculator = new ShortestPathCalculator(graph);
    }

    @Test
    public void testShortestPathExist() {
        // 测试从 A 到 D 的最短路径
        String result = calculator.calcShortestPath("A", "D");
        assertEquals("Shortest path: A -> B -> D", result);
    }

    @Test
    public void testNoPathExist() {
        // 测试从 D 到 A 没有路径的情况
        String result = calculator.calcShortestPath("D", "A");
        assertEquals("No path between D and A!", result);
    }

    @Test
    public void testStartNodeNotInGraph() {
        // 测试起始节点不在图中的情况
        String result = calculator.calcShortestPath("X", "A");
        assertEquals("No path between X and A!", result);
    }

    @Test
    public void testEndNodeNotInGraph() {
        // 测试目标节点不在图中的情况
        String result = calculator.calcShortestPath("A", "X");
        assertEquals("No path between A and X!", result);
    }

    @Test
    public void testSameStartAndEndNode() {
        // 测试起始节点和目标节点相同的情况
        String result = calculator.calcShortestPath("A", "A");
        assertEquals("No path between A and A!", result);
    }

    @Test
    public void testPathWithSingleEdge() {
        // 测试只有一条边的最短路径
        String result = calculator.calcShortestPath("A", "B");
        assertEquals("Shortest path: A -> B", result);
    }

    @Test
    public void testDisconnectedGraph() {
        // 测试图中有不连通部分的情况
        graph.addEdge("E", "F");
        String result = calculator.calcShortestPath("A", "E");
        assertEquals("No path between A and E!", result);
    }
}

