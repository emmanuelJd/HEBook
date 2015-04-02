/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.client.servlet;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Emmanuel
 */
@XmlRootElement
public class RecapReservationByClient implements Serializable{
    
    private Long idClient;
    private Long idReservation;
    private double prixSejour;
    private double prixTotal;
    private int nombreJour;

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public double getPrixSejour() {
        return prixSejour;
    }

    public void setPrixSejour(double prixSejour) {
        this.prixSejour = prixSejour;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public int getNombreJour() {
        return nombreJour;
    }

    public void setNombreJour(int nombreJour) {
        this.nombreJour = nombreJour;
    }

    public Long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
    }
  
    
}
