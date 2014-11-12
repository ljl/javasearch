package search;

import org.elasticsearch.action.admin.indices.refresh.RefreshResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.StatusToXContent;
import org.elasticsearch.common.xcontent.ToXContent;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import java.io.IOException;
import java.util.Map;

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

    public RefreshResponse reindex() {
        RefreshResponse response = client.admin()
                .indices()
                .prepareRefresh()
                .execute()
                .actionGet();
        return response;
    }

    public String search(String query) throws Exception {
        SearchResponse response = client.prepareSearch("documents")
                .setTypes("document")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.fuzzyLikeThisQuery("url", "title", "path").likeText(query))
                .execute()
                .actionGet();

        return this.responseAsString(response);
    }


    public String defaultSearch() throws Exception {
        SearchResponse response = client.prepareSearch().execute().actionGet();
        return this.responseAsString(response);
    }

    private String responseAsString(StatusToXContent o) throws Exception {
        XContentBuilder builder = jsonBuilder();
        builder.startObject();
        o.toXContent(builder, ToXContent.EMPTY_PARAMS);
        builder.endObject();

        return builder.string();
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