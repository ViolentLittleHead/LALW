package com.landian.util;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.xmlbeans.XmlException;
import org.junit.Test;

import java.io.*;

public class TestFileUtil {

    @Test
    public void test(){
        String str ="消元算法.docx";
        System.out.println(str.contains(".docx"));
    }

    @Test
    public void testStringIntoFile(){
        File file = FileUtil.StringIntoFile("豪岛东南哇哦i脑电波啊", "a.txt");
        Reader reader = null;
        StringBuilder stringBuilder = new StringBuilder();
        /*OPCPackage opcPackage = null;
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
        }*/

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

        System.out.println(stringBuilder.toString());
    }



}
