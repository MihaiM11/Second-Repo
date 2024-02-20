package org.example.data;

import org.example.model.Curs;
import org.example.model.Student;
import org.example.utils.dataSaverUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class dataSaver {
    private void saveCourses (Map<Curs, List<Student>> map) throws IOException {
        Set<Curs> set=map.keySet();
        String continut="";
        for(Curs curs:set){
            continut=continut.concat(curs.toString().concat("\n"));

        }
        System.out.println(continut);

        dataSaverUtils.writeFile(dataSaverUtils.SAVE_COURSE_FILE_PATH,continut);

    }
    private void saveStudenti(Map<Curs,List<Student>> map) throws IOException {
        String continut="";
        for(List<Student> students: map.values()){
            for(Student student:students){
                continut=continut.concat(student.toString().concat("\n"));
            }
        }

        dataSaverUtils.writeFile(dataSaverUtils.SAVE_STUDENT_FILE_PATH,continut);

    }

    private void saveMaping(Map<Curs,List<Student>> map) throws IOException {
        String continut="";
        for(Map.Entry<Curs,List<Student>> inregistrare: map.entrySet()){
            int cursId=inregistrare.getKey().getIdCurs();
            for (Student student: inregistrare.getValue()){
                int studentId=student.getIdStudent();
                continut=continut.concat(cursId+ "," + studentId + "\n");
            }
 
        }

        dataSaverUtils.writeFile(dataSaverUtils.SAVE_MAPING_FILE_PATH,continut);

    }

    public void saveData(Map<Curs,List<Student>> map) throws IOException {
        saveCourses(map);
        saveStudenti(map);
        saveMaping(map);
    }
}
