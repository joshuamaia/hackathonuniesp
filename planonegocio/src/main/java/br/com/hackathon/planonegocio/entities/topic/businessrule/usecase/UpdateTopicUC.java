package br.com.hackathon.planonegocio.entities.topic.businessrule.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.hackathon.planonegocio.entities.reportgpt.interfaceadapter.repository.ReportGPTRepository;
import br.com.hackathon.planonegocio.entities.topic.businessrule.UpdateTopic;
import br.com.hackathon.planonegocio.entities.topic.domain.Topic;
import br.com.hackathon.planonegocio.entities.topic.interfaceadapter.repository.TopicRepository;
import br.com.hackathon.planonegocio.entities.topic.interfaceadapter.repository.model.TopicMapper;
import br.com.hackathon.planonegocio.entities.topic.interfaceadapter.repository.model.TopicModel;
import br.com.hackathon.planonegocio.entities.topic.interfaceadapter.repository.model.TopicModelMapper;

@Service
public class UpdateTopicUC implements UpdateTopic {

	@Autowired
	TopicRepository topicRepository;

	@Autowired
	TopicMapper topicMapper;

	@Autowired
	TopicModelMapper topicModelMapper;

	@Autowired
    ReportGPTRepository reportGPTRepository;

	@Override
	@Transactional
	public Topic updateTopic(Topic topic) {
		Long countTopicByReport = reportGPTRepository.countReportByTopic(topic.getId());

		if (countTopicByReport > 0) {
			throw new RuntimeException("Este tópico não pode ser atualizado. Já existe um Relatório GPT associado a ele!");
		}

		TopicModel toReturn = topicRepository.save(topicMapper.modelToEntity(topic));

		return topicModelMapper.modelToEntity(toReturn);
	}

}
