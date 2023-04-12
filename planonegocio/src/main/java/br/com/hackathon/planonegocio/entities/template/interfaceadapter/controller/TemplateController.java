package br.com.hackathon.planonegocio.entities.template.interfaceadapter.controller;

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

import br.com.hackathon.planonegocio.entities.template.domain.Template;
import br.com.hackathon.planonegocio.entities.template.interfaceadapter.TemplateGateway;
import br.com.hackathon.planonegocio.entities.template.interfaceadapter.controller.model.GetTemplateResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

//
@RestController
@RequestMapping("/api/templates")
public class TemplateController {

	@Autowired
	private TemplateGateway templateGateway;

	@Operation(summary = "Search all Templates")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found the List of TemplateModel", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = GetTemplateResponse.class))) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@GetMapping("/list")
	public ResponseEntity<List<GetTemplateResponse>> getAll() {
		List<GetTemplateResponse> templates = templateGateway.getAllTemplate();
		return new ResponseEntity<List<GetTemplateResponse>>(templates, HttpStatus.OK);
	}

	@Operation(summary = "Search all Templates pagened")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the List of TemplateModel", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TemplatePage.class))),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@GetMapping(value = { "/{page}/{size}", "/{page}/{size}/{wordSearch}" })
	public ResponseEntity<Page<GetTemplateResponse>> getAll(@PathVariable Integer page, @PathVariable Integer size,
			@PathVariable(required = false) String wordSearch) {
		Page<GetTemplateResponse> templates = templateGateway.searchAllPage(page, size, wordSearch);
		return new ResponseEntity<Page<GetTemplateResponse>>(templates, HttpStatus.OK);
	}

	@Operation(summary = "Search all Persons pagened")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the List of TemplateModel", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TemplatePage.class))),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@GetMapping("/filter")
	public ResponseEntity<Page<GetTemplateResponse>> filter(@RequestParam(required = false) String title, @RequestParam(required = false) String description,
			@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {

		var Templates = this.templateGateway.filter(title, description, page, size);
		return ResponseEntity.ok().body(Templates);
	}

	@Operation(summary = "Create TemplateModel")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "TemplateModel created with sucessful", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = GetTemplateResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@PostMapping
	public ResponseEntity<GetTemplateResponse> create(@RequestBody Template template) {
		GetTemplateResponse templateSave = templateGateway.saveTemplate(template);
		return new ResponseEntity<GetTemplateResponse>(templateSave, HttpStatus.CREATED);
	}

	@Operation(summary = "Update TemplateModel")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "TemplateModel updated with sucessful", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = GetTemplateResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@PutMapping
	public ResponseEntity<GetTemplateResponse> update(@RequestBody Template template) {
		GetTemplateResponse templateUpdate = templateGateway.updateTemplate(template);
		return new ResponseEntity<GetTemplateResponse>(templateUpdate, HttpStatus.OK);
	}

//	@Operation(summary = "Search TemplateModel By id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the TemplateModel", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Template.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@GetMapping("/{id}")
	public ResponseEntity<GetTemplateResponse> getOneById(@PathVariable Long id) {
		GetTemplateResponse Template = templateGateway.findOneTemplate(id);
		return new ResponseEntity<GetTemplateResponse>(Template, HttpStatus.OK);
	}

	@Operation(summary = "Delete TemplateModel By id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "No Content", description = "Delete TemplateModel", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		templateGateway.deleteTemplate(id);
		return ResponseEntity.noContent().build();
	}

	class TemplatePage extends PageImpl<GetTemplateResponse> {
		public TemplatePage(List<GetTemplateResponse> content, Pageable pageable, long total) {
			super(content, pageable, total);
		}
	}
}
