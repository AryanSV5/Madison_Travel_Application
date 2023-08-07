import java.util.Iterator;
import java.util.List;

public interface DijkstraInterface<NodeType, EdgeType extends Number>
    extends GraphADT<NodeType, EdgeType> {
  public Iterator<NodeType> iterator();

  public List<NodeType> shortestBuildingPathData(NodeType start, NodeType end);

  public double shortestBuildingPathCost(NodeType start, NodeType end);
}


