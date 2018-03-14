import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Reader {
    private MappedByteBuffer buff;
    private FileChannel channel;
    private IntBuffer humanReadableFormat;
    private int operationResult = 0;

    public Reader(Path _path, MappedByteBuffer buffer) throws Exception {
        buff = buffer;
        channel = FileChannel.open(_path, StandardOpenOption.READ);
    }

    public void readFromFile() throws Exception {
        channel.read(buff);
        channel.close();
        buff.flip();
        humanReadableFormat = buff.asIntBuffer();
        calc();
    }

    private void calc(){
        while (humanReadableFormat.hasRemaining()){
            operationResult += humanReadableFormat.get();
        }
    }

    public int result(){
        return operationResult;
    }
}
