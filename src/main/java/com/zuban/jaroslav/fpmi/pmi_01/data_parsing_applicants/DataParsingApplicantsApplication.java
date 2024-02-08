package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DataParsingApplicantsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataParsingApplicantsApplication.class, args);
	}

}
