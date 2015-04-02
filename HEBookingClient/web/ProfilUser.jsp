<%-- 
    Document   : ProfilUser
    Created on : 13 nov. 2014, 20:03:38
    Author     : Emmanuel
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.client.servlet.ReservationResult"%>
<%@page import="java.util.Collection"%>
<%@page import="org.client.entite.HebookingClient"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            HebookingClient enduser = (HebookingClient)session.getAttribute("user");
            ArrayList<ReservationResult> resUpdatable = (ArrayList<ReservationResult>) session.getAttribute("resUpdatable");
            ArrayList<ReservationResult> resNoUpdatable = (ArrayList<ReservationResult>) session.getAttribute("resNoUpdatable");
           
        %>
        <h1><a href="./Hebooking/">H&E Booking</a></h1>
        <h2>
            <span><%=enduser.getClientNom()%></span> <span><%=enduser.getClientPrenom()%></span>
        </h2>
        
        <h3>Reservation du client</h3>
        <table border="1">
            <th>Id Reservation Du client</th><th>Modifier</th><th>Supprimer</th>
            <%
                for(int i =0;i<resUpdatable.size();i++){
            %>
            <tr>
                <td>
                    <%=resUpdatable.get(i).getIdReservation()%>
                </td>
                
                <td>
                    <a href="Hebooking/reservation?mode=modif&idReservation=<%=resUpdatable.get(i).getIdReservation()%>">Modifier</a>
                </td>
                
                <td>
                    <a href="Hebooking/supprimerReservation?id=<%=resUpdatable.get(i).getIdReservation()%>">Supprimer</a>
                </td>
            </tr>
            <%
                }
            %>
            
            <%
                for(int i =0;i<resNoUpdatable.size();i++){
            %>
            <tr>
                <td>
                    <%=resNoUpdatable.get(i).getIdReservation() %>
                </td>
                
                <td>
                </td>
                
                <td>
                </td>
            </tr>
            <%
                }
            %>
        </table>
        
        <h3>Excursion du client</h3>
        <table border="1">
            <th>Id Excursion Du client</th>
            <%
                for(int i =0;i<resUpdatable.size();i++){
                    List<Long> excursion = resUpdatable.get(i).getIdExcursions();
                    for(int j =0;j<excursion.size();j++){
            %>
            <tr>
                <td>
                    <%=excursion.get(j) %>
                </td>
                
                
            </tr>
            <%
                    }
                }
            %>
        </table>
        
    </body>
</html>
