package org.example.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class dataSaverUtils {
    public static final String SAVE_COURSE_FILE_PATH="export_cursuri.csv";
    public static final String SAVE_STUDENT_FILE_PATH="export_student.csv";
    public static final String SAVE_MAPING_FILE_PATH="export_mapare.csv";

    public static void writeFile(String path, String continut) throws IOException {
        Path path1= Paths.get(path);

      //  File file = new File(path1.toUri());
        Files.write(path1,continut.getBytes());

    }
}
