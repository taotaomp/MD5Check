package cn.PApudding.BytesPipeline;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class File2Bytes {
    private File file;
    private FileInputStream fileInputStream;

    public static File2Bytes getInstance(String path) {
        File2Bytes file2Bytes = new File2Bytes();
        file2Bytes.file = new File(path);

        try {
            file2Bytes.fileInputStream = new FileInputStream(file2Bytes.file);
            return file2Bytes;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public MappedByteBuffer getFileBytes() throws IOException {
        MappedByteBuffer byteBuffer = fileInputStream.getChannel()
                .map(FileChannel.MapMode.READ_ONLY,0,file.length());
        return byteBuffer;
    }

    public void release() throws IOException {
        fileInputStream.close();
    }
}
