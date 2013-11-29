package service;

import model.JsonResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import search.MySearch;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/search")
@Produces(MediaType.APPLICATION_JSON)
public class SearchService {

    @GET
    @Path("{q}")
    public SearchResponse doSearch(@PathParam("q")String query) {
        MySearch search = new MySearch();
        try {
            return search.search(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
