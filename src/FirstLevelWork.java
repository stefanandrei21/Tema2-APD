import java.io.*;

public class FirstLevelWork extends Thread{
    PrintWriter outOrders;
    PrintWriter outProducts;
    Integer id;

    Integer start;
    Integer stop;
    String pathToOrder;

    String pathToProducts;

    public FirstLevelWork(PrintWriter outOrders, PrintWriter outProducts, Integer id, Integer start,
                          Integer stop, String pathToOrder, String pathToProducts) {
        this.outOrders = outOrders;
        this.outProducts = outProducts;
        this.id = id;
        this.start = start;
        this.stop = stop;
        this.pathToOrder = pathToOrder;
        this.pathToProducts = pathToProducts;
    }

@Override
    public void run() {
        System.out.println("merge schema" + this.start + " " + this.stop);

    try {
        BufferedReader read = new BufferedReader(new FileReader(pathToOrder));

        for (int i = 1; i <= stop; i++) {
            String line = read.readLine();
            if(line == null) break;

            if (i < start) {
               continue;
            }
            outOrders.write(line + "\n");

            outOrders.flush();
        }




    } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
}
