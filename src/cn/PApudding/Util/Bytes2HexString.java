package cn.PApudding.Util;

public class Bytes2HexString {
    public static String bytes2HexString(byte[] bytes){
        StringBuilder stringBuilder = new StringBuilder();
        int digital;
        for(byte b :bytes){
            digital = b;
            if(digital<0){
                digital+=256;
            }
            if(digital<16){
                stringBuilder.append("0");
            }
            stringBuilder.append(Integer.toHexString(digital));
        }
        return stringBuilder.toString().toLowerCase();
    }
}
