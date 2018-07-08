package ynu.com.ynubbs.Web;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class StreamTool {

    public static byte[] read(InputStream inStream) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        //如果字节流中的数据不等于-1，就说明一直有，然后循环读出
        while( (len=inStream.read(buffer)) !=-1){
            //将读出的数据放入内存中
            outputStream.write(buffer);

        }
        inStream.close();
        return outputStream.toByteArray();
    }

}