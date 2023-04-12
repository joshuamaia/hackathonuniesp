package br.com.hackathon.planonegocio.entities.template.interfaceadapter.repository.model;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.hackathon.planonegocio.entities.template.domain.Template;

@Component
public class TemplateModelMapper {

	@Autowired
	private ModelMapper modelMapper;

	public Template modelToEntity(TemplateModel model) {
		return modelMapper.map(model, Template.class);
	}

}
