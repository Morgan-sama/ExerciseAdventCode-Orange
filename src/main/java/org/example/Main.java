//Exercice de Ango MORGANE CLAUDEL

package org.example;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {

        //Les résultats à afficher à la fin du programme
            long goodpasswordsrule1 = 0;
            long goodpasswordsrule2 = 0;

            //Définition du format de la police + le mot de passe, tous les ensembles (police + mot de passe) ne
        // respectant pas cette règle ne seront pas pris en compte
        String model = "(\\d+)-(\\d+)\\s([a-zA-Z]):\\s(.*)";

        //Complilation de l'expression régulière par l'outil regex
        Pattern pattern = Pattern.compile(model);

        try {

            //Instanciation d'un fichier texte contenant tous les mots de passe à tester
            File file = new File("passwords.txt");
            Scanner scanner = new Scanner(file);

            // A chaque ligne trouvée dans le fichier renseigné , la boucle va se répeter
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                //Verification du format de la ligne
                Matcher matcher = pattern.matcher(line);

                if (matcher.matches()) {

                    //Découpage de chaque ligne pour récupérer ,la lettre devant apparaitre dans le mot de passe
                    // son minimum d'occurences ainsi que son maximum d'occurence
                    int min = Integer.parseInt(matcher.group(1));
                    int max = Integer.parseInt(matcher.group(2));
                    char lettre = matcher.group(3).charAt(0);

                    //Ici , d'après l'expression fournie , on tire le mot de passe qu'on affecte à la variable password
                    String password = matcher.group(4);

                    //Opération permattant de vérifier à chaque fois le nombre d'occurences de la lettre pour savoir si
                    // le mot de passe est valide
                    long compteur = password.chars().filter(ch -> ch == lettre).count();

                    if (compteur >= min && compteur <= max) {
                               goodpasswordsrule1++;             }

                    if (EstValide2(min,max,lettre,password)){
                        goodpasswordsrule2 ++;
                    }
                }
            }
            System.out.println("Le nombre de mot de passe valides selon la règle n°1 est : "+ goodpasswordsrule1);
            System.out.println("Le nombre de mot de passe valides selon la règle n°2 est : "+ goodpasswordsrule2);

        } catch (FileNotFoundException e) {
            System.out.println("Nous n'avons pas trouvé le fichier ! ");
        }
    }
    public static boolean EstValide2(int pos1, int pos2, char lettre, String password) {

        // Les positions sont 1-indexées, donc il faut soustraire 1 pour l'index des chaînes
        boolean firstPosition = password.charAt(pos1 - 1) == lettre;
        boolean secondPosition = password.charAt(pos2 - 1) == lettre;

        // Retourne true si exactement une des deux positions correspond
        return firstPosition ^ secondPosition; // ^ est l'opérateur XOR (En gros avec XOR , 1 XOR 1 font 0 ,0 X0R 0 font 0 puis 1 XOR 0 font 1 c'est le resultat qu'on cherche ;)
    }
}
