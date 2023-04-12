package br.com.hackathon.planonegocio.entities.topic.interfaceadapter;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;

import br.com.hackathon.planonegocio.entities.topic.domain.Topic;
import br.com.hackathon.planonegocio.entities.topic.interfaceadapter.controller.model.GetTopicResponse;

public interface TopicGateway {
	
	GetTopicResponse saveTopic(Topic topic);
	
	GetTopicResponse updateTopic(Topic topic);
	
	List<GetTopicResponse> getAllTopic();
	
	Page<GetTopicResponse> filter(
			@Nullable String description,
			@Nullable Integer page,
			@Nullable Integer size);
	
	GetTopicResponse findOneTopic(Long id);
	
	void deleteTopic(Long id);
	
	public Page<GetTopicResponse> searchAllPage(Integer page, Integer size, String wordSearch);

}
