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

    public Supervisor(String _path, int bufferSize) throws Exception {
        path = Paths.get(_path);
        channel = FileChannel.open(path, StandardOpenOption.WRITE, StandardOpenOption.READ);
        buff = channel.map(FileChannel.MapMode.READ_WRITE, 0, bufferSize);
    }

    public Path path(){
        return path;
    }

    public MappedByteBuffer buffer() {
        return buff;
    }
}
