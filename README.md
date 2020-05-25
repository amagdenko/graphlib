## Graph lib 

Simple graph lib to keep vertexes/edges and get path between two vertexes (not optimal).
Graph can be directed and undirected.

**Note**: between two vertexes can be added more than one edge.

Library don't have any external dependencies (commons-logging, slf4j)

#### Build from Gradle
    - Execute from module folder

        gradlew build

Find JAR file in folder build/libs/

#### About Questions to be ready to answer (don’t have to implement).

#####1.  Add weighted edges support in your lib.

For weighted edges I can update the code in DirectedGraph.
Create new class WigthedVertex to include weight for calculated path and return it instead of simple Vertex.
And on step 2 loop over all edges and find maximum weight to return.

    private WigthedVertex<T> findPath(Vertex<T> vertex, Vertex<T> targetVertex, Set<Vertex<T>> usedVertexes, LinkedList<Edge<Vertex<T>>> path)    private Vertex<T> findPath(Vertex<T> vertex, Vertex<T> targetVertex, Set<Vertex<T>> usedVertexes, LinkedList<Edge<Vertex<T>>> path) {
          ...
          
        Map<Edge<Vertex<T>>, WigthedVertex<T>> foundVertexes = new HashMap<>();

        // 2. Loop over edges and deep for every edge.
        for (Edge<Vertex<T>> nextEdge : nextEdges) {
            path.addLast(nextEdge);
  
            WigthedVertex<T> found = findPath(nextEdge.getTo(), targetVertex, usedVertexes, path);
  
            // 3. Check result for every calculated edge.
            if (found.getVertex() == targetVertex) {
                foundVertexes.put(nextEdge, found);
            }

            path.removeLast();
        }

        // Find maximum weighted vertex in map foundVertexes.
          
          ...
      }


#####2. Add traverse function that will take a user defined function and apply it on every vertex of the graph.
 For abstract graph add simple method
        
            public synchronized <R> List<R> traverseAll(Function<T, R> function) {
                return vertexes.stream()
                        .map(vertex -> function.apply(vertex.getObject()))
                        .collect(Collectors.toList());
            }

We can replace stream() with parallel() or add method to include _java.util.concurrent.ExecutorService_ and
 return list of _java.util.concurrent.Future<T>_


#### Comments
1. I didn't create package visible classes for model Edge and Vertex to make it extendable and used for
serialization/deserialization. But if necessary it simple to add separate Builder-classes for them.

For simplicity used simple _synchronized_ keyword for method to lock other method calls instead of ReadWriteLock and etc.


2. For real projects it is better to use _org:jgrapht_ library. 
It is amazing and very easy to use. 
I have experience with old version 'org.jgrapht:jgrapht-core:0.9.1'. Where we can simply extend our DirectedGraph 

            public class DirectedGraph extends DirectedPseudograph<Vertex, Edge<Vertex>> implements Serializable {
                ... 
            } 
            
