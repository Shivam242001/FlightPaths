// --== CS400 File Header Information ==--
// Name: Aayush Ritesh Dani
// Email: ardani@wisc.edu
// Team: EF
// TA: Yelun Bao
// Lecturer: Florian Heimerl
// Notes to Grader: N/A


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the implementation of CS400Graph for the individual component of
 * Project Three: the implementation of Dijsktra's Shortest Path algorithm.
 */
public class GraphTest {

    private CS400Graph<String> graph;
    
    /**
     * Instantiate graph from last week's shortest path activity.
     */
    @BeforeEach
    public void createGraph() {
        graph = new CS400Graph<>();
        // insert vertices A-E
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");
        // insert edges from Week 09. Dijkstra's Activity
        graph.insertEdge("A","B",2);
        graph.insertEdge("A","D",4);
        graph.insertEdge("A","E",1);
        graph.insertEdge("B","C",5);
        graph.insertEdge("C","A",3);
        graph.insertEdge("D","B",3);
        graph.insertEdge("D","C",7);
        graph.insertEdge("D","E",1);
        graph.insertEdge("E","C",8);
    }

    /**
     * Checks the distance/total weight cost from the vertex labelled C to E
     * (should be 4), and from the vertex labelled A to C (should be 7).
     */
    @Test
    public void providedTestToCheckPathCosts() {
        assertTrue(graph.getPathCost("C", "E") == 4);    
        assertTrue(graph.getPathCost("A", "C") == 7);
    }

    /**
     * Checks the ordered sequence of data within vertices from the vertex 
     * labelled C to E, and from the vertex labelled A to C.
     */
    @Test
    public void providedTestToCheckPathContents() {
        assertTrue(graph.shortestPath("C", "E").toString().equals(
            "[C, A, E]"
        ));
        assertTrue(graph.shortestPath("A", "C").toString().equals(
            "[A, B, C]"
        ));
    }

    /**
     * Checks the distance/total weight cost from the vertex labelled A to C
     * (should be 7), i.e, the distance to the furthest node.
     */
    @Test
    public void testToCheckFurthestNodeDistance() {
        assertTrue(graph.getPathCost("A", "C") == 7);
    }

    /**
     * Checks the ordered sequence of data within vertices from the vertex 
     * labelled A to C, i.e, the sequence to the furthest node.
     */
    @Test
    public void testToCheckFurthestNodeSequence() {
        assertTrue(graph.shortestPath("A", "C").toString().equals(
            "[A, B, C]"
        ));
    }

    /**
     * Checks the distance/total weight cost from the vertex labelled A to all the vertices 
     * in the graph.
     */
    @Test
    public void testToCheckDistanceToAllNodes() {
        assertTrue(graph.getPathCost("A", "B") == 2);
        assertTrue(graph.getPathCost("A", "C") == 7);
        assertTrue(graph.getPathCost("A", "D") == 4);
        assertTrue(graph.getPathCost("A", "E") == 1);

    }

    /**
     *  Checks the ordered sequence of data within vertices from the vertex 
     * labelled A every other vertex in the graph.
     */
    @Test
    public void testToCheckSequenceToAllNodes() {
        assertTrue(graph.shortestPath("A", "B").toString().equals(
            "[A, B]"
        ));
        assertTrue(graph.shortestPath("A", "C").toString().equals(
            "[A, B, C]"
        ));
        assertTrue(graph.shortestPath("A", "D").toString().equals(
            "[A, D]"
        ));
        assertTrue(graph.shortestPath("A", "E").toString().equals(
            "[A, E]"
        ));
    }
}
