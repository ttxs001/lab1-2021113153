package org.example;

import java.util.Random;

// 根据桥接词生成新文本的类
public class NewTextGenerator {
    private BridgeWordFinder bridgeWordFinder;

    // 构造方法
    public NewTextGenerator(BridgeWordFinder bridgeWordFinder) {
        this.bridgeWordFinder = bridgeWordFinder;
    }

    // 生成新文本的方法
    public String generateNewText(String inputText) {
        String[] words = inputText.toLowerCase().split("\\s+");
        StringBuilder newText = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < words.length - 1; i++) {
            newText.append(words[i]).append(" ");
            String bridgeWords = bridgeWordFinder.queryBridgeWords(words[i], words[i + 1]);
            if (!bridgeWords.startsWith("No bridge words")) {
                String[] bridges = bridgeWords.split(": ")[1].split(", ");
                newText.append(bridges[random.nextInt(bridges.length)]).append(" ");
            }
        }
        // 处理最后一个单词
        newText.append(words[words.length - 1]);

        return newText.toString();
    }
}
