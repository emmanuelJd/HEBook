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
import javax.persistence.JoinTable;
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
@Table(name = "HEBOOKING_EXCURSION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HebookingExcursion.findAll", query = "SELECT h FROM HebookingExcursion h"),
    @NamedQuery(name = "HebookingExcursion.findByExcursionId", query = "SELECT h FROM HebookingExcursion h WHERE h.excursionId = :excursionId"),
    @NamedQuery(name = "HebookingExcursion.findByExcursionDateDebut", query = "SELECT h FROM HebookingExcursion h WHERE h.excursionDateDebut = :excursionDateDebut"),
    @NamedQuery(name = "HebookingExcursion.findByExcursionDateFin", query = "SELECT h FROM HebookingExcursion h WHERE h.excursionDateFin = :excursionDateFin"),
    @NamedQuery(name = "HebookingExcursion.findByExcursionNombrePlace", query = "SELECT h FROM HebookingExcursion h WHERE h.excursionNombrePlace = :excursionNombrePlace"),
    @NamedQuery(name = "HebookingExcursion.findByExcursionPrix", query = "SELECT h FROM HebookingExcursion h WHERE h.excursionPrix = :excursionPrix"),
    @NamedQuery(name = "HebookingExcursion.findByExcursionTheme", query = "SELECT h FROM HebookingExcursion h WHERE h.excursionTheme = :excursionTheme")})
public class HebookingExcursion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "EXCURSION_ID")
    private Long excursionId;
    @Column(name = "EXCURSION_DATE_DEBUT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date excursionDateDebut;
    @Column(name = "EXCURSION_DATE_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date excursionDateFin;
    @Column(name = "EXCURSION_NOMBRE_PLACE")
    private Integer excursionNombrePlace;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "EXCURSION_PRIX")
    private Double excursionPrix;
    @Size(max = 255)
    @Column(name = "EXCURSION_THEME")
    private String excursionTheme;
    @JoinTable(name = "EXCURSION_RESERVATION", joinColumns = {
        @JoinColumn(name = "EXCURSION_ID", referencedColumnName = "EXCURSION_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "RESERVATION_ID", referencedColumnName = "RESERVATION_ID")})
    @ManyToMany
    private Collection<HebookingReservation> hebookingReservationCollection;
    @JoinColumn(name = "HOTEL_ID", referencedColumnName = "HOTEL_ID")
    @ManyToOne
    private HebookingHotel hotelId;

    public HebookingExcursion() {
    }

    public HebookingExcursion(Long excursionId) {
        this.excursionId = excursionId;
    }

    public Long getExcursionId() {
        return excursionId;
    }

    public void setExcursionId(Long excursionId) {
        this.excursionId = excursionId;
    }

    public Date getExcursionDateDebut() {
        return excursionDateDebut;
    }

    public void setExcursionDateDebut(Date excursionDateDebut) {
        this.excursionDateDebut = excursionDateDebut;
    }

    public Date getExcursionDateFin() {
        return excursionDateFin;
    }

    public void setExcursionDateFin(Date excursionDateFin) {
        this.excursionDateFin = excursionDateFin;
    }

    public Integer getExcursionNombrePlace() {
        return excursionNombrePlace;
    }

    public void setExcursionNombrePlace(Integer excursionNombrePlace) {
        this.excursionNombrePlace = excursionNombrePlace;
    }

    public Double getExcursionPrix() {
        return excursionPrix;
    }

    public void setExcursionPrix(Double excursionPrix) {
        this.excursionPrix = excursionPrix;
    }

    public String getExcursionTheme() {
        return excursionTheme;
    }

    public void setExcursionTheme(String excursionTheme) {
        this.excursionTheme = excursionTheme;
    }

    @XmlTransient
    public Collection<HebookingReservation> getHebookingReservationCollection() {
        return hebookingReservationCollection;
    }

    public void setHebookingReservationCollection(Collection<HebookingReservation> hebookingReservationCollection) {
        this.hebookingReservationCollection = hebookingReservationCollection;
    }

    public HebookingHotel getHotelId() {
        return hotelId;
    }

    public void setHotelId(HebookingHotel hotelId) {
        this.hotelId = hotelId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (excursionId != null ? excursionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HebookingExcursion)) {
            return false;
        }
        HebookingExcursion other = (HebookingExcursion) object;
        if ((this.excursionId == null && other.excursionId != null) || (this.excursionId != null && !this.excursionId.equals(other.excursionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.client.entite.HebookingExcursion[ excursionId=" + excursionId + " ]";
    }
    
}
