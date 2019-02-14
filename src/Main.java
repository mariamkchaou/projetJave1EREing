import entities.*;
import error.ErrorsDeal;
import error.exception.ExceptionDeal;
import org.omg.CORBA.Object;
import responce.Menu;
import service.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static List<Client> clients;

    public static LocalDate currentDate = LocalDate.now();

    public static void main(String[] args) throws ExceptionDeal {

        try {

            /**
             *initilisation client
             */
            clients = new ArrayList<>();
            clients = ServiceClient.loadClientFile();
            /**
             * initiaisation fournisseur
             */

            List<Fournisseur> fournisseurs = ServiceFournisseur.loadFournisseurFile();
            ServiceFournisseur.saveFournisseurFile(fournisseurs);

            /**
             * initialisation categorie
             */

            List<Categories> categories = ServiseCategorie.loadCategorieFile();
            ServiseCategorie.saveCategorieFile(categories);
         List<Deal> deals = remplirDeal(categories, fournisseurs);
            ServiceDeal.saveCategorieFile(deals);
            List<Deal> deal=ServiceDeal.loadCategorieFile(categories,fournisseurs);
            System.out.println(deal.size());

            ServiceDeal.saveCategorieFile(deal);
            // String choix = Menu.lireChoix();

        } catch (ExceptionDeal e) {
            System.out.println(e.getError().getDesc());
        } catch (Exception e) {
            System.out.println(ErrorsDeal.ERRORS_GENERALE.getDesc() + "" + e);


        }
    }

    /**
     * @return
     */
    public static HashMap<String, String> lireClientInformation() {
        Scanner scan = new Scanner(System.in);
        HashMap<String, String> map = new HashMap<>();
        System.out.println("Donner  votre nom");
        String name = scan.nextLine();
        map.put("nom", name);
        System.out.println("Donner  votre adresse");
        String adresse = scan.nextLine();
        map.put("adresse", adresse);
        System.out.println("Donner  votre numero de telephone");
        String téléphon = scan.nextLine();
        map.put("telephone", téléphon);
        System.out.println("Donner  votre numéro cin ");
        String cin = scan.nextLine();
        map.put("cin", cin);
        System.out.println("Donner  votre mail ");
        String mail = scan.nextLine();
        map.put("mail", mail);
        return map;
    }

    /**
     * @return
     */


    /**
     * @return
     */
    public static HashMap<String, String> lireDealInformation() {
        Scanner scan = new Scanner(System.in);
        HashMap<String, String> map = new HashMap<>();
        System.out.println("Donner le nom de Deal ");
        String nom = scan.nextLine();
        map.put("nom", nom);
        System.out.println("Donner le code de Deal ");
        String code = scan.nextLine();
        map.put("code", code);
        System.out.println("Donner le description de Deal ");
        String desc = scan.nextLine();
        map.put("desc", desc);
        System.out.println("Donner le prix initial de Deal ");
        String prixInitial = scan.nextLine();
        map.put("prixInitial", prixInitial);
        System.out.println("Donner le prix  de Deal ");
        String prixDeal = scan.nextLine();
        map.put("prixDeal", prixDeal);
        System.out.println("Donner le code de fournisseur.");
        String CodeFournisseur = scan.nextLine();
        map.put("CodeFournisseur", CodeFournisseur);
        System.out.println("Donner le code de categorie. :");
        System.out.println(" 1-spa  restauration hôtel");
        System.out.println(" 2-  restauration");
        System.out.println(" 3- hôtel");
        String categoriesCode = scan.nextLine();
        map.put("categoriesCode", categoriesCode);
        return map;
    }

    /**
     * @return
     */
    public static HashMap<String, String> lireFourniseurInformation() {
        Scanner scan = new Scanner(System.in);
        HashMap<String, String> map = new HashMap<>();
        System.out.println("Donner  votre nom");
        String name = scan.nextLine();
        map.put("nom", name);
        System.out.println("Donner  votre adresse");
        String adresse = scan.nextLine();
        map.put("adresse", adresse);
        System.out.println("Donner  votre numero de telephone");
        String téléphon = scan.nextLine();
        map.put("telephone", téléphon);
        System.out.println("Donner  votre numéro cin ");
        String cin = scan.nextLine();
        map.put("cin", cin);
        System.out.println("Donner  votre mail ");
        String mail = scan.nextLine();
        map.put("mail", mail);
        System.out.println("Donner  votre heures de travaille ");
        String heure = scan.nextLine();
        map.put("heure", heure);
        System.out.println("Donner  votre code");
        String code = scan.nextLine();
        map.put("code", code);
        return map;
    }

    public static List<Deal> remplirDeal(List<Categories> categoriesList, List<Fournisseur> fournisseurs) throws ExceptionDeal {

        List<Deal> deals = new ArrayList<>();
        Deal deal1 = ServiceDeal.creat(" Profitez de nos offres spéciales! Petits Prix. Réservez Maintenant. Sans frais de réservation. ",
                "Palace", 100f, 120f, currentDate, currentDate.plusMonths(2), 123, 1, "100", categoriesList, fournisseurs);
        Deal deal2 = ServiceDeal.creat(" Profitez de nos offres spéciales! Petits Prix. Réservez Maintenant. Sans frais de réservation. ",
                "Talaso", 100f, 120f, currentDate, currentDate.plusMonths(2), 123, 1, "101",
                categoriesList, fournisseurs);
        Deal deal3 = ServiceDeal.creat(" Profitez de nos offres spéciales! Petits Prix. Réservez Maintenant. Sans frais de réservation. ",
                "Vacance", 100f, 120f, currentDate, currentDate.plusMonths(2), 123, 3, "103", categoriesList, fournisseurs);
        deals.add(deal1);
        deals.add(deal2);
        deals.add(deal3);
        return  deals;
    }
}

   /* List<Vente> ventes = new ArrayList<>();
    LocalDate dateAchat = LocalDate.now();
    Vente vente1 = ServiceVente.creeat(dateAchat, deal1.getCode(), client1.getCin(), 1f, clients, deals);
    Vente vente2= ServiceVente.creeat( dateAchat,  deal2,  client2,  3f,clients, deals);
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



