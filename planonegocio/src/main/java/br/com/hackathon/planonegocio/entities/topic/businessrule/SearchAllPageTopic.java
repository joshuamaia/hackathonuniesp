package br.com.hackathon.planonegocio.entities.topic.businessrule;


import org.springframework.data.domain.Page;

import br.com.hackathon.planonegocio.entities.topic.domain.Topic;

public interface SearchAllPageTopic {
	
	public Page<Topic> searchAllPage(Integer page, Integer size, String wordSearch);

}
