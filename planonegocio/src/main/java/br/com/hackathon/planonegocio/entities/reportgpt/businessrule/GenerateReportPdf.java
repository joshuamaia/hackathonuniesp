package br.com.hackathon.planonegocio.entities.reportgpt.businessrule;

import java.util.Map;

public interface GenerateReportPdf {
	
	public byte[] generateReportPdf(String nameReport, Map<String, Object> parameters);

}
