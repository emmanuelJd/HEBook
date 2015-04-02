/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.client.servlet;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe qui représente la chambre coté client 
 * @author Emmanuel
 */
@XmlRootElement
public class ChambreResult implements Serializable{
    
    
    private Long idChambre;
    private int numero;
    private String etage;
    private double prix;
    private String categorie;

    private Long idHotel;

    public Long getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(Long idHotel) {
        this.idHotel = idHotel;
    }
    
    public Long getIdChambre() {
        return idChambre;
    }

    public void setIdChambre(Long idChambre) {
        this.idChambre = idChambre;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getEtage() {
        return etage;
    }

    public void setEtage(String etage) {
        this.etage = etage;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
    
    
}
