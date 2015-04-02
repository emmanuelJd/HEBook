/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hebook.querymodel;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe qui représente la récapitulation d'une réservation pour un client avec l'identifiant pour le client, l'identifiant pour la réservation, le prix du séjour, le prix total et le nombre de jour passé
 * @author Emmanuel
 */
@XmlRootElement
public class RecapReservationByClient implements Serializable{
    
    private Long idClient;
    private Long idReservation;
    private double prixSejour;
    private double prixTotal;
    private int nombreJour;

    /**
     *
     * @return
     */
    public Long getIdClient() {
        return idClient;
    }

    /**
     *
     * @param idClient
     */
    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    /**
     *
     * @return
     */
    public double getPrixSejour() {
        return prixSejour;
    }

    /**
     *
     * @param prixSejour
     */
    public void setPrixSejour(double prixSejour) {
        this.prixSejour = prixSejour;
    }

    /**
     *
     * @return
     */
    public double getPrixTotal() {
        return prixTotal;
    }

    /**
     *
     * @param prixTotal
     */
    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    /**
     *
     * @return
     */
    public int getNombreJour() {
        return nombreJour;
    }

    /**
     *
     * @param nombreJour
     */
    public void setNombreJour(int nombreJour) {
        this.nombreJour = nombreJour;
    }

    /**
     *
     * @return
     */
    public Long getIdReservation() {
        return idReservation;
    }

    /**
     *
     * @param idReservation
     */
    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
    }
  
    
}
