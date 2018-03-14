import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class Writer {
    private MappedByteBuffer buff;
    private FileOutputStream out;
    private FileChannel channel;
    private int[] data;
    private int expectedResult = 0;

    public Writer(File writable, MappedByteBuffer buffer, int[] toWrite) throws Exception {
        buff = buffer;
        out = new FileOutputStream(writable);
        channel = out.getChannel();
        data = toWrite;
    }

    public void writeToFile() throws Exception {
        DataOutputStream dos = new DataOutputStream(out);

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
