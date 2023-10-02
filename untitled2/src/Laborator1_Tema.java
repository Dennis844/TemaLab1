import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static java.lang.System.exit;

public class Laborator1_Tema {
    public static void main(String[] args) throws IOException {
        int opt;
        String inputFile="in.txt";
        String outputFile="out.txt";
        //Crearea unui obiect Random
        Random random=new Random();

        do {
            System.out.println("Meniu:");
            System.out.println("0.Iesire");
            System.out.println("1.Se cere să se scrie un program Java care să calculeze şi să afişeze perimetru şi aria unui\n" +
                    "dreptunghi");
            System.out.println("2. Să se scrie un program care citește un set de numerele din fișierul de intrare in.txt, care\n" +
                    "conține câte un număr pe un rând, având valorile din figura 18. Programul va determină suma\n" +
                    "lor, media aritmetică, valoarea minimă, valoarea maximă, va afișa aceste valori pe ecran și le\n" +
                    "va scrie în fișierul de ieșire out.txt");
            System.out.println("3.Să se scrie un program care citește un număr n natural de la tastatură și afișează toți\n" +
                    "divizorii acestuia pe ecran. Dacă numărul este prim se va afișa un mesaj corespunzător. ");
            System.out.println("4. Să se determine cmmdc a două numere naturale, a căror valoare maximă este 30. Numerele\n" +
                    "vor fi generate aleatoriu cu ajutorul unui obiect de tip Random și metodei nextInt(); ");
            System.out.println("5. Să se scrie un program care generează aleatoriu un număr întreg cuprins între 0 și 20.\n" +
                    "Programul va determina dacă numărul aparține șirului lui Fobonacci. ");
            Scanner scanner=new Scanner(System.in);
            System.out.println("Opt dvs:");
            opt=scanner.nextInt();

            switch (opt)
            {
                case 0:
                    exit(0);
                    break;
                case 1:
                    int a, b, perimetru, aria;
                    System.out.println("a=");
                    a=scanner.nextInt();
                    System.out.println("b=");
                    b=scanner.nextInt();
                    perimetru=2*a+2*b;
                    aria=a*b;
                    if(a<b)
                    {
                        System.out.println("Valoarea minima este: "+a);
                        System.out.println("Valoarea maxima este: "+b);
                    }
                    else if(a==b)
                    {
                        System.out.println("Valorile atribuite sunt egale");
                    }
                    else
                    {
                        System.out.println("Valoarea minima este: "+b);
                        System.out.println("Valoarea maxima este: "+a);
                    }
                    System.out.println("Perimetrul drept. este: "+perimetru);
                    System.out.println("Aria este: "+aria);
                    break;
                case 2:
                    List<Integer> numere=readIntegersFromFile(inputFile);
                    if(numere.isEmpty()) {
                        System.out.println("Eroare la citire/Fisier gol!\n");
                    } else {
                        Double media= calculeazaMedia(numere);
                        Integer suma=calculeazaSuma(numere);
                        Integer minim=Integer.MAX_VALUE;
                        Integer maxim=Integer.MIN_VALUE;
                        for(Integer numar:numere)
                        {
                            if (minim>numar)
                                minim=numar;
                            if(numar>maxim)
                                maxim=numar;
                        }

                        System.out.println("Suma: " + suma);
                        System.out.println("Media aritmetică: " + media);
                        System.out.println("Valoarea minimă: " + minim);
                        System.out.println("Valoarea maximă: " + maxim);

                        writeResultsToFile("out.txt", suma, media, minim, maxim);
                    }

                    break;
                case 3:
                    Integer n, ok=1;
                    System.out.println("n=");
                    n=scanner.nextInt();
                    for(Integer i=2; i<=n/2; i++)
                    {
                        if(n%i==0) {
                            System.out.println(i + " ");
                            ok=0;
                        }
                    }
                    if(ok==1)
                        System.out.println(n+ " este numar prim.\n");

                    break;
                case 4:


                    // gen aleatorie
                    int num1=random.nextInt(30)+1;
                    int num2=random.nextInt(30)+1;

                    System.out.println("Numarul 1: "+num1);
                    System.out.println("Numarul 1: "+num2);

                    int divizorcomun=cmmdc(num1, num2);
                    System.out.println("CMMDC al celor doua numere: " + divizorcomun);

                    break;
                case 5:
                    Integer nr=random.nextInt(20);
                    System.out.println("Numarul generat: "+nr);
                    Integer d=0, e=1, f=1;
                    while(f<=nr)
                    {
                        f=d+e;
                        d=e;
                        e=f;
                        if(f==nr)
                            System.out.println("Numarul face parte din sirul lui fibonacci!");
                    }
                    break;
                default:
                    System.out.println("Alegere invalida!\n");
            }



        }while(true);


    }

    private static List<Integer> readIntegersFromFile(String fileName) throws IOException {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int number = Integer.parseInt(line);
                numbers.add(number);
            }
        }
        return numbers;
    }

    private static void writeResultsToFile(String fileName, Integer sum, double average, Integer minValue, Integer maxValue)
            throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Suma: " + sum + "\n");
            writer.write("Media aritmetică: " + average + "\n");
            writer.write("Valoarea minimă: " + minValue + "\n");
            writer.write("Valoarea maximă: " + maxValue + "\n");
        }
    }


    public static Integer calculeazaSuma(List<Integer> numere) {
        Integer suma=0;
        for(Integer numar:numere) {
            suma+=numar;
        }
        return suma;
    }

    public static Double calculeazaMedia(List<Integer> numere) {
        Integer suma=0;
        double media;
        Integer counter=0;
        for(Integer numar:numere) {
            suma+=numar;
            counter++;
        }
        media=suma.doubleValue()/counter.doubleValue();
        return media;
    }

    public static int cmmdc(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

}
