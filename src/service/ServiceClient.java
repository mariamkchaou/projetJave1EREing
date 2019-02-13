package service;

import entities.Client;

import entities.Vente;
import error.exception.*;
import sun.applet.Main;

import java.util.ArrayList;
import java.util.List;

public class ServiceClient {


    /**
     * o suppose que le deal de client est vide lors de sa creation
     *
     * @param nom
     * @param adresse
     * @param téléphone
     * @param email
     * @param cin
     * @throws ExceptionDeal
     */
    public static Client creat(String nom, String adresse, String téléphone,
                               String email, String cin,List<Client> clients) throws ExceptionDeal {
        if (nom == null || adresse == null || téléphone == null || email == null || cin == null) {
            throw new ChampsObligatoireExceptionn();

        }
        /**
         *    todo   je ne peut pas cree un client  deja existe a le mm nombre de cin
         */

        try {

            if (!(cin.length() == 8)) {
                throw new CINException();
            }
            Integer.parseInt(cin);
        } catch (NumberFormatException nfe) {
            throw new CINException();
        }

        try {
            Integer.parseInt(téléphone);
        } catch (NumberFormatException nfe) {
            throw new NumeroTelException();
        }

        if (!email.contains("@")) {
            throw new EmailFormatException();
        }

        try{
            Client client1=chercheByCIN(cin, clients);
            throw  new ClientInscrit();
        }catch (ClientNotFound e){
            List<Vente> ventes=new ArrayList<>();

            Client client = new Client(nom, adresse, téléphone, email, cin, ventes);
            return client;

        }


   }

    /**
     * @param clients
     * @throws ExceptionDeal
     */
    public static void affiche(List<Client> clients) {

        int i = 0;
        while (i < clients.size()) {
            System.out.println(clients.get(i).toString());
            System.out.println("*******************\n");
            i++;
        }
    }

    /**
     *
     * @param client
     * @throws ExceptionDeal
     */
    public static void afficheOne(Client client)  {
        System.out.println("*******************\n");
        System.out.println(client.toString());
        System.out.println("*******************\n");
    }

    /**
     * Rechercher un client par son CIN
     *
     * @param cin
     * @param clients
     * @return
     * @throws ExceptionDeal
     */
    public static Client chercheByCIN(String cin, List<Client> clients) throws ExceptionDeal {

        int i = 0;
        while (i < clients.size()) {
            if (clients.get(i).getCin().equals(cin)) {
                return clients.get(i);
            } else {
                i++;
            }
        }
        throw new ClientNotFound();
    }
    /**
     * Créer un client
     */
    /**
     * Rechercher un client par son numéro de téléphone
     *
     * @param tel
     * @param clients
     * @return
     * @throws ExceptionDeal
     */
    public static Client chercheByTel(String tel, List<Client> clients) throws ExceptionDeal {

        int i = 0;
        while (i < clients.size()) {
            if (clients.get(i).getTéléphone().equals(tel)) {
                return clients.get(i);
            } else {
                i++;
            }
        }
        throw new ClientNotFound();
    }


}
