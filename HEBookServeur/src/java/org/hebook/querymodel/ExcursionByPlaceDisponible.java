/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hebook.querymodel;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe qui représente le couple identifiant Excursion et le nombre de place disponible
 * @author Emmanuel
 */
@XmlRootElement
public class ExcursionByPlaceDisponible {
    
    private Long idExcursion;
    private int nbrePlaceDisponible;

    /**
     *
     * @return
     */
    public Long getIdExcursion() {
        return idExcursion;
    }

    /**
     *
     * @param idExcursion
     */
    public void setIdExcursion(Long idExcursion) {
        this.idExcursion = idExcursion;
    }

    /**
     *
     * @return
     */
    public int getNbrePlaceDisponible() {
        return nbrePlaceDisponible;
    }

    /**
     *
     * @param nbrePlaceDisponible
     */
    public void setNbrePlaceDisponible(int nbrePlaceDisponible) {
        this.nbrePlaceDisponible = nbrePlaceDisponible;
    }
    
    
    
    
}
