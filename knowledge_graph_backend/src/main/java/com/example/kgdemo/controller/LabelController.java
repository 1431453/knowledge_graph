package com.example.kgdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.kgdemo.entity.Label;
import com.example.kgdemo.mapper.LabelMapper;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.restful.HanLPClient;
import com.hankcs.hanlp.seg.Segment;
import org.neo4j.driver.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
@CrossOrigin
@RestController
public class LabelController {

    @Autowired
    private LabelMapper labelMapper;
    //连接neo4j
    Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j","123456"));
    Session session = driver.session();

    @GetMapping("/label/findAll")
    public List<Label> findAll() {
        //使用mybatis plus提供的方法查询
        List<Label> list = labelMapper.selectList(null);
        return list;
    }

    @CrossOrigin
    @GetMapping("/label")
    public List query(HttpServletRequest request) {
        //使用mybatis查询
        //判断前端点击的是哪个标签
        /*List<Label> list = new ArrayList<>();
        Boolean done = Boolean.parseBoolean(request.getParameter("done"));
        if(done) {
            list = labelMapper.findByName(request.getParameter("name"));

        }
        System.out.println(request.getParameter("title"));
        System.out.println(list);*/

        //不用判断前端点击哪个标签
        List<Label> list = labelMapper.findByName(request.getParameter("name"));

        System.out.println(request.getParameter("title"));
        System.out.println(list);
        return list;
    }

    @GetMapping("/label/find")
    public List<Label> find() {

        QueryWrapper<Label> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name","diveeq");
        return labelMapper.selectList(queryWrapper);
    }

    @GetMapping("/label/findByPage")
    public IPage findByPage() {
        //设置起始值及每页条数
        Page<Label> page = new Page<>(0,2);
        IPage iPage = labelMapper.selectPage(page,null);
        return iPage;
    }

    @CrossOrigin
    @GetMapping("/search2")
    public String search2(HttpServletRequest request) throws IOException {
        String s = request.getParameter("title");
        // auth不填则匿名，zh中文，mul多语种
        HanLPClient HanLP = new HanLPClient("https://www.hanlp.com/api","MjI2N0BiYnMuaGFubHAuY29tOlh1b2lDSDFCZEpDdXY0Zlk=");
        String a = "舰艇损管的目的是什么？";
        System.out.println(HanLP.parse(s));
        System.out.println(HanLP.parse(a));
        return "已经收到了数据";
    }

    @GetMapping("/match")
    public List<String> match(HttpServletRequest request) {
        //实体标签label 实体entity 关系或属性relation
        String label = request.getParameter("label");
        String entity = request.getParameter("entity");
        String relation = request.getParameter("relation");

        String cypherQuery = "match (n:"+label+"{name:'"+entity+"'})-[r:"+relation+"]->(n1) return n1.name as name";
        Result result = session.run(cypherQuery);
        //定义list接收问题的答案
        List<String> list= new ArrayList<>();
        while (result.hasNext()) {
            Record record = result.next();
            String name = record.get("name").asString();
            list.add(name);
            System.out.println(name);
        }
        //session.close();
        //driver.close();
        return list;
    }

    @CrossOrigin
    @GetMapping("/search")
    public List<String> search(HttpServletRequest request) /*throws IOException*/ {
// auth不填则匿名，zh中文，mul多语种
// HanLPClient HanLP = new HanLPClient("https://www.hanlp.com/api","MjI2N0BiYnMuaGFubHAuY29tOlh1b2lDSDFCZEpDdXY0Zlk=");
        CustomDictionary.add("舰艇潜水作业");
        CustomDictionary.add("主要任务");
        CustomDictionary.add("潜水减压病");
        CustomDictionary.add("治疗方法");
        CustomDictionary.add("基本原则");
        CustomDictionary.add("原则");
        //String s = "舰艇损管的目的是什么？";
        //String s = "潜水减压病的治疗方法有哪些？";
        String title = request.getParameter("title");//title是一个前端发送的问题
        List<Term> termList = HanLP.segment(title);//接收句子
        String filePath = "E:\\developProjects\\IdeaProjects\\mavenProject\\kgdemo\\src\\main\\resources\\cn_stopwords.txt";
        File file = new File(filePath);
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<String> stopWords = new ArrayList<>();
        String temp = null;
        while (true) {
            try {
                if (!((temp = bufferedReader.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            stopWords.add(temp.trim());
        }
        List<String> termStringList = new ArrayList<>();//用List存放分词的结果，可以.get()获取值
        for(Term term:termList) {
            termStringList.add(term.word);
        }
        termStringList.removeAll(stopWords);
        //输出分词的数据termStringList
        System.out.println(termStringList);

        String e1 =termStringList.get(0);//e1是实体名
        String e2 =termStringList.get(1);//e2是实体间的关系
        //连接neo4j
        /*Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j","123456"));
        Session session = driver.session();*/

        //String a="divemedical",b="潜水减压病",c="治疗方法";
        String cypherQuery = "match (n:divemedical{name:'"+e1+"'})-[r:"+e2+"]->(n1) return n1.name as name";
        if(e2.equals("原理")) {
            cypherQuery = "MATCH (n:divemedical{name:'"+e1+"'})  return n.原理 as name";
        }
        Result result = session.run(cypherQuery);
        //定义list接收问题的答案
        List<String> list= new ArrayList<>();
        while (result.hasNext()) {
            Record record = result.next();
            String name = record.get("name").asString();
            list.add(name);
            System.out.println(name);
        }

        //session.close();
        //driver.close();

        return list;

    }

}
