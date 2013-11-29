package search;

import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

public class SearchClient {

    private static Node node;

    public static Client getClient() {

        if (node == null) {
            node = nodeBuilder().node();
        }
        return node.client();

    }

    public static void shutdown() {
        if (node != null) {
            node.close();
        }
    }
}
