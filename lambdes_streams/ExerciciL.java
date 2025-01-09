package mp3_uf5_2425.lambdes_streams;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ExerciciL {

    public static void main(String[] args) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyy");
        Persona p1 = new Persona("Arya", Persona.Genere.DONA, LocalDate.parse("25/12/2002",format) );
        Persona p2 = new Persona("Tyrion", Persona.Genere.HOME, LocalDate.parse("12/10/1980",format));
        Persona p3 = new Persona("Cersei", Persona.Genere.DONA, LocalDate.parse("10/01/1984",format));
        Persona p4 = new Persona("Eddard", Persona.Genere.HOME, LocalDate.parse("24/04/1974",format));
        Persona p5 = new Persona("Sansa", Persona.Genere.DONA, LocalDate.parse("24/04/1992",format));
        Persona p6 = new Persona("Jaime", Persona.Genere.HOME, LocalDate.parse("24/04/1979",format));
        Persona p7 = new Persona("Khal", Persona.Genere.HOME, LocalDate.parse("10/08/1979",format));
        Persona p8 = new Persona("Daenerys", Persona.Genere.DONA, LocalDate.parse("12/11/1992",format));
        Persona p9 = new Persona("Davos", Persona.Genere.HOME, LocalDate.parse("12/11/1965",format));
        Persona p10 = new Persona("Jon Neu", Persona.Genere.HOME, LocalDate.parse("12/11/1986",format));
        Persona p11 = new Persona("Brienne", Persona.Genere.DONA, LocalDate.parse("12/11/1989",format));

        Persona[] lpers = {p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11};
        List<Persona> llistaPersones;
        llistaPersones = new ArrayList<>(Arrays.asList(lpers));
        Map<Integer,Integer> mapPersones = new HashMap<>();

        // 1 - Canviar a lambda
//        Collections.sort(llistaPersones, new Comparator<Persona>() {
//            @Override
//            public int compare(Persona o1, Persona o2) {
//                if(o1.getNom().charAt(0) >= o2.getNom().charAt(0)) return 1;
//                else return -1;
//            }
//        });
        Collections.sort(llistaPersones, (Comparator.comparingInt(p -> p.getNom().charAt(0))));

        // 2 - Canviar a Lambda
//        for(Persona p: llistaPersones) {
//            System.out.println(p);
//        }

        llistaPersones.forEach(persona -> System.out.println(persona));

        // 3 - Canvia a classe anònima
        // ordenació alfabètica inversa del nom
        //llistaPersones.sort((o1,o2) -> o2.getNom().compareTo(o1.getNom()));

        llistaPersones.sort(new Comparator<Persona>() {
            @Override
            public int compare(Persona o1, Persona o2) {
                return o2.getNom().compareTo(o1.getNom());
            }
        });

        // 4 - fes servir un reference method en comptes del for-loop
//        for(Persona p: llistaPersones) {
//            System.out.println(p);
//        };

        llistaPersones.forEach(System.out::println);


        // 5 - Omplir map. Canviar el for-loop per un forEach amb lambda
//        for(Persona per : llistaPersones) {
//            mapPersones.put(per.getAge(),1);
//        }

        llistaPersones.forEach(persona -> mapPersones.put(persona.getAge(), 1));

        // 6 - Canvia el for-loop per un recorregut forEach amb lambda
//        for(Map.Entry entry : mapPersones.entrySet()) {
//            System.out.println(entry.getKey() + " : " + entry.getValue());
//        }

        mapPersones.forEach((key, value) -> System.out.println(key + " : " + value));

        /* 7 -
            Esbrina com s'utilitzen i perquè serveixen els mètodes de map següents
                map.putIfAbsent
                map.computeIfAbsent
                map.computeIfPresent

             afegeix al map la freqüència d'edat de les persones, de la llistaPersones

             **Si vols fes-ho primer sense els mètodes anomenats i sense lambdes
             i després amb els mètodes i amb lambdes**

             La sortida és aquesta:
                34 anys -> 1
                38 anys -> 1
                39 anys -> 2
                26 anys -> 2
                44 anys -> 1
                15 anys -> 1

         */

        // SENSE ELS METODES

        System.out.println("SENSE ELS METODES");
        for (Persona p : llistaPersones){
            int edat = p.getAge();

            if (mapPersones.containsKey(edat)){
                mapPersones.put(edat, mapPersones.get(edat) + 1);
            } else{
                mapPersones.put(edat, 1);
            }
            for (Map.Entry<Integer, Integer> entry : mapPersones.entrySet()){
                System.out.println(entry.getKey() + " anys -> " + entry.getValue());
            }
        }

        // AMB ELS METODES ANTERIORMENTS ESMENTATS
        System.out.println("AMB ELS METODES ANTERIORMENT ESMENTATS");

        llistaPersones.forEach(persona -> {
            int edat = persona.getAge();
            mapPersones.computeIfAbsent(edat, key -> 0); // SI LA EDAT NO HI ES, LA INICIALITZEM
            mapPersones.computeIfPresent(edat, (key, value) -> value + 1);  // INCREMENTEM FREQUENCIA :)
        });
        mapPersones.forEach((key, value) -> System.out.println(key + " anys -> " + value)); // IMPRIMIR EL MAP AMB LAMBDA


        // STREAMS

        // 8 - llistat de persones DONA amb lambda (stream)

        List<Persona> dones = llistaPersones.stream().filter(persona -> persona.getGenere() == Persona.Genere.DONA).toList();

        dones.forEach(System.out::println);
        // 9 - Llistat dels dos HOMES més joves (stream)
        List<Persona> homesMesJoves = llistaPersones.stream().filter(persona -> persona.getGenere() == Persona.Genere.HOME)
                .sorted(Comparator.comparingInt(Persona::getAge)).limit(2).toList();

        homesMesJoves.forEach(System.out::println);

        // 10- Esborrar (no filtrar o imprimir) del llistat les persones entre 30 i 40 anys (amb lambda)
        llistaPersones.removeIf(persona -> persona.getAge() >= 30 && persona.getAge() <= 40);

        // 11 - Persones que tinguin una 'a' al seu nom
        List<Persona> personesAmbA = llistaPersones.stream().filter(persona -> persona.getNom().
                toLowerCase().contains("a")).toList();

        personesAmbA.forEach(System.out::println);

        //12 - Llistat de les dates de naixament + dos dies
        List<LocalDate> dates = llistaPersones.stream()
                .map(persona -> persona.getDataNaixament().plusDays(3))
                .toList();
        dates.forEach(System.out::println);

        //13 - Rejovenir dos anys a totes les persones
        llistaPersones.forEach(persona -> persona.setDataNaixament(persona.getDataNaixament().minusYears(2)));

        //14. A partir de la següent llista de noms d'alumnes crea una nova llista d'alumnes amb aquests alumnes nous
        // pots modificar la classe alumne si cal. I imprimeix la llista dels nous alumnes
        List<String> alumnesNous = Arrays.asList("Pedro","Pablo","Bilma");

        List<Alumne> llistaAlumnesNous = alumnesNous.stream()
                .map(Alumne::new).toList();

        llistaAlumnesNous.forEach(System.out::println);

        //Creem cotxes i els assignem a les diferents persones
        Cotxe c1 = new Cotxe("Seat", "Ibiza");
        Cotxe c2 = new Cotxe("Seat","Leon");
        Cotxe c3 = new Cotxe("Seat ","Arona");
        Cotxe c4 = new Cotxe("Toyota","Auris");
        Cotxe c5 = new Cotxe("Toyota","Corolla");
        Cotxe c6 = new Cotxe("Toyota","Yaris");
        p1.setCotxe(c1); p2.setCotxe(c2);
        p3.setCotxe(c1); p4.setCotxe(c3);
        p5.setCotxe(c4); p6.setCotxe(c4);
        p7.setCotxe(c5); p8.setCotxe(c6);
        p9.setCotxe(c5); p10.setCotxe(c3);
        p11.setCotxe(c2);

        //15. Crear una llista de persones amb el coxes de la marca Seat i una altra
        // amb les persones que tenen un Toyota. Fes servir streams
        // imprimeix les dues llistes per ordenades per l'edat de les persones
        System.out.println("\n15 - Persones amb Seat i Toyota");

        List<Persona> llistaPersonesSeat = llistaPersones.stream()
                .filter(persona -> persona.getCotxe().getMarca().equalsIgnoreCase("seat"))
                .sorted(Comparator.comparingInt(Persona::getAge))
                .toList();

        llistaPersonesSeat.forEach(System.out::println);

        List<Persona> llistaPersonesToyota = llistaPersones.stream()
                .filter(persona -> persona.getCotxe().getMarca().equalsIgnoreCase("toyota"))
                .sorted(Comparator.comparingInt(Persona::getAge))
                .toList();

        llistaPersonesToyota.forEach(System.out::println);

        //16. Imprimeix les persones ordenades per ordre alfabètic de la marca dels cotxes
        System.out.println("\n16 - Persones ordenades per ordre alfabètic de la marca dels cotxes");

        List<Persona> personesOrdenadesMarca = llistaPersones.stream()
                .filter(persona -> persona.getCotxe() != null)
                .sorted(Comparator.comparing(persona -> persona.getCotxe().getMarca()))
                .toList();
        personesOrdenadesMarca.forEach(System.out::println);
    }


}
