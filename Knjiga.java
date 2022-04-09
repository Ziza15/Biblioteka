/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteka;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zivko
 */
public class Knjiga {
    private String idKnjige, naziv, autor, zanr, idIzdavackeKuce;
    private int dostpuaKolicina, god_izdavanja, idIzdate;

    
    private String nazivIzdavacke, nazivIzdate;

    public Knjiga() {
        this.idKnjige = "";
        this.naziv = "";
        this.autor = "";
        this.zanr = "";
        this.god_izdavanja = 0;
        this.dostpuaKolicina = 0;
        this.idIzdavackeKuce = "";
        this.nazivIzdavacke = "";
    }
    
    public Knjiga(String idKnjige, String naziv, String autor, String zanr, int god_izdavanja, int dostpuaKolicina,String idIzdavackeKuce) {
        this.idKnjige = idKnjige;
        this.naziv = naziv;
        this.autor = autor;
        this.zanr = zanr;
        this.god_izdavanja = god_izdavanja;
        this.dostpuaKolicina = dostpuaKolicina;
        this.idIzdavackeKuce = idIzdavackeKuce;
    }
    public Knjiga(int idIzdate, String nazivIzdate) {
        this.idIzdate = idIzdate;
        this.nazivIzdate = nazivIzdate;
    }
     
    public String getIdIzdavackeKuce() {
        return idIzdavackeKuce;
    }

    public String getIdKnjige() {
        return idKnjige;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getAutor() {
        return autor;
    }

    public String getZanr() {
        return zanr;
    }

    public int getDostpuaKolicina() {
        return dostpuaKolicina;
    }

    public int getGod_izdavanja() {
        return god_izdavanja;
    }

    public String getNazivIzdavacke() {
        return nazivIzdavacke;
    }

    public void setDostpuaKolicina(int dostpuaKolicina) {
        this.dostpuaKolicina = dostpuaKolicina;
    }

    public int getIdIzdate() {
        return idIzdate;
    }

    public String getNazivIzdate() {
        return nazivIzdate;
    }
    
    public static boolean proveraIdKnjige(ArrayList<Knjiga> listaKnjiga, String proveraID){
        for (Knjiga k: listaKnjiga) {
            if (k.getIdKnjige().trim().equals(proveraID)) {
                return true;
            }
        }
        return false;
    }
    public static boolean proveraIdKnjige1(ArrayList<Knjiga> listaKnjiga, String idKnjige){
        for (Knjiga k: listaKnjiga) {
            if (k.getIdKnjige().trim().equals(idKnjige)) {
                return false;
            }
        }
        return true;
    }
    public static boolean proveraIzdateKnjige(ArrayList<Knjiga> listaIzdatih, int provera){
        for (Knjiga li: listaIzdatih) {
            if (li.getIdIzdate()==provera) {
                return true;
            }
        }
        return false;
    }
    public String UzmiNazivIzdavackeKuce(ArrayList<IzdavackaKuca> listaKuca){
        String nazivKuce="";
        for (IzdavackaKuca ik: listaKuca) {
            if (ik.getId().equals(this.idIzdavackeKuce)) {
                nazivKuce=ik.getNaziv();
            }else   nazivKuce="Nepoznato";
        }
        return nazivKuce;
    }
     
    public String toString(ArrayList<IzdavackaKuca> listaKuca) {
        return idKnjige+" - "+naziv+" - "+autor+" - "+zanr+" - "+"broj knjiga na stanju: "+dostpuaKolicina+" -  izdavacka kuca: "+UzmiNazivIzdavackeKuce(listaKuca);
    }
    
    public static ArrayList<Knjiga> ispisKnjiga(String imeDadoteke) {
        ArrayList<Knjiga> listaKnjiga = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(imeDadoteke);
            Scanner sc = new Scanner(fis);
            while (sc.hasNext()) {
                String[] podaci = sc.nextLine().split("-");
                 
                listaKnjiga.add(new Knjiga(podaci[0].trim(), podaci[1].trim(), podaci[2].trim(), podaci[3].trim(),Integer.parseInt(podaci[4].trim()),Integer.parseInt(podaci[5].trim()),podaci[6]));

            }
            sc.close();
            fis.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IzdavackaKuca.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IzdavackaKuca.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaKnjiga;
    }
    
    public static ArrayList<Knjiga> ipsisIzdatih(String imeDadoteke){
        ArrayList<Knjiga> listaIzdatih = new ArrayList<>();
        try {
            FileInputStream fis= new FileInputStream("Spisak.txt");
            Scanner sc = new Scanner(fis);
            while (sc.hasNext()) {
                int broj = sc.nextInt();
                String red= sc.nextLine();
                listaIzdatih.add(new Knjiga(broj,red));
            }
            sc.close();
            fis.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Biblioteka.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Knjiga.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaIzdatih;
    }
    
    
    
}
