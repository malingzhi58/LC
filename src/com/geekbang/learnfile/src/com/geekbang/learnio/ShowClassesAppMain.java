package com.geekbang.learnfile.src.com.geekbang.learnio;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ShowClassesAppMain {
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        File target = create();
        writeToFile(target);

    }

    private static void writeToFile(File target) throws FileNotFoundException {
        try (FileOutputStream lier1 = new FileOutputStream(target);
             OutputStreamWriter lier2 = new OutputStreamWriter(lier1, StandardCharsets.UTF_8);
             PrintWriter lier3 = new PrintWriter(lier2);) {
            System.out.println("yes or not");
            System.out.println("输入的内容会实时写入文件，如果输入空行则结束");
            while (true) {
                String lineToWrite = scanner.nextLine();
                System.out.println("输入内容为" + lineToWrite);
                if (lineToWrite.trim().isBlank()) {
                    System.out.println("输入结束");
                    break;
                } else {
                    lier3.println(lineToWrite);
                    lier3.flush();
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static File create() throws IOException {
        System.out.println("file name");
        String name = scanner.nextLine();
//        while(true){

        File newFile = new File("." + File.separator, name + ".txt");
        if (newFile.isFile()) {
            System.out.println("目标文件存在，删除：" + newFile.delete());
        }
        boolean success = newFile.createNewFile();
        System.out.println(success + "done");
        return newFile;

//        }
//        System.out.println("请输入文件名：");
//        String fileName = scanner.nextLine().trim();
//        File f = new File("." + File.separator + fileName + ".txt");
//        if (f.isFile()) {
//            System.out.println("目标文件存在，删除：" + f.delete());
//        }
//        //file.delete()  file.createNewFile()
//        System.out.println(f.createNewFile());
//        return f;
    }
}
