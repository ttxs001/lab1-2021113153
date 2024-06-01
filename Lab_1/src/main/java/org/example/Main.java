package org.example;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
// 添加注释，用于测试R3
// 主类
public class Main {
    public static void main(String[] args) {
        try {
            if (args.length < 1) {
                System.out.println("Usage: java Main <file_path>");
                return;
            }
            // 读取并处理文本文件
            List<String> words = TextProcessor.processTextFile(args[0]);
            DirectedGraph graph = new DirectedGraph();
            // 构建有向图
            for (int i = 0; i < words.size() - 1; i++) {
                graph.addEdge(words.get(i), words.get(i + 1));
            }
            // 初始化功能类
            BridgeWordFinder bridgeWordFinder = new BridgeWordFinder(graph);
            NewTextGenerator newTextGenerator = new NewTextGenerator(bridgeWordFinder);
            ShortestPathCalculator shortestPathCalculator = new ShortestPathCalculator(graph);
            RandomWalker randomWalker = new RandomWalker(graph);

            // 提供用户交互菜单
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Choose an option:");
                System.out.println("1. Show Directed Graph");
                System.out.println("2. Query Bridge Words");
                System.out.println("3. Generate New Text");
                System.out.println("4. Calculate Shortest Path");
                System.out.println("5. Random Walk");
                System.out.println("6. Exit");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        graph.displayGraph();
                        graph.showGraphVisual();
                        break;
                    case 2:
                        System.out.print("Enter word1: ");
                        String word1 = scanner.nextLine();
                        System.out.print("Enter word2: ");
                        String word2 = scanner.nextLine();
                        System.out.println(bridgeWordFinder.queryBridgeWords(word1, word2));
                        break;
                    case 3:
                        System.out.print("Enter new text: ");
                        String inputText = scanner.nextLine();
                        System.out.println(newTextGenerator.generateNewText(inputText));
                        break;
                    case 4:
                        System.out.print("Enter word1: ");
                        word1 = scanner.nextLine();
                        System.out.print("Enter word2: ");
                        word2 = scanner.nextLine();
                        System.out.println(shortestPathCalculator.calcShortestPath(word1, word2));
                        break;
                    case 5:
                        System.out.println(randomWalker.randomWalk());
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
