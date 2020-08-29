import com.oracle.tools.packager.IOUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Formatter;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите количество позиций");
        int amount = sc.nextInt();
        String listName[] = new String[amount];
        double listSum[] = new double [amount];
        double listPrice[] = new double[amount];
        String listPosition[] = new String[amount];
        double listCost[] = new double[amount];
        double total = 0.0;
        try (PrintWriter out = new PrintWriter(new FileWriter("src/main/resources/out.txt"))){
        for (int i=0; i<amount; i++) {
            try {
                System.out.println("Введите товар №" + (i + 1));
                listName[i] = sc.next();
                System.out.println("Введите количество товара №" + (i + 1));
                listSum[i] = sc.nextDouble();
                System.out.println("Введите цену товара №" + (i + 1));
                listPrice[i] = sc.nextDouble();
                listCost[i] = listSum[i] * listPrice[i];
                listPosition[i] = String.format("%-10s%10.2f%3s%6.3f%10.2f",
                        listName[i], listPrice[i],"x", listSum[i], listCost[i]);
                total += listCost[i];
            } catch (InputMismatchException e) {
                System.out.println("Неверный формат параметра");
            }
        }
        out.printf("%-10s%10s%9s%10s\n","Название","Цена","Кол-во","Стоимость");
        out.println("=======================================");
            for (int a=0;a<amount;a++) {
                out.println(listPosition[a]);
            }
        out.println("=======================================");
        out.printf("%-5s%34s","Итого",total);
        out.close();
            System.out.println("Чек создан");
            try {
                BufferedReader in = new BufferedReader(new FileReader("src/main/resources/out.txt"));
                System.out.println(in.readLine());

                while (in.readLine() != null) {
                    System.out.println(in.readLine());
                }
                in.close();
            }catch(IOException e2){
                e2.printStackTrace();
            }

        }catch(IOException e1){
            e1.printStackTrace();
        }


    }
}
