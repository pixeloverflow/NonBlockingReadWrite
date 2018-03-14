import java.io.File;
import java.io.FileInputStream;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class Reader {
    private MappedByteBuffer buff;
    private FileInputStream in;
    private FileChannel channel;
    private IntBuffer humanReadableFormat;
    private int operationResult = 0;

    public Reader(File readable, MappedByteBuffer buffer) throws Exception {
        buff = buffer;
        in = new FileInputStream(readable);
        channel = in.getChannel();
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
