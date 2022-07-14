package com.ll.exam;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Util {
    public static void saveToFile(String path, String body) {
       //파일을 읽을 때 권한이 없을 수도 있고, 파일이 없을 수도 있으므로 그런 위험요소 -> try/catch
        //close() 해줘야 하는 것을 인수로 넣으면 자동해제됨
        try (RandomAccessFile stream = new RandomAccessFile(path, "rw");
             FileChannel channel = stream.getChannel()) {
            byte[] strBytes = body.getBytes();
            ByteBuffer buffer = ByteBuffer.allocate(strBytes.length);
            buffer.put(strBytes);
            buffer.flip();
            channel.write(buffer);
        } catch (IOException e) {

        }
    }

    //폴더 만들기
    public static void mkdir(String path) {
        File dir = new File(path);
        dir.mkdirs();
    }

    //파일 읽기
    public static String readFromFile(String path) {
        try (RandomAccessFile reader = new RandomAccessFile(path, "r")) {
            String body = "";

            String line = null;
            while ((line = reader.readLine()) != null) {
                //파일에서 텍스트 읽어올 때 한글 깨짐 현상 해결
                body += new String(line.getBytes("iso-8859-1"), "utf-8") + "\n";
            }

            return body.trim();
        }
        catch ( IOException e ) {
        }

        return "";
    }
}
