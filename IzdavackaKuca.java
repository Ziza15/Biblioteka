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
public class IzdavackaKuca {
    private String id,naziv, adresa, brTelefona, email;
    private int saradnjOd;

    public IzdavackaKuca(String naziv) {
        this.naziv = naziv;
    }
    
    public IzdavackaKuca(String id, String naziv, String adresa, String brTelefona, String email, int saradnjOd) {
        this.id = id;
        this.naziv = naziv;
        this.adresa = adresa;
        this.brTelefona = brTelefona;
        this.email = email;
        this.saradnjOd = saradnjOd;
    }
    
    public String getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getBrTelefona() {
        return brTelefona;
    }

    public String getEmail() {
        return email;
    }

    public int getSaradnjOd() {
        return saradnjOd;
    }
    
    public static boolean proveraId(ArrayList<IzdavackaKuca> listaIzdavackihKuca, String id){
        for (IzdavackaKuca ik: listaIzdavackihKuca) {
            if (ik.getId().equals(id)) {
                return false;
            }
        }
        return true;
    }
    @Override
    public String toString() {
        return "IzdavackaKuca{" + "id=" + id + ", naziv=" + naziv + ", adresa=" + adresa + ", brTelefona=" + brTelefona + ", email=" + email + ", saradnjOd=" + saradnjOd + '}';
    }
    
    
    public static ArrayList<IzdavackaKuca> ispisKuca(String imeDadoteke){
        ArrayList<IzdavackaKuca> listaKuca = new ArrayList<>();
        
        try {
            FileInputStream fis = new FileInputStream(imeDadoteke);
            Scanner sc= new Scanner(fis);
            while (sc.hasNext()) {
                String[] podaci= sc.nextLine().split("-");
                listaKuca.add(new IzdavackaKuca(podaci[0].trim(),podaci[1],podaci[2],podaci[3],podaci[4], Integer.parseInt(podaci[5])));
               
            }
            sc.close();
            fis.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IzdavackaKuca.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IzdavackaKuca.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaKuca;
    }
    
}
