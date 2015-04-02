/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hebook.querymodel;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe qui représente la chambre sans référence direct sur un hotel
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

    /**
     *
     * @return
     */
    public Long getIdHotel() {
        return idHotel;
    }

    /**
     *
     * @param idHotel
     */
    public void setIdHotel(Long idHotel) {
        this.idHotel = idHotel;
    }
    
    /**
     *
     * @return
     */
    public Long getIdChambre() {
        return idChambre;
    }

    /**
     *
     * @param idChambre
     */
    public void setIdChambre(Long idChambre) {
        this.idChambre = idChambre;
    }

    /**
     *
     * @return
     */
    public int getNumero() {
        return numero;
    }

    /**
     *
     * @param numero
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     *
     * @return
     */
    public String getEtage() {
        return etage;
    }

    /**
     *
     * @param etage
     */
    public void setEtage(String etage) {
        this.etage = etage;
    }

    /**
     *
     * @return
     */
    public double getPrix() {
        return prix;
    }

    /**
     *
     * @param prix
     */
    public void setPrix(double prix) {
        this.prix = prix;
    }

    /**
     *
     * @return
     */
    public String getCategorie() {
        return categorie;
    }

    /**
     *
     * @param categorie
     */
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
    
    
}
