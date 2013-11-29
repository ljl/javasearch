package service;

import crawler.BasicCrawlController;
import model.JsonResponse;
import org.elasticsearch.action.index.IndexResponse;
import search.MySearch;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;


@Path("/index")
@Produces(MediaType.APPLICATION_JSON)
public class IndexService {

    @GET
    @Path("{seed}")
    public JsonResponse startIndex(@PathParam("seed")String seed) {
        try {
            BasicCrawlController.startCrawl("http://www.aftenposten.no", 1);
        } catch (Exception e) {
            return new JsonResponse("ERROR", e.getMessage());
        }
        return new JsonResponse("OK", "Started indexing of site " + seed);
    }

    @GET
    @Path("test")
    public JsonResponse testIndex() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("test", "testvalue");
        MySearch search = new MySearch();
        IndexResponse response = search.index(map);
        return new JsonResponse("OK", response.getId());
    }
}
