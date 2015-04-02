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
@Table(name = "HEBOOKING_HOTEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HebookingHotel.findAll", query = "SELECT h FROM HebookingHotel h"),
    @NamedQuery(name = "HebookingHotel.findByHotelId", query = "SELECT h FROM HebookingHotel h WHERE h.hotelId = :hotelId"),
    @NamedQuery(name = "HebookingHotel.findByHotelDescription", query = "SELECT h FROM HebookingHotel h WHERE h.hotelDescription = :hotelDescription"),
    @NamedQuery(name = "HebookingHotel.findByHotelImage", query = "SELECT h FROM HebookingHotel h WHERE h.hotelImage = :hotelImage"),
    @NamedQuery(name = "HebookingHotel.findByHotelNom", query = "SELECT h FROM HebookingHotel h WHERE h.hotelNom = :hotelNom"),
    @NamedQuery(name = "HebookingHotel.findByHotelNbreChambre", query = "SELECT h FROM HebookingHotel h WHERE h.hotelNbreChambre = :hotelNbreChambre"),
    @NamedQuery(name = "HebookingHotel.findByHotelEtoile", query = "SELECT h FROM HebookingHotel h WHERE h.hotelEtoile = :hotelEtoile"),
    @NamedQuery(name = "HebookingHotel.findByHotelVille", query = "SELECT h FROM HebookingHotel h WHERE h.hotelVille = :hotelVille")})
public class HebookingHotel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "HOTEL_ID")
    private Long hotelId;
    @Size(max = 255)
    @Column(name = "HOTEL_DESCRIPTION")
    private String hotelDescription;
    @Size(max = 255)
    @Column(name = "HOTEL_IMAGE")
    private String hotelImage;
    @Size(max = 255)
    @Column(name = "HOTEL_NOM")
    private String hotelNom;
    @Column(name = "HOTEL_NBRE_CHAMBRE")
    private Integer hotelNbreChambre;
    @Column(name = "HOTEL_ETOILE")
    private Integer hotelEtoile;
    @Size(max = 255)
    @Column(name = "HOTEL_VILLE")
    private String hotelVille;
    @OneToMany(mappedBy = "hotelId")
    private Collection<HebookingExcursion> hebookingExcursionCollection;
    @OneToMany(mappedBy = "hotelId")
    private Collection<HebookingChambre> hebookingChambreCollection;

    public HebookingHotel() {
    }

    public HebookingHotel(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelDescription() {
        return hotelDescription;
    }

    public void setHotelDescription(String hotelDescription) {
        this.hotelDescription = hotelDescription;
    }

    public String getHotelImage() {
        return hotelImage;
    }

    public void setHotelImage(String hotelImage) {
        this.hotelImage = hotelImage;
    }

    public String getHotelNom() {
        return hotelNom;
    }

    public void setHotelNom(String hotelNom) {
        this.hotelNom = hotelNom;
    }

    public Integer getHotelNbreChambre() {
        return hotelNbreChambre;
    }

    public void setHotelNbreChambre(Integer hotelNbreChambre) {
        this.hotelNbreChambre = hotelNbreChambre;
    }

    public Integer getHotelEtoile() {
        return hotelEtoile;
    }

    public void setHotelEtoile(Integer hotelEtoile) {
        this.hotelEtoile = hotelEtoile;
    }

    public String getHotelVille() {
        return hotelVille;
    }

    public void setHotelVille(String hotelVille) {
        this.hotelVille = hotelVille;
    }

    @XmlTransient
    public Collection<HebookingExcursion> getHebookingExcursionCollection() {
        return hebookingExcursionCollection;
    }

    public void setHebookingExcursionCollection(Collection<HebookingExcursion> hebookingExcursionCollection) {
        this.hebookingExcursionCollection = hebookingExcursionCollection;
    }

    @XmlTransient
    public Collection<HebookingChambre> getHebookingChambreCollection() {
        return hebookingChambreCollection;
    }

    public void setHebookingChambreCollection(Collection<HebookingChambre> hebookingChambreCollection) {
        this.hebookingChambreCollection = hebookingChambreCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hotelId != null ? hotelId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HebookingHotel)) {
            return false;
        }
        HebookingHotel other = (HebookingHotel) object;
        if ((this.hotelId == null && other.hotelId != null) || (this.hotelId != null && !this.hotelId.equals(other.hotelId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.client.entite.HebookingHotel[ hotelId=" + hotelId + " ]";
    }
    
}
