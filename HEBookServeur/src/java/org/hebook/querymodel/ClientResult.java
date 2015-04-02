/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hebook.querymodel;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe qui représente le client sans référence direct sur la réservation
 * @author Emmanuel
 */
@XmlRootElement
public class ClientResult implements Serializable{
    
    private Long idClient;
    private String nomClient;
    private String prenomClient;
    private String motDePasse;
    private List<Long> reservations;

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
    public String getNomClient() {
        return nomClient;
    }

    /**
     *
     * @param nomClient
     */
    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    /**
     *
     * @return
     */
    public String getPrenomClient() {
        return prenomClient;
    }

    /**
     *
     * @param prenomClient
     */
    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }

    /**
     *
     * @return
     */
    public String getMotDePasse() {
        return motDePasse;
    }

    /**
     *
     * @param motDePasse
     */
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    /**
     *
     * @return
     */
    public List<Long> getReservations() {
        return reservations;
    }

    /**
     *
     * @param reservations
     */
    public void setReservations(List<Long> reservations) {
        this.reservations = reservations;
    }

    
}
