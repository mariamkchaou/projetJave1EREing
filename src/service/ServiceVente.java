package service;

import entities.Categories;
import entities.Client;
import entities.Deal;
import entities.Vente;
import error.exception.ClientNotFound;
import error.exception.DealNotExiste;
import error.exception.ExceptionDeal;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServiceVente {

    public static String fileUrl="vente.txt";
    /**
     * ventes d’un deal existant à un client existant, puis afficher le Total à payer
     *
     * @param dateAchat
     * @param codeDeal
     * @param cin
     * @param quantité
     * @param clients
     * @param deals
     * @return
     * @throws ExceptionDeal
     * @return
     */
    public static Vente creeat(LocalDate dateAchat, String codeDeal, String cin, Float quantité, List<Client> clients,Integer id, List<Deal> deals) throws ExceptionDeal {

        Client client= ServiceClient.chercheByCIN(cin, clients);
        if (client == null) {
            throw new ClientNotFound();
        }

        Deal deal=ServiceDeal.chercheDealEnCoursByCode(deals,codeDeal);

        if (deal==null) {
            throw new DealNotExiste();
        }
        Vente vente = new Vente(dateAchat, deal, client, quantité,id);

        System.out.println(" La vente a été réalisée avec succès, au prix : " + calculePrix(vente));
        client.getVentes().add(vente);
        deal.getVentes().add(vente);
        return vente;

    }

    public static Float calculePrix(Vente vente) {
        return vente.getDeals().getPrixDeal() * vente.getQuantité();
    }


   /* public static List<Categories> loadCategorieFile() throws IOException {
        ArrayList<Categories> categories = new ArrayList<Categories>();
        FileReader fileReader = new FileReader(fileUrl);
        BufferedReader ReadFileBuffer = new BufferedReader(fileReader);
        String line = ReadFileBuffer.readLine();
        while(line!=null){
            String[] attributs =  line.split( ";");
            Categories categorie = creat(Integer.parseInt(attributs[0]),attributs[1]);
            categories.add(categorie);
            line = ReadFileBuffer.readLine();
            if(line!=null)
                System.out.println(line);
        }
        return categories;

    }
*/
    /**
     *
     * @param venteList
     * @throws IOException
     */
    public static void saveCategorieFile(List<Vente> venteList) throws IOException {
        FileWriter fileWriter = new FileWriter(fileUrl);
        BufferedWriter WriteFileBuffer = new BufferedWriter(fileWriter);
        int i = 0;
        while (i < venteList.size()) {
            String line =  String.valueOf(venteList.get(i).getId())+';'+venteList.get(i).getDeals().getCode()+";"
                    +venteList.get(i).getClient().getCin()+";"
                    +String.valueOf(venteList.get(i).getQuantité())+";"  +String.valueOf(venteList.get(i).getDateAchat()+";");
            WriteFileBuffer.write(line);
            WriteFileBuffer.newLine();
            i++;
        }
        WriteFileBuffer.close();

    }

}
