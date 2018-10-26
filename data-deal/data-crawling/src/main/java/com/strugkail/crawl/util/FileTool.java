package com.strugkail.crawl.util;



import com.strugkail.crawl.page.Page;
import com.strugkail.crawl.page.RequestAndResponseTool;
import org.apache.commons.lang3.StringUtils;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Administrator
 */ /*  本类主要是 下载那些已经访问过的文件*/
public class FileTool {

    private static String dirPath;


    /**
     * getMethod.getResponseHeader("Content-Type").getValue()
     * 根据 URL 和网页类型生成需要保存的网页的文件名，去除 URL 中的非文件名字符
     */
    private static String getFileNameByUrl(String url, String contentType) {
        //去除 http://
        url = url.substring(7);
        //text/html 类型
//        if (contentType.indexOf("html") != -1) {
//            url = url.replaceAll("[\\?/:*|<>\"]", "_") + ".html";
//            return url;
//        }
        if (contentType.indexOf("html") != -1) {
//            url = url.replaceAll("[\\?/:*|<>\"]", "_") + ".html";
            return "";
        }
        //如 application/pdf 类型
        else {
            return url.replaceAll("[\\?/:*|<>\"]", "_") + "." +
                    contentType.substring(contentType.lastIndexOf("/") + 1);
        }
    }

    /*
    *  生成目录
    */
    private static void mklocaldir() {
        if (dirPath == null) {
            dirPath = Class.class.getClass().getResource("/").getPath() + "jpg/wechatbp/wu/";
        }
        File fileDir = new File(dirPath);
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }
    }

    /**
     * 保存网页字节数组到本地文件，filePath 为要保存的文件的相对地址
     */

    public static String saveToLocal(Page page) {
        mklocaldir();
        String fileName = "" ;
        if(page.getContentType().equals("image/gif")){
            fileName =  System.currentTimeMillis()+".gif" ;
        }else if(page.getContentType().equals("image/jpeg")){
            fileName =  System.currentTimeMillis()+".jpg" ;
        }
        if(StringUtils.isBlank(fileName)){
                return "";
        }
        String filePath = dirPath + fileName ;
        byte[] data = page.getContent();
        try {
//            Files.lines(Paths.get("E:\\jd.txt"), StandardCharsets.UTF_8).forEach(System.out::println);
            DataOutputStream out = new DataOutputStream(new FileOutputStream(new File(filePath)));
            for (int i = 0; i < data.length; i++) {
                out.write(data[i]);
            }
            out.flush();
            out.close();
//            System.out.println("文件："+ fileName + "已经被存储在"+ filePath  );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }

    public static void saveTP(LinkedList<String> strList){
        mklocaldir();
        strList.forEach(url ->{
            //根据URL得到page;
            Page page = null;
            try {
                page = RequestAndResponseTool.sendRequstAndGetResponse(url);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            String fileName =  System.currentTimeMillis()+"" ;
            if(page.getContentType().equals("image/gif")){
                fileName =  fileName+".gif" ;
            }else{
                fileName =  fileName+".jpg" ;
            }

//            if(url.endsWith("gif")){
//                fileName =  System.currentTimeMillis()+".gif" ;
//            }

            String filePath = dirPath + fileName ;
            byte[] data = page.getContent();
            DataOutputStream out = null;
            try {
                out = new DataOutputStream(new FileOutputStream(new File(filePath)));
                for (int i = 0; i < data.length; i++) {
                    out.write(data[i]);
                }
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

}
