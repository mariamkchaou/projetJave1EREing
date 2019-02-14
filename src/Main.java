import entities.*;
import error.ErrorsDeal;
import error.exception.ExceptionDeal;
import org.omg.CORBA.Object;
import responce.ListClientByDeal;
import responce.Menu;
import service.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static List<Client> clients;
    public static List<Deal> deal;
    public static List<Vente> ventes;

    public static LocalDate currentDate = LocalDate.now();

    public static void main(String[] args) throws ExceptionDeal {

        try {
            String choix;

            /**
             *initilisation client
             */
            clients = new ArrayList<>();
            clients = ServiceClient.loadClientFile();

            /**
             * initiaisation fournisseur
             */
            List<Fournisseur> fournisseurs = ServiceFournisseur.loadFournisseurFile();


            /**
             * initialisation categorie
             */

            List<Categories> categories = ServiseCategorie.loadCategorieFile();


            /**
             * initialisation deal
             */
            deal = new ArrayList<>();
            // deal=remplirDeal( categories,  fournisseurs);
            deal = ServiceDeal.loadDealFile(categories, fournisseurs, deal);


            /**
             *initialisation vente
             */
            ventes = new ArrayList<>();
            //    remplirVente(deal, clients, ventes);
            ventes = ServiceVente.loadVentesFile(clients, deal);
            choix = "1";

            while (!choix.equals("7")) {
                try {


                    choix = Menu.lireChoix();
                    if (choix.equals("1")) {
                        HashMap<String, String> map = lireClientInformation();
                        ServiceClient.creat(map.get("nom"), map.get("adresse"), map.get("telephone"), map.get("mail"), map.get("cin"), clients);

                    } else if (choix.equals("2")) {
                        HashMap<String, String> map = lireFourniseurInformation();
                        ServiceFournisseur.creat(map.get("nom"), map.get("adresse"), map.get("telephone"), map.get("mail"), map.get("cin"), Float.valueOf(map.get("heure")), Integer.valueOf(map.get("code")), fournisseurs);

                    } else if (choix.equals("3")) {

                        String ch = Menu.TypeClient();
                        if (ch.equals("1")) {

                            String cin = Menu.SingClient();
                            Client clientConnect = ServiceClient.chercheByCIN(cin, clients);
                            String x = Menu.MenuClient();
                            if (x.equals("1")) {
                                /**
                                 * Afficher la liste des deals  par ordre chronologique.
                                 */
                                ServiceDeal.afficheListDealOrderChrono(deal);

                            } else if ((x.equals("2"))) {
                                /**
                                 *  Rechercher les deals en cours par catégorie.
                                 */

                                String categorieNumber = Menu.MenuCategorie();
                                String categorieName = "hôtel";
                                if (categorieNumber.equals("1")) {
                                    categorieName = "spa";
                                } else if (categorieNumber.equals("2")) {
                                    categorieName = "restauration";
                                } else if (categorieNumber.equals("3")) {
                                    categorieName = "hôtel";
                                }

                                ServiceDeal.afficheListDealByCategorie(deal, categorieName);
                            } else if ((x.equals("3"))) {
                                /**
                                 *  Acheter un deal
                                 *  todo
                                 */
                                ServiceDeal.affiche(deal);
                                System.out.println("Donner le code de Deal ");
                                Scanner scan = new Scanner(System.in);
                                String code = scan.nextLine();

                                System.out.println("Donner la quantité de Deal ");
                                String q = scan.nextLine();
                                ServiceVente.creat(currentDate,code,clientConnect.getCin(),Float.valueOf(q),clients,1,deal,ventes);

                            }

                        } else if (ch.equals("2")) {
                            // fournisseur compte
                            String cin = Menu.SingClient();
                            Fournisseur fournisseur = ServiceFournisseur.getFournisseurCin(cin, fournisseurs);
                            String FournisseurChoix = Menu.MenuFournisseur();
                            //Afficher la liste des clients par deal.
                            if (FournisseurChoix.equals("1")) {
                                /**
                                 *
                                 Créer Deal
                                 */

                                HashMap<String, String> map = lireDealInformation();
                                ServiceDeal.creat(map.get("desc"), map.get("nom"),
                                        Float.valueOf(map.get("prixInitial")),
                                        Float.valueOf(map.get("prixDeal")), currentDate, currentDate.plusMonths(1),
                                        fournisseur.getCode(), Integer.valueOf(map.get("categoriesCode")), map.get("desc"), categories, fournisseurs, deal);
                            } else if (FournisseurChoix.equals("2")) {
                                /**
                                 *  Afficher la liste des clients par deal
                                 */
                                System.out.println(deal.size());
                                ServiceDeal.afficheListClientByDeal(deal);
                            } else if (FournisseurChoix.equals("7")) {

                            }

                        }


                    } else if (choix.equals("4")) {
                        /**
                         *  Rechercher les deals en cours par catégorie.
                         */

                        String categorieNumber = Menu.MenuCategorie();
                        String categorieName = "hôtel";
                        if (categorieNumber.equals("1")) {
                            categorieName = "spa";
                        } else if (categorieNumber.equals("2")) {
                            categorieName = "restauration";
                        } else if (categorieNumber.equals("3")) {
                            categorieName = "hôtel";
                        }

                        ServiceDeal.afficheListDealByCategorie(deal, categorieName);

                    }
                } catch (ExceptionDeal e) {
                    System.out.println(e.getError().getDesc());
                }
            }


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
        System.out.println("Donner le code de categorie. :");
        System.out.println(" 1- spa restauration hôtel");
        System.out.println(" 2- restauration");
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

    public static List<Deal> remplirDeal(List<Categories> categoriesList, List<Fournisseur> fournisseurs) throws ExceptionDeal, IOException {

        List<Deal> deals = new ArrayList<>();
        Deal deal1 = ServiceDeal.creat(" Profitez de nos offres spéciales! Petits Prix. Réservez Maintenant. Sans frais de réservation. ",
                "Palace", 100f, 120f, currentDate, currentDate.plusMonths(2), 123, 1, "100", categoriesList, fournisseurs, deals);
        Deal deal2 = ServiceDeal.creat(" Profitez de nos offres spéciales! Petits Prix. Réservez Maintenant. Sans frais de réservation. ",
                "Talaso", 100f, 120f, currentDate, currentDate.plusMonths(2), 123, 1, "101",
                categoriesList, fournisseurs, deals);
        Deal deal3 = ServiceDeal.creat(" Profitez de nos offres spéciales! Petits Prix. Réservez Maintenant. Sans frais de réservation. ",
                "Vacance", 100f, 120f, currentDate, currentDate.plusMonths(2), 123, 3, "103", categoriesList, fournisseurs, deals);

        return deals;
    }

    public static void remplirVente(List<Deal> deal, List<Client> clients, List<Vente> ventes) throws IOException, ExceptionDeal {

        LocalDate dateAchat = LocalDate.now();
        Vente vente1 = ServiceVente.creat(dateAchat, deal.get(1).getCode(), clients.get(1).getCin(), 1f, clients, 1, deal, ventes);
        Vente vente2 = ServiceVente.creat(dateAchat, deal.get(2).getCode(), clients.get(2).getCin(), 3f, clients, 2, deal, ventes);
        Vente vente3 = ServiceVente.creat(dateAchat, deal.get(1).getCode(), clients.get(1).getCin(), 9f, clients, 3, deal, ventes);
        Vente vente4 = ServiceVente.creat(dateAchat, deal.get(3).getCode(), clients.get(2).getCin(), 1f, clients, 4, deal, ventes);
        Vente vente5 = ServiceVente.creat(dateAchat, deal.get(2).getCode(), clients.get(1).getCin(), 1f, clients, 5, deal, ventes);
        Vente vente6 = ServiceVente.creat(dateAchat, deal.get(1).getCode(), clients.get(2).getCin(), 1f, clients, 6, deal, ventes);
        Vente vente7 = ServiceVente.creat(dateAchat, deal.get(2).getCode(), clients.get(1).getCin(), 1f, clients, 7, deal, ventes);
        Vente vente8 = ServiceVente.creat(dateAchat, deal.get(3).getCode(), clients.get(2).getCin(), 1f, clients, 8, deal, ventes);
        Vente vente9 = ServiceVente.creat(dateAchat, deal.get(2).getCode(), clients.get(3).getCin(), 1f, clients, 9, deal, ventes);
        Vente vente10 = ServiceVente.creat(dateAchat, deal.get(3).getCode(), clients.get(1).getCin(), 1f, clients, 10, deal, ventes);
        Vente vente11 = ServiceVente.creat(dateAchat, deal.get(1).getCode(), clients.get(3).getCin(), 1f, clients, 11, deal, ventes);


    }
}

