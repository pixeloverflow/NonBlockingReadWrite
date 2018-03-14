import java.nio.IntBuffer;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) throws Exception {
        int[] input = new int[args.length];

        for (int i = 0; i<=args.length-1 ;++i){

            try {
                input[i] = Integer.parseInt(args[i]);
            } catch (Exception e) {
                System.out.println("Please enter proper data (integers only)");
                return;
            }
        }
        Scanner waiter = new Scanner(System.in);

        Supervisor supervisor = new Supervisor(Constants.fileExample(), input.length*4);
        Writer wr = new Writer(supervisor.path(), supervisor.buffer(), input);
        Reader rd = new Reader(supervisor.path(), supervisor.buffer());

        IntBuffer bf = supervisor.buffer().asIntBuffer();

        waiter.nextLine();

        wr.writeToFile();
        System.out.println(wr.getExpectedResult());

        waiter.nextLine();

        rd.readFromFile();
        System.out.println(rd.result());

    }
}
