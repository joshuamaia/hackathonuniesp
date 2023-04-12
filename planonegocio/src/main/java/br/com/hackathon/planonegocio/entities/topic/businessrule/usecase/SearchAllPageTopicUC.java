package br.com.hackathon.planonegocio.entities.topic.businessrule.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.hackathon.planonegocio.entities.topic.businessrule.SearchAllPageTopic;
import br.com.hackathon.planonegocio.entities.topic.domain.Topic;
import br.com.hackathon.planonegocio.entities.topic.interfaceadapter.repository.TopicRepository;
import br.com.hackathon.planonegocio.entities.topic.interfaceadapter.repository.model.TopicModelMapper;

@Service
public class SearchAllPageTopicUC implements SearchAllPageTopic {

	@Autowired
	TopicRepository topicRepository;

	@Autowired
	TopicModelMapper topicModelMapper;

	@Override
	public Page<Topic> searchAllPage(Integer page, Integer size, String wordSearch) {
		PageRequest pageRequest = PageRequest.of(page, size);
		if (wordSearch == null || wordSearch.trim().isEmpty()) {
			return topicRepository.findAll(pageRequest).map(topicModelMapper::modelToEntity);
		}
		wordSearch = wordSearch.toLowerCase();
		return topicRepository.searchAllPage(wordSearch, pageRequest).map(topicModelMapper::modelToEntity);

	}

}
