package com.b4f.ubo.graph;

import com.b4f.ubo.domain.*;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author maniar othmane
 */
public class UBODirectedWeightedGraphImpl implements UBODirectedWeightedGraph {

    private final int MAXIMUM_PATH_LENTH = 100;

    @Override
    public List<NaturalPerson> filterByNaturalPerson(BusinessEntity businessEntity) {
        return this.filterByNaturalPerson(UBODirectedWeightedGraph.of(businessEntity));
    }

    public List<NaturalPerson> filterByNaturalPerson(Graph<LegalPerson, String> graph) {
        return graph
                .vertexSet()
                .stream()
                .filter(legalPerson -> legalPerson instanceof NaturalPerson)
                .map(person -> (NaturalPerson) person).collect(Collectors.toList());
    }
    @Override
    public Set<LegalPerson> filterByLegalPerson(BusinessEntity businessEntity) {
        return this.filterByLegalPerson(UBODirectedWeightedGraph.of(businessEntity));
    }

    @Override
    public List<OwnerShip> filterByOwnerShip(BusinessEntity businessEntity) {
        return this.filterByOwnerShip(UBODirectedWeightedGraph.of(businessEntity));
    }

    public Set<LegalPerson> filterByLegalPerson(Graph<LegalPerson, String> graph) {
        return graph
                .vertexSet();

    }
    public List<OwnerShip> filterByOwnerShip(Graph<LegalPerson, String> graph) {
        return graph
                .vertexSet().stream()
                .filter(legalPerson -> legalPerson instanceof BusinessEntity)
                .flatMap(businessEntity -> ((BusinessEntity) businessEntity).getOwnerships().stream())
                .collect(Collectors.toList())
                ;

    }




    public List<UltimateBeneficialOwnership> conputeAllUltimateBeneficialOwnership(BusinessEntity be) {
        var graph=UBODirectedWeightedGraph.of(be);
        AllDirectedPaths<LegalPerson, String> allDirectedPaths = new AllDirectedPaths<>(graph);
        return this.filterByNaturalPerson(graph)
                .stream()
                .map(person -> new UltimateBeneficialOwnership(this.computePathWeight(conputeAllPaths(graph, person, be)),person))
                .collect(Collectors.toList());

    }

    public List<GraphPath<LegalPerson, String>> conputeAllPaths(Graph<LegalPerson, String> graph, NaturalPerson person, BusinessEntity be) {
        var allDirectedPaths = new AllDirectedPaths<>(graph);
        return allDirectedPaths.getAllPaths(person, be, true, MAXIMUM_PATH_LENTH);
    }

    public double computePathWeight(GraphPath<LegalPerson, String> graphPath) {
        return graphPath
                .getEdgeList()
                .stream()
                .mapToDouble(edge -> graphPath.getGraph().getEdgeWeight(edge))
                .reduce(1, (a, b) -> a * b);
    }

    public double computePathWeight(List<GraphPath<LegalPerson, String>> listPaths) {
        return listPaths
                .stream()
                .mapToDouble(this::computePathWeight)
                .sum();
    }


}
