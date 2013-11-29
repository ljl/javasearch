package search;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.ClientConfig;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

public class MySearch {
    private Client client;

    public MySearch() {
        this.client = SearchClient.getClient();
    }

    public IndexResponse index(Map<String, String> source) {
//        Index index = new Index.Builder(source).index("documents").type("document").build();
//        JestResult res = null;
//        try
//        {
//            res = client.execute(index);
//            System.out.println(res.getJsonString());
//        }
//        catch ( Exception e )
//        {
//            e.printStackTrace();
//        }
        IndexRequestBuilder requestBuilder = client.prepareIndex("documents", "document");

        XContentBuilder builder = null;
        try {
            builder = jsonBuilder().startObject();
            for (Map.Entry<String, String> e : source.entrySet()) {
                builder.field(e.getKey(), e.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        requestBuilder.setSource(builder);

        IndexResponse response = requestBuilder.execute().actionGet();

        return response;
    }

    public SearchResponse search(String query) throws Exception {
        SearchResponse response = client.prepareSearch("documents")
                .setTypes("document")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.termQuery("multi", query))
                .execute()
                .actionGet();

        return response;
    }


}


//"query": {
//        "fuzzy_like_this": {
//        "fields": [
//        "url",
//        "title",
//        "path"
//        ],
//        "like_text": "norge",
//        "min_similarity": 0.6
//        }
//        },
//        "facets": {
//        "terms": {
//        "terms": {
//        "field": "title",
//        "size": 3
//        }
//        }
//        },