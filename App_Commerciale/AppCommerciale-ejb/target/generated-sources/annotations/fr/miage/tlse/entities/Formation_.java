package fr.miage.tlse.entities;

import fr.miage.tlse.entities.Formation.Niveau;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-12-02T15:16:39")
@StaticMetamodel(Formation.class)
public class Formation_ { 

    public static volatile SingularAttribute<Formation, Integer> capaciteMax;
    public static volatile SingularAttribute<Formation, Integer> tarif;
    public static volatile SingularAttribute<Formation, Integer> capaciteMin;
    public static volatile SingularAttribute<Formation, List> competencesNecessaires;
    public static volatile SingularAttribute<Formation, String> description;
    public static volatile SingularAttribute<Formation, Integer> duree;
    public static volatile SingularAttribute<Formation, Long> codeFormation;
    public static volatile SingularAttribute<Formation, List> equipementsNecessaires;
    public static volatile SingularAttribute<Formation, String> intitule;
    public static volatile SingularAttribute<Formation, Niveau> niveau;

}