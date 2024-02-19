package org.example.data;

import org.example.Exceptii.BugetInvalidException;
import org.example.model.Curs;
import org.example.model.Student;
import org.example.utils.dataUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class dataLoader {
    private Map<Curs, List<Student>> mapCursuriSiStudenti;
    private List<Student> studentiLista;

    public dataLoader(){
        mapCursuriSiStudenti=new HashMap<>();
        studentiLista=new ArrayList<>();
    }

    public void loadData() throws IOException, BugetInvalidException {
        loadCursuri();
        loadStudenti();
        mapStudentiLaCursuri();

    }

    private void mapStudentiLaCursuri() throws IOException, BugetInvalidException {
        List<String> map=dataUtils.readFile(dataUtils.MAPPING_FILE_PATH);

        for(String mapare:map){
            String[] iduri=mapare.split(",");
            int idStudent=Integer.parseInt(iduri[0]);
            int idCurs=Integer.parseInt(iduri[1]);
            Student studentGasit=null;
            Curs cursGasit=null;

            for(Student student: studentiLista){ // aici se verifica daca studentul se regaseste pe lista de mai sus
                if(student.getIdStudent()==idStudent){
                    studentGasit=student;
                    break;

                }
            }

            for(Curs curs:mapCursuriSiStudenti.keySet()){ // asa luam doar key-le mapului , in cazul nostru id-ul cursului
                // se verifica daca cursul se regaseste in lista de mai sus

                if(curs.getIdCurs()==idCurs){
                    cursGasit=curs;
                    break;
                }

            }

            if(studentGasit!=null && cursGasit!=null){
              //  System.out.println("Am gasit un match intre student si curs");



                double bugetStudent=studentGasit.getBuget();
                double pretCurs=cursGasit.getPret();



                double bugetRamas=bugetStudent-pretCurs;

                try{
                    studentGasit.setBuget(bugetRamas);
                    mapCursuriSiStudenti.get(cursGasit).add(studentGasit);

                }catch (BugetInvalidException e){
                    System.out.println("Studentul nu are buget eficient, nu s-a facut inrolarea la curs");
                }








            }


        }
    }

    private void loadStudenti() throws IOException {
        List <String> studenti=dataUtils.readFile(dataUtils.STUDENT_FILE_PATH);
       // System.out.println("\n Lista studentilor inscrisi este: ");

        for(String student:studenti){


            String [] listaStudenti=student.split(",");
            Student studentNou=creareStudent(listaStudenti);
           // System.out.println(studentNou);
            studentiLista.add(studentNou);
        }
    }

    private void loadCursuri() throws IOException {
        List<String> cursuri= dataUtils.readFile(dataUtils.CURS_FILE_PATH);


        for (String curs: cursuri){
            String[] dateCurs=curs.split(",");
            Curs cursNou=creareCurs(dateCurs);
         //   System.out.println(cursNou);

            mapCursuriSiStudenti.put(cursNou,new ArrayList<>());


        }
      //  System.out.println("\n Cursuri si studenti: ");

    }

    private Curs creareCurs(String[] dateCurs){
        int id=Integer.parseInt(dateCurs[0]);
        String name=dateCurs[1];
        double pret=Double.parseDouble(dateCurs[2]);
        LocalDate dataInceput=LocalDate.parse(dateCurs[3]);
        return new Curs(id,name,pret,dataInceput);
    }

    private Student creareStudent(String[] listaStudenti){
        int id=Integer.parseInt(listaStudenti[0]);
        String nume=listaStudenti[1];
        double buget=Double.parseDouble(listaStudenti[2]);

        return new Student(id,nume,buget);
    }

    public Map<Curs,List<Student>> getMapCursuriSiStudenti (){ // folosim pentru listarea maparilor
        return mapCursuriSiStudenti;
    }

    public static void afisareCursuri(Map<Curs,List<Student>> mapare){
        Set<Curs> listaCursuri=mapare.keySet();
        System.out.println(listaCursuri);

    }

    public static void adauagaCurs(Curs cursNou,Map <Curs,List<Student>> mapare){
        mapare.put(cursNou,new ArrayList<>());
    }


}
