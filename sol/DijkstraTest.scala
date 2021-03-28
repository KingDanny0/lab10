package sol

import src.DirectedGraph
import src.IGraph.Vertex
import tester.Tester

class DijkstraTest

object DijkstraTest {
  val g1 = new DirectedGraph
  val n1 = new Vertex(0)
  val n2 = new Vertex(1)
  val n3 = new Vertex(2)
  val n4 = new Vertex(3)
  val n5 = new Vertex(4)
  val n6 = new Vertex(5)

  g1.addVertex(n1)
  g1.addVertex(n2)
  g1.addVertex(n3)
  g1.addVertex(n4)
  g1.addVertex(n5)
  g1.addVertex(n6)
  g1.addEdge(n1, n2, 2)
  g1.addEdge(n1, n3, 3)
  g1.addEdge(n2, n3, 2)
  g1.addEdge(n2, n5, 2)
  g1.addEdge(n3, n4, 1)
  g1.addEdge(n4, n5, 5)
  g1.addEdge(n4, n6, 2)

  val d1 = new Dijkstra(g1, n1)
  println(d1.findShortestPath(n1))
  val g2 = new DirectedGraph
  val vertex1 = new Vertex(0)
  val vertex2 = new Vertex(1)
  val vertex3 = new Vertex(2)
  val vertex4 = new Vertex(3)
  val vertex5 = new Vertex(4)

  g2.addVertex(vertex1)
  g2.addVertex(vertex2)
  g2.addVertex(vertex3)
  g2.addVertex(vertex4)
  g2.addVertex(vertex5)

  g2.addEdge(vertex1, vertex2, 10)
  g2.addEdge(vertex1, vertex3, 3)
  g2.addEdge(vertex2, vertex3, 1)
  g2.addEdge(vertex2, vertex4, 2)
  g2.addEdge(vertex3, vertex2, 4)
  g2.addEdge(vertex3, vertex4, 8)
  g2.addEdge(vertex3, vertex5, 2)
  g2.addEdge(vertex4, vertex5, 7)
  g2.addEdge(vertex5, vertex4, 9)

  val d2 = new Dijkstra(g2, vertex2)

  val nopathgraph = new DirectedGraph
  val v1 = new Vertex(1)
  val v2 = new Vertex(2)
  val v3 = new Vertex(3)
  val v4 = new Vertex(4)
  val v5 = new Vertex(5)

  nopathgraph.addVertex(v1)
  nopathgraph.addVertex(v2)
  nopathgraph.addVertex(v3)
  nopathgraph.addVertex(v4)
  nopathgraph.addVertex(v5)

  nopathgraph.addEdge(v1, v2, 10)
  nopathgraph.addEdge(v1, v3, 3)
  nopathgraph.addEdge(v2, v3, 1)
  nopathgraph.addEdge(v4, v5, 5)
  val nopathD = new Dijkstra(nopathgraph, v1)

  def testNoPathEdge(t: Tester) {
    t.checkExpect(nopathD.findShortestDistance(v4), None)
    t.checkExpect(nopathD.findShortestPath(v4), None)
  }

  def testShortestDistance(t: Tester) {
    t.checkExpect(d1.findShortestDistance(n6), Some(6.0))
    t.checkExpect(d2.findShortestDistance(vertex3), Some(1.0))
    t.checkExpect(d2.findShortestDistance(vertex5), Some(3.0))
  }

  def testShortestPath(t: Tester) {
    t.checkExpect(d1.findShortestPath(n6), Some(List(n1, n3, n4, n6)))
    t.checkExpect(d2.findShortestPath(vertex3), Some(List(vertex2, vertex3)))
    t.checkExpect(d2.findShortestPath(vertex5), Some(List(vertex2, vertex3, vertex5)))
  }
}

object DijkstraTester extends App {
  Tester.run(DijkstraTest)
}
