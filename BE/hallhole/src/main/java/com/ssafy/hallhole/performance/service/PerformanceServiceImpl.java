package com.ssafy.hallhole.performance.service;

import com.ssafy.hallhole.performance.domain.DetailPerformance;
import com.ssafy.hallhole.performance.domain.Facility;
import com.ssafy.hallhole.performance.domain.Performance;
import com.ssafy.hallhole.performance.repository.PerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PerformanceServiceImpl implements PerformanceService {

    private final PerformanceRepository performanceRepository;

    private final Environment env;


    @Override
    @Transactional
    public List<Performance> getPerformances() {
        return null;
    }

    @Override
    @Transactional
    public Performance findOne() {
        return null;
    }

    @Override
    @Transactional
    public DetailPerformance getDetail() {
        return null;
    }

    @Override
    public void initData() throws Exception {
        getPerformanceData("AAAA");
        getPerformanceData("AAAD");
        getFacilityData();
    }

    public void getPerformanceData(String type) throws SAXException, IOException, ParserConfigurationException {
        for (int pg_num = 1; pg_num <= 5; pg_num++) {

            String urlstr = "http://www.kopis.or.kr/openApi/restful/pblprfr?service=" + env.getProperty("kopisApiKey") +
                    "&shcate=" + type +
                    "&rows=100" +
                    "&cpage=" + pg_num;

            DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
            Document doc = dBuilder.parse(urlstr);

            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("db");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                NodeList items = nNode.getChildNodes();
                HashMap<String, String> map = new HashMap();
                for (int i = 0; i < items.getLength(); i++) {
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Node node = items.item(i);
                        if (node.getNodeName().equals("mt20id")) {
                            map.put("id", node.getTextContent());
                        } else if (node.getNodeName().equals("prfnm")) {
                            map.put("name", node.getTextContent());
                        } else if (node.getNodeName().equals("prfpdfrom")) {
                            map.put("from", node.getTextContent());
                        } else if (node.getNodeName().equals("prfpdto")) {
                            map.put("to", node.getTextContent());
                        } else if (node.getNodeName().equals("fcltynm")) {
                            map.put("facility_name", node.getTextContent());
                        } else if (node.getNodeName().equals("poster")) {
                            map.put("poster", node.getTextContent());
                        } else if (node.getNodeName().equals("genrenm")) {
                            map.put("genre", node.getTextContent());
                        }
                    }
                }
                if (performanceRepository.findOnePerformanceById(map.get("id")) == null) {

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
                    LocalDateTime startDate = LocalDate.from(LocalDate.parse(map.get("from"), formatter)).atStartOfDay();
                    LocalDateTime endDate = LocalDate.from(LocalDate.parse(map.get("to"), formatter)).atStartOfDay();

                    Performance performance = Performance.builder()
                            .id(map.get("id"))
                            .name(map.get("name"))
                            .startDate(startDate)
                            .endDate(endDate)
                            .facility_name(map.get("facility_name"))
                            .poster(map.get("poster"))
                            .genre(map.get("genre"))
                            .build();
                    performanceRepository.save(performance);
                }
            }
        }
    }

    @Override
    public void getFacilityData() throws Exception {

        String urlstr = "http://www.kopis.or.kr/openApi/restful/prfplc?service=" + env.getProperty("kopisApiKey") +
                "&rows=3000" +
                "&cpage=1";

        DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
        Document doc = dBuilder.parse(urlstr);

        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("db");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            NodeList items = nNode.getChildNodes();
            for (int i = 0; i < items.getLength(); i++) {
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Node node = items.item(i);
                    if (node.getNodeName().equals("mt10id")) {
                        if (performanceRepository.findOneFacility(node.getTextContent()) == null) {
                            Facility facility = getFacilityDetailData(node.getTextContent());
                            performanceRepository.saveFacility(facility);
                        }
                    }
                }
            }
        }
    }

    private Facility getFacilityDetailData(String id) throws Exception {
        String urlstr = "http://www.kopis.or.kr/openApi/restful/prfplc/" + id + "?service=" + env.getProperty("kopisApiKey");
        DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
        Document doc = dBuilder.parse(urlstr);

        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("db");
        Node nNode = nList.item(0);
        NodeList items = nNode.getChildNodes();
        HashMap<String, String> map = new HashMap();
        Facility facility = null;
        for (int i = 0; i < items.getLength(); i++) {
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Node node = items.item(i);
                if (node.getNodeName().equals("mt10id")) {
                    map.put("id", node.getTextContent());
                } else if (node.getNodeName().equals("fcltynm")) {
                    map.put("name", node.getTextContent());
                } else if (node.getNodeName().equals("adres")) {
                    map.put("addr", node.getTextContent());
                } else if (node.getNodeName().equals("la")) {
                    map.put("la", node.getTextContent());
                } else if (node.getNodeName().equals("lo")) {
                    map.put("lo", node.getTextContent());
                }
            }
        }
        facility = Facility.builder()
                .id(map.get("id"))
                .addr(map.get("addr"))
                .name(map.get("name"))
                .lat(Double.valueOf(map.get("la")))
                .lon(Double.valueOf(map.get("lo")))
                .build();
        return facility;
    }


    @Override
    public void getDetailPerformanceData() {
//        List<Performance> performanceList = performanceRepository.findAllPerformance();
//        for (Performance performance : performanceList) {
//            if(performanceRepository.findOneDetailPerformance(performance)==null){
//
//            }
//        }
    }

}
