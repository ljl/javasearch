import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

/**
 * @author Yasser Ganjisaffar <lastname at gmail dot com>
 */
public class BasicCrawlController
{
    private static Search search;

    public static void main( String[] args )
        throws Exception
    {
        if ( args.length != 2 )
        {
            System.out.println( "Needed parameters: " );
            System.out.println( "\t rootFolder (it will contain intermediate crawl data)" );
            System.out.println( "\t numberOfCralwers (number of concurrent threads)" );
            return;
        }

        String crawlStorageFolder = args[0];

        int numberOfCrawlers = 10;

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

        controller.addSeed( "http://aftenposten.no/" );
        controller.start( BasicCrawler.class, numberOfCrawlers );
    }

    public static Search getSearch() {
        if (search == null) {
            search = new Search();
        }
        return search;
    }
}