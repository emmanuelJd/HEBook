<%-- 
    Document   : reservation
    Created on : 13 nov. 2014, 12:57:26
    Author     : Emmanuel
--%>

<%@page import="org.client.servlet.ExcursionResult"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.client.servlet.ReservationResult"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.client.entite.*" %>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reservation</title>
    </head>
    
    <body>
        <h1><a href="./Hebooking/">H&E Booking</a></h1>
        <%
            if(session.getAttribute("user") != null){
                HebookingClient endu = (HebookingClient) session.getAttribute("user");


                String nom = endu.getClientNom();
                String prenom = endu.getClientPrenom();
                
                if(session.getAttribute("mode").equals("create")){
                    %>
                    <h2>Reservation de la chambre numéro <%=session.getAttribute("numChambre")%>, de l'hotel <%=session.getAttribute("nomHotel")%> Pour <%=nom%> <%=prenom%></h2><br /><br />
                    <form method="post" action="<%=session.getAttribute("reservationAction")%>">
                        <label>Date de debut : </label><input type="date" name="date_Debut" /><br />
                        <label>Date de fin : </label><input type="date" name="date_Fin" /><br />
                        <label>Numero de carte : </label><input type="text" name="numCarte" /><br />
                        
                        <%
                            ArrayList<ExcursionResult> result = (ArrayList<ExcursionResult>) session.getAttribute("ListExcursion");
                            
                            for(int i =0; i < result.size();i++){
                        %>
                            <span><%=result.get(i).getIdExcursion()%> <%=result.get(i).getTheme()%></span><input type="checkbox" name="excursions" value="<%=result.get(i).getIdExcursion()%>" /><br />
                        <%
                            }
                        %>
                        <button type="submit"><%=session.getAttribute("reservationSubmit")%></button>
                        <button type="reset">Annuler</button>
                    </form>
                    <%
                }else if(session.getAttribute("mode").equals("modif")){
                        
                        ReservationResult tamponReservation = (ReservationResult) session.getAttribute("clientResult");
                        
                        SimpleDateFormat sDatF = new SimpleDateFormat("yyyy-MM-dd");
                        
                        System.out.println(tamponReservation.getDateDebut());
                        System.out.println(tamponReservation.getDateFin());
                        
                       
                        
                        String dateDeb = sDatF.format(tamponReservation.getDateDebut());
                        String dateFin = sDatF.format(tamponReservation.getDateFin());
                        
                        System.out.println("Avec le format => "+dateDeb);
                        System.out.println(dateFin);
                    %>
                    <h2>Modification de la Reservation de la chambre d'Id <%=tamponReservation.getIdChambre() %> Pour <%=nom%> <%=prenom%></h2><br /><br />
                    <form method="post" action="<%=session.getAttribute("reservationAction")%>">
                        <input type="hidden" value="<%=tamponReservation.getIdReservation() %>" name="reservation" />
                        <label>Date de debut : </label><input type="date" name="date_Debut" value="<%=dateDeb%>"/><br />
                        <label>Date de fin : </label><input type="date" name="date_Fin" value="<%=dateFin%>"/><br />
                        <label>Numero de carte : </label><input type="text" name="numCarte" value="<%=tamponReservation.getNumeroCarteBleu()%>"/><br />
                        <%
                            ArrayList<ExcursionResult> result = (ArrayList<ExcursionResult>) session.getAttribute("ListExcursion");
                            
                            for(int i =0; i < result.size();i++){
                                if(tamponReservation.getIdExcursions().contains(result.get(i).getIdExcursion())){
                        %>
                                    <span><%=result.get(i).getIdExcursion()%> <%=result.get(i).getTheme()%></span><input type="checkbox" name="excursions" value="<%=result.get(i).getIdExcursion()%>" checked /><br />
                        <%
                                }else{
                        %>
                                    <span><%=result.get(i).getIdExcursion()%> <%=result.get(i).getTheme()%></span><input type="checkbox" name="excursions" value="<%=result.get(i).getIdExcursion()%>" /><br />
                        <%
                                }
                            }
                        %>
                        <button type="submit"><%=session.getAttribute("reservationSubmit")%></button>
                        <button type="reset">Annuler</button>
                    </form>
                    <%
                }
            }
        %>
    </body>
</html>
