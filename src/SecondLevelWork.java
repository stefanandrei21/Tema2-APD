import java.io.*;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class SecondLevelWork implements Runnable{
    String orderId;

    PrintWriter outOrders;

    String readingPath;
    int productNumber;

    PrintWriter outProducts;

    String orderName;
    Integer orderNumber;


    public SecondLevelWork(String orderId, String readingPath, int productNumber,
                           PrintWriter outProducts, PrintWriter outOrders,
                           String orderName, Integer orderNumber) {

        this.orderId = orderId;
        this.readingPath = readingPath;
        this.productNumber = productNumber;
        this.outProducts = outProducts;
        this.outOrders = outOrders;
        this.orderName = orderName;
        this.orderNumber = orderNumber;
    }

    @Override
    public void run() {
        int countProducts = 0;
        FileReader productsFile = null;
        try {
            productsFile = new FileReader(readingPath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
               BufferedReader scan = new BufferedReader(productsFile);
                while (true) {

                    String line = scan.readLine();
                    String[] splitLine = line.split(",", 2);
                    if (orderId.equals(splitLine[0])) {
                        ++countProducts;
                        if (countProducts == productNumber) {
                            FirstLevelWork.ShippProduct(orderId, orderNumber);
                            outProducts.write(splitLine[0] + "," + splitLine[1] + ",shipped\n");
                            outProducts.flush();
                            break;
                        }

                    }
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
