package responce;

import java.util.Scanner;

public class Menu {
    public static String lireChoix() {
        Scanner scan = new Scanner(System.in);
        System.out.println("***************************************");
        System.out.println("    Donner  votre  choix : ");
        System.out.println("1- Créer Client.");
        System.out.println("3- Créer  un fournisseur.");
        System.out.println("6- Rechercher les deals en cours par catégorie.");
        System.out.println("7- exit");
        System.out.println("***************************************");
        String choix = scan.nextLine();
        return choix;
    }
    public static String MenuClient() {
        Scanner scan = new Scanner(System.in);
        System.out.println("***************************************");
        System.out.println("    Donner  votre  choix : ");
        System.out.println("1- Créer Client.");
        System.out.println("5- Afficher la liste des deals  par ordre chronologique.");
        System.out.println("6- Rechercher les deals en cours par catégorie.");
        System.out.println("7- exit");
        System.out.println("***************************************");
        String choix = scan.nextLine();
        return choix;
    }

    public static String MenuFournisseur() {
        Scanner scan = new Scanner(System.in);
        System.out.println("***************************************");
        System.out.println("    Donner  votre  choix : ");
        System.out.println("2- Créer Deal.");
        System.out.println("3- Créer  un fournisseur.");
        System.out.println("4- Afficher la liste des clients par deal.");
        System.out.println("7- exit");
        System.out.println("***************************************");
        String choix = scan.nextLine();
        return choix;
    }
    public static String SingClient() {
        Scanner scan = new Scanner(System.in);
        System.out.println("***************************************");
        System.out.println("    Donner  votre  cin ");
        System.out.println("7- exit");
        System.out.println("***************************************");
        String choix = scan.nextLine();
        return choix;
    }


}
