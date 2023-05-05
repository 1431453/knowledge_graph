package com.example.kgdemo;


import org.neo4j.driver.*;

import java.util.HashMap;
import java.util.Map;

public class Neo4jQuery {

    public static void main(String[] args) {
        //连接neo4j
/*
        Driver driver = GraphDatabase.driver("bolt://localhost:7687",AuthTokens.basic("neo4j","123456"));
        Session session = driver.session();

        String a="divemedical",b="潜水减压病",c="治疗方法";
        //String cypherQuery = "match (n:divemedical) return n.name as name";
        //String cypherQuery = "match (n) where n.name ='"+a+"' return n.name as name";
        String cypherQuery = "match (n:"+a+"{name:'"+b+"'})-[r:"+c+"]->(n1) return n1.name as name";
        Result result = session.run(cypherQuery);

        while (result.hasNext()) {
            Record record = result.next();
            String name = record.get("name").asString();
            System.out.println(name);
        }

        session.close();
        driver.close();

 */
    }
}
