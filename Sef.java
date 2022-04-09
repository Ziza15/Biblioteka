/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteka;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zivko
 */
public class Sef extends Informacije implements MogucnostDodavanjaClana, MogucnostDodavanjaKnjige,MogucnosDodavanjaIzdavackeKuce, MogucnostDodavanjaRadnika{

    public Sef(String ime, String prezime, String korisnickoIme, String sifra, String email) {
        super(ime, prezime, korisnickoIme, sifra, email);
    }

    public Sef() {
    }
    
    @Override
    public boolean proveraPrijave(String korisnickoIme, String sifra) {
        if (korisnickoIme.equals(this.korisnickoIme) && sifra.equals(this.sifra)) {
            return true;
        } else
            return false;
        
    }
    public static ArrayList<Sef> procitajSefa(String imeDadoteke){
        ArrayList<Sef> listaSefova = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(imeDadoteke);
            Scanner sc = new Scanner(fis);

            while (sc.hasNext()) {
                String ime = sc.next();
                String prezime = sc.next();
                String korisnickoIme = sc.next();
                String sifra = sc.next();
                String email = sc.nextLine().trim();

                listaSefova.add(new Sef(ime, prezime, korisnickoIme, sifra, email));
            }
            sc.close();
            fis.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Sef.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Sef.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaSefova;
    }
    public boolean proveraKorImena(ArrayList<Clan> listaClanova, String korIme) {
        for (Clan c : listaClanova) {
            if (c.getKorisnickoIme().trim().equals(korIme)) {
                return false;
            }
        }
        return true;
    }
    @Override
    public void MogucnostDodavanjaClana() {
        ArrayList<Clan> listaClanova = Clan.ispisiKorisnike("clanovi.txt");
        Scanner sc = new Scanner(System.in);
        System.out.println("Unesite podatke novog clana!");
        System.out.println("Unesite ime");
        String ime = sc.nextLine();
        System.out.println("Unesite prezime");
        String prezime = sc.nextLine();
        String korIme;
        do {
            System.out.println("Unesite njegovo korisnicko ime: ");
            korIme = sc.nextLine();
            if (!(proveraKorImena(listaClanova, korIme))) {
                System.out.println("Uneli ste korisnicko ime koje već postoji! Pokusajte ponovo!");
            }
        } while (!(proveraKorImena(listaClanova, korIme)));
        System.out.println("Unesite sifru korisniku: ");
        String sifra = sc.nextLine();
        System.out.println("Unesite njegov email: ");
        String email = sc.nextLine();
        int uclanjenOd;
        System.out.println("Godina uclanjenja: ");
        while (true) {
            if (sc.hasNextInt()) {
                uclanjenOd = sc.nextInt();
                break;
            }
            System.out.println("Unos mora biti u int formatu");
            sc.nextLine();
        }

        String imeDadoteke = "clanovi.txt";
        Clan c = new Clan(ime, prezime, korIme, sifra, email, uclanjenOd);
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(imeDadoteke, true)));

            pw.println("\n" + ime + " " + prezime + " " + korIme + " " + sifra + " " + uclanjenOd + " " + email);

            pw.flush();
            pw.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Nismo pronasli dadoteku!");
        } catch (IOException ex) {
            System.out.println("Problem pri zatovranju dadoteke");
        }
    }

    @Override
    public void dodajKnjig() {
        Scanner sc  = new Scanner(System.in);
        ArrayList<Knjiga> listaKnjiga= Knjiga.ispisKnjiga("knjige.txt");
        String idKnjige;
        do {
            System.out.println("Uneiste id knjige: ");
            idKnjige = sc.nextLine();
            if (!(Knjiga.proveraIdKnjige1(listaKnjiga, idKnjige))) {
                System.out.println("ID Knjige koje ste unijeli vec postoji!Pokusajte ponovo!");
            }
        } while (!(Knjiga.proveraIdKnjige1(listaKnjiga, idKnjige)));
        System.out.println("Unesite naziv knjige:");
        String nazivKnjige= sc.nextLine();
        System.out.println("Unesite autora: ");
        String autor= sc.nextLine();
        System.out.println("Unesite žanr: ");
        String zanr = sc.nextLine();
        System.out.println("Unesite id preduzeca");
        String idPreduzeca= sc.nextLine();
        System.out.println("Unesite godinu izdavanja: ");
        int godIzdavanja;
        while (true) {
            if (sc.hasNextInt()) {
             godIzdavanja = sc.nextInt();
             break;
            }
            System.out.println("Unos mora biti u int formatu");
            sc.nextLine();
        }
        System.out.println("Unesite stanje knjiga: ");
        int stanjeKnjiga;
        while (true) {
            if (sc.hasNextInt()) {
                stanjeKnjiga = sc.nextInt();
                break;
            }
            System.out.println("Unos mora biti u int formatu");
            sc.nextLine(); 
        }
        String imeDadoteke = "knjige.txt";
        Knjiga k = new Knjiga(idKnjige, nazivKnjige, autor, zanr, godIzdavanja,stanjeKnjiga, idPreduzeca.trim());
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(imeDadoteke, true)));

            pw.println(idKnjige + "-" + nazivKnjige + "-" + autor + "-" + zanr + "-" + godIzdavanja + "-" + stanjeKnjiga+"-"+idPreduzeca);
            
            pw.flush();
            pw.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Nismo pronasli dadoteku!");
        } catch (IOException ex) {
            System.out.println("Problem pri zatovranju dadoteke");
        }
    }

    @Override
    public void dodajIzdavackuKucu() {
        Scanner unos = new Scanner(System.in);
        ArrayList<IzdavackaKuca> listaIzdavackihKuca = IzdavackaKuca.ispisKuca("izdavackeKuce.txt");
        String idKuce;
        do {
            System.out.println("Unesite ID Izdavacke kuce: ");
            idKuce = unos.nextLine();
            if (!IzdavackaKuca.proveraId(listaIzdavackihKuca, idKuce)) {
                System.out.println("ID izdavacke kuce koji ste unijeli vec postoji! Pokusajte ponovo!");
            }

        } while (!IzdavackaKuca.proveraId(listaIzdavackihKuca, idKuce));
        System.out.println("Unesite naziv izdavacke kuce: ");
        String naziv = unos.nextLine();
        System.out.println("Unesite adresu izdavacke kuce: ");
        String adresa = unos.nextLine();
        System.out.println("Unesite telefonski broj izdavacke kuce: ");
        String brTel = unos.nextLine();
        System.out.println("Uneiste email adresu izdavacke kuce: ");
        String email = unos.nextLine();
        System.out.println("Unesite godinu pocetka saradnje: ");
        int godSaradnje;
        while (true) {
            if (unos.hasNextInt()) {
                godSaradnje = unos.nextInt();
                break;
            }
            System.out.println("Unos mora biti u int formatu");
            unos.nextLine();
        }
        String imeDatoteke = "IzdavackeKuce.txt";

        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(imeDatoteke, true)));
            pw.println(idKuce + "-" + naziv + "-" + adresa + "-" + brTel + "-" + email + "-" + godSaradnje);
            pw.flush();
            pw.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Nismo pronasli dadoteku!");
        } catch (IOException ex) {
            System.out.println("Problem pri zatovranju dadoteke");
        }
    }

    @Override
    public void dodajRadnika() {
        ArrayList<Radnik> listaRadnika = Radnik.ispisiKorisnike("radnici.txt");
        Scanner sc = new Scanner(System.in);
        System.out.println("Unesi ime: ");
        String ime = sc.nextLine();
        System.out.println("Unesite prezime: ");
        String prezime = sc.nextLine();
        String korIme;
        do {
            System.out.println("Unesite korisnicko ime: ");
            korIme= sc.nextLine();
            if (!(Radnik.proveraKorImenaRadnika(listaRadnika, korIme))) {
                System.out.println("Uneli ste korisnicko ime koje vec postoji! Pokusajte ponovo");
            }
            
        } while (!(Radnik.proveraKorImenaRadnika(listaRadnika, korIme)));
        System.out.println("Unesite sifru radniku: ");
        String sifra = sc.nextLine();
        System.out.println("Unesite email adresu: ");
        String email = sc.nextLine();
        int zaposlenOd;
        System.out.println("Godina zaposlenja: ");
        while (true) {
            if (sc.hasNextInt()) {
                zaposlenOd = sc.nextInt();
                break;
            }
            System.out.println("Unos mora biti u int formatu");
            sc.nextLine();
        }        
        String imeDadoteke = "radnici.txt";
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(imeDadoteke, true)));
            pw.println(ime+" "+prezime+" "+korIme+" "+sifra+" "+zaposlenOd+" "+email);
            pw.flush();
            pw.close();
        } catch (IOException ex) {
            Logger.getLogger(Sef.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
