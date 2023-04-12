package br.com.hackathon.planonegocio.entities.reportgpt.interfaceadapter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.hackathon.planonegocio.entities.reportgpt.domain.ReportGPT;
import br.com.hackathon.planonegocio.entities.reportgpt.interfaceadapter.ReportGPTGateway;
import br.com.hackathon.planonegocio.entities.reportgpt.interfaceadapter.controller.model.GetReportGTPResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

//
@RestController
@RequestMapping("/api/reportgpts")
public class ReportGPTController {

	@Autowired
	private ReportGPTGateway reportGPTGateway;

	@Operation(summary = "Search all ReportGTPs")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the List of ReportGPTModel", content = {
					@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = GetReportGTPResponse.class))) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@GetMapping("/list")
	public ResponseEntity<List<GetReportGTPResponse>> getAll() {
		List<GetReportGTPResponse> reportGTPs = reportGPTGateway.getAllReportGTP();
		return new ResponseEntity<List<GetReportGTPResponse>>(reportGTPs, HttpStatus.OK);
	}

	@Operation(summary = "Search all ReportGTPs pagened")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the List of ReportGPTModel", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReportGTPPage.class))),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@GetMapping(value = { "/{page}/{size}", "/{page}/{size}/{wordSearch}" })
	public ResponseEntity<Page<GetReportGTPResponse>> getAll(@PathVariable Integer page, @PathVariable Integer size,
			@PathVariable(required = false) String wordSearch) {
		Page<GetReportGTPResponse> reportGTPs = reportGPTGateway.searchAllPage(page, size, wordSearch);
		return new ResponseEntity<Page<GetReportGTPResponse>>(reportGTPs, HttpStatus.OK);
	}

	@Operation(summary = "Search all ReportGTPs pagened")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the List of ReportGPTModel", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReportGTPPage.class))),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@GetMapping("/filter")
	public ResponseEntity<Page<GetReportGTPResponse>> filter(@RequestParam(required = false) String description,
			@RequestParam(required = false) String topicDescription,
			@RequestParam(required = false) String templateTitle, @RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size) {

		var reportGTPs = this.reportGPTGateway.filter(description, topicDescription, templateTitle, page, size);
		return ResponseEntity.ok().body(reportGTPs);
	}

	@Operation(summary = "Create ReportGPTModel")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "ReportGPTModel created with sucessful", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = GetReportGTPResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@PostMapping
	public ResponseEntity<GetReportGTPResponse> create(@RequestBody ReportGPT reportGPT) {
		GetReportGTPResponse reportGTPSave = reportGPTGateway.saveReportGTP(reportGPT);
		return new ResponseEntity<GetReportGTPResponse>(reportGTPSave, HttpStatus.CREATED);
	}

	@Operation(summary = "Update ReportGPTModel")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "ReportGPTModel updated with sucessful", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = GetReportGTPResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@PutMapping
	public ResponseEntity<GetReportGTPResponse> update(@RequestBody ReportGPT reportGPT) {
		GetReportGTPResponse reportGTPUpdate = reportGPTGateway.updateReportGTP(reportGPT);
		return new ResponseEntity<GetReportGTPResponse>(reportGTPUpdate, HttpStatus.OK);
	}

//	@Operation(summary = "Search ReportGPTModel By id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the ReportGPTModel", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ReportGPT.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@GetMapping("/{id}")
	public ResponseEntity<GetReportGTPResponse> getOneById(@PathVariable Long id) {
		GetReportGTPResponse ReportGTP = reportGPTGateway.findOneReportGTP(id);
		return new ResponseEntity<GetReportGTPResponse>(ReportGTP, HttpStatus.OK);
	}

	@Operation(summary = "Delete ReportGPTModel By id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "No Content", description = "Delete ReportGPTModel", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		reportGPTGateway.deleteReportGTP(id);
		return ResponseEntity.noContent().build();
	}

	class ReportGTPPage extends PageImpl<GetReportGTPResponse> {
		public ReportGTPPage(List<GetReportGTPResponse> content, Pageable pageable, long total) {
			super(content, pageable, total);
		}
	}
}
