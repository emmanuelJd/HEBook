<%-- 
    Document   : connexionOrInscription
    Created on : 12 nov. 2014, 08:34:05
    Author     : Emmanuel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>H&EBooking</title>
    </head>
    <body>
        <h1><a href="./Hebooking/">H&E Booking</a></h1>
        <h2>Formulaire de <%=session.getAttribute("type")%></h2>
        <form method="POST" action="./Hebooking/<%=session.getAttribute("typeAction")%>">
            <label>Nom : </label><input type="text" name="nom"/><br />
            <label>Prenom : </label><input type ="text" name ="prenom"/><br />
            <label>Mot de Passe : </label><input type="text" name ="motdepasse"/><br />
            
            <button type="submit"><%=session.getAttribute("typeSubmit")%></button>
            <button type="reset">Effacer</button>
        </form>
    </body>
</html>
