package br.com.hackathon.planonegocio.entities.topic.businessrule.usecase;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hackathon.planonegocio.entities.topic.businessrule.FindOneTopic;
import br.com.hackathon.planonegocio.entities.topic.domain.Topic;
import br.com.hackathon.planonegocio.entities.topic.interfaceadapter.repository.TopicRepository;
import br.com.hackathon.planonegocio.entities.topic.interfaceadapter.repository.model.TopicModel;
import br.com.hackathon.planonegocio.entities.topic.interfaceadapter.repository.model.TopicModelMapper;

@Service
public class FindOneTopicUC implements FindOneTopic {

	@Autowired
	TopicRepository topicRepository;

	@Autowired
	TopicModelMapper topicModelMapper;

	@Override
	public Topic findOneTopic(Long id) {
		Optional<TopicModel> e = topicRepository.findById(id);
		if (!e.isPresent()) {
			throw new RuntimeException("Entity not present!");
		}
		return topicModelMapper.modelToEntity(e.get());
	}

}
