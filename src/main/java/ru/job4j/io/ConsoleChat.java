package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> chat = new ArrayList<>();
        Random random = new Random();
        String[] words = readPhrases().toArray(new String[0]);
        boolean exit = false;
        Scanner reader = new Scanner(System.in);
        while (!exit) {
            String myWord = reader.nextLine();
            if (myWord.equals(OUT)) {
                exit = true;
                chat.add(myWord);
                continue;
            }
            if (myWord.equals(STOP)) {
                chat.add(myWord);
                while (!myWord.equals(CONTINUE)) {
                    myWord = reader.nextLine();
                    chat.add(myWord);
                }
                continue;
            }
            String botWord = words[random.nextInt(words.length)];
            chat.add(myWord);
            chat.add(botWord);
            System.out.println(botWord);
        }
        saveLog(chat);
    }

    private List<String> readPhrases() {
        List<String> strings = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(new FileReader(botAnswers))) {
            input.lines().forEach(strings::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strings;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter output = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(path)))) {
            for (String str : log) {
                output.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("src/data/log.txt", "src/data/botAnswers.txt");
        consoleChat.run();
    }
}
