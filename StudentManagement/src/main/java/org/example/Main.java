package org.example;

import org.example.Exceptii.BugetInvalidException;
import org.example.data.dataLoader;
import org.example.data.dataSaver;
import org.example.model.Curs;
import org.example.model.Student;

import java.io.IOException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, BugetInvalidException {
        dataLoader dl=new dataLoader();
        dl.loadData();

        Map dataMap= new HashMap(dl.getMapCursuriSiStudenti());

       // System.out.println(dl.getMapCursuriSiStudenti());


        Scanner scanner= new Scanner(System.in);


        System.out.println("Optiuni:" +
                "\n 0. Ies din program" +
                "\n 1. Afiseaza cursuri" +
                "\n 2. Introduceti un curs nou " +
                "\n 3. Introduceti un student nou si inrolati-l la curs" +
                "\n 4. Cautati un studebt dupa nume " +
                "\n 5. Afiseaza studentii si cursul la care participa" +
                "\n 6. Salvare date cursuri si studenti pentru export !");



        while (true) {
            System.out.println("Alege o optiune :");
            int optiune= Integer.parseInt(scanner.nextLine());

            switch (optiune){
                case 0:
                    System.exit(0);
                    break;

                case 1:
                    System.out.println("Cursurile care de desfasoara in acest moment sunt :");
                    dataLoader.afisareCursuri(dataMap);
                    break;

                case 2:
                    System.out.println("Introduceti cursuri noi ");
                    System.out.println("Introduceti id-ul cursului:");
                    int idCurs=Integer.parseInt(scanner.nextLine());

                    System.out.println("Introduceti numele cursului");
                    String nume=scanner.nextLine();

                    System.out.println("Introduceti pretului cursului:");
                    double pretCurs=Double.parseDouble(scanner.nextLine());

                    System.out.println("Introduceti data de inceput dd//MM/yyyy :");
                    String data= scanner.nextLine();

                    DateTimeFormatter formater=DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate dataCurs=LocalDate.parse(data,formater);

                    Curs cursNou=new Curs(idCurs,nume,pretCurs,dataCurs);
                    System.out.println("S-a introdus urmatorul curs:");
                    System.out.println(cursNou);

                    dataLoader.adauagaCurs(cursNou,dataMap);
                    dataLoader.afisareCursuri(dataMap);



                    break;

                case 3:

                    System.out.println("Cursurile disponibile in momentul de fata sunt: !!") ;

                    int cursDisponibil=0;
                    for(Map.Entry<Curs,List<Student>> inregistrate : dl.getMapCursuriSiStudenti().entrySet()){

                        if(inregistrate.getValue().size()<9){
                            cursDisponibil++;
                            System.out.println("Curs disponibil" + inregistrate.getKey() );
                        }

                    }
                    System.out.println("\n");

                    if(cursDisponibil==0){
                        System.out.println("Nu exista vreun curs disponibil");
                    }

                    System.out.println("Introduceti numele cursului la care doriti sa se efectueze inrolarea: ");
                    String numeCurs=scanner.nextLine();
                    LocalDate now=LocalDate.now();

                    Curs cursGasit=null;

                    for(Curs curs : dl.getMapCursuriSiStudenti().keySet()) {
                        if (curs.getNume().equals(numeCurs) && now.isBefore(curs.getDataInceput())) {
                            System.out.println("Am gasit cursul " + curs);
                            cursGasit = curs;
                            break;

                        }
                    }
                    if (cursGasit==null){
                        System.out.println("Acest curs nu exista");
                    }

                    System.out.println("Introduceti id-ul studentului :");
                    int idStudentNou = Integer.parseInt(scanner.nextLine());

                    System.out.println("Introduceti numele studentului");
                    String numeStudentNou= scanner.nextLine();

                    System.out.println("Introduceti bugetul studentului:");
                    double bugetStudentNou=Double.parseDouble(scanner.nextLine());

                    Student studentNou=new Student(idStudentNou,numeStudentNou,bugetStudentNou);
                    System.out.println("Ati introdus urmatorul student" + studentNou);
                    double bugetStudent=studentNou.getBuget();
                    double bugetCurs=cursGasit.getPret();
                    double bugetRamas= bugetStudent-bugetCurs;
                    try{
                        studentNou.setBuget(bugetRamas);

                    }catch (BugetInvalidException e){
                        System.out.println("Studentul nu are buget suficient");
                        System.out.println("Nu s-a efectuat inrolarea la curs ");
                        break;
                    }


                    dl.getMapCursuriSiStudenti().get(cursGasit).add(studentNou);
                    System.out.println(dataMap);


                    break;

                case 4 :
                    System.out.println("Cautam un student");
                    System.out.println("Introduceti numele studentului:");
                    String numeIntrodus=scanner.nextLine();
                    for(List<Student> listaStudenti : dl.getMapCursuriSiStudenti().values()){
                        for(Student student :listaStudenti){
                            if(student.getNameStudent().contains(numeIntrodus)){
                                System.out.println("S-au gasit urmatorii:  " + student);
                            }
                        }
                    }
                    break;

                case 5 :
                    System.out.println("Afisam studentii si cursurile");
                    for(Curs curs : dl.getMapCursuriSiStudenti().keySet()){
                        System.out.println("La cusrsul " + curs.getNume() + " participa urmatorii studenti ") ;
                        for(Student student : dl.getMapCursuriSiStudenti().get(curs)){
                            System.out.println(student.getNameStudent());
                        }
                        System.out.println("\n");

                        //1:30
                    }
                    break;

                case 6 :
                    System.out.println("Salvare date cursuri si studenti pentru export:");

                    dataSaver ds= new dataSaver();
                    ds.saveData(dl.getMapCursuriSiStudenti());
                    break;

                default:
                    System.out.println("Aceasta optiune nu exista!!");
            }
        }


    }
}