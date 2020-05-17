package batch_download;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DownloadURLFile {

    /**
     * @param args
     */
    public static void main(String[] args) throws IOException {
        Geturl url = new Geturl();
        List<String> link_list = new ArrayList<String>();
        link_list = url.links("http://www.hanbook.cn/erwm/zjtytl/day.asp?d=3");
        for(int i=0;i<link_list.size();i++) {
//            这里加上前缀是因为在上面的这个网站中的href link没有显示完整，所以这里把前面去掉的部分补上
            String res = downloadFromUrl("http://www.hanbook.cn/" + link_list.get(i), "D:\\java_project\\");
//            String res = downloadFromUrl(link_list.get(i),"D:\\java_project\\");
        System.out.println(res);
        }
    }


    public static String downloadFromUrl(String url,String dir) {
        try {
            URL httpurl = new URL(url);
            String fileName = getFileNameFromUrl(url);
            System.out.println(fileName);
            File f = new File(dir + fileName);
            FileUtils.copyURLToFile(httpurl, f);
        } catch (Exception e) {
            e.printStackTrace();
            return "Fault!";
        }
        return "Successful!";
    }

    public static String getFileNameFromUrl(String url){
        String name = new Long(System.currentTimeMillis()).toString() + ".X";
        int index = url.lastIndexOf("/");
        if(index > 0){
            name = url.substring(index + 1);
            if(name.trim().length()>0){
                return name;
            }
        }
        return name;
    }
}