package br.com.hackathon.planonegocio.entities.reportgpt.interfaceadapter;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hackathon.planonegocio.entities.reportgpt.businessrule.GenerateReportPdf;
import br.com.hackathon.planonegocio.entities.reportgpt.businessrule.GenerateReportOdt;

@Service
public class ReportGPTGatewayReportImpl implements ReportGPTGatewayReport {
	
	@Autowired
	private GenerateReportPdf generateReportPdf;
	
	@Autowired
	private GenerateReportOdt generateReportWord;
	
	@Override
	public byte[] generateReportPdf(String nameReport, Map<String, Object> parameters) {
		return generateReportPdf.generateReportPdf(nameReport, parameters);
	}

	@Override
	public byte[] generateReportOdt(String nameReport, Map<String, Object> parameters) {
		return generateReportWord.generateReportOdt(nameReport, parameters);
	}



}
