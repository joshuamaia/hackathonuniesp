package br.com.hackathon.planonegocio.entities.topic.interfaceadapter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.hackathon.planonegocio.entities.topic.businessrule.DeleteTopic;
import br.com.hackathon.planonegocio.entities.topic.businessrule.FilterTopic;
import br.com.hackathon.planonegocio.entities.topic.businessrule.FindOneTopic;
import br.com.hackathon.planonegocio.entities.topic.businessrule.GetAllTopic;
import br.com.hackathon.planonegocio.entities.topic.businessrule.SaveTopic;
import br.com.hackathon.planonegocio.entities.topic.businessrule.SearchAllPageTopic;
import br.com.hackathon.planonegocio.entities.topic.businessrule.UpdateTopic;
import br.com.hackathon.planonegocio.entities.topic.domain.Topic;
import br.com.hackathon.planonegocio.entities.topic.interfaceadapter.controller.model.GetTopicResponse;
import br.com.hackathon.planonegocio.entities.topic.interfaceadapter.controller.model.GetTopicResponseMapper;

@Service
public class TopicGatewayImpl implements TopicGateway {

	@Autowired
	private SaveTopic saveTopic;

	@Autowired
	private UpdateTopic updateTopic;

	@Autowired
	private GetAllTopic getAllTopic;

	@Autowired
	private FindOneTopic findOneTopic;

	@Autowired
	private DeleteTopic deleteTopic;

	@Autowired
	private SearchAllPageTopic searchAllPageTopic;

	@Autowired
	private FilterTopic filterTopic;

	@Autowired
	GetTopicResponseMapper getTopicResponseMapper;

	@Override
	public GetTopicResponse saveTopic(Topic topic) {
		return getTopicResponseMapper.modelToEntity(saveTopic.saveTopic(topic));
	}

	@Override
	public GetTopicResponse updateTopic(Topic topic) {
		return getTopicResponseMapper.modelToEntity(updateTopic.updateTopic(topic));
	}

	@Override
	public List<GetTopicResponse> getAllTopic() {
		return getAllTopic.getAllTopic().stream().map(getTopicResponseMapper::modelToEntity)
				.collect(Collectors.toList());
	}

	@Override
	public GetTopicResponse findOneTopic(Long id) {
		return getTopicResponseMapper.modelToEntity(findOneTopic.findOneTopic(id));
	}

	@Override
	public void deleteTopic(Long id) {
		deleteTopic.deleteTopic(id);
	}

	@Override
	public Page<GetTopicResponse> searchAllPage(Integer page, Integer size, String wordSearch) {
		return searchAllPageTopic.searchAllPage(page, size, wordSearch).map(getTopicResponseMapper::modelToEntity);
	}

	@Override
	public Page<GetTopicResponse> filter(String description, Integer page, Integer size) {
		return filterTopic.filter(description, page, size).map(getTopicResponseMapper::modelToEntity);
	}

}
