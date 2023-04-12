package br.com.hackathon.planonegocio.entities.reportgpt.interfaceadapter.controller;

import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM;
import static org.springframework.http.MediaType.APPLICATION_PDF;
import static org.springframework.http.ResponseEntity.ok;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.hackathon.planonegocio.entities.reportgpt.interfaceadapter.ReportGPTGatewayReport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/reportgpt-reports")
public class ReportController {

	public static final String X_SUGGESTED_FILENAME_HEADER = "X-SUGGESTED-FILENAME";

	@Autowired
	private ReportGPTGatewayReport reportGPTGatewayReport;

	@Operation(summary = "Generate Report PDF of ReportGTPs")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found the Report PDF", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Byte.class))) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@GetMapping("/pdf/{nameReport}")
	public ResponseEntity<byte[]> generateReportGTPReportPdf(@PathVariable String nameReport,
			@RequestParam("topicId") Long topicId, @RequestParam(name = "templateId") Long templateId) {

		Map<String, Object> parameters = new LinkedHashMap<>();
		parameters.put("topicId", topicId);
		parameters.put("templateId", templateId);

		byte[] relatorio = reportGPTGatewayReport.generateReportPdf(nameReport, parameters);
		return ok().contentType(APPLICATION_PDF) //
				.header(X_SUGGESTED_FILENAME_HEADER, "ReportGPT.pdf").body(relatorio);
	}

	@Operation(summary = "Generate Report WORD of ReportGTPs")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found the Report WORD", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Byte.class))) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@GetMapping(path = "/odt/{nameReport}")
	public ResponseEntity<byte[]> generateReportGTPReportWord(@PathVariable String nameReport,
			@RequestParam("topicId") Long topicId, @RequestParam(name = "templateId") Long templateId) {

		Map<String, Object> parameters = new LinkedHashMap<>();
		parameters.put("topicId", topicId);
		parameters.put("templateId", templateId);

		byte[] relatorio = reportGPTGatewayReport.generateReportOdt(nameReport, parameters);
		return ok().contentType(APPLICATION_OCTET_STREAM) //
				.header(X_SUGGESTED_FILENAME_HEADER, "ReportGPT.docx").body(relatorio);
	}

}
