package com.b4f.ubo;

import com.b4f.ubo.domain.BusinessEntity;
import com.b4f.ubo.graph.UBODirectedWeightedGraph;
import com.b4f.ubo.graph.UBODirectedWeightedGraphImpl;
import com.b4f.ubo.helpers.BusinessEntityFactory;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.within;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@Execution(ExecutionMode.CONCURRENT)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SpringJUnitConfig(UBODirectedWeightedGraphImpl.class)
public class UBODirectedWeigtedGraphTest {

    @Autowired
    private UBODirectedWeightedGraph uboDirectedWeightedGraph;

    private BusinessEntity clientA;

    @BeforeEach
    void setUp() {

        this.clientA= BusinessEntityFactory.createBusinessEntityHierarchy();


    }
    @Test
    void filterByNaturalPerson_should_return_two_persons_mary_and_john() {

        // given
        this.clientA = BusinessEntityFactory.createBusinessEntityHierarchy();

        //when
        var persons = uboDirectedWeightedGraph.filterByNaturalPerson(this.clientA);

        //then
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(persons.size()).isEqualTo(2);
        softly.assertThat(persons.get(0).getName()).isEqualTo("John");
        softly.assertThat(persons.get(1).getName()).isEqualTo("Mary");

        softly.assertAll();

    }

    @Test
    void conputeAllPaths_should_compute_correct_Paths() {

        //given
        var graph = UBODirectedWeightedGraph.of(this.clientA);
        var persons = uboDirectedWeightedGraph.filterByNaturalPerson(this.clientA);

        //when
        var paths01 = uboDirectedWeightedGraph.conputeAllPaths(graph, persons.get(0), this.clientA);

        //then
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(paths01.size()).isEqualTo(4L);
        softly.assertThat(paths01.get(0).getLength()).isEqualTo(3);
        softly.assertThat(paths01.get(1).getLength()).isEqualTo(2);
        softly.assertThat(paths01.get(2).getLength()).isEqualTo(3);
        softly.assertThat(paths01.get(3).getLength()).isEqualTo(3);
        softly.assertAll();

    }

    @Test
    void computePathWeight_should_return_044_and_021() {

        //given
        var graph = UBODirectedWeightedGraph.of(this.clientA);
        var persons = uboDirectedWeightedGraph.filterByNaturalPerson(this.clientA);

        //when
        var paths01 = uboDirectedWeightedGraph.conputeAllPaths(graph, persons.get(0), this.clientA);
        var paths02 = uboDirectedWeightedGraph.conputeAllPaths(graph, persons.get(1), this.clientA);

        double johnFoundUboPercentage=uboDirectedWeightedGraph.computePathWeight(paths01);
        double maryFoundUboPercentage=uboDirectedWeightedGraph.computePathWeight(paths02);

        double johnExpectedUboPercentage=0.449;
        double maryExpectedUboPercentage=0.216;



        //then
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(johnFoundUboPercentage ).isCloseTo(johnExpectedUboPercentage,within(0.001) );
        softly.assertThat(maryExpectedUboPercentage).isCloseTo( maryFoundUboPercentage,within(0.001));
        softly.assertAll();

    }


}
