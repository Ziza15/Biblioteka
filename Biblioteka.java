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
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zivko
 */
public class Biblioteka {
    public static void main(String[] args) {
            
            pocetniMeni();



    }
 
    static void pocetniMeni(){
        Scanner unos = new Scanner(System.in);
        int opcija;
        System.out.println("Dobrodsli u biblioteku GlOBUS\n\n");
        System.out.println("1. Prijavite se kao šef\n");
        System.out.println("2. Priajvite se kao radnik\n");
        System.out.println("3. Prijavite se kao član\n\n");
        System.out.println("4. Ako niste učlanjeni posaljite zahtjev za učlanjenje");
        System.out.println("0. Izlaz iz programa");
        
        System.out.println("Uensite opciju: ");
        while (true) {
            if (unos.hasNextInt()) {
                opcija = unos.nextInt();
                break;
            }
            System.out.println("Niste unijeli pravilno opciju! Opcija mora biti broj.\nPOKUSAJTE PONOVO");
            unos.nextLine();
        }
        switch (opcija) {
            case 1:
                prijavaSefa(); 
                meniSef();
                break;
            case 2:
                prijavaRadnik();
                meniRadnik();
                break;
            case 3:
                prijavaClana();
                meniClan();
                break;
            case 4:
                zahtjevZaClanstvo();
                pocetniMeni();
                break;
            
            case 0: 
                System.out.println("Dovidjenja");
                System.exit(0);
                break;
            default:
                pocetniMeni();
        }
    }
    static void prijavaRadnik(){
        ArrayList<Radnik> listaRadnika = Radnik.ispisiKorisnike("radnici.txt");
        Scanner unos = new Scanner(System.in);
        boolean log = false;
        while (true) {
            System.out.println("Unesite vase korisnicko ime: ");
            String korIme = unos.nextLine();
            System.out.println("Unesite vasu sifru: ");
            String sifra = unos.nextLine();
            for (Radnik r : listaRadnika) {
                if (r.proveraPrijave(korIme, sifra) == true) {
                    try {
                        PrintWriter pw = new PrintWriter("trenutniKorisnik.txt");
                        pw.println(r.getKorisnickoIme());
                        
                        pw.flush();
                        pw.close();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Biblioteka.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    log = true;
                    break;
                }

            }
            if (log) {
                System.out.println("Uspjesno ste se prijavili");
                break;
            } else {
                System.out.println("Niste se uspjesno prijavili. Pokusajte ponovo");
            }

        }
    }
    static void prijavaClana(){
        ArrayList<Clan> listaClanova = Clan.ispisiKorisnike("clanovi.txt");
        Scanner unos = new Scanner(System.in);
        boolean log = false;
        while (true) {
            System.out.println("Unesite vase korisnicko ime: ");
            String korIme = unos.nextLine();
            System.out.println("Unesite vasu sifru: ");
            String sifra = unos.nextLine();
            for (Clan c : listaClanova) {
                if (c.proveraPrijave(korIme, sifra) == true) {
                    try {
                        PrintWriter pw = new PrintWriter("trenutniKorisnik.txt");
                        pw.println(korIme);
                       
                        
                        pw.flush();
                        pw.close();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Biblioteka.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    log = true;
                    break;
                }

            }
            if (log) {
                System.out.println("Uspjesno ste se prijavili");
                break;
            } else {
                System.out.println("Niste se uspjesno prijavili. Pokusajte ponovo");
            }

        }
    }
    static void prijavaSefa(){
        ArrayList<Sef> listaSefova= Sef.procitajSefa("sefovi.txt");
        Scanner unos = new Scanner(System.in);
        boolean log= false;
        while (true) {
            System.out.println("Unesite vase korisnicko ime: ");
            String korIme = unos.nextLine();
            System.out.println("Unesite vasu sifur: ");
            String sifra = unos.nextLine();
            for (Sef s : listaSefova) {
                if (s.proveraPrijave(korIme, sifra) == true) {
                    try {
                        PrintWriter pw = new PrintWriter("trenutniKorisnik.txt");
                        pw.println(korIme);

                        pw.flush();
                        pw.close();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Biblioteka.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    log = true;
                    break;
                }

            }
            if (log) {
                System.out.println("Uspjesno ste se prijavili");
                break;
            } else {
                System.out.println("Niste se uspjesno prijavili. Pokusajte ponovo");
            }
        }
    }
    static void zahtjevZaClanstvo(){
        ArrayList<Clan> listaClanova = Clan.ispisiKorisnike("clanovi.txt");
        Scanner unos = new Scanner(System.in);
        System.out.println("Unesite svoje podatke potrebne za kreiranje naloga");
        System.out.println("Uneiste ime:");
        String ime = unos.nextLine();
        System.out.println("Unesite prezime");
        String prezime= unos.nextLine();
        String korIme;
        do {
            System.out.println("Unesite korisnicko ime: ");
            korIme = unos.nextLine();
            if ((Clan.proveraClana(listaClanova, korIme))) {
                System.out.println("Uneli ste korisnicko ime koje već postoji! Pokusajte ponovo!");
            }
        } while ((Clan.proveraClana(listaClanova, korIme)));
        System.out.println("Unesite email");
        String email= unos.nextLine();
        String imeDatoteke = "noviZahtjeviZaClanstvo.txt";
        try {   
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(imeDatoteke, true)));
            pw.println("Ime: "+ime+" Prezime: "+prezime+" Korisnicko ime: "+korIme+" Email: "+email);
            pw.flush();
            pw.close();
        } catch (IOException ex) {
            System.out.println("Greska sa pri otvarnju datoteke");
        }
        
        System.out.println("Uspjesno ste poslali zahtejev!\nSifra posle pravljenja naloga ce biti 12345678, mozete je promijeniti prilikom prijavljivalnja na sistem");
        }
    static void citanjeZahtejva(){
        try {
            FileInputStream fis = new FileInputStream("noviZahtjeviZaClanstvo.txt");
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                String red= sc.nextLine();
                System.out.println(red);
                
            }
            sc.close();
            fis.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Problem prilikom otvaranja datoteke");
        } catch (IOException ex) {
            System.out.println("Greška prilikom zatvranja datoteke");
        }
        
    }
    static void meniSef(){
        Scanner unos = new Scanner(System.in);
        int opcija;
        System.out.println("1. Pregled knjiga");
        System.out.println("2. Dodaj knjigu");
        System.out.println("3. Pregled članova");
        System.out.println("4. Dodaj član");
        System.out.println("5. Pregled izdavaćkih kuća");
        System.out.println("6. Dodaj izdavačku kuču");
        System.out.println("7. Pregled radnika");
        System.out.println("8. Dodaj radnika");
        System.out.println("9. Spisak izdatih knjiga");
        System.out.println("10. Ukloni člana");
        System.out.println("11. Ukloni knjigu");
        System.out.println("12. Ukloni izdavačku kuću");
        System.out.println("13. Ukloni radnika");
        System.out.println("0. Vratite se na početni meni");
        while (true) {
            if (unos.hasNextInt()) {
                opcija = unos.nextInt();
                break;
            }
            System.out.println("Niste unijeli pravilno opciju! Opcija mora biti broj.\nPOKUSAJTE PONOVO");
            unos.nextLine();
        }        
        switch (opcija) {
            case 1:
                pregledKnjiga();
                povratakNaMeniSefa();
                break;
            case 2:
                dodajKnjigu();
                povratakNaMeniSefa();
                break;
            case 3:
                pregledClanova();
                povratakNaMeniSefa();
                break;
            case 4:
                citanjeZahtejva();
                dodajClana();
                povratakNaMeniSefa();
                break;  
            case 5:
                pregledIzdavackihKuca();
                povratakNaMeniSefa();
                break;
            case 6:
                dodajIzdavackuKucu();
                povratakNaMeniSefa();
                break;
            case 7:
                pregledRadnik();
                povratakNaMeniSefa();
                break;
            case 8:
                dodajRadnika();
                povratakNaMeniSefa();
                break;
            case 9:
                pregledtIzdatihKnjiga();
                povratakNaMeniSefa();
                break;
            case 10:
                ukloniClana();
                povratakNaMeniSefa();
                break;
            case 11:
                ukloniKnjigu();
                povratakNaMeniSefa();
                break;
            case 12:
                ukloniIzdavackuKucu();
                povratakNaMeniSefa();
                break; 
            case 13:
                ukloniRadnika();
                povratakNaMeniSefa();
                break;
            case 0:
                pocetniMeni();
                break;
            default:
                meniSef();
        }
    }
    static void meniRadnik(){
        Scanner unos = new Scanner(System.in);
        int opcija;
        System.out.println("1. Pregled knjiga");
        System.out.println("2. Dodaj knjigu");
        System.out.println("3. Pregled članova");
        System.out.println("4. Dodaj član");
        System.out.println("5. Pregled izdavaćkih kuća");
        System.out.println("6. Dodaj izdavačku kuču");
        System.out.println("7. Spisak izdatih knjiga");
        System.out.println("8. Ukloni člana");
        System.out.println("9. Ukloni knjigu");
        System.out.println("10. Ukloni izdavačku kuću");
        System.out.println("0. Vratite se na početni meni");
        while (true) {
            if (unos.hasNextInt()) {
                opcija= unos.nextInt();
                break;
            }
            System.out.println("Niste unijeli pravilno opciju! Opcija mora biti broj.\nPOKUSAJTE PONOVO");
            unos.nextLine();
        }
        switch (opcija) {
            case 1:
                pregledKnjiga();
                povratakNaMeniRadnik();
                break;
            case 2:
                dodajKnjigu();
                povratakNaMeniRadnik();
                break;
            case 3:
                pregledClanova();
                povratakNaMeniRadnik();
                break;
            case 4:
                citanjeZahtejva();
                dodajClana();
                povratakNaMeniRadnik();
                break;
            case 5:
                pregledIzdavackihKuca();
                povratakNaMeniRadnik();
                break;
            case 6:
                dodajIzdavackuKucu();
                povratakNaMeniRadnik();
                break;
            case 7:
                pregledtIzdatihKnjiga();
                povratakNaMeniRadnik();
                break;
            case 8:
                ukloniClana();
                povratakNaMeniRadnik();
                break;
            case 9:
                ukloniKnjigu();
                povratakNaMeniRadnik();
                break;
            case 10:
                ukloniIzdavackuKucu();
                povratakNaMeniRadnik();
                break;
            case 0:
                pocetniMeni();
                break;
            default:
                meniRadnik();
        }
    }
    static void dodajClana(){
        ArrayList<Clan> listaClanova = Clan.ispisiKorisnike("clanovi.txt");
        Radnik r = new Radnik();
        r.MogucnostDodavanjaClana();
        
    }
    static void dodajRadnika(){
        ArrayList<Radnik> listaRadnika = Radnik.ispisiKorisnike("radnici.txt");
        Sef s = new Sef();
        s.dodajRadnika();
    }    
    static void dodajKnjigu(){
        ArrayList<Knjiga> listaKnjiga = Knjiga.ispisKnjiga("knjige.txt");
        Radnik r = new Radnik();
        r.dodajKnjig();
        
    }
    static void dodajIzdavackuKucu(){
        ArrayList<IzdavackaKuca> listaIzdavackihKuca= IzdavackaKuca.ispisKuca("izdavackeKuce.txt");
        Radnik r= new Radnik();
        r.dodajIzdavackuKucu();
    }
    static void pregledKnjiga(){
        ArrayList<IzdavackaKuca> listaKuca = IzdavackaKuca.ispisKuca("izdavackeKuce.txt");
        ArrayList<Knjiga> listaKnjiga = Knjiga.ispisKnjiga("knjige.txt");
        System.out.println("Spisak knjiga: ");
        for (Knjiga k : listaKnjiga) {
            k.UzmiNazivIzdavackeKuce(listaKuca);
            System.out.println(k.toString(listaKuca));
        }
        
    }
    static void pregledRadnik(){
        ArrayList<Radnik> pregledRadnika = Radnik.ispisiKorisnike("radnici.txt");
        System.out.println("Spisak radnika: ");
        for (Radnik r : pregledRadnika) {
            System.out.println(r);
        }
    }
    static void pregledClanova(){
        ArrayList<Clan> listaClanova= Clan.ispisiKorisnike("clanovi.txt");
        for(Clan c: listaClanova){
            System.out.println(c);
        }
        
    }
    static void pregledIzdavackihKuca(){
        ArrayList<IzdavackaKuca> listaIzdavackihKuca = IzdavackaKuca.ispisKuca("izdavackeKuce.txt");
        for (IzdavackaKuca ik: listaIzdavackihKuca) {
            System.out.println(ik);
        }
    }
    static void povratakNaMeniRadnik(){
        System.out.println("Ako zelize da se vratiti na MENI RADNIK pritisnite ENTER");
        Scanner unos = new Scanner(System.in);
        unos.nextLine();
        meniRadnik();
    }
    static void povratakNaMeniClan(){
        System.out.println("Ako zelize da se vratiti na MENI CLAN pritisnite ENTER");
        Scanner unos = new Scanner(System.in);
        unos.nextLine();
        meniClan();
    }
    static void povratakNaMeniSefa() {
        System.out.println("Ako zelize da se vratiti na MENI SEFA pritisnite ENTER");
        Scanner unos = new Scanner(System.in);
        unos.nextLine();
        meniSef();
    }
    
    public static void ukloniClana() {
        ArrayList<Clan> listaClanova = Clan.ispisiKorisnike("clanovi.txt");
        Scanner unos = new Scanner(System.in);
        String ukloniKorIme;
        do {
            System.out.println("Unesite korisnicko ime clana kojeg zelite da uklonite");
            ukloniKorIme = unos.nextLine(); 
            if (!(Clan.proveraClana(listaClanova, ukloniKorIme))) {
                System.out.println("Niste unijeli postojece korisnicko ime.Pokusajate ponovo!");
            }
 
        } while (!(Clan.proveraClana(listaClanova, ukloniKorIme)));
        
        for (int i = 0; i < listaClanova.size(); i++) {
            if (listaClanova.get(i).getKorisnickoIme().equals(ukloniKorIme)) {
                listaClanova.remove(i);
                
                System.out.println("Uspjesno ste izbrisali člana");
            }
        }
        try {
            PrintWriter pw = new PrintWriter("clanovi.txt");

            for (int i = 0; i < listaClanova.size(); i++) {
                pw.println(listaClanova.get(i).getIme().trim()+" "+listaClanova.get(i).getPrezime().trim()+" "+listaClanova.get(i).getKorisnickoIme().trim()+" "+listaClanova.get(i).getSifra().trim()+" "+listaClanova.get(i).getUclanjenOd()+" "+listaClanova.get(i).getEmail());
            }

            pw.flush();
            pw.close();
        } catch (FileNotFoundException ex) { 
            System.out.println("Problem sa datotekom!");
        }

        
        
    }
    public static void ukloniRadnika(){
        ArrayList<Radnik> listaRadnika =Radnik.ispisiKorisnike("radnici.txt");
        Scanner unos = new Scanner(System.in);
        String ukloniKorIme;
        do {
            System.out.println("Unesite korisnicko ime radnika kojeg zelite da uklonite");
            ukloniKorIme = unos.nextLine();
            if (Radnik.proveraKorImenaRadnika(listaRadnika, ukloniKorIme)) {
                System.out.println("Niste unijeli postojece korisnicko ime.Pokusajate ponovo!");
            }

        } while (Radnik.proveraKorImenaRadnika(listaRadnika, ukloniKorIme));
        
        for (int i = 0; i < listaRadnika.size(); i++) {
            if ((listaRadnika.get(i).getKorisnickoIme().trim().equals(ukloniKorIme))) {
                listaRadnika.remove(i);

                System.out.println("Uspjesno ste izbrisali radnika");
            }
        }
        try {
            PrintWriter pw = new PrintWriter("radnici.txt");

            for (int i = 0; i < listaRadnika.size(); i++) {
                pw.println(listaRadnika.get(i).getIme().trim()+" "+listaRadnika.get(i).getPrezime().trim()+" "+listaRadnika.get(i).getKorisnickoIme().trim()+" "+listaRadnika.get(i).getSifra().trim()+" "+listaRadnika.get(i).getZaposlenOd()+" "+listaRadnika.get(i).getEmail().trim());
            }

            pw.flush();
            pw.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Problem sa datotekom!");
        }

    }
    public static void ukloniIzdavackuKucu(){
        ArrayList<IzdavackaKuca> listaIzdavackihKuca = IzdavackaKuca.ispisKuca("izdavackeKuce.txt");
        Scanner unos = new Scanner(System.in);
        String idIzdavacke;
        do {
            System.out.println("Unesite ID izdavacke kuce koji zelite da uklonite");
            idIzdavacke = unos.nextLine();
            if (IzdavackaKuca.proveraId(listaIzdavackihKuca, idIzdavacke)) {
                System.out.println("Niste unijeli postojece korisnicko ime.Pokusajate ponovo!");
            }

        } while (IzdavackaKuca.proveraId(listaIzdavackihKuca, idIzdavacke));
                for (int i = 0; i < listaIzdavackihKuca.size(); i++) {
            if (listaIzdavackihKuca.get(i).getId().equals(idIzdavacke)) {
                listaIzdavackihKuca.remove(i);
                i--;
                System.out.println("Uspjesno ste izbrisali izdavačku kuću");
            }
        }
        try {
            PrintWriter pw = new PrintWriter("izdavackeKuce.txt");

            for (int i = 0; i < listaIzdavackihKuca.size(); i++) {
                pw.println(listaIzdavackihKuca.get(i).getId().trim()+"-"+listaIzdavackihKuca.get(i).getNaziv().trim()+"-"+listaIzdavackihKuca.get(i).getAdresa().trim()+"-"+listaIzdavackihKuca.get(i).getBrTelefona().trim()+"-"+listaIzdavackihKuca.get(i).getEmail().trim()+"-"+listaIzdavackihKuca.get(i).getSaradnjOd());
            }

            pw.flush();
            pw.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Problem sa datotekom!");
        }
        
    }
    public static void ukloniKnjigu() {
        ArrayList<Knjiga> listaKnjiga = Knjiga.ispisKnjiga("knjige.txt");
        for (Knjiga k: listaKnjiga) {
            System.out.println(k.getIdKnjige());
        }
        Scanner unos = new Scanner(System.in);
        String unetiID;
        do {
            System.out.println("Unesite ID knjige koju zelite da uklonite");
            unetiID = unos.nextLine();
            if (!(Knjiga.proveraIdKnjige(listaKnjiga, unetiID))) {
                System.out.println("Niste unijeli postojece ID.Pokusajate ponovo!");
            }

        } while (!(Knjiga.proveraIdKnjige(listaKnjiga, unetiID)));

        for (int i = 0; i < listaKnjiga.size(); i++) {
            if (listaKnjiga.get(i).getIdKnjige().trim().equals(unetiID)) {
                listaKnjiga.remove(i);

                System.out.println("Uspjesno ste izbrisali knjigu");
            } 
        }
        try {
            PrintWriter pw = new PrintWriter("knjige.txt");

           for (int i = 0; i < listaKnjiga.size(); i++) {
                pw.println(listaKnjiga.get(i).getIdKnjige().trim()+"-"+listaKnjiga.get(i).getNaziv().trim()+"-"+listaKnjiga.get(i).getAutor().trim()+"-"+listaKnjiga.get(i).getZanr().trim()+"-"+listaKnjiga.get(i).getGod_izdavanja()+"-"+listaKnjiga.get(i).getDostpuaKolicina()+"-"+listaKnjiga.get(i).getIdIzdavackeKuce().trim());
            }

            pw.flush();
            pw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Biblioteka.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        

    }
    static void meniClan(){
        Scanner unos = new Scanner(System.in);
        int opcija;
        System.out.println("1. Pogledajte ponudu knjiga");
        System.out.println("2. Iznajmite knjigu");
        System.out.println("3. Vratite knjigu");
        System.out.println("4. Promijenite svoju lozinku");
        System.out.println("0. Vratite se na početni meni");
        
        System.out.println("Uensite opciju: ");
        while (true) {
            if (unos.hasNextInt()) {
                opcija = unos.nextInt();
                break;
            }
            System.out.println("Niste unijeli pravilno opciju! Opcija mora biti broj.\nPOKUSAJTE PONOVO");
            unos.nextLine();
        }
        switch (opcija) {
            case 1:
                pregledKnjiga();
                povratakNaMeniClan();
                break;
            case 2:
                iznajmiKnjigu();
                break;
            case 3:
                vratiKnjigu();
                break;
            case 4:
                promejnaSifre();
                povratakNaMeniClan();
                break;
    
            case 0:
                pocetniMeni();
                break;   
            default:
                meniClan();
        }
    }   
    static void iznajmiKnjigu(){
        Scanner unos = new Scanner(System.in);
        ArrayList<Knjiga> listaKnjiga = Knjiga.ispisKnjiga("knjige.txt");
        GregorianCalendar vreme = new GregorianCalendar();
        Random r = new Random();
        int idIznajmljivanja=r.nextInt(10000-0)+0;
        String idKnjige ;
        do {
            System.out.println("Unesite ID knjige koju zelite da iznajmite: ");
            idKnjige = unos.nextLine();
            if (!(Knjiga.proveraIdKnjige(listaKnjiga, idKnjige))) {
                System.out.println("Niste unijeli postojeci ID knjige! Pokusajte ponovo!");
            } 
        } while (!(Knjiga.proveraIdKnjige(listaKnjiga, idKnjige)));
        
        for (int i = 0; i < listaKnjiga.size(); i++) {
            if (listaKnjiga.get(i).getIdKnjige().trim().equals(idKnjige)) {
                if (listaKnjiga.get(i).getDostpuaKolicina()>0) {
                    System.out.println("Iznajmili ste knjigu: " + listaKnjiga.get(i).getNaziv().trim());
                    listaKnjiga.get(i).setDostpuaKolicina((listaKnjiga.get(i).getDostpuaKolicina()) - 1);
                }else{
                    System.out.println("Knjiga nije dostupna!");
                    povratakNaMeniClan();
                }
  
                
                try {
                    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("Spisak.txt", true)));
                    pw.println(idIznajmljivanja+" -"+listaKnjiga.get(i).getIdKnjige()+" Iznajmljena knjiga: "+listaKnjiga.get(i).getNaziv().trim()+" korisnik koji je iznajmio knjigu: "+procitajTrenutnogKorisnika()+" - "+ vreme.getTime());
                    
                    pw.flush();
                    pw.close();
                } catch (IOException ex) {
                    Logger.getLogger(Biblioteka.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        try {
            PrintWriter pw = new PrintWriter("knjige.txt");

            for (int i = 0; i < listaKnjiga.size(); i++) {
                pw.println(listaKnjiga.get(i).getIdKnjige() + " - " + listaKnjiga.get(i).getNaziv() + " - " + listaKnjiga.get(i).getAutor() + " - " + listaKnjiga.get(i).getZanr() + " - " + listaKnjiga.get(i).getGod_izdavanja() + " - " + listaKnjiga.get(i).getDostpuaKolicina() + " - " + listaKnjiga.get(i).getIdIzdavackeKuce());
            }

            pw.flush();
            pw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Biblioteka.class.getName()).log(Level.SEVERE, null, ex);
        }
        povratakNaMeniClan();
    }
   
    static void vratiKnjigu(){
        ArrayList<Knjiga> listaIzdatih = Knjiga.ipsisIzdatih("Spisak.txt");
        ArrayList<Knjiga> listaKnjiga = Knjiga.ispisKnjiga("knjige.txt");
        Scanner unos= new Scanner(System.in);
        int brojIzdadtih = 0;
        System.out.println("Lista knjiga koju treba da vratite");
        for (Knjiga k: listaIzdatih) {
            if (k.getNazivIzdate().contains(procitajTrenutnogKorisnika())) {
                System.out.println(k.getIdIzdate()+" - "+k.getNazivIzdate());
                brojIzdadtih+=1;
            } 
            
        }
        if(brojIzdadtih==0){
            System.out.println("Nemate nijednu izdatu knjigu");
            povratakNaMeniClan();
        }
        int idIzdate;
        do {
            System.out.println("Uneiste ID izdate knjige, da bi ste je vratili: ");
            while (true) {
                if (unos.hasNextInt()) {
                    idIzdate= unos.nextInt();
                    break;
                }
            }
            if (!(Knjiga.proveraIzdateKnjige(listaIzdatih, idIzdate))) {
                System.out.println("Ovaj id ne postoji: ");
            }
        } while (!(Knjiga.proveraIzdateKnjige(listaIzdatih, idIzdate)));
        

        for (int i = 0; i < listaIzdatih.size(); i++) {
            if (listaIzdatih.get(i).getIdIzdate() == idIzdate) {
                for (int j = 0; j < listaKnjiga.size(); j++) {
                    if (listaIzdatih.get(i).getNazivIzdate().contains((listaKnjiga.get(j).getIdKnjige()))) {
                        listaKnjiga.get(j).setDostpuaKolicina(listaKnjiga.get(j).getDostpuaKolicina()+1);
                        
                    }
                }
                listaIzdatih.remove(i);
                i--;
                System.out.println("Uspjesno ste vratili knjigu");
            }
       }     
        try {
            PrintWriter pw = new PrintWriter("Spisak.txt");
            for (int i = 0; i < listaIzdatih.size(); i++) {
                pw.println(listaIzdatih.get(i).getIdIzdate()+" -"+listaIzdatih.get(i).getNazivIzdate());
  
            }
    
            pw.flush();
            pw.close();
        } catch (FileNotFoundException ex) {                
            Logger.getLogger(Biblioteka.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            PrintWriter pw = new PrintWriter("knjige.txt");

            for (int i = 0; i < listaKnjiga.size(); i++) {
                pw.println(listaKnjiga.get(i).getIdKnjige() + " - " + listaKnjiga.get(i).getNaziv() + " - " + listaKnjiga.get(i).getAutor() + " - " + listaKnjiga.get(i).getZanr() + " - " + listaKnjiga.get(i).getGod_izdavanja() + " - " + listaKnjiga.get(i).getDostpuaKolicina() + " - " + listaKnjiga.get(i).getIdIzdavackeKuce());
            }

            pw.flush();
            pw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Biblioteka.class.getName()).log(Level.SEVERE, null, ex);
        }
        povratakNaMeniClan();
    }
    static void pregledtIzdatihKnjiga(){
        ArrayList<Knjiga> listaIzdatih= Knjiga.ipsisIzdatih("Spisak.txt");
        for (Knjiga li: listaIzdatih) {
            System.out.println(li.getIdIzdate()+li.getNazivIzdate());
        }
    }
    static String procitajTrenutnogKorisnika(){
        String korisnik="";
        try {
            FileInputStream fis = new FileInputStream("trenutniKorisnik.txt");
            Scanner sc = new Scanner(fis);
            korisnik= sc.nextLine().trim();
            
            sc.close();
            fis.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Biblioteka.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Biblioteka.class.getName()).log(Level.SEVERE, null, ex);
        }
        return korisnik;
    }

    static void promejnaSifre(){
        System.out.println("Unesite novu šifru: ");
        Scanner unos= new Scanner(System.in);
        String sifra = unos.nextLine();
        
        ArrayList<Clan> listaClanova = Clan.ispisiKorisnike("clanovi.txt");
        
        for (int i = 0; i < listaClanova.size(); i++) {
            if (listaClanova.get(i).getKorisnickoIme().trim().equals(procitajTrenutnogKorisnika())) {
                listaClanova.get(i).setSifra(sifra);
                System.out.println("Uspjesno ste promijenili šifru");
            }
        }
        try {
            PrintWriter pw = new PrintWriter("clanovi.txt");
            for (int i = 0; i < listaClanova.size(); i++) {
                pw.println(listaClanova.get(i).getIme()+" "+listaClanova.get(i).getPrezime()+" "+listaClanova.get(i).getKorisnickoIme()+" "+listaClanova.get(i).getSifra()+" "+listaClanova.get(i).getUclanjenOd()+" "+listaClanova.get(i).getEmail());
            }
            
            pw.flush();
            pw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Biblioteka.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}