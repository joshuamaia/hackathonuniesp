package br.com.hackathon.planonegocio.entities.topic.interfaceadapter.repository.model;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.hackathon.planonegocio.entities.topic.domain.Topic;

@Component
public class TopicModelMapper {

	@Autowired
	private ModelMapper modelMapper;

	public Topic modelToEntity(TopicModel model) {
		return modelMapper.map(model, Topic.class);
	}

}
