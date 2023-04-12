package br.com.hackathon.planonegocio.entities.reportgpt.businessrule.usecase;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hackathon.planonegocio.entities.reportgpt.businessrule.GenerateReportOdt;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Service
public class GenerateReportOdtUC implements GenerateReportOdt {

	@Autowired
	DataSource dataSource;

	@Override
	public byte[] generateReportOdt(String nameReport, Map<String, Object> parameters) {
		try {
			String fileReport = String.format("/report/src/%s.jasper", nameReport);
			JasperReport compile = (JasperReport) JRLoader.loadObject(this.getClass().getResourceAsStream(fileReport));
			try (Connection connection = dataSource.getConnection()) {
				JasperPrint jasperPrint = JasperFillManager.fillReport(compile, parameters, connection);

				// Export to Word
				String tmpdir = System.getProperty("java.io.tmpdir");
				JROdtExporter exporter = new JROdtExporter();
				exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
				File exportReportFile = new File(String.format("%s/reportword.odt", tmpdir));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(exportReportFile));
				exporter.exportReport();
				byte[] fileReturn = Files.readAllBytes(Paths.get(tmpdir).resolve("reportword.odt").toAbsolutePath());
				if (exportReportFile.exists()) {
					exportReportFile.delete();
				}
				return fileReturn;
			} catch (SQLException sqle) {
				throw new RuntimeException("Report SQL Error", sqle);
			} catch (IOException e) {
				throw new RuntimeException("Report Path Error", e);
			}
		} catch (JRException jrpe) {
			throw new RuntimeException("Report Error", jrpe);
		}
	}

}
