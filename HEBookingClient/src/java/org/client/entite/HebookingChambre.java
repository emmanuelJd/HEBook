/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.client.entite;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jean-vitus
 */
@Entity
@Table(name = "HEBOOKING_CHAMBRE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HebookingChambre.findAll", query = "SELECT h FROM HebookingChambre h"),
    @NamedQuery(name = "HebookingChambre.findByChambreId", query = "SELECT h FROM HebookingChambre h WHERE h.chambreId = :chambreId"),
    @NamedQuery(name = "HebookingChambre.findByChambreCategorie", query = "SELECT h FROM HebookingChambre h WHERE h.chambreCategorie = :chambreCategorie"),
    @NamedQuery(name = "HebookingChambre.findByChambreEtage", query = "SELECT h FROM HebookingChambre h WHERE h.chambreEtage = :chambreEtage"),
    @NamedQuery(name = "HebookingChambre.findByChambreNumero", query = "SELECT h FROM HebookingChambre h WHERE h.chambreNumero = :chambreNumero"),
    @NamedQuery(name = "HebookingChambre.findByChambrePrix", query = "SELECT h FROM HebookingChambre h WHERE h.chambrePrix = :chambrePrix")})
public class HebookingChambre implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CHAMBRE_ID")
    private Long chambreId;
    @Size(max = 255)
    @Column(name = "CHAMBRE_CATEGORIE")
    private String chambreCategorie;
    @Size(max = 255)
    @Column(name = "CHAMBRE_ETAGE")
    private String chambreEtage;
    @Column(name = "CHAMBRE_NUMERO")
    private Integer chambreNumero;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CHAMBRE_PRIX")
    private Double chambrePrix;
    @JoinColumn(name = "HOTEL_ID", referencedColumnName = "HOTEL_ID")
    @ManyToOne
    private HebookingHotel hotelId;
    @OneToMany(mappedBy = "chambreId")
    private Collection<HebookingReservation> hebookingReservationCollection;

    public HebookingChambre() {
    }

    public HebookingChambre(Long chambreId) {
        this.chambreId = chambreId;
    }

    public Long getChambreId() {
        return chambreId;
    }

    public void setChambreId(Long chambreId) {
        this.chambreId = chambreId;
    }

    public String getChambreCategorie() {
        return chambreCategorie;
    }

    public void setChambreCategorie(String chambreCategorie) {
        this.chambreCategorie = chambreCategorie;
    }

    public String getChambreEtage() {
        return chambreEtage;
    }

    public void setChambreEtage(String chambreEtage) {
        this.chambreEtage = chambreEtage;
    }

    public Integer getChambreNumero() {
        return chambreNumero;
    }

    public void setChambreNumero(Integer chambreNumero) {
        this.chambreNumero = chambreNumero;
    }

    public Double getChambrePrix() {
        return chambrePrix;
    }

    public void setChambrePrix(Double chambrePrix) {
        this.chambrePrix = chambrePrix;
    }

    public HebookingHotel getHotelId() {
        return hotelId;
    }

    public void setHotelId(HebookingHotel hotelId) {
        this.hotelId = hotelId;
    }

    @XmlTransient
    public Collection<HebookingReservation> getHebookingReservationCollection() {
        return hebookingReservationCollection;
    }

    public void setHebookingReservationCollection(Collection<HebookingReservation> hebookingReservationCollection) {
        this.hebookingReservationCollection = hebookingReservationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chambreId != null ? chambreId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HebookingChambre)) {
            return false;
        }
        HebookingChambre other = (HebookingChambre) object;
        if ((this.chambreId == null && other.chambreId != null) || (this.chambreId != null && !this.chambreId.equals(other.chambreId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.client.entite.HebookingChambre[ chambreId=" + chambreId + " ]";
    }
    
}
