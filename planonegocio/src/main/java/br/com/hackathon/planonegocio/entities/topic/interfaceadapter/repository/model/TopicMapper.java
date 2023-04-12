package br.com.hackathon.planonegocio.entities.topic.interfaceadapter.repository.model;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.hackathon.planonegocio.entities.topic.domain.Topic;

@Component
public class TopicMapper {

	@Autowired
	private ModelMapper modelMapper;

	public TopicModel modelToEntity(Topic model) {
		return modelMapper.map(model, TopicModel.class);
	}

}
