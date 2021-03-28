package sol

import src.IGraph.Vertex
import src.{DirectedGraph, IDijkstra}

import scala.collection.mutable
import scala.collection.mutable.HashMap

/**
 * class implements Dijkstra's algorithm on a graph from a source
 *
 * @param graph  - the nonempty graph
 * @param source - the source node for the algorithm
 */
class Dijkstra(graph: DirectedGraph, source: Vertex) extends IDijkstra {
  private val distances = new HashMap[Vertex, Double]()
  private val parents = new HashMap[Vertex, Option[Vertex]]()
  parents(source) = None
  dijkstra()

  /**
   * Performs Djikstra's algorithm from the given source Vertex, filling the
   * distances HashMap with distance from the source.
   */
  private def dijkstra() {
    val ordering = Ordering[Double].on[(Vertex, Double)](-1 * _._2)

    val pq = new mutable.PriorityQueue[(Vertex, Double)]()(ordering)
    val alreadyDone = new mutable.HashMap[Int, Boolean]()

    for (v <- graph.vertices) {
      if (v == source) {
        distances(v) = 0.0
      } else {
        distances(v) = Double.NegativeInfinity
      }
      val tup = (v, distances(v))
      pq += tup
    }

    while (!pq.isEmpty) {
      val (vert, dist) = pq.dequeue()
      if (!alreadyDone.getOrElse(vert.id, false)) {
        alreadyDone(vert.id) = true
        for (edge <- vert.getNeighbors) {
          val newEst = distances(vert) + dist
          if (newEst < distances(edge.target)) {
            distances(edge.target) = newEst
            val newTuple = (edge.target, dist)
            pq += newTuple
            parents(edge.target) = Some(vert)
          }
        }
      }
    }
  }

  /**
   * method finds the shortest distance from source node to given node
   *
   * @param destination - the destination vertex
   * @return the length of the shortest path from the source
   *         to the given destination
   */
  override def findShortestDistance(destination: Vertex): Option[Double] = {
    val dist = distances.get(destination)
    if (dist.equals(Some(Double.PositiveInfinity))) {
      None
    } else {
      dist
    }
  }

  /**
   * method finds the shortest route from source to destination node
   *
   * @param destination - the destination vertex
   * @return the list of vertices from the source to the given destination
   *         that comprise the shortest path
   */
  override def findShortestPath(destination: Vertex): Option[List[Vertex]] = {
    if (destination.equals(source)) {
      Some(List(source))
    } else if (parents.get(destination) == None) {
      None
    } else {
      Some(findShortestPath(parents(destination).get).getOrElse(List(parents(destination).get)) ::: List(destination))
    }
  }
}
