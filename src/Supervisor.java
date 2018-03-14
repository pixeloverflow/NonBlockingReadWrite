import com.sun.xml.internal.bind.api.impl.NameConverter;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Supervisor {
    private MappedByteBuffer buff;
    private FileChannel channel;
    private Path path;

    public Supervisor(String path) throws Exception {
        this.path = Paths.get(path);
        channel = FileChannel.open(this.path, StandardOpenOption.WRITE, StandardOpenOption.READ);
        buff = channel.map(FileChannel.MapMode.READ_WRITE, 0, (int)channel.size());
    }

    public Path path(){
        return path;
    }

    public MappedByteBuffer buffer() {
        return buff;
    }
}
