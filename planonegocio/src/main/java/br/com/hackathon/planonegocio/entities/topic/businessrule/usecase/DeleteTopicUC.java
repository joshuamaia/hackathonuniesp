package br.com.hackathon.planonegocio.entities.topic.businessrule.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hackathon.planonegocio.entities.topic.businessrule.DeleteTopic;
import br.com.hackathon.planonegocio.entities.topic.interfaceadapter.repository.TopicRepository;

@Service
public class DeleteTopicUC implements DeleteTopic{
	
	@Autowired
	TopicRepository topicRepository;

	@Override
	public void deleteTopic(Long id) {
		topicRepository.deleteById(id);
	}

}
