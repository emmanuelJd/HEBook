/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.client.servlet;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.client.entite.HebookingChambre;
import org.client.entite.HebookingClient;
import org.client.entite.HebookingExcursion;
import org.client.entite.HebookingHotel;
import org.client.jersey.ChambreClient;
import org.client.jersey.ClientClient;
import org.client.jersey.ExcursionClient;
import org.client.jersey.HotelClient;
import org.client.jersey.ReservationClient;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;


/**
 *
 * @author Emmanuel
 */
public class HEBookingServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /*
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            /*out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HEBookingServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HEBookingServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        String nextJSP = "/error.jsp";
        try {

                String action = request.getPathInfo();
                if("/".equals(action)) {
                        nextJSP = displayHotelAcceuil(request);
                }else if("/detailHotel".equals(action)) {
                        nextJSP = displayDetailHotel(request);
                }else if("/formulaireClient".equals(action)){
                        nextJSP = displayFormulaireClient(request);
                }else if("/reservation".equals(action)){
                        nextJSP = reservationClient(request);
                }else if("/profilUser".equals(action)){
                        nextJSP = profilUser(request);
                }else if("/supprimerReservation".equals(action)){
                        nextJSP = supprimerReservation(request);
                }else if("/supprimerExcursion".equals(action)){
                        nextJSP = supprimerExcursion(request);
                }

        } catch (Exception e) {
                e.printStackTrace();
        }

        /*forward
        RequestDispatcher dispatcher = this.getServletConfig().getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(req,resp);
        */
        System.out.println(nextJSP);
        response.sendRedirect(request.getContextPath()+nextJSP);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        String nextJSP = "/error.jsp";
        try {

                String action = request.getPathInfo();
                if("/Hotelfiltre".equals(action)) {
                        nextJSP = displayHotelFiltrer(request);
                }else if("/connexion".equals(action)) {
                        nextJSP = connexion(request);
                }else if("/inscription".equals(action)){
                        nextJSP = inscription(request);
                }else if("/createReservation".equals(action)) {
                        nextJSP = createReservation(request);
                }else if("/modifierReservation".equals(action)){
                        nextJSP = modifierReservation(request);
                }/*else if("/pizzaVersionDeux".equals(action)) {
                        nextJSP = modeDeuxPourAdminPizza(request);
                }*/

        } catch (Exception e) {
                e.printStackTrace();
        }

        /*forward
        RequestDispatcher dispatcher = this.getServletConfig().getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(req,resp);
        */
        System.out.println(nextJSP);
        response.sendRedirect(request.getContextPath()+nextJSP);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private String displayHotelAcceuil(HttpServletRequest req)
			throws ServletException {
            try {
                    HotelClient hotel = new HotelClient();
                    ClientResponse response = hotel.findAllByDisponibilite_JSON(ClientResponse.class);
                    JSONArray result = response.getEntity(JSONArray.class);

                    ClientResponse responseChamprePerHotel = hotel.findHotelAndChambreCountDisponible_JSON(ClientResponse.class);
                    JSONArray resultChambrePerHotel = responseChamprePerHotel.getEntity(JSONArray.class);
                    //for(int i = 0; i < result.length();i++){
                    //}
                    //System.out.println("Start...");
                    /*for(HebookingHotel test : data){
                        System.out.println(" id Hotel "+test.getHotelId());
                    }*/
                    //System.out.println(response.getEntity(JSONArray.class).length());
                    //System.out.println("End...");
                    req.getSession().setAttribute("ListHotelJSONArray",result);
                    req.getSession().setAttribute("ListHotelChambreJSONArray",resultChambrePerHotel);
                    
            } catch (Exception se) {
                    se.printStackTrace();
                    throw new ServletException("error Listing Hotel " + se);
            }

            return "/index.jsp";
	}
    
    private String displayHotelFiltrer(HttpServletRequest req)
			throws ServletException {
            try {
                    String ville = req.getParameter("ville");
                    String prixInf = req.getParameter("prixInf");
                    String prixSup = req.getParameter("prixSup");
                    String prix = "-1";
                    
                    String nombreEtoile = req.getParameter("nombreEtoile");
                    
                    String error = "";
                    
                    if(ville.equals("") || !ville.matches("^[a-zA-Z]{1,20}$")){
                        error += " attribut ville non ou mal renseigné, ";
                        ville="null";
                    }
                    
                    if(prixInf.equals(prixSup) && prixInf.matches("^[0-9]{2,4}.[0-9]{2}$")){
                            prix = prixInf;
                            prixInf = "-1";
                            prixSup = "-1";
                    }
                    
                    if(prixInf.equals("") || !prixInf.matches("^[0-9]{2,4}.[0-9]{2}$")){
                        error += " attribut prixInf non ou mal renseigné, ";
                        prixInf = "-1";
                    }
                    
                    if(prixSup.equals("") || !prixSup.matches("^[0-9]{2,4}.[0-9]{2}$")){
                        error += " attribut prixSup non ou mal renseigné, ";
                        prixSup = "-1";
                    }
                    
                    if(nombreEtoile.equals("") || !nombreEtoile.matches("^[0-5]{1}$")){
                        error += " attribut nombre Etoile non ou mal renseigné, ";
                        nombreEtoile = "-1";
                    }

                    HotelClient hotel = new HotelClient();
                    ClientResponse response = hotel.findAllByFilter_JSON(ClientResponse.class, nombreEtoile, prixSup, prix, prixInf, ville);
                    JSONArray result = response.getEntity(JSONArray.class);

                    ClientResponse responseChamprePerHotel = hotel.findHotelAndChambreCountDisponible_JSON(ClientResponse.class);
                    JSONArray resultChamprePerHotel = responseChamprePerHotel.getEntity(JSONArray.class);
                     
                    
                    
                    req.getSession().setAttribute("ListHotelJSONArray",result);
                    req.getSession().setAttribute("ListHotelChambreJSONArray",resultChamprePerHotel);
                    
                    req.getSession().setAttribute("nombreEtoile",nombreEtoile);
                    req.getSession().setAttribute("ville",ville);
                    req.getSession().setAttribute("prixInf",prixInf);
                    req.getSession().setAttribute("prixSup",prixSup);
                    
                    req.getSession().setAttribute("error", error);
            } catch (Exception se) {
                    se.printStackTrace();
                    throw new ServletException("error Listing Hotel " + se);
            }

            return "/index.jsp";
	}
    
    
    private String displayDetailHotel(HttpServletRequest req)
			throws ServletException {
            try {
                    HotelClient hotel = new HotelClient();
                    ExcursionClient excursion = new ExcursionClient();
                    ChambreClient chambre = new ChambreClient();
                    
                    ClientResponse responseHotel = hotel.findWithoutLoop_JSON(ClientResponse.class,req.getParameter("id"));
                    
                    JSONObject result = responseHotel.getEntity(JSONObject.class);
                    
                    
                    HebookingHotel hebhotel = new HebookingHotel(); 
                    hebhotel.setHotelId(Long.valueOf(String.valueOf(result.get("idHotel"))));
                    hebhotel.setHotelDescription((String) result.get("description"));
                    hebhotel.setHotelEtoile((Integer) result.get("nombreEtoile"));
                    if(result.get("imageHotel") != null){
                        hebhotel.setHotelImage(String.valueOf(result.get("imageHotel")));
                    }
                    hebhotel.setHotelNbreChambre((Integer) result.get("nombreChambre"));
                    hebhotel.setHotelNom((String) result.get("nomHotel"));
                    hebhotel.setHotelVille((String) result.get("ville"));
                    
                    Collection<HebookingExcursion> excursionList = new ArrayList<HebookingExcursion>();
                    //Collection<HebookingChambre> chambreList = new ArrayList<HebookingChambre>();
                    
                    //System.out.println("l'id de L'Hotel : "+String.valueOf(result.get("idHotel")));
                    ClientResponse responseExcursion = excursion.findAllByFilter_JSON(ClientResponse.class, "-1", "-1", "null", "-1", "-1", String.valueOf(result.get("idHotel")), "-1");
                    //System.out.println("Response "+responseExcursion);
                    JSONArray resultExcursion = responseExcursion.getEntity(JSONArray.class);
                    for(int i = 0 ; i < resultExcursion.length() ; i++){
                        
                        JSONObject jsonExcursion = resultExcursion.getJSONObject(i);
                        HebookingExcursion excursionR = new HebookingExcursion();
                        
                        excursionR.setExcursionId(Long.valueOf(String.valueOf(jsonExcursion.get("idExcursion"))));
                        excursionR.setExcursionPrix((Double) jsonExcursion.get("prix"));
                        excursionR.setExcursionDateDebut(new Date((Long)jsonExcursion.get("dateDebut")));
                        excursionR.setExcursionDateFin(new Date ((Long)jsonExcursion.get("dateFin")));
                        excursionR.setExcursionTheme((String) jsonExcursion.get("theme"));
                        excursionR.setExcursionNombrePlace((Integer) jsonExcursion.get("nombrePlace"));
                        
                        
                        excursionList.add(excursionR);
                    }
                    hebhotel.setHebookingExcursionCollection(excursionList);

                    /*
                    ClientResponse responseChambre = chambre.findChambreByHotel_JSON(ClientResponse.class, String.valueOf(result.get("idHotel")));
                    JSONArray resultChambre = responseChambre.getEntity(JSONArray.class);
                    
                    for(int i = 0; i< resultChambre.length(); i++){
                        
                        JSONObject jsonChambre = resultChambre.getJSONObject(i);
                        
                        HebookingChambre chambreR = new HebookingChambre();
                        chambreR.setChambreId(Long.valueOf(String.valueOf(jsonChambre.get("idChambre"))));
                        chambreR.setChambreNumero((Integer) jsonChambre.get("numero"));
                        chambreR.setChambreEtage((String) jsonChambre.get("etage"));
                        chambreR.setChambrePrix((Double) jsonChambre.get("prix"));
                        chambreR.setChambreCategorie((String) jsonChambre.get("categorie"));
                        
                        chambreList.add(chambreR);
                    }
                    hebhotel.setHebookingChambreCollection(chambreList);
                   */
                    ClientResponse responseChambreDispo = chambre.findChambreByHotelDisponible_JSON(ClientResponse.class, String.valueOf(result.get("idHotel")));
                    JSONArray resultChambre = responseChambreDispo.getEntity(JSONArray.class);
                    Collection<HebookingChambre> chambreDispo = new ArrayList<HebookingChambre>();
                    for(int i = 0; i< resultChambre.length(); i++){
                        
                        JSONObject jsonChambre = resultChambre.getJSONObject(i);
                        
                        HebookingChambre chambreR = new HebookingChambre();
                        chambreR.setChambreId(Long.valueOf(String.valueOf(jsonChambre.get("idChambre"))));
                        chambreR.setChambreNumero((Integer) jsonChambre.get("numero"));
                        chambreR.setChambreEtage((String) jsonChambre.get("etage"));
                        chambreR.setChambrePrix((Double) jsonChambre.get("prix"));
                        chambreR.setChambreCategorie((String) jsonChambre.get("categorie"));
                        
                        chambreDispo.add(chambreR);
                    }
                    
                    ClientResponse responseChambreNoDispo = chambre.findChambreByHotelNoDisponible_JSON(ClientResponse.class, String.valueOf(result.get("idHotel")));
                    JSONArray resultChambreNo = responseChambreNoDispo.getEntity(JSONArray.class);
                    Collection<HebookingChambre> chambreNoDispo = new ArrayList<HebookingChambre>();
                    for(int i = 0; i< resultChambreNo.length(); i++){
                        
                        JSONObject jsonChambre = resultChambreNo.getJSONObject(i);
                        
                        HebookingChambre chambreR = new HebookingChambre();
                        chambreR.setChambreId(Long.valueOf(String.valueOf(jsonChambre.get("idChambre"))));
                        chambreR.setChambreNumero((Integer) jsonChambre.get("numero"));
                        chambreR.setChambreEtage((String) jsonChambre.get("etage"));
                        chambreR.setChambrePrix((Double) jsonChambre.get("prix"));
                        chambreR.setChambreCategorie((String) jsonChambre.get("categorie"));
                        
                        chambreNoDispo.add(chambreR);
                    }
                    req.getSession().setAttribute("ChambreDispo",chambreDispo);
                    req.getSession().setAttribute("ChambreNoDispo",chambreNoDispo);

                    req.getSession().setAttribute("HotelDetail",hebhotel);
                   

            } catch (Exception se) {
                    se.printStackTrace();
                    throw new ServletException("error Listing Hotel " + se);
            }

            return "/HotelDetail.jsp";
	}
    
    private String displayFormulaireClient(HttpServletRequest req)
			throws ServletException {
            
            String type = req.getParameter("type");
            if(type.equals("connexion")){
                req.getSession().setAttribute("type", "Connexion");
                req.getSession().setAttribute("typeAction", "connexion");
                req.getSession().setAttribute("typeSubmit", "Connexion");
            }else if(type.equals("inscription")){
                req.getSession().setAttribute("type", "d'Incription");
                req.getSession().setAttribute("typeAction", "inscription");
                req.getSession().setAttribute("typeSubmit", "S'inscrire");
            }
            
            return "/connexionOrInscription.jsp";
	}
    
    private String connexion(HttpServletRequest req)
			throws ServletException {
            try {
                
                String  prenom = req.getParameter("prenom");
                String nom = req.getParameter("nom");
                String motdepasse= req.getParameter("motdepasse");
                
                System.out.println(prenom + " nom = "+ nom +" mdp : "+motdepasse );
                
                ClientClient clientRST = new ClientClient();
                ClientResponse resultatClient = clientRST.connectionClient_JSON(ClientResponse.class, prenom, motdepasse, nom);
                //clientRST.
                HebookingClient client = new HebookingClient();
                
                JSONObject result = resultatClient.getEntity(JSONObject.class);
                //JSONObject result = resulta.getJSONObject(0);
                System.out.println(result);
                
                client.setClientId(result.getLong("idClient"));
                client.setClientMotDePasse(result.getString("motDePasse"));
                client.setClientNom(result.getString("nomClient"));
                client.setClientPrenom(result.getString("prenomClient"));
                if(client.getClientNom() != null && !client.getClientNom().equals("null")){
                    req.getSession().setAttribute("user", client);
                }
            } catch (Exception se) {
                    se.printStackTrace();
                    throw new ServletException("error Connexion ! détail ==> "+se);
            }

            return "/index.jsp";
	}
    
    private String inscription(HttpServletRequest req)
			throws ServletException {
            try {
                
                String  prenom = req.getParameter("prenom");
                String nom = req.getParameter("nom");
                String motdepasse= req.getParameter("motdepasse");

               
                ClientClient clientRST = new ClientClient();
                JSONObject request = new JSONObject();
                
                request.put("id", 0);
                request.put("nomClient", nom);
                request.put("prenomClient", prenom);
                request.put("motdepasse", motdepasse);
                
                                
                clientRST.createSecure_JSON(request);
                
            } catch (Exception se) {
                    se.printStackTrace();
                    throw new ServletException("error Inscription ! détail ==> "+se);
            }
            return connexion(req);
	}
    
    private String reservationClient(HttpServletRequest req)
			throws ServletException {
                 String next = "/HotelDetail.jsp";
            try {
                
                String  mode = req.getParameter("mode");
                

                if(req.getSession().getAttribute("user") != null){
                    
                    if(mode.equals("create")){
                        
                        
                        
                        String idHotel = req.getParameter("idHotel");
                        String idChambre = req.getParameter("idChambre");
                        String nomHotel = req.getParameter("nomHotel");
                        String numChambre = req.getParameter("numChambre");
                        
                        ExcursionClient excursion = new ExcursionClient();
                        ClientResponse resultExcursion = excursion.findAllByFilter_JSON(ClientResponse.class, "-1", "-1", "null", "-1", "-1", idHotel, "-1");
                        
                        JSONArray result = resultExcursion.getEntity(JSONArray.class);
                        
                        ArrayList<ExcursionResult> excursResult = new ArrayList<ExcursionResult>();
                        
                        for(int i =0; i< result.length();i++){
                            
                            ExcursionResult excursionRes = new ExcursionResult(); 
                            JSONObject excursionObject = result.getJSONObject(i);
                            
                            excursionRes.setIdExcursion(excursionObject.getLong("idExcursion"));
                            excursionRes.setIdHotel(excursionObject.getLong("idHotel"));
                            excursionRes.setPrix(excursionObject.getDouble("prix"));
                            excursionRes.setTheme(excursionObject.getString("theme"));
                            excursionRes.setNombrePlace(excursionObject.getInt("nombrePlace"));
                            excursionRes.setDateDebut(new java.util.Date(excursionObject.getLong("dateDebut")));
                            excursionRes.setDateFin(new java.util.Date(excursionObject.getLong("dateFin")));
                          
                            excursResult.add(excursionRes);
                        }
                        
                        req.getSession().setAttribute("idHotel", idHotel);
                        req.getSession().setAttribute("idChambre", idChambre);
                        req.getSession().setAttribute("mode", mode);
                        req.getSession().setAttribute("reservationAction", "Hebooking/createReservation");
                        req.getSession().setAttribute("reservationSubmit", "Enregistrer");
                        req.getSession().setAttribute("nomHotel", nomHotel);
                        req.getSession().setAttribute("numChambre", numChambre);
                        req.getSession().setAttribute("ListExcursion", excursResult);
                        
                    }else if(mode.equals("modif")){
                        
                        req.getSession().removeAttribute("idHotel");
                        req.getSession().removeAttribute("idChambre");
                        
                        req.getSession().removeAttribute("mode");
                        req.getSession().removeAttribute("clientResult");
                        
                        req.getSession().removeAttribute("reservationAction");
                        req.getSession().removeAttribute("reservationSubmit");
                        req.getSession().removeAttribute("nomHotel");
                        req.getSession().removeAttribute("numChambre");
                        req.getSession().removeAttribute("ListExcursion");
                        
                        Long id = Long.parseLong(req.getParameter("idReservation"));

                        ReservationClient client = new ReservationClient();
                        //ReservationResult clientResult = client.find_JSON(ReservationResult.class, String.valueOf(id));
                        ClientResponse clientResult_JSON = client.find_JSON(ClientResponse.class, String.valueOf(id));
                        JSONObject clientResult_Object = clientResult_JSON.getEntity(JSONObject.class);
                        ReservationResult clientResult = new ReservationResult();
                        
                        clientResult.setIdReservation(clientResult_Object.getLong("idReservation"));
                        clientResult.setIdClient(clientResult_Object.getLong("idClient"));
                        clientResult.setIdChambre(clientResult_Object.getLong("idChambre"));
                        clientResult.setDateDebut(clientResult_Object.getLong("dateDebut"));
                        clientResult.setDateFin(clientResult_Object.getLong("dateFin"));

                        ArrayList<Long>  idexcurs = new ArrayList<Long>();
                        JSONArray jsonArray = clientResult_Object.getJSONArray("idExcursions");
                        for(int j = 0;j<jsonArray.length();j++){
                            Long idExc = jsonArray.getLong(j);
                            idexcurs.add(idExc);
                        }
                        clientResult.setIdExcursions(idexcurs);
                        clientResult.setNumeroCarteBleu(clientResult_Object.getString("numeroCarteBleu"));
                        System.out.println("Client Object => "+clientResult_Object);
                        
                        System.out.println("client result deb => "+clientResult.getDateDebut());
                        System.out.println("client result fin => "+clientResult.getDateFin());
                        ChambreClient chambreClient = new ChambreClient();
                        ChambreResult chambre = chambreClient.find_JSON(ChambreResult.class, String.valueOf(clientResult.getIdChambre()));
                        
                        ExcursionClient excursion = new ExcursionClient();
                        ClientResponse resultExcursion = excursion.findAllByFilter_JSON(ClientResponse.class, "-1", "-1", "null", "-1", "-1", String.valueOf(chambre.getIdHotel()), "-1");
                        
                        JSONArray result = resultExcursion.getEntity(JSONArray.class);
                        
                        ArrayList<ExcursionResult> excursResult = new ArrayList<ExcursionResult>();
                        
                        for(int i =0; i< result.length();i++){
                            
                            ExcursionResult excursionRes = new ExcursionResult(); 
                            JSONObject excursionObject = result.getJSONObject(i);
                            
                            excursionRes.setIdExcursion(excursionObject.getLong("idExcursion"));
                            excursionRes.setIdHotel(excursionObject.getLong("idHotel"));
                            excursionRes.setPrix(excursionObject.getDouble("prix"));
                            excursionRes.setTheme(excursionObject.getString("theme"));
                            excursionRes.setNombrePlace(excursionObject.getInt("nombrePlace"));
                            
                            excursionRes.setDateDebut(new java.util.Date(excursionObject.getLong("dateDebut")/1000));
                            excursionRes.setDateFin(new java.util.Date(excursionObject.getLong("dateFin")/1000));
                          
                            excursResult.add(excursionRes);
                        }
                        
                        req.getSession().setAttribute("idHotel", null);
                        req.getSession().setAttribute("idChambre", null);
                        
                        req.getSession().setAttribute("mode", mode);
                        req.getSession().setAttribute("clientResult", clientResult);
                        
                        req.getSession().setAttribute("reservationAction", "Hebooking/modifierReservation");
                        req.getSession().setAttribute("reservationSubmit", "Modifier");
                        req.getSession().setAttribute("nomHotel", null);
                        req.getSession().setAttribute("numChambre", null);
                        req.getSession().setAttribute("ListExcursion", excursResult);
                    }
                    
                    next = "/reservation.jsp";
                }else{
                    req.getSession().setAttribute("ErrorMessage","Veuillez vous connecter avant de réaliser une réservation");
                }
               
                
            } catch (Exception se) {
                    se.printStackTrace();
                    throw new ServletException("error Inscription ! détail ==> "+se);
            }
            return next;
	}
    
    private String createReservation(HttpServletRequest req)
			throws ServletException {
        String next = "/HotelDetail.jsp";
        try {
                
                
                if(req.getParameter("date_Debut") != null){
                    
                    HebookingClient endu = (HebookingClient) req.getSession().getAttribute("user");
                    
                    String dateDeb = req.getParameter("date_Debut");
                    String dateF = req.getParameter("date_Fin");
                    String numCarte = req.getParameter("numCarte");
                    String typeCard = "1";
                    
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
                    Date dateDebut = null;  
                    Date dateFin = null;
                      
                    dateDebut = df.parse(dateDeb);  
                    dateFin = df.parse(dateF);

                    System.out.println("Timestamp date début : "+dateDebut.getTime());
                    System.out.println("Timestamp date fin : "+dateFin.getTime());
                    
                    ReservationClient reservation = new ReservationClient();
                    ClientResponse responseCard = reservation.getVerifCard_JSON(ClientResponse.class, typeCard, numCarte);
                    String res = responseCard.getEntity(String.class);
                    
                    System.out.println("Réponse du numéro de la carte : "+res);
                    
                    ArrayList<Long> ExcursionCheck = new ArrayList<Long>();
                    String select[] = req.getParameterValues("excursions"); 
                    if (select != null && select.length != 0) {
                        //out.println("You have selected: ");
                        for (int i = 0; i < select.length; i++) {
                            ExcursionCheck.add(Long.valueOf(select[i]));
                        }
                    }
                    
                    if(res.equals("true")){
                        
                        JSONObject request = new JSONObject();

                        request.put("idReservation", 0);
                        request.put("dateDebut", dateDebut.getTime());
                        request.put("dateFin", dateFin.getTime());
                        request.put("numeroCarteBleu", numCarte);
                        request.put("idClient", endu.getClientId());
                        request.put("idChambre", req.getSession().getAttribute("idChambre"));
                        request.put("idExcursions", ExcursionCheck);
                        System.out.println(request);
                        reservation.createSecure_JSON(request);
                    }else{
                        next = "/reservation.jsp";
                    }         
                    //clientRST.createSecure_JSON(request);
                }else{
                    next = "/reservation.jsp";
                }
               
                req.getSession().setAttribute("Confirmation", "Enregistrement reservation réussi !");
            } catch (Exception se) {
                    se.printStackTrace();
                    throw new ServletException("error createReservation ! détail ==> "+se);
            }
            return next;
	}
    
    private String profilUser(HttpServletRequest req)
			throws ServletException {
        String next = "/error.jsp";
        try {
                
                HebookingClient client = (HebookingClient) req.getSession().getAttribute("user");
                ReservationClient reservaClient = new ReservationClient();
                ClientResponse response = reservaClient.reservationByClient_JSON(ClientResponse.class, String.valueOf(client.getClientId()));
                
                JSONArray arrayResponse = response.getEntity(JSONArray.class);
                
                Collection<ReservationResult> resUpdatable = new ArrayList<ReservationResult>();
                Collection<ReservationResult> resNoUpdatable = new ArrayList<ReservationResult>();
                
                for(int i =0 ; i < arrayResponse.length() ;i++){
                    JSONObject resultat = arrayResponse.getJSONObject(i);
                    
                    ReservationResult reservation = new ReservationResult();
                    
                    reservation.setIdReservation(resultat.getLong("idReservation"));
                    reservation.setIdClient(resultat.getLong("idClient"));
                    reservation.setIdChambre(resultat.getLong("idChambre"));
                    reservation.setDateDebut(resultat.getLong("dateDebut"));
                    reservation.setDateFin(resultat.getLong("dateFin"));
                    
                    ArrayList<Long>  idexcurs = new ArrayList<Long>();
                    JSONArray jsonArray = resultat.getJSONArray("idExcursions");
                    for(int j = 0;j<jsonArray.length();j++){
                        Long idExc = jsonArray.getLong(j);
                        idexcurs.add(idExc);
                    }
                    reservation.setIdExcursions(idexcurs);
                    reservation.setNumeroCarteBleu(resultat.getString("numeroCarteBleu"));
                    
                    if(reservation.getDateDebut() > (new Date()).getTime() ){
                        resUpdatable.add(reservation);
                    }else{
                        resNoUpdatable.add(reservation);
                    }
                    System.out.println(reservation);
               }
               /*
               ExcursionClient excursionList = new ExcursionClient();
               
               ClientResponse excursionClient = excursionList.getExcursionByClient_JSON(ClientResponse.class, String.valueOf(client.getClientId()));
               JSONArray excursionClientArray = excursionClient.getEntity(JSONArray.class);
               
               ArrayList<ExcursionResult> excursionResult = new ArrayList<ExcursionResult>();
               
               for(int i =0; i <excursionClientArray.length() ;i++){
                   
                   ExcursionResult tamponResult = new ExcursionResult();
                   JSONObject excursionJSON = excursionClientArray.getJSONObject(i);
                   
                   //tamponResult.setDateDebut(new Date(excursionJSON.getLong("")));
                  // tamponResult.setDateFin(new Date(excursionJSON.getLong("")));
                   tamponResult.setIdExcursion(excursionJSON.getLong("idExcursion"));
                   tamponResult.setIdHotel(excursionJSON.getLong("idHotel"));
                   tamponResult.setNombrePlace(excursionJSON.getInt("nombrePlace"));
                   tamponResult.setPrix(excursionJSON.getDouble("prix"));
                   tamponResult.setTheme(excursionJSON.getString("theme"));
                   
                   excursionResult.add(tamponResult);
               }*/
               
               req.getSession().setAttribute("resUpdatable", resUpdatable);
               req.getSession().setAttribute("resNoUpdatable", resNoUpdatable);

                //req.getSession().setAttribute("ListExcursion",excursionResult);
                //req.getSession().setAttribute("",);
                next = "/ProfilUser.jsp";
                
                
        }catch (Exception se) {
                    se.printStackTrace();
                    throw new ServletException("error profilUser ! détail ==> "+se);
        }
            return next;
	}
    
    private String modifierReservation(HttpServletRequest req)
			throws ServletException {
        String next = "/error.jsp";
        try {
            if(req.getParameter("date_Debut") != null){
                    
                    HebookingClient endu = (HebookingClient) req.getSession().getAttribute("user");
                    
                    String dateDeb = req.getParameter("date_Debut");
                    String dateF = req.getParameter("date_Fin");
                    String numCarte = req.getParameter("numCarte");
                    String idRe = req.getParameter("reservation");
                    String typeCard = "1";
                    
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
                    Date dateDebut = null;  
                    Date dateFin = null;
                      
                    dateDebut = df.parse(dateDeb);  
                    dateFin = df.parse(dateF);

                    System.out.println("Timestamp date début : "+dateDebut.getTime());
                    System.out.println("Timestamp date fin : "+dateFin.getTime());
                    
                    ReservationClient reservation = new ReservationClient();
                    ClientResponse responseCard = reservation.getVerifCard_JSON(ClientResponse.class, typeCard, numCarte);
                    String res = responseCard.getEntity(String.class);
                    
                    System.out.println("Réponse du numéro de la carte : "+res);
                    
                     ArrayList<Long> ExcursionCheck = new ArrayList<Long>();
                    String select[] = req.getParameterValues("excursions"); 
                    if (select != null && select.length != 0) {
                        //out.println("You have selected: ");
                        for (int i = 0; i < select.length; i++) {
                            ExcursionCheck.add(Long.valueOf(select[i]));
                        }
                    }
                    
                    if(res.equals("true")){
                        
                        JSONObject request = new JSONObject();

                        request.put("idReservation", idRe);
                        request.put("dateDebut", dateDebut.getTime());
                        request.put("dateFin", dateFin.getTime());
                        request.put("numeroCarteBleu", numCarte);
                        request.put("idClient", endu.getClientId());
                        request.put("idChambre", ((ReservationResult)req.getSession().getAttribute("clientResult")).getIdChambre() );
                        request.put("idExcursions", ExcursionCheck);
                        //System.out.println(request);
                        
                        reservation.editReservation_JSON(ClientResponse.class, request);
                    }else{
                        next = "/reservation.jsp";
                    }         
                    //clientRST.createSecure_JSON(request);
                }else{
                    next = "/reservation.jsp";
                }
               
                req.getSession().setAttribute("Confirmation", "Enregistrement reservation réussi !");
            } catch (Exception se) {
                    se.printStackTrace();
                    throw new ServletException("error createReservation ! détail ==> "+se);
            }
            return next;
    }
    
    private String supprimerReservation(HttpServletRequest req)
			throws ServletException {
        String next = "/error.jsp";
        try {
            String id = req.getParameter("id");    
            System.out.println("id reserv => "+id);
            ReservationClient clientReserv = new ReservationClient();
            ClientResponse resDelete = clientReserv.deleteReservation_JSON(ClientResponse.class, id);
            //JSONObject deleteResult = resDelete.getEntity(JSONObject.class);
            System.out.println("Resultat ligne 621 fichier HEBookingServlet => "+resDelete);
            
        }catch (Exception se) {
                    se.printStackTrace();
                    throw new ServletException("error profilUser ! détail ==> "+se);
        }
        return next;
    }
    
    private String supprimerExcursion(HttpServletRequest req)
			throws ServletException {
        String next = "/error.jsp";
        try {
            Long idReserv = Long.parseLong(req.getParameter("idReserv"));
            Long idExcurs = Long.parseLong(req.getParameter("idExcurs"));
            
            ExcursionClient clientExcursion = new ExcursionClient();
            ClientResponse excDelete = clientExcursion.deleteInscriptionExcursion_JSON(ClientResponse.class, String.valueOf(idExcurs), String.valueOf(idExcurs));
            Boolean deleteExcurs = excDelete.getEntity(Boolean.class);
            System.out.println("Resultat ligne 641 fichier HEBookingServlet => "+deleteExcurs);
                
        }catch (Exception se) {
                    se.printStackTrace();
                    throw new ServletException("error profilUser ! détail ==> "+se);
        }
        return next;
    }
}
