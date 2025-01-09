package expressions_regulars;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Visites {
    public static void main(String[] args) throws IOException {
        String fitxer = "src/expressions_regulars/santako.txt";

        System.out.println("Amb expressions regulars:");
        processaAmbRegex(fitxer);

        System.out.println("\nAmb mètodes de String:");
        processaAmbStrings(fitxer);
    }

    private static void processaAmbRegex(String fitxer) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fitxer));
        String linia;

        Pattern pareNoel = Pattern.compile(Pattern.quote("*<]:-DOo"));
        Pattern ren = Pattern.compile(Pattern.quote(">:o)"));
        Pattern follet = Pattern.compile(Pattern.quote("<]:-D"));

        while ((linia = br.readLine()) != null) {
            // Comptar Pare Noel i eliminar coincidències
            int countPareNoel = comptaCoincidencies(pareNoel, linia);
            linia = linia.replaceAll(Pattern.quote("*<]:-DOo"), "");

            // Comptar Rens i eliminar coincidències
            int countRen = comptaCoincidencies(ren, linia);
            linia = linia.replaceAll(Pattern.quote(">:o)"), "");

            // Comptar Follets
            int countFollet = comptaCoincidencies(follet, linia);

            mostraVisites(countPareNoel, countRen, countFollet);
        }
        br.close();
    }

    // Solució amb mètodes de String
    public static void processaAmbStrings(String fitxer) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fitxer));
        String linia;

        String pareNoel = "*<]:-DOo";
        String ren = ">:o)";
        String follet = "<]:-D";

        while ((linia = br.readLine()) != null) {
            // Comptar Pare Noel i eliminar coincidències
            int countPareNoel = comptaCoincidenciesString(pareNoel, linia);
            linia = linia.replace(pareNoel, "");

            // Comptar Rens i eliminar coincidències
            int countRen = comptaCoincidenciesString(ren, linia);
            linia = linia.replace(ren, "");

            // Comptar Follets
            int countFollet = comptaCoincidenciesString(follet, linia);

            mostraVisites(countPareNoel, countRen, countFollet);
        }

        br.close();
    }

    private static int comptaCoincidenciesString(String subcadena, String linia) {
        int count = 0;
        int index = 0;

        while ((index = linia.indexOf(subcadena, index)) != -1) {
            count++;
            index += subcadena.length();
        }

        return count;
    }

    private static int comptaCoincidencies(Pattern pattern, String linia) {
        Matcher matcher = pattern.matcher(linia);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    private static void mostraVisites(int pareNoel, int ren, int follet) {
        StringBuilder resultat = new StringBuilder();

        if (pareNoel > 0) {
            resultat.append("Pare Noel (").append(pareNoel).append(") ");
        }
        if (ren > 0) {
            resultat.append("Ren (").append(ren).append(") ");
        }
        if (follet > 0) {
            resultat.append("Follet (").append(follet).append(") ");
        }

        if (resultat.length() > 0) {
            System.out.println(resultat.toString().trim());
        }else {
            System.out.println();
        }
    }
}
