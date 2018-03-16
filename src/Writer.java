import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Writer {
    private MappedByteBuffer buff;
    private FileChannel channel;
    private int[] data;
    private Path path;

    public Writer(String _path, String rawData) throws Exception {
        path = Paths.get(_path);
        channel = FileChannel.open(path, StandardOpenOption.WRITE, StandardOpenOption.READ);
        String[] strings = rawData.split(";");
        data = new int[strings.length];
        buff = channel.map(FileChannel.MapMode.READ_WRITE, 0, (data.length+1)*4);
        for (int i = 0; i <= strings.length-1 ;++i) {
            data[i] = Integer.parseInt(strings[i]);
        }
    }

    private int random(){
        return (int)(Math.random()*10);
    }

    private void start() throws Exception {
        while (true) {
            if (buff.getInt(0)==1) {
                System.out.print("Adding :");
                for (int i = 1; i <= data.length - 1; ++i) {
                    int r = random();
                    if (r == 1) r = 0;
                    System.out.print(" " + r);
                    data[i] = r;
                }
                System.out.println();

                for (int i = 1; i <= data.length - 1; ++i) {
                    buff.putInt(data[i]);
                }

                buff.putInt(0, 0);
                buff.rewind();
            }
            Thread.sleep(100);
        }
    }

    public static void main(String[] args) throws Exception {
        Writer wr = new Writer(args[0], args[1]);
        wr.start();
    }
}
