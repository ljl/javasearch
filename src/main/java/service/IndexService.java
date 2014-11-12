package service;

import crawler.BasicCrawlController;
import model.JsonResponse;
import org.elasticsearch.action.admin.indices.refresh.RefreshResponse;
import org.elasticsearch.action.index.IndexResponse;
import search.MySearch;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;


@Path("/index")
@Produces(MediaType.APPLICATION_JSON)
public class IndexService {

    @POST
    @Path("start")
    public JsonResponse startIndex(@FormParam("startUrl")String startUrl) {
        try {
            //TODO: Check that URL is valid
            new BasicCrawlController().setStartUrl(startUrl).setCrawlers(1).doCrawl();
        } catch (Exception e) {
            return new JsonResponse("ERROR", e.getMessage());
        }
        return new JsonResponse("OK", "Started indexing of site " + startUrl);
    }

    @POST
    @Path("test")
    public JsonResponse testIndex(@FormParam("value")String value) {
        System.out.println("Testing index with " + value);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("test", value);
        MySearch search = new MySearch();
        IndexResponse response = search.index(map);
        return new JsonResponse("OK", response.getId());
    }

    @GET
    @Path("reindex")
    public RefreshResponse reindex() {
        MySearch search = new MySearch();
        return search.reindex();
    }
}
