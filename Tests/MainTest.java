import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainTest {
    private Supervisor supervisor;
    private Writer wr;
    private Reader rd;

    public void prep() throws Exception {
        int[] data = { 9, 2, 1, 12, 6};
        supervisor = new Supervisor(Constants.fileExample());
        wr = new Writer(supervisor.path(), supervisor.buffer(), data);
        rd = new Reader(supervisor.path(), supervisor.buffer());
    }
    @Test
    public void writeReadAndCheck() throws Exception {
        prep();
        wr.writeToFile();
        rd.readFromFile();
        Assertions.assertEquals(wr.getExpectedResult(), rd.result());
    }
}