package responce;

import java.util.Scanner;

public class Menu {
    public static String lireChoix() {
        Scanner scan = new Scanner(System.in);
        System.out.println("***************************************");
        System.out.println("    Donner  votre  choix : ");
        System.out.println("1- Créer Client.");//
        System.out.println("2- Créer  un fournisseur.");//
        System.out.println("3- acceder a votre compte.");//
        System.out.println("4- Rechercher les deals en cours par catégorie.");//
        System.out.println("7- exit");
        System.out.println("***************************************");
        String choix = scan.nextLine();
        return choix;
    }
    public static String MenuClient() {
        Scanner scan = new Scanner(System.in);
        System.out.println("***************************************");
        System.out.println("    Donner  votre  choix : ");
        System.out.println("1- Afficher la liste des deals  par ordre chronologique.");
        System.out.println("2- Rechercher les deals en cours par catégorie.");//
        System.out.println("3- Acheter un deal.");
        System.out.println("7- exit");
        System.out.println("***************************************");
        String choix = scan.nextLine();
        return choix;
    }

    public static String MenuFournisseur() {
        Scanner scan = new Scanner(System.in);
        System.out.println("***************************************");
        System.out.println("    Donner  votre  choix : ");
        System.out.println("1- Créer Deal.");
        System.out.println("2- Afficher la liste des clients par deal.");
        System.out.println("7- exit");
        System.out.println("***************************************");
        String choix = scan.nextLine();
        return choix;
    }
    public static String SingClient() {
        Scanner scan = new Scanner(System.in);
        System.out.println("***************************************");
        System.out.println("    Donner  votre  cin ");//
        System.out.println("7- exit");
        System.out.println("***************************************");
        String choix = scan.nextLine();
        return choix;
    }
    public static String TypeClient() {
        Scanner scan = new Scanner(System.in);
        System.out.println("***************************************");
        System.out.println("   vous  étes ");
        System.out.println("1-client");//
        System.out.println("2-Fournisseur");//
        System.out.println("***************************************");
        String choix = scan.nextLine();
        return choix;
    }

    public static String MenuCategorie() {//
        Scanner scan = new Scanner(System.in);
        System.out.println("***************************************");
        System.out.println("    Donner  votre  choix de categorie: ");
        System.out.println("1-spa ");
        System.out.println("2-restauration ");
        System.out.println("3- hôtel");
        System.out.println("7- exit");
        System.out.println("***************************************");
        String choix = scan.nextLine();
        return choix;
    }


}
