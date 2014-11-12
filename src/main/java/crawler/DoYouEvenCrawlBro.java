package crawler;

/**
 * Created by lasjul on 12/11/14.
 */
public class DoYouEvenCrawlBro {
    public static void main(String...args) throws Exception {
        BasicCrawlController crawlController = new BasicCrawlController();
        crawlController.setStartUrl("http://www.vg.no");
        crawlController.setCrawlers(1);
        System.out.println("Staring crawl");
        crawlController.doCrawl();
    }
}
