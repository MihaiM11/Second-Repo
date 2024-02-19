package org.example.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class dataUtils {
    public static final String CURS_FILE_PATH= "C:\\Users\\acer\\Desktop\\StudentManagement\\StudentManagement\\cursuri.csv";
    public static final String STUDENT_FILE_PATH= "C:\\Users\\acer\\Desktop\\StudentManagement\\StudentManagement\\studenti.csv";
    public static final String MAPPING_FILE_PATH= "C:\\Users\\acer\\Desktop\\StudentManagement\\StudentManagement\\mapari.csv";

    public static List<String> readFile(String filePathStr) throws IOException {
        Path path= Paths.get(filePathStr);

        return Files.readAllLines(path);
    }
}
