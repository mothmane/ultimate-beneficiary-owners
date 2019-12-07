package com.b4f.ubo.graph;

import com.b4f.ubo.domain.*;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;

import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * @author maniar othmane
 */
public interface UBODirectedWeightedGraph {


    public List<NaturalPerson> filterByNaturalPerson(BusinessEntity businessEntity);
    public Set<LegalPerson> filterByLegalPerson(BusinessEntity businessEntity);
    public List<OwnerShip> filterByOwnerShip(BusinessEntity businessEntity);

    public List<UltimateBeneficialOwnership> conputeAllUltimateBeneficialOwnership(BusinessEntity be);

    public List<GraphPath<LegalPerson, String>> conputeAllPaths(Graph<LegalPerson, String> graph, NaturalPerson person, BusinessEntity be);

    public double computePathWeight(List<GraphPath<LegalPerson, String>> listPaths);

    public double computePathWeight(GraphPath<LegalPerson, String> path);


    public static Graph<LegalPerson, String> of(BusinessEntity businessEntity) {
        Graph<LegalPerson, String> graph = new DefaultDirectedWeightedGraph(LegalPerson.class);
        build(businessEntity, graph);
        return graph;
    }


    private static void build(BusinessEntity be, Graph graph) {

        if (!graph.containsVertex(be))
            graph.addVertex(be);
        for (OwnerShip ownership : be.getOwnerships()) {
            var currentLegalPerson = ownership.getLegalPerson();
            var percentage = ownership.getPercentage();
            var idEdge=currentLegalPerson.getName()+ "->" +be.getName();

            addEdgeIfGraphNotAlreadyContainsIt(graph, currentLegalPerson, be,percentage, idEdge);
            if (currentLegalPerson instanceof BusinessEntity)
                build((BusinessEntity) currentLegalPerson, graph);
        }
    }

    /**
     * This method adds aVertex and a wighted edge if they do not exst already in the graph
     *
     * @param graph
     * @param lp
     * @param be
     * @param percentage
     * @param idEdge
     */
    private static void addEdgeIfGraphNotAlreadyContainsIt(Graph<LegalPerson, String> graph, LegalPerson lp, BusinessEntity be, Double percentage, String idEdge) {
        if (!graph.containsVertex(lp))
            graph.addVertex(lp);

        if (!graph.containsEdge(lp, be)) {
            graph.addEdge(lp, be, idEdge);
            graph.setEdgeWeight(lp, be, percentage);
        }

    }


}
