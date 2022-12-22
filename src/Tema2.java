import java.io.*;
import java.util.Scanner;

public class Tema2 {
    public static void main(String[] args) throws IOException {
        Input input = new Input(args[0], Integer.parseInt(args[1]));
        System.out.println(input);
        File fileOrders = new File(input.folderName + "/" + "orders.txt");
        File fileProducts= new File(input.folderName + "/" + "order_products.txt");

        Scanner scan = new Scanner(fileProducts);
        while (scan.hasNextLine()) {
            System.out.println(scan.nextLine());
        }
        File ordO = new File("orders_out.txt");
        File ordP = new File("products_out.txt");
        CreateFiles createFiles = new CreateFiles(ordO, ordP);

        PrintWriter outOrders = new PrintWriter("orders_out.txt");
        PrintWriter outProducts = new PrintWriter("order_products_out.txt");

        outProducts.write("manevra");

        outProducts.flush();




    }
}
