package br.com.hackathon.planonegocio.entities.topic.interfaceadapter.controller.model;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.hackathon.planonegocio.entities.topic.domain.Topic;

@Component
public class GetTopicResponseMapper {

	@Autowired
	private ModelMapper modelMapper;

	public GetTopicResponse modelToEntity(Topic model) {
		return modelMapper.map(model, GetTopicResponse.class);
	}

}
