package com.yl.study;


import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

/**
 * Created by Administrator on 2018/7/11 0011.
 */
public class VerificationCode {


    public static void main(String[] args) throws TesseractException {
        ITesseract instance = new Tesseract();
        //如果未将tessdata放在根目录下需要指定绝对路径
        instance.setDatapath("E:\\aaa\\Tess4J-3.4.8-src\\Tess4J\\tessdata");
        // 我们需要指定识别语种
        instance.setLanguage("chi_sim");
        // 指定识别图片
//        File imgDir = new File("E:\\aaa\\tt.jpg");
        File imgDir = new File("E:\\aaa\\bb.png");
        long startTime = System.currentTimeMillis();
        String ocrResult = instance.doOCR(imgDir);
        // 输出识别结果
        System.out.println("OCR Result: \n" + ocrResult + "\n 耗时：" + (System.currentTimeMillis() - startTime) + "ms");


    }


}
