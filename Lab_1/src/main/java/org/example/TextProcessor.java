package org.example;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 处理文本文件的类
public class TextProcessor {
    // 读取文本文件并处理文本，返回处理后的单词列表
    public static List<String> processTextFile(String filePath) throws IOException {
        List<String> words = new ArrayList<>();
        // 读取文件内容
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        // 替换所有非字母字符为空格，并将文本转换为小写
        content = content.replaceAll("[^a-zA-Z\\s]", " ").replaceAll("\\s+", " ").toLowerCase();
        // 将处理后的内容分割成单词
        words.addAll(Arrays.asList(content.split(" ")));
        return words;
    }
}

