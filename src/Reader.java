import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Reader {
    private MappedByteBuffer buff;
    private FileChannel channel;
    private IntBuffer humanReadableFormat;
    private int operationResult = 0;
    private boolean flag = false;

    public Reader(String _path, String bufferSize) throws Exception {
        channel = FileChannel.open(Paths.get(_path), StandardOpenOption.READ, StandardOpenOption.WRITE);
        buff = channel.map(FileChannel.MapMode.READ_WRITE, 0, Integer.parseInt(bufferSize)*4);
        humanReadableFormat = buff.asIntBuffer();
        humanReadableFormat.put(0,0);
    }

    private void readFromFile() throws Exception {
        if(humanReadableFormat.get(0) == 0) {
            calc();
            humanReadableFormat.rewind();
            System.out.println("Read : " + result());
            resetResult();
        }
    }

    private void calc(){
        while (humanReadableFormat.hasRemaining()){
            operationResult += humanReadableFormat.get();
        }
    }

    private int result(){
        return operationResult;
    }

    private void resetResult(){
        operationResult = 0;
        humanReadableFormat.put(0, 1);
    }

    private void start() throws Exception {
        while(true){
            readFromFile();
            Thread.sleep(100);
        }
    }

    public static void main(String[] args) throws Exception {
        Reader rd = new Reader(args[0], args[1]);
        rd.start();
    }
}
