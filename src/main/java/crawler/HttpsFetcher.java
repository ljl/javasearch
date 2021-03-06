package crawler;

import org.apache.http.conn.scheme.Scheme;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;

public class HttpsFetcher extends PageFetcher
{

	public HttpsFetcher(CrawlConfig config) {
		super(config);

		if (config.isIncludeHttpsPages()) {
			try {
				httpClient.getConnectionManager().getSchemeRegistry()
						.unregister("https");
				httpClient.getConnectionManager().getSchemeRegistry().register(
						new Scheme("https", 443, new MockSSLSocketFactory()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


}
