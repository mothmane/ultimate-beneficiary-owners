package com.b4f.ubo.helpers;

import com.b4f.ubo.domain.BusinessEntity;
import com.b4f.ubo.domain.NaturalPerson;
import com.b4f.ubo.domain.OwnerShip;
import com.b4f.ubo.domain.UltimateBeneficialOwnership;
import com.b4f.ubo.dtos.BusinessEntityDTO;
import com.b4f.ubo.dtos.NaturalPersonDTO;
import com.b4f.ubo.dtos.OwnerShipDTO;
import com.b4f.ubo.dtos.UltimateBeneficialOwnershipDTO;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface BusinessEntityFactory {

    public static BusinessEntity createBusinessEntityHierarchy(){
        // given
        // bisiness entities
        BusinessEntity ca = new BusinessEntity("Client A");
        BusinessEntity abc = new BusinessEntity("ABC Holding LLC ");
        BusinessEntity xyz = new BusinessEntity("XYZ Investments Inc");
        BusinessEntity island = new BusinessEntity("Sunny Island Offshore LLC");
        BusinessEntity smart = new BusinessEntity("Smart Investments Inc");
        BusinessEntity familly = new BusinessEntity("Familly Co LLC");

        // ownership
        OwnerShip fiftyCA = new OwnerShip(0.5,abc);
        OwnerShip tenCA = new OwnerShip(0.10,smart);
        OwnerShip fortyCA = new OwnerShip(0.40,xyz);

        ca.setOwnerships(List.of(fiftyCA,tenCA,fortyCA));

        OwnerShip fiftyABC01= new OwnerShip(0.50,island);
        OwnerShip fiftyABC02= new OwnerShip(0.50,smart);

        abc.setOwnerships(List.of(fiftyABC01,fiftyABC02));

        OwnerShip sixtyXYZ= new OwnerShip(0.60,familly);

        xyz.add(sixtyXYZ);


        NaturalPerson john = new NaturalPerson("John");
        NaturalPerson mary = new NaturalPerson("Mary");

        OwnerShip hundredIsland= new OwnerShip(1,john);

        island.add(hundredIsland);

        OwnerShip fiftySmart= new OwnerShip(0.50,john);

        smart.add(fiftySmart);

        OwnerShip tenFamilly= new OwnerShip(0.10,john);
        familly.add(tenFamilly);

        OwnerShip nineteenFamilly= new OwnerShip(0.90,mary);
        familly.add(nineteenFamilly);

        return ca;

    }
    public static BusinessEntityDTO createBusinessEntityDTOHierarchy(){
        // given
        // bisiness entities
        BusinessEntityDTO ca = new BusinessEntityDTO("Client A");
        BusinessEntityDTO abc = new BusinessEntityDTO("ABC Holding LLC ");
        BusinessEntityDTO xyz = new BusinessEntityDTO("XYZ Investments Inc");
        BusinessEntityDTO island = new BusinessEntityDTO("Sunny Island Offshore LLC");
        BusinessEntityDTO smart = new BusinessEntityDTO("Smart Investments Inc");
        BusinessEntityDTO familly = new BusinessEntityDTO("Familly Co LLC");

        // ownership
        OwnerShipDTO fiftyCA = new OwnerShipDTO(0.5,abc);
        OwnerShipDTO tenCA = new OwnerShipDTO(0.10,smart);
        OwnerShipDTO fortyCA = new OwnerShipDTO(0.40,xyz);

        ca.setOwnerships(List.of(fiftyCA,tenCA,fortyCA));

        OwnerShipDTO fiftyABC01= new OwnerShipDTO(0.50,island);
        OwnerShipDTO fiftyABC02= new OwnerShipDTO(0.50,smart);

        abc.setOwnerships(List.of(fiftyABC01,fiftyABC02));

        OwnerShipDTO sixtyXYZ= new OwnerShipDTO(0.60,familly);

        xyz.add(sixtyXYZ);


        NaturalPersonDTO john = new NaturalPersonDTO(UUID.randomUUID(),"John");
        NaturalPersonDTO mary = new NaturalPersonDTO(UUID.randomUUID(),"Mary");

        OwnerShipDTO hundredIsland= new OwnerShipDTO(1,john);

        island.add(hundredIsland);

        OwnerShipDTO fiftySmart= new OwnerShipDTO(0.50,john);

        smart.add(fiftySmart);

        OwnerShipDTO tenFamilly= new OwnerShipDTO(0.10,john);
        familly.add(tenFamilly);

        OwnerShipDTO nineteenFamilly= new OwnerShipDTO(0.90,mary);
        familly.add(nineteenFamilly);

        return ca;

    }
    public static BusinessEntity store(TestEntityManager em){
        // given
        // bisiness entities
        BusinessEntity ca = new BusinessEntity("Client A");
        BusinessEntity abc = new BusinessEntity("ABC Holding LLC ");
        BusinessEntity xyz = new BusinessEntity("XYZ Investments Inc");
        BusinessEntity island = new BusinessEntity("Sunny Island Offshore LLC");
        BusinessEntity smart = new BusinessEntity("Smart Investments Inc");
        BusinessEntity familly = new BusinessEntity("Familly Co LLC");

        // ownership
        OwnerShip fiftyCA = new OwnerShip(0.5,abc);
        OwnerShip tenCA = new OwnerShip(0.10,smart);
        OwnerShip fortyCA = new OwnerShip(0.40,xyz);

        em.persist(fiftyCA);
        em.persist(tenCA);
        em.persist(fortyCA);

        ca.setOwnerships(List.of(fiftyCA,tenCA,fortyCA));

        OwnerShip fiftyABC01= new OwnerShip(0.50,island);
        OwnerShip fiftyABC02= new OwnerShip(0.50,smart);

        em.persist(fiftyABC01);
        em.persist(fiftyABC02);


        abc.setOwnerships(List.of(fiftyABC01,fiftyABC02));

        OwnerShip sixtyXYZ= new OwnerShip(0.60,familly);
        em.persist(sixtyXYZ);

        xyz.add(sixtyXYZ);


        NaturalPerson john = new NaturalPerson("John");
        NaturalPerson mary = new NaturalPerson("Mary");

        OwnerShip hundredIsland= new OwnerShip(1,john);
        em.persist(hundredIsland);

        island.add(hundredIsland);

        OwnerShip fiftySmart= new OwnerShip(0.50,john);

        smart.add(fiftySmart);

        OwnerShip tenFamilly= new OwnerShip(0.10,john);
        em.persist(tenFamilly);
        familly.add(tenFamilly);


        OwnerShip nineteenFamilly= new OwnerShip(0.90,mary);
        em.persist(nineteenFamilly);
        familly.add(nineteenFamilly);

        return ca;

    }


    public static UltimateBeneficialOwnershipDTO createUltimateBeneficialOwnershipDTOs(double percentage,String name){

        UltimateBeneficialOwnershipDTO uboDTO = new UltimateBeneficialOwnershipDTO();
        uboDTO.setPercentage(percentage);
        uboDTO.setNaturalPerson(new NaturalPersonDTO((name)));

        return uboDTO;

    }

    public static UltimateBeneficialOwnership createUltimateBeneficialOwnerships(double percentage,String name){

        UltimateBeneficialOwnership ubo = new UltimateBeneficialOwnership();
        ubo.setPercentage(percentage);
        ubo.setNaturalPerson(new NaturalPerson((name)));

        return ubo;

    }
}
