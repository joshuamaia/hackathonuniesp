package br.com.hackathon.planonegocio.entities.topic.businessrule;

import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;

import br.com.hackathon.planonegocio.entities.topic.domain.Topic;

public interface FilterTopic {
	
	public Page<Topic> filter(
			@Nullable String description,
			@Nullable Integer page,
			@Nullable Integer size);

}
