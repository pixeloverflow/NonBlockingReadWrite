import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class Supervisor {
    private File file;
    private RandomAccessFile rfile;
    private MappedByteBuffer buff;
    private FileChannel channel;

    public Supervisor(String path) throws Exception {
        file = new File(path);
        rfile = new RandomAccessFile(file, "rw");
        channel = rfile.getChannel();
        buff = channel.map(FileChannel.MapMode.READ_WRITE, 0, (int)channel.size());
    }

    public File file(){
        return file;
    }

    public MappedByteBuffer buffer() {
        return buff;
    }
}
