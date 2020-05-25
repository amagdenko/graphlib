package org.graphlib;

import java.util.List;

import org.graphlib.error.IllegalEdgeArgumentException;
import org.graphlib.error.NoPathFoundException;
import org.junit.Assert;
import org.junit.Test;

/**
 * <p>
 *
 * @author Aleksandr Magdenko
 */
public class UndirectedGraphTest {
    @Test
    public void testAddVertex() {
        UndirectedGraph<Integer> graph = new UndirectedGraph<>();
        Vertex<Integer> vertex = graph.addVertex(123);

        Assert.assertEquals(Integer.valueOf(123), vertex.getObject());
    }

    @Test
    public void testAddVertexNullValue() {
        UndirectedGraph<Integer> graph = new UndirectedGraph<>();
        Vertex<Integer> vertex = graph.addVertex(null);

        Assert.assertNull( vertex.getObject());
    }

    @Test
    public void testAddEdge() {
        UndirectedGraph<Integer> graph = new UndirectedGraph<>();
        Vertex<Integer> v1 = graph.addVertex(1);
        Vertex<Integer> v2 = graph.addVertex(2);
        Edge<Vertex<Integer>> edge = graph.addEdge(v1, v2);

        Assert.assertNotNull(edge);
        Assert.assertEquals(v1, edge.getFrom());
        Assert.assertEquals(v2, edge.getTo());
    }

    @Test(expected = IllegalEdgeArgumentException.class)
    public void testAddEdgeFromNull() {
        UndirectedGraph<Integer> graph = new UndirectedGraph<>();
        Vertex<Integer> v2 = graph.addVertex(2);
        graph.addEdge(null, v2);
    }

    @Test(expected = IllegalEdgeArgumentException.class)
    public void testAddEdgeToNull() {
        UndirectedGraph<Integer> graph = new UndirectedGraph<>();
        Vertex<Integer> v1 = graph.addVertex(1);
        graph.addEdge(v1, null);
    }

    @Test(expected = IllegalEdgeArgumentException.class)
    public void testAddEdgeTheSameVertex() {
        UndirectedGraph<Integer> graph = new UndirectedGraph<>();
        Vertex<Integer> v1 = graph.addVertex(1);
        graph.addEdge(v1, v1);
    }

    @Test
    public void testGetPath() {
        UndirectedGraph<String> graph = new UndirectedGraph<>();
        Vertex<String> v1 = graph.addVertex("v1");
        Vertex<String> v2 = graph.addVertex("v2");
        graph.addEdge(v1, v2);

        Vertex<String> v3 = graph.addVertex("v3");
        graph.addEdge(v2, v3);

        Vertex<String> v6 = graph.addVertex("v6");
        graph.addEdge(v2, v6);
        graph.addEdge(v6, v1);

        Vertex<String> v5 = graph.addVertex("v5");
        graph.addEdge(v1, v5);
        Vertex<String> v7 = graph.addVertex("v7");
        graph.addEdge(v5, v7);

        Vertex<String> v4 = graph.addVertex("v4");
        graph.addEdge(v3, v4);

        List<Edge<Vertex<String>>> path = graph.getPath(v1, v4);

        Assert.assertNotNull(path);
        Assert.assertEquals("Wrong path size", 3, path.size());
        Assert.assertEquals("Wrong 2nd vertex", "v2", path.get(0).getTo().getObject());
        Assert.assertEquals("Wrong 3th vertex", "v3", path.get(1).getTo().getObject());
        Assert.assertEquals("Wrong 4th vertex", "v4", path.get(2).getTo().getObject());
    }

    @Test(expected = NoPathFoundException.class)
    public void testGetPathNotFound() {
        UndirectedGraph<String> graph = new UndirectedGraph<>();
        Vertex<String> v1 = graph.addVertex("v1");
        Vertex<String> v2 = graph.addVertex("v2");
        graph.addEdge(v1, v2);

        Vertex<String> v3 = graph.addVertex("v3");
        graph.addEdge(v2, v3);

        Vertex<String> v6 = graph.addVertex("v6");
        graph.addEdge(v2, v6);
        graph.addEdge(v6, v1);

        Vertex<String> v5 = graph.addVertex("v5");
        graph.addEdge(v1, v5);
        Vertex<String> v7 = graph.addVertex("v7");
        graph.addEdge(v5, v7);

        Vertex<String> v4 = graph.addVertex("v4");
        // We don't add edge to v4

        graph.getPath(v1, v4);
    }

    @Test
    public void testGetPathBackDirect() {
        UndirectedGraph<String> graph = new UndirectedGraph<>();
        Vertex<String> v1 = graph.addVertex("v1");
        Vertex<String> v2 = graph.addVertex("v2");
        graph.addEdge(v1, v2);

        Vertex<String> v3 = graph.addVertex("v3");
        graph.addEdge(v2, v3);

        Vertex<String> v6 = graph.addVertex("v6");
        graph.addEdge(v2, v6);
        graph.addEdge(v6, v1);

        Vertex<String> v5 = graph.addVertex("v5");
        graph.addEdge(v1, v5);
        Vertex<String> v7 = graph.addVertex("v7");
        graph.addEdge(v5, v7);

        Vertex<String> v4 = graph.addVertex("v4");
        // Edge with direction  from v4 to v3
        graph.addEdge(v4, v3);

        graph.getPath(v1, v4);
    }
}