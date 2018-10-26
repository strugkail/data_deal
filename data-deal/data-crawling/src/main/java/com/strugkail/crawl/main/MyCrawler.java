package com.strugkail.crawl.main;

import com.strugkail.crawl.link.Links;
import com.strugkail.crawl.page.Page;
import com.strugkail.crawl.page.PageParserTool;
import com.strugkail.crawl.page.RequestAndResponseTool;
import com.strugkail.crawl.util.FileTool;
import org.jsoup.select.Elements;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class MyCrawler {

    /**
     * 使用种子初始化 URL 队列
     *
     * @param seeds 种子 URL
     * @return
     */
    private static void initCrawlerWithSeeds(String[] seeds) {
        for (int i = 0; i < seeds.length; i++) {
            Links.addUnvisitedUrlQueue(seeds[i]);
        }
    }


    /**
     * 抓取过程
     *
     * @param seeds
     * @return
     */
    public static List<String> crawling(String[] seeds) {

        //初始化 URL 队列
        initCrawlerWithSeeds(seeds);

        //定义过滤器，提取以 http://www.baidu.com 开头的链接
//        LinkFilter filter = new LinkFilter() {
//            @Override
//            public boolean accept(String url) {
//                if (url.startsWith("http://www.wowoqq.com"))
//                    return true;
//                else
//                    return false;
//            }
//        };

        //循环条件：待抓取的链接不空且抓取的网页不多于 1000
        while (!Links.unVisitedUrlQueueIsEmpty() && Links.getVisitedUrlNum() <= 1000) {

            //先从待访问的序列中取出第一个；
            String visitUrl = (String) Links.removeHeadOfUnVisitedUrlQueue();
            if (visitUrl == null) {
                continue;
            }

            //根据URL得到page;
            Page page = null;
            try {
                page = RequestAndResponseTool.sendRequstAndGetResponse(visitUrl);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            if (null != page) {
                //对page进行处理： 访问DOM的某个标签
//                Elements es = PageParserTool.select(page, "a");
////                if (!es.isEmpty()) {
//////                    System.out.println("下面将打印所有a标签： ");
//////                    System.out.println(es);
////                }

                //将保存文件
                String filePath = FileTool.saveToLocal(page);
//                System.out.println("page"+page);
                //将已经访问过的链接放入已访问的链接中；
                Links.addVisitedUrlSet(visitUrl);

                //得到超链接
                Set<String> links = PageParserTool.getLinks(page, "img");
                for (String link : links) {
                    Links.addImgPath(link);
                    Links.addUnvisitedUrlQueue(link);
//                    System.out.println("新增爬取路径: " + link);
                }
            Set<String> links1 = PageParserTool.getLinks(page,"a");
                for (String link :links1){
                    Links.addUnvisitedUrlQueue(link);
//                    System.out.println("新增爬取路径: " + link);
                }
            }
        }
        LinkedList<String> ll = Links.getPathList();
//        System.out.println("总数量："+ll.size());
//        ll.forEach(System.out::println);
//        FileTool.saveTP(ll);
        return ll;
    }


    //main 方法入口
    public static void main(String[] args) {
//        crawling(new String[]{"https://www.enterdesk.com/bizhi/8201.html",
//                "https://www.enterdesk.com/special/lizhi",
//                "https://www.enterdesk.com/search/1-0-6-0-0-0/%E7%BE%8E%E5%A5%B3"});
//        crawling(new String[]{"https://www.enterdesk.com"});
//        crawling(new String[]{"https://image.baidu.com/search/detail?z=0&word=%E6%91%84%E5%BD%B1%E5%B8%88%E8%8B%8F%E5%B0%8F%E5%BC%BA&hs=0&pn=5&spn=0&di=0&pi=54620974346&tn=baiduimagedetail&is=0%2C0&ie=utf-8&oe=utf-8&cs=1268535730%2C1012489156&os=&simid=&adpicid=0&lpn=0&fm=&sme=&cg=&bdtype=-1&oriquery=&objurl=http%3A%2F%2Fc.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F7e3e6709c93d70cf312a368af4dcd100bba12b60.jpg&fromurl=&gsm=0&catename=pcindexhot&islist=&querylist="});
//        crawling(new String[]{"http://616pic.com/tupian/meinv.html"});
//        crawling(new String[]{"http://www.27270.com/ent/meinvtupian/","http://www.umei.cc/tags/shaonv.htm"});
        ArrayList<String> strList = new ArrayList<>();
        String baseStr = "http://www.gaoxiaogif.com/qqbiaoqing/wu/index_";
        String urlStr = "";
        strList.add("http://www.gaoxiaogif.com/qqbiaoqing/wu");
        for (int i = 2; i < 62; i++) {
            urlStr = baseStr + i + ".html";
            strList.add(urlStr);
        }
        strList.forEach(e->{
            System.out.println("============================"+e);
        });
        crawling(strList.toArray(new String[0]));

    }
}
