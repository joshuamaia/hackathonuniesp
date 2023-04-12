package br.com.hackathon.planonegocio.entities.reportgpt.businessrule;

import java.util.Map;

public interface GenerateReportOdt {
	
	public byte[] generateReportOdt(String nameReport, Map<String, Object> parameters);

}
