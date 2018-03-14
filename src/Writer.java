import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Writer {
    private MappedByteBuffer buff;
    private FileChannel channel;
    private int[] data;
    private int expectedResult = 0;
    private Path path;

    public Writer(Path _path, MappedByteBuffer buffer, int[] toWrite) throws Exception {
        path = _path;
        channel = FileChannel.open(path, StandardOpenOption.WRITE);
        buff = buffer;
        data = toWrite;
    }

    public void writeToFile() throws Exception {
        for (int i = 0 ; i <= data.length-1 ;++i){
            buff.putInt(data[i]);
            expectedResult += data[i];
        }
        channel.write(buff);
        buff.force();
        channel.close();
        buff.rewind();
    }

    public int getExpectedResult(){
        return expectedResult;
    }
}
