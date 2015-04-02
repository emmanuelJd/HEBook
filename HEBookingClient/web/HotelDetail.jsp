<%-- 
    Document   : HotelDetail
    Created on : 9 nov. 2014, 17:32:27
    Author     : Emmanuel
--%>

<%@page import="java.util.Collection"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.client.entite.HebookingExcursion"%>
<%@page import="org.client.entite.HebookingChambre"%>
<%@page import="org.client.entite.HebookingHotel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HEBooking</title>
    </head>
    <body>
        <h1><a href="./Hebooking/">H&E Booking</a></h1>
        <%
            HebookingHotel hotel = (HebookingHotel)session.getAttribute("HotelDetail");
            if(session.getAttribute("ErrorMessage") != null){
        %>
                <span><%=session.getAttribute("ErrorMessage")%></span>
        <%
               session.removeAttribute("ErrorMessage");
            }
        %>
        <h1>Detail Hotel : <%=hotel.getHotelNom()%></h1>
        
        
        <span>Situé à : <%=hotel.getHotelVille()%>,</span><br />
        <span> Possedant <%=hotel.getHotelEtoile()%> étoile(s).</span><br/><br />
        
        <span>Description : <%=hotel.getHotelDescription()%></span><br />
        
        <table border="1">
            <th>Chambre de l'hotel Dispo</th>
         <%
            for(HebookingChambre hebchambre : (Collection<HebookingChambre>)session.getAttribute("ChambreDispo")){
                
         %>
           <tr>
             <td>
                <span>Identifiant de la Chambre : <%=hebchambre.getChambreId()%></span><br />
                <span>Chambre numéro : <%=hebchambre.getChambreNumero()%></span>, à l'étage <%=hebchambre.getChambreEtage()%><br />
                <span>Catégorie : <%=hebchambre.getChambreCategorie()%></span><br />
                <span>Prix : <%=hebchambre.getChambrePrix()%> </span><br />
                <span><a href="Hebooking/reservation?mode=create&nomHotel=<%=hotel.getHotelNom()%>&numChambre=<%=hebchambre.getChambreNumero()%>&idHotel=<%=hotel.getHotelId()%>&idChambre=<%=hebchambre.getChambreId()%>">Reserver</a></span>
            </td>
          </tr>
         <%    
            }
        %>
        </table>
        <br /><br />
        <table border="1">
            <th>Chambre de l'hotel Non Disponible</th>
         <%
            for(HebookingChambre hebchambre : (Collection<HebookingChambre>)session.getAttribute("ChambreNoDispo")){
                
         %>
           <tr>
             <td>
                <span>Identifiant de la Chambre : <%=hebchambre.getChambreId()%></span><br />
                <span>Chambre numéro : <%=hebchambre.getChambreNumero()%></span>, à l'étage <%=hebchambre.getChambreEtage()%><br />
                <span>Catégorie : <%=hebchambre.getChambreCategorie()%></span><br />
                <span>Prix : <%=hebchambre.getChambrePrix()%> </span><br />
                <span>Déjà Réserver</span>
            </td>
          </tr>
         <%    
            }
        %>
        </table>
        
        <br /><br />
        <table border="1">
            <th>Excursion proposée par l'hotel</th>
         <%
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm:ss");
            for(HebookingExcursion hebexcursion : hotel.getHebookingExcursionCollection()){
                
         %>
           <tr><td>
            <span>Départ le : <%=dateFormat.format(hebexcursion.getExcursionDateDebut())%></span>, Arrivée le <%=dateFormat.format(hebexcursion.getExcursionDateFin())%><br />
            <span>Thème : <%=hebexcursion.getExcursionTheme()%></span><br />
            <span>Prix : <%=hebexcursion.getExcursionPrix()%> </span><br />
            <span>nombre de Place : <%=hebexcursion.getExcursionNombrePlace()%></span><br />
           
           </td></tr>
         <%     
            }
         %>
        </table>
    </body>
</html>
