import entities.Categories;
import entities.Client;
import entities.Deal;
import entities.Fournisseur;
import error.exception.ExceptionDeal;
import service.ServiceClient;
import service.ServiceDeal;
import service.ServiceFournisseur;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<Client> clients;

    public static void main(String[] args) throws ExceptionDeal {
        /**
         * replir categorie
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
            clients.add(ServiceClient.creat("mariam", "nabeul", "40323232", "mariam@5515", "00000000", clients));
            clients.add(ServiceClient.creat("hana", "mahdia", "40323234", "hana@5515", "10000000", clients));
            clients.add(ServiceClient.creat("hana", "mahdia", "40323234", "mariam@5515", "00000002", clients));
                //**** fournisseur
            Fournisseur fournisseur= ServiceFournisseur.creat("Jon", "Tunis", "55223665",
                    "JonFournisseur@gmail.com", "12003665", 8f, 1);

                 ServiceClient.chercheByCIN("00000000", clients);
                 ServiceClient.afficheOne(ServiceClient.chercheByCIN("00000000", clients));


            //*********************** deals
            List<Deal> deals = new ArrayList<>();
            Deal deal1 = ServiceDeal.creat(" Profitez de nos offres spéciales! Petits Prix. Réservez Maintenant. Sans frais de réservation. ",
                    "palace", 100f, 120f, currentDate, currentDate.plusMonths(2), fournisseur, categories3);
            deal1.toString();
            Deal deal2 = ServiceDeal.creat(" Profitez de nos offres spéciales! Petits Prix. Réservez Maintenant. Sans frais de réservation. ",
                    "palace", 100f, 120f, currentDate, currentDate.plusMonths(2), fournisseur, categories3);
            Deal deal3 = ServiceDeal.creat(" Profitez de nos offres spéciales! Petits Prix. Réservez Maintenant. Sans frais de réservation. ",
                    "palace", 100f, 120f, currentDate, currentDate.plusMonths(2), fournisseur, categories3);
            deals.add(deal1);
            deals.add(deal2);
            deals.add(deal3);

            ServiceDeal.chercheDealEnCoursByCtagorie(deals, "hôtel");
            ServiceDeal.affiche(deals);
            ServiceDeal.afficheClientByDeal(deal1);
            ServiceDeal.afficheListClientByDeal(deals);

        } catch (ExceptionDeal e) {
            System.out.println(e.getError().getDesc());
        }


    }
}
