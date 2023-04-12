package br.com.hackathon.planonegocio.entities.topic.businessrule.usecase;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hackathon.planonegocio.entities.topic.businessrule.GetAllTopic;
import br.com.hackathon.planonegocio.entities.topic.domain.Topic;
import br.com.hackathon.planonegocio.entities.topic.interfaceadapter.repository.TopicRepository;
import br.com.hackathon.planonegocio.entities.topic.interfaceadapter.repository.model.TopicModelMapper;

@Service
public class GetAllTopicUC implements GetAllTopic {

	@Autowired
	TopicRepository topicRepository;

	@Autowired
	TopicModelMapper topicModelMapper;

	@Override
	public List<Topic> getAllTopic() {
		return topicRepository.findAll().stream().map(topicModelMapper::modelToEntity).collect(Collectors.toList());
	}

}
