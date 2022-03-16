package com.landian.util;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.xmlbeans.XmlException;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class FileUtil {

    /**
     * 将字符串写入文件中
     * @param s
     * @param fileName
     * @return
     */
    public static File StringIntoFile(String s,String fileName){
        FileWriter fw = null;
        File f = new File(fileName);

        try {
            if(!f.exists()){
                f.createNewFile();
            }
            fw = new FileWriter(f);
            BufferedWriter out = new BufferedWriter(fw);
            out.write(s, 0, s.length()-1);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return f;
    }


    /**
     * 读取MultipartFile类型中的内容转换成String返回
     * @param uploadFile
     * @return
     */
    public static String multipartFileToString(MultipartFile uploadFile) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String originalFilename = uploadFile.getOriginalFilename();
        File file = new File(originalFilename);
        uploadFile.transferTo(file);
        if(originalFilename.endsWith(".txt")){
            Reader reader = null;
            try {
                reader = new InputStreamReader(new FileInputStream(file));
                int tempchar;
                while ((tempchar = reader.read()) != -1) {
                    // 对于windows下，\r\n这两个字符在一起时，表示一个换行。
                    // 但如果这两个字符分开显示时，会换两次行。
                    // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
                    if (((char) tempchar) != '\r') {
                        stringBuilder.append((char) tempchar);
                        System.out.print((char) tempchar);
                    }
                }
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else
        if(originalFilename.endsWith(".doc")){
            try {
                WordExtractor re = new WordExtractor(new FileInputStream(file));
                stringBuilder.append(re.getText());
                re.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if(originalFilename.endsWith(".docx")){
            OPCPackage opcPackage = null;
            try {
                opcPackage = POIXMLDocument.openPackage(file.getAbsolutePath());
                POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
                stringBuilder.append(extractor.getText());
                extractor.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlException e) {
                e.printStackTrace();
            } catch (OpenXML4JException e) {
                e.printStackTrace();
            }
        }
        file.delete();
        return stringBuilder.toString();
    }


}
