package service;

import entities.Categories;
import entities.Vente;
import error.exception.ExceptionDeal;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ServiseCategorie {
    static String fileUrl = "categorie.txt";

    public static Categories creat(Integer id, String categorie) {

        Categories categories = new Categories(id, categorie);
        return categories;
    }

    public static Categories getCategorieById(Integer id, List<Categories> categoriesList) {
        int i = 0;
        while (i < categoriesList.size()) {

            if (categoriesList.get(i).getId().equals(id)) {
                return categoriesList.get(i);
            }
            i++;
        }

        return null;
    }


    public static List<Categories> loadCategorieFile() throws IOException {
        ArrayList<Categories> categories = new ArrayList<Categories>();
        FileReader fileReader = new FileReader(fileUrl);
        BufferedReader ReadFileBuffer = new BufferedReader(fileReader);
        String line = ReadFileBuffer.readLine();
        while (line != null) {
            String[] attributs = line.split(";");
            Categories categorie = creat(Integer.parseInt(attributs[0]), attributs[1]);
            categories.add(categorie);
            line = ReadFileBuffer.readLine();
            if (line != null)
                System.out.println(line);
        }
        return categories;

    }

    /**
     * @param categoriesList
     * @throws IOException
     */
    public static void saveCategorieFile(List<Categories> categoriesList) throws IOException {
        FileWriter fileWriter = new FileWriter(fileUrl);
        BufferedWriter WriteFileBuffer = new BufferedWriter(fileWriter);
        int i = 0;
        while (i < categoriesList.size()) {
            String line = String.valueOf(categoriesList.get(i).getId()) + ';' + categoriesList.get(i).getCcategorie() + ";";
            WriteFileBuffer.write(line);
            WriteFileBuffer.newLine();
            i++;
        }
        WriteFileBuffer.close();

    }
}
