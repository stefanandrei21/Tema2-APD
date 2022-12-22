import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class FirstLevelWork extends Thread{
    static PrintWriter outOrders;
    PrintWriter outProducts;
    Integer id;

    Integer start;
    Integer stop;
    String pathToOrder;

    String pathToProducts;

    ExecutorService executorService;

    public FirstLevelWork(PrintWriter outOrders, PrintWriter outProducts, Integer id, Integer start,
                          Integer stop, String pathToOrder, String pathToProducts, ExecutorService executorService) {
        this.outOrders = outOrders;
        this.outProducts = outProducts;
        this.id = id;
        this.start = start;
        this.stop = stop;
        this.pathToOrder = pathToOrder;
        this.pathToProducts = pathToProducts;
        this.executorService = executorService;
    }

@Override
    public void run() {
    try {
        BufferedReader read = new BufferedReader(new FileReader(pathToOrder));

        for (int i = 1; i <= stop; i++) {
            String line = read.readLine();

            if(line == null) break;

            if (i < start) {
               continue;
            }
            String[] lineSep = line.split(",", 2);
       Integer numberInOrder = Integer.valueOf(lineSep[1]);
            CheckShippedProduct.numberOfShippedProducts.put(lineSep[0],numberInOrder);
            boolean ok = true;
            if(numberInOrder == 0) {
                ok = false;
            }
            for(int j = 1; j <= numberInOrder; j++) {
                executorService.submit(new SecondLevelWork(lineSep[0], pathToProducts, j, outProducts, outOrders, lineSep[0], numberInOrder));

            }

        }




    } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
    public static synchronized void ShippProduct(String orderId, Integer orderNumber) {
        CheckShippedProduct.numberOfShippedProducts.replace(orderId, CheckShippedProduct.numberOfShippedProducts.get(orderId) - 1);

                if(CheckShippedProduct.numberOfShippedProducts.get(orderId) == 0) {
                    outOrders.write(orderId + "," + orderNumber + ",shipped\n");

                    outOrders.flush();


                }

    }
}
