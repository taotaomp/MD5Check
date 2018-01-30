package cn.PApudding.Util;

import java.nio.MappedByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CodeGenerate{

    public static String generateValueByBytes(MappedByteBuffer mappedByteBuffer,String Algorithm) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(Algorithm);
        messageDigest.update(mappedByteBuffer);
        byte[]buff = messageDigest.digest();
        return Bytes2HexString.bytes2HexString(buff);
    }

}
