import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Tema2 {
    public static void main(String[] args) throws IOException, InterruptedException {
        Input input = new Input(args[0], Integer.parseInt(args[1]));

        File fileOrders = new File(input.folderName + "/" + "orders.txt");
        File fileProducts= new File(input.folderName + "/" + "order_products.txt");

        File ordO = new File("orders_out.txt");
        File ordP = new File("products_out.txt");
        CreateFiles createFiles = new CreateFiles(ordO, ordP);

        PrintWriter outOrders = new PrintWriter("orders_out.txt");
        PrintWriter outProducts = new PrintWriter("order_products_out.txt");

        ExecutorService executorService = Executors.newFixedThreadPool(input.numberOfThreads);

        Long numberOfBytes = Files.size(Path.of(input.folderName + "/" + "orders.txt"));
        Long numberOfLines = numberOfBytes / 16;
        Integer workSize = Math.toIntExact(numberOfLines / input.numberOfThreads);
        Integer start = 1;
        Integer stop = workSize;

        List<FirstLevelWork> firstLevelWorkList = new ArrayList<>();
        String pathToOrders = input.folderName + "/" + "orders.txt";
        String pathToProducts = input.folderName + "/" + "order_products.txt";
        for(int i = 0; i < input.numberOfThreads; i++) {

            if (i == input.numberOfThreads - 1) {
                stop += workSize;
            }
            FirstLevelWork levelWork = new FirstLevelWork(outOrders, outProducts, i, start, stop, pathToOrders, pathToProducts, executorService);
            start += workSize;
            stop += workSize;

            firstLevelWorkList.add(levelWork);
            levelWork.start();

        }
        for(FirstLevelWork work : firstLevelWorkList) {
            work.join();
        }
        executorService.shutdown();



    }
}
