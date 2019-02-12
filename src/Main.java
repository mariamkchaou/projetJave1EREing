import entities.*;
import error.exception.ExceptionDeal;
import service.ServiceClient;
import service.ServiceDeal;
import service.ServiceFournisseur;
import service.ServiceVente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<Client> clients;

    public static void main(String[] args) throws ExceptionDeal {
        /**
         * remplir categorie
         */
        LocalDate currentDate = LocalDate.now();

        List<Categories> categories = new ArrayList<>();
        Categories categories1 = new Categories(1L, "spa");
        Categories categories2 = new Categories(2L, "restauration");
        Categories categories3 = new Categories(3L, "hôtel");
        categories.add(categories1);
        categories.add(categories2);
        categories.add(categories3);
        try {
            clients = new ArrayList<>();
            Client client1=ServiceClient.creat("mariam", "nabeul", "40323232", "mariam@5515", "00000000", clients);
            Client client2=ServiceClient.creat("hana", "mahdia", "40323235", "hana@5515", "10000000", clients);
            Client client3=ServiceClient.creat("tesnime", "sousse", "40323238", "tesnime@5515", "00000002", clients);
            Client client4=ServiceClient.creat("Ali", "sousse", "40323237", "mariam@5515", "00000008", clients);
            clients.add(client1);
            clients.add(client2);
            clients.add(client3);
            clients.add(client4);
            /*
             fournisseur
             */
            Fournisseur fournisseur = ServiceFournisseur.creat("Jon", "Tunis", "55223665",
                    "JonFournisseur@gmail.com", "12003665", 8f, 1);
            /**
             * remplir Deal
             */
            List<Deal> deals = new ArrayList<>();
            Deal deal1 = ServiceDeal.creat(" Profitez de nos offres spéciales! Petits Prix. Réservez Maintenant. Sans frais de réservation. ",
                    "Palace", 100f, 120f, currentDate, currentDate.plusMonths(2), fournisseur, categories3,"100");
            deal1.toString();
            Deal deal2 = ServiceDeal.creat(" Profitez de nos offres spéciales! Petits Prix. Réservez Maintenant. Sans frais de réservation. ",
                    "Talaso", 100f, 120f, currentDate, currentDate.plusMonths(2), fournisseur, categories3,"101");
            Deal deal3 = ServiceDeal.creat(" Profitez de nos offres spéciales! Petits Prix. Réservez Maintenant. Sans frais de réservation. ",
                    "Vacance", 100f, 120f, currentDate, currentDate.plusMonths(2), fournisseur, categories3,"103");
            deals.add(deal1);
            deals.add(deal2);
            deals.add(deal3);
            /**
             * remplir Vente
             */
            List<Vente> ventes=new ArrayList<>();
            LocalDate dateAchat= LocalDate.now();


            Vente vente1= ServiceVente.creeat( dateAchat,  deal1.getCode(),  client1.getCin(),  1f,clients, deals);
           /* Vente vente2= ServiceVente.creeat( dateAchat,  deal2,  client2,  3f,clients, deals);
            Vente vente3= ServiceVente.creeat( dateAchat,  deal1,  client3,  9f,clients, deals);
            Vente vente4= ServiceVente.creeat( dateAchat,  deal1,  client1,  1f,clients, deals);
            Vente vente5= ServiceVente.creeat( dateAchat,  deal1,  client1,  1f,clients, deals);
            Vente vente6= ServiceVente.creeat( dateAchat,  deal1,  client1,  1f,clients, deals);
            Vente vente7= ServiceVente.creeat( dateAchat,  deal1,  client1,  1f,clients, deals);
            Vente vente8= ServiceVente.creeat( dateAchat,  deal1,  client1,  1f,clients, deals);
            Vente vente9= ServiceVente.creeat( dateAchat,  deal1,  client1,  1f,clients, deals);
            Vente vente10= ServiceVente.creeat( dateAchat,  deal1,  client1,  1f,clients, deals);
            Vente vente11= ServiceVente.creeat( dateAchat,  deal1,  client1,  1f,clients, deals);
            ServiceClient.chercheByCIN("00000000", clients);
            ServiceClient.afficheOne(ServiceClient.chercheByCIN("00000000", clients));*/
            /**
             *
             */

            //*********************** deals


         /*   ServiceDeal.chercheDealEnCoursByCtagorie(deals, "hôtel");
            ServiceDeal.affiche(deals);
            ServiceDeal.afficheClientByDeal(deal1);
            ServiceDeal.afficheListClientByDeal(deals);*/

        } catch (ExceptionDeal e) {
            System.out.println(e.getError().getDesc());
        }


    }
}
