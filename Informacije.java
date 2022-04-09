/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteka;

/**
 *
 * @author zivko
 */
public abstract class Informacije {
    protected String ime, prezime, korisnickoIme, sifra,email;

    public Informacije(String ime, String prezime, String korisnickoIme, String sifra, String email) {
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
        this.email = email;
    }

    public Informacije() {
        this.ime = "";
        this.prezime = "";
        this.korisnickoIme = "";
        this.sifra = "";
        this.email = "";
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public String getEmail() {
        return email;
    }
    
    
    
    public abstract boolean proveraPrijave(String korisnickoIme, String sifra);
    
     
}
