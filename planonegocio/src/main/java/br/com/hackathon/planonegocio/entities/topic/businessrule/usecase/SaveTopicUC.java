package br.com.hackathon.planonegocio.entities.topic.businessrule.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.hackathon.planonegocio.entities.topic.businessrule.SaveTopic;
import br.com.hackathon.planonegocio.entities.topic.domain.Topic;
import br.com.hackathon.planonegocio.entities.topic.interfaceadapter.repository.TopicRepository;
import br.com.hackathon.planonegocio.entities.topic.interfaceadapter.repository.model.TopicMapper;
import br.com.hackathon.planonegocio.entities.topic.interfaceadapter.repository.model.TopicModel;
import br.com.hackathon.planonegocio.entities.topic.interfaceadapter.repository.model.TopicModelMapper;

@Service
public class SaveTopicUC implements SaveTopic {
	
	@Autowired
	TopicRepository topicRepository;
	
	@Autowired
	TopicMapper topicMapper;
	
	@Autowired
	TopicModelMapper topicModelMapper;

	@Override
	@Transactional
	public Topic saveTopic(Topic topic) {
		TopicModel toReturn = topicRepository.save(topicMapper.modelToEntity(topic));

		return topicModelMapper.modelToEntity(toReturn);
	}

}
