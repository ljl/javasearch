import java.io.IOException;
import java.util.Map;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.ClientConfig;
import io.searchbox.core.Index;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

public class Search
{
    private JestClient client;

    public Search() {
        ClientConfig clientConfig = new ClientConfig.Builder("http://localhost:9200").multiThreaded(true).build();

        JestClientFactory factory = new JestClientFactory();
        factory.setClientConfig(clientConfig);
        this.client = factory.getObject();



    }

    public void index(Map<String,String> source) {
        Index index = new Index.Builder(source).index("documents").type("document").build();
        JestResult res = null;
        try
        {
            res = client.execute(index);
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        System.out.println(res.getJsonString());
    }




}
