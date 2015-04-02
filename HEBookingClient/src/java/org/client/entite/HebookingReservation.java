/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.client.entite;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jean-vitus
 */
@Entity
@Table(name = "HEBOOKING_RESERVATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HebookingReservation.findAll", query = "SELECT h FROM HebookingReservation h"),
    @NamedQuery(name = "HebookingReservation.findByReservationId", query = "SELECT h FROM HebookingReservation h WHERE h.reservationId = :reservationId"),
    @NamedQuery(name = "HebookingReservation.findByReservationDebut", query = "SELECT h FROM HebookingReservation h WHERE h.reservationDebut = :reservationDebut"),
    @NamedQuery(name = "HebookingReservation.findByReservationFin", query = "SELECT h FROM HebookingReservation h WHERE h.reservationFin = :reservationFin"),
    @NamedQuery(name = "HebookingReservation.findByReservationNumeroCarteBleu", query = "SELECT h FROM HebookingReservation h WHERE h.reservationNumeroCarteBleu = :reservationNumeroCarteBleu")})
public class HebookingReservation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "RESERVATION_ID")
    private Long reservationId;
    @Column(name = "RESERVATION_DEBUT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reservationDebut;
    @Column(name = "RESERVATION_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reservationFin;
    @Size(max = 255)
    @Column(name = "RESERVATION_NUMERO_CARTE_BLEU")
    private String reservationNumeroCarteBleu;
    @ManyToMany(mappedBy = "hebookingReservationCollection")
    private Collection<HebookingExcursion> hebookingExcursionCollection;
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "CLIENT_ID")
    @ManyToOne
    private HebookingClient clientId;
    @JoinColumn(name = "CHAMBRE_ID", referencedColumnName = "CHAMBRE_ID")
    @ManyToOne
    private HebookingChambre chambreId;

    public HebookingReservation() {
    }

    public HebookingReservation(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Date getReservationDebut() {
        return reservationDebut;
    }

    public void setReservationDebut(Date reservationDebut) {
        this.reservationDebut = reservationDebut;
    }

    public Date getReservationFin() {
        return reservationFin;
    }

    public void setReservationFin(Date reservationFin) {
        this.reservationFin = reservationFin;
    }

    public String getReservationNumeroCarteBleu() {
        return reservationNumeroCarteBleu;
    }

    public void setReservationNumeroCarteBleu(String reservationNumeroCarteBleu) {
        this.reservationNumeroCarteBleu = reservationNumeroCarteBleu;
    }

    @XmlTransient
    public Collection<HebookingExcursion> getHebookingExcursionCollection() {
        return hebookingExcursionCollection;
    }

    public void setHebookingExcursionCollection(Collection<HebookingExcursion> hebookingExcursionCollection) {
        this.hebookingExcursionCollection = hebookingExcursionCollection;
    }

    public HebookingClient getClientId() {
        return clientId;
    }

    public void setClientId(HebookingClient clientId) {
        this.clientId = clientId;
    }

    public HebookingChambre getChambreId() {
        return chambreId;
    }

    public void setChambreId(HebookingChambre chambreId) {
        this.chambreId = chambreId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reservationId != null ? reservationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HebookingReservation)) {
            return false;
        }
        HebookingReservation other = (HebookingReservation) object;
        if ((this.reservationId == null && other.reservationId != null) || (this.reservationId != null && !this.reservationId.equals(other.reservationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.client.entite.HebookingReservation[ reservationId=" + reservationId + " ]";
    }
    
}
