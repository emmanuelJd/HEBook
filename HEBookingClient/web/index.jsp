<%-- 
    Document   : index
    Created on : 9 nov. 2014, 14:10:10
    Author     : Emmanuel
--%>

<%@page import="org.codehaus.jettison.json.JSONObject"%>
<%@page import="org.codehaus.jettison.json.JSONArray"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="java.text.*" %>
<%@page import="org.client.entite.*" %>
<%@page import="org.codehaus.jettison.json.JSONArray" %>
<%@page import="org.codehaus.jettison.json.JSONObject" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HEBooking</title>
    </head>
    <body>
        <h1><a href="./Hebooking/">H&E Booking</a></h1>
        <%
            String valEtoile = "value=''";
            String valVille = "value=''";
            String valPrixSup = "value=''";
            String valPrixInf = "value=''";
            
            if(session.getAttribute("nombreEtoile") != null){
                valEtoile = "value='"+(String) session.getAttribute("nombreEtoile")+"'";
                valVille = "value='"+(String) session.getAttribute("ville")+"'";
                valPrixSup = "value='"+(String) session.getAttribute("prixSup")+"'";
                valPrixInf = "value='"+(String) session.getAttribute("prixInf")+"'";
            }
            
            if(session.getAttribute("error") != null){
        %>
            <div><span><%=session.getAttribute("error")%></span></div>
        <%
            }
        %>
        <h2>Liste des Hotels</h2>
        <form method="post" action="./Hebooking/Hotelfiltre">
            <label>Etoile : </label><input type="number" name="nombreEtoile" <%=valEtoile%> />
            <label>ville : </label><input type="text" name="ville" <%=valVille%> />
            <label>prix supérieur : </label><input type="text" name="prixSup" <%=valPrixSup%> />
            <label>prix inférieur : </label><input type="text" name="prixInf"  <%=valPrixInf%> />
            <button type="submit">Rechercher</button>
        </form>
        <%
            if(session.getAttribute("user") != null){
                HebookingClient client = (HebookingClient) session.getAttribute("user");
        %>
                <span>Bonjour <%=client.getClientNom()%> <%=client.getClientPrenom()%></span>
                
                <span><a href="Hebooking/profilUser">Profil de l'utilisateur</a></span>
        <%
         }else{
        %>
            <a href="Hebooking/formulaireClient?type=connexion">Connexion</a>
            <a href="Hebooking/formulaireClient?type=inscription">Inscription</a>
        <%
            } 
        %>
        <% 
            
            JSONArray listHotel = (JSONArray) session.getAttribute("ListHotelJSONArray");
            JSONArray listHotelChambre = (JSONArray) session.getAttribute("ListHotelChambreJSONArray");
            
            for(int i = 0;i<listHotel.length() ;i++)
            {
                JSONObject hotel = listHotel.getJSONObject(i);
                
                String idHotel = String.valueOf(hotel.get("idHotel"));
                String nombreChambreDisponible = "";
                int j = 0;
                boolean find = false;
                while(j<listHotelChambre.length() && !find){
                    JSONObject hotelChambre = listHotelChambre.getJSONObject(j);
                    if(idHotel.equals(String.valueOf(hotelChambre.get("idHotel")))){
                        nombreChambreDisponible = String.valueOf(hotelChambre.get("nbreChambre"));
                        find = true;
                    }
                    j++;
                }
        %>
        <hr width="95%">
            <div id="Hotel_<%=hotel.get("idHotel")%>">
                <span>Nom : <%=hotel.get("nomHotel")%> </span><br />
                <span>Situé à : <%=hotel.get("ville")%></span><br />
                <span> Possedant <%=hotel.get("nombreEtoile")%> étoile(s)</span><br />
                <span> Nombre De Chambre Disponible : <%=nombreChambreDisponible%></span><br/>
                <a href="Hebooking/detailHotel?id=<%=hotel.get("idHotel")%>">Suite...</a><br /><br />
            </div>
        <%
                  
            }
        %>
    </body>
</html>
