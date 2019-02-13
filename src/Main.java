import entities.*;
import error.ErrorsDeal;
import error.exception.ExceptionDeal;
import org.omg.CORBA.Object;
import service.ServiceClient;
import service.ServiceDeal;
import service.ServiceFournisseur;
import service.ServiceVente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

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
            /**
             * initialisation
             */
                /*
             fournisseur
             */

            List<Fournisseur> fournisseurs = new ArrayList<>();
            Fournisseur fournisseur = ServiceFournisseur.creat("Jon", "Tunis", "55223665",
                    "JonFournisseur@gmail.com", "12003665", 8f, 1);
            fournisseurs.add(fournisseur);

            /**
             *
             */
            clients = new ArrayList<>();
            Client client1 = ServiceClient.creat("mariam", "nabeul", "40323232", "mariam@5515", "00000000", clients);
            Client client2 = ServiceClient.creat("hana", "mahdia", "40323235", "hana@5515", "10000000", clients);
            Client client3 = ServiceClient.creat("tesnime", "sousse", "40323238", "tesnime@5515", "00000002", clients);
            Client client4 = ServiceClient.creat("Ali", "sousse", "40323237", "mariam@5515", "00000008", clients);
            clients.add(client1);
            clients.add(client2);
            clients.add(client3);
            clients.add(client4);

            /**
             * remplir Deal
             */

            List<Deal> deals = new ArrayList<>();
            Deal deal1 = ServiceDeal.creat(" Profitez de nos offres spéciales! Petits Prix. Réservez Maintenant. Sans frais de réservation. ",
                    "Palace", 100f, 120f, currentDate, currentDate.plusMonths(2), fournisseur, categories3, "100");
            deal1.toString();
            Deal deal2 = ServiceDeal.creat(" Profitez de nos offres spéciales! Petits Prix. Réservez Maintenant. Sans frais de réservation. ",
                    "Talaso", 100f, 120f, currentDate, currentDate.plusMonths(2), fournisseur, categories3, "101");
            Deal deal3 = ServiceDeal.creat(" Profitez de nos offres spéciales! Petits Prix. Réservez Maintenant. Sans frais de réservation. ",
                    "Vacance", 100f, 120f, currentDate, currentDate.plusMonths(2), fournisseur, categories3, "103");
            deals.add(deal1);
            deals.add(deal2);
            deals.add(deal3);
            /**
             * verifier votre choix
             */
            String choix = lireChoix();

            if (choix.equals("1")) {
                HashMap<String, String> map = lireClientInformation();
                Client client0 = ServiceClient.creat(map.get("nom"), map.get("adresse"), map.get("telephone"), map.get("mail"), map.get("cin"), clients);
                clients.add(client0);

            }
            if (choix.equals("2")) {

                Categories categorie = null;
                HashMap<String, String> mapDeal = lireDealInformation();
                if (mapDeal.get("categoriesCode").equals("1")) {
                    categorie = categories1;
                } else if (mapDeal.get("categoriesCode").equals("2")) {
                    categorie = categories2;
                } else if (mapDeal.get("categoriesCode").equals("3")) {
                    categorie = categories3;
                } else {
                    System.out.println("categorie not existe");
                }
                Deal deal0 = ServiceDeal.creat(mapDeal.get("desc"),
                        mapDeal.get("nom"), Float.parseFloat(mapDeal.get("prixInitial")), Float.parseFloat(mapDeal.get("prixDeal")), currentDate, currentDate.plusMonths(2), fournisseur, categorie, mapDeal.get("code"));
            }
            if (choix.equals("3")) {

                HashMap<String, String> mapFournisseur = lireFourniseurInformation();
                Fournisseur fournisseur1 = ServiceFournisseur.creat(mapFournisseur.get("nom"), mapFournisseur.get("adresse"), mapFournisseur.get("telephone"), mapFournisseur.get("mail"), mapFournisseur.get("cin")
                        , Float.parseFloat(mapFournisseur.get("heure")), Integer.parseInt(mapFournisseur.get("code")));
                fournisseurs.add(fournisseur1);

            }
            if (choix.equals("4")) {
                ServiceDeal.afficheClientByDeal(deal1);
            }

            if (choix.equals("5")) {

                ServiceDeal.afficheListDealOrderChrono(client2);
            }
            if (choix.equals("6")) {

                Scanner scan = new Scanner(System.in);
                System.out.println(" 1-spa  restauration spa");
                System.out.println(" 2-  restauration");
                System.out.println(" 3- hôtel");
                System.out.println("Donner  le numero de categorie");
                String categorie = scan.nextLine();
                if (categorie.equals("1")) {
                    categorie = "spa";
                } else if (categorie.equals("2")) {
                    categorie = "restauration";
                } else if (categorie.equals("3")) {
                    categorie = "hôtel";
                } else {
                    System.out.println("categorie not existe");
                }
                /**
                 * par le non de categorie
                 */
                ServiceDeal.afficheListDealByCategorie(deals,  categorie);
            }
            if (choix.equals("7")) {

            }


            /**
             * remplir Vente
             */
            List<Vente> ventes = new ArrayList<>();
            LocalDate dateAchat = LocalDate.now();


            Vente vente1 = ServiceVente.creeat(dateAchat, deal1.getCode(), client1.getCin(), 1f, clients, deals);
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

            /*   ServiceDeal.chercheDealEnCoursByCtagorie(deals, "hôtel");
            ServiceDeal.affiche(deals);

            ServiceDeal.afficheListClientByDeal(deals);*/

        } catch (ExceptionDeal e) {
            System.out.println(e.getError().getDesc());
        } catch (Exception e) {
            System.out.println(ErrorsDeal.ERRORS_GENERALE.getDesc());


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
    public static String lireChoix() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Donner  votre  choix : ");
        System.out.println("1- Créer Client.");
        System.out.println("2- Créer Deal.");
        System.out.println("3- Créer  un fournisseur.");
        System.out.println("4- Afficher la liste des clients par deal.");
        System.out.println("5- Afficher la liste des deals d’un client par ordre chronologique.");
        System.out.println("6- Rechercher les deals en cours par catégorie.");
        System.out.println("7- exit");
        String choix = scan.nextLine();
        return choix;
    }

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

}
