package crawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import search.MySearch;


public class BasicCrawlController
{
    private static MySearch search;
    private String startUrl;
    private int crawlers;


    public void doCrawl()
        throws Exception
    {
        String crawlStorageFolder = "/Users/lasjul/workspace/javasearch/src/main/resources";

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder( crawlStorageFolder );
        config.setPolitenessDelay( 1000 );
        config.setMaxDepthOfCrawling( 2 );
        config.setMaxPagesToFetch( 1000 );
        config.setResumableCrawling( false );

        PageFetcher pageFetcher = new HttpsFetcher( config );
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer( robotstxtConfig, pageFetcher );
        CrawlController controller = new CrawlController( config, pageFetcher, robotstxtServer );

        controller.addSeed( this.startUrl );
        System.out.println("Controller, starting");
        controller.startNonBlocking(BasicCrawler.class, this.crawlers);
    }

    public static MySearch getSearch() {
        if (search == null) {
            search = new MySearch();
        }
        return search;
    }

    public BasicCrawlController setStartUrl(String startUrl) {
        this.startUrl = startUrl;
        return this;
    }

    public BasicCrawlController setCrawlers(int crawlers) {
        this.crawlers = crawlers;
        return this;
    }
}