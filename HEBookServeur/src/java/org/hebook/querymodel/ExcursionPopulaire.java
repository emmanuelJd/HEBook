/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hebook.querymodel;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe qui représente le couple l'identifiant de l'excursion et le nombre de participant associé à cette excursion
 * @author Emmanuel
 */
@XmlRootElement
public class ExcursionPopulaire implements Serializable {
    
    private Long idExcursion;
    private Long nbreParticipant;

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
    public Long getNbreParticipant() {
        return nbreParticipant;
    }

    /**
     *
     * @param nbreParticipant
     */
    public void setNbreParticipant(Long nbreParticipant) {
        this.nbreParticipant = nbreParticipant;
    }
}
