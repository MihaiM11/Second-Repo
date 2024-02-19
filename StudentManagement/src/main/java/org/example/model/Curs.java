package org.example.model;

import java.time.LocalDate;
import java.util.Objects;

public class Curs {
    private int idCurs;
    private String nume;
    private double pret;
    private LocalDate dataInceput;

    public Curs(int idCurs, String nume, double pret, LocalDate dataInceput) {
        this.idCurs = idCurs;
        this.nume = nume;
        this.pret = pret;
        this.dataInceput = dataInceput;
    }

    public int getIdCurs() {
        return idCurs;
    }

    public void setIdCurs(int idCurs) {
        this.idCurs = idCurs;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public LocalDate getDataInceput() {
        return dataInceput;
    }

    public void setDataInceput(LocalDate dataInceput) {
        this.dataInceput = dataInceput;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curs curs = (Curs) o;
        return idCurs == curs.idCurs && Objects.equals(nume, curs.nume) && Objects.equals(pret, curs.pret) && Objects.equals(dataInceput, curs.dataInceput);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCurs, nume, pret, dataInceput);
    }

    @Override
    public String toString() {
        return  idCurs  + "," +
                  nume  + "," +
                 pret   + "," +
                 dataInceput  ;

    }
}
