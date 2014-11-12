package service;

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
    public String doSearch(@PathParam("q")String query) {
        MySearch search = new MySearch();
        try {
            return search.search(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GET
    @Path("default")
    public String defaultSearch() {
        MySearch search = new MySearch();
        try {
            return search.defaultSearch();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
