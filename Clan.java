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
public class Clan extends Informacije{
    public int uclanjenOd;

    public Clan(String ime, String prezime, String korisnickoIme, String sifra, String email,int uclanjenOd) {
        super(ime, prezime, korisnickoIme, sifra, email);
        this.uclanjenOd = uclanjenOd;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public int getUclanjenOd() {
        return uclanjenOd;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getSifra() {
        return sifra;
    }

    public String getEmail() {
        return email;
    }

    
    
    public static boolean  proveraClana(ArrayList<Clan> listaClanova, String unetoKorIme){
        for (Clan c: listaClanova) {
            if (c.getKorisnickoIme().trim().equals(unetoKorIme)) {
                return true;
            }
        }return false;
    };
    @Override
    public boolean proveraPrijave(String korisnickoIme, String sifra) {
        if (korisnickoIme.equals(this.korisnickoIme) && sifra.equals(this.sifra)) {
            return true;
        } else {
            return false;
        }
    }

    public static ArrayList<Clan> ispisiKorisnike(String imeDadoteke) {
        ArrayList<Clan> listaClanova = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream(imeDadoteke);
            Scanner sc = new Scanner(fis);

            while (sc.hasNext()) {
                String ime = sc.next();
                String prezime = sc.next();
                String korisnickoIme = sc.next();
                String sifra = sc.next();
                int uclanjenOd = sc.nextInt();
                String email = sc.nextLine().trim();

                listaClanova.add(new Clan(ime, prezime, korisnickoIme, sifra, email, uclanjenOd));
            }
            sc.close();
            fis.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Radnik.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Clan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaClanova;
    }

    @Override
    public String toString() {
        return ime+" "+prezime+" "+ korisnickoIme +" "+email+" "+uclanjenOd;
    }
    
}
