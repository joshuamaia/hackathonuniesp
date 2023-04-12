package br.com.hackathon.planonegocio.entities.topic.interfaceadapter.controller;

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

import br.com.hackathon.planonegocio.entities.topic.domain.Topic;
import br.com.hackathon.planonegocio.entities.topic.interfaceadapter.TopicGateway;
import br.com.hackathon.planonegocio.entities.topic.interfaceadapter.controller.model.GetTopicResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

//
@RestController
@RequestMapping("/api/topics")
public class TopicController {

	@Autowired
	private TopicGateway topicGateway;

	@Operation(summary = "Search all Topics")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found the List of TopicModel", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = GetTopicResponse.class))) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@GetMapping("/list")
	public ResponseEntity<List<GetTopicResponse>> getAll() {
		List<GetTopicResponse> topics = topicGateway.getAllTopic();
		return new ResponseEntity<List<GetTopicResponse>>(topics, HttpStatus.OK);
	}

	@Operation(summary = "Search all Topics pagened")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the List of TopicModel", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TopicPage.class))),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@GetMapping(value = { "/{page}/{size}", "/{page}/{size}/{wordSearch}" })
	public ResponseEntity<Page<GetTopicResponse>> getAll(@PathVariable Integer page, @PathVariable Integer size,
			@PathVariable(required = false) String wordSearch) {
		Page<GetTopicResponse> Topics = topicGateway.searchAllPage(page, size, wordSearch);
		return new ResponseEntity<Page<GetTopicResponse>>(Topics, HttpStatus.OK);
	}

	@Operation(summary = "Search all Persons pagened")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the List of TopicModel", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TopicPage.class))),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@GetMapping("/filter")
	public ResponseEntity<Page<GetTopicResponse>> filter(@RequestParam(required = false) String description,
			@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {

		var topics = this.topicGateway.filter(description, page, size);
		return ResponseEntity.ok().body(topics);
	}

	@Operation(summary = "Create TopicModel")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "TopicModel created with sucessful", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = GetTopicResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@PostMapping
	public ResponseEntity<GetTopicResponse> create(@RequestBody Topic topic) {
		GetTopicResponse topicSave = topicGateway.saveTopic(topic);
		return new ResponseEntity<GetTopicResponse>(topicSave, HttpStatus.CREATED);
	}

	@Operation(summary = "Update TopicModel")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "TopicModel updated with sucessful", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = GetTopicResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@PutMapping
	public ResponseEntity<GetTopicResponse> update(@RequestBody Topic topic) {
		GetTopicResponse topicUpdate = topicGateway.updateTopic(topic);
		return new ResponseEntity<GetTopicResponse>(topicUpdate, HttpStatus.OK);
	}

//	@Operation(summary = "Search TopicModel By id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the TopicModel", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Topic.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@GetMapping("/{id}")
	public ResponseEntity<GetTopicResponse> getOneById(@PathVariable Long id) {
		GetTopicResponse Topic = topicGateway.findOneTopic(id);
		return new ResponseEntity<GetTopicResponse>(Topic, HttpStatus.OK);
	}

	@Operation(summary = "Delete TopicModel By id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "No Content", description = "Delete TopicModel", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		topicGateway.deleteTopic(id);
		return ResponseEntity.noContent().build();
	}

	class TopicPage extends PageImpl<GetTopicResponse> {
		public TopicPage(List<GetTopicResponse> content, Pageable pageable, long total) {
			super(content, pageable, total);
		}
	}
}
