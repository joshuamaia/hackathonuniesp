package br.com.hackathon.planonegocio.entities.reportgpt.businessrule.usecase;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hackathon.planonegocio.entities.reportgpt.businessrule.GenerateReportPdf;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
public class GenerateReportPdfUC implements GenerateReportPdf {

	@Autowired
	DataSource dataSource;

	@Override
	public byte[] generateReportPdf(String nameReport, Map<String, Object> parameters) {
		try {
			String fileReport = String.format("/report/src/%s.jasper" ,nameReport);
			JasperReport compile = (JasperReport) JRLoader
					.loadObject(this.getClass().getResourceAsStream(fileReport));
			try (Connection connection = dataSource.getConnection()) {
				JasperPrint jasperPrint = JasperFillManager.fillReport(compile, parameters, connection);
				return JasperExportManager.exportReportToPdf(jasperPrint);
			} catch (SQLException sqle) {
				throw new RuntimeException("Report SQL Error", sqle);
			}
		} catch (JRException jrpe) {
			throw new RuntimeException("Report Error", jrpe);
		}
	}
	
}
