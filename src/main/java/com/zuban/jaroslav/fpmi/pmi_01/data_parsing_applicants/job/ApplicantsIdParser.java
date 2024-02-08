package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.job;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service.PersonDataService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ApplicantsIdParser implements Parser {
    private final String URL = "https://joblab.ru/search.php?r=res&srregion=100&page=";
    private final String key = "&submit=1";
    private final PersonDataService personDataService;

    @Autowired
    public ApplicantsIdParser(PersonDataService personDataService) {
        this.personDataService = personDataService;
    }

    @Override
    @Scheduled(fixedDelay = 1000)
    public void parser() {
        /*int applicantsLinkCount = 1;

        for (int i = 1; i < 168; i++) {
            try {
                Document document = Jsoup.connect(URL + i + key)
                        .userAgent("Chrome")
                        .timeout(5000)
                        .referrer("https://google.com")
                        .get();

                Elements linkApplicants = document.select("p.prof a");

                for (Element element : linkApplicants) {
                    System.out.println("Номер вакансии " + applicantsLinkCount + " ссылка:" + element.attr("href"));
                    applicantsLinkCount++;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }*/
    }
}
