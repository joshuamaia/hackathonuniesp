package br.com.hackathon.planonegocio.entities.reportgpt.businessrule;


import org.springframework.data.domain.Page;

import br.com.hackathon.planonegocio.entities.reportgpt.domain.ReportGPT;

public interface SearchAllPageReportGPT {
	
	public Page<ReportGPT> searchAllPage(Integer page, Integer size, String wordSearch);

}
