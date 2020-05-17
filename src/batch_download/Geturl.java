package batch_download;


import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Example program to list links from a URL.
 */
public class Geturl {
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Geturl A = new Geturl();
        List<String> link_list = new ArrayList<String>();
        link_list = A.links("http://www.hanbook.cn/erwm/zjtytl/day.asp?d=3");
        System.out.println(link_list.toString());

    }
    public List<String> links(String url) throws IOException{
        List<String> list = new ArrayList<String>();
        print("Fetching %s...", url);
        String html = Jsoup.connect(url).get().html();
        Document doc = Jsoup.parse(html);
        Elements links = doc.select("a");
        Elements imports = doc.select("link[href]");

        print("\nLinks: (%d)", links.size());
        int i=0;
        for (Element link : links) {
            String resCallBackJson = "downcenter";
//            这里做了一个剪切，因为在实例所用的页面中href link address显示不完整，所以将省略号去掉留下有用的部分
            String link1 = link.attr("href").substring(link.attr("href").indexOf(resCallBackJson));
            list.add(link1);
            i++;
        }
        return list;
    }
    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }
}
