package br.com.hackathon.planonegocio.entities.template.interfaceadapter.repository.model;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.hackathon.planonegocio.entities.template.domain.Template;

@Component
public class TemplateMapper {

	@Autowired
	private ModelMapper modelMapper;

	public TemplateModel modelToEntity(Template model) {
		return modelMapper.map(model, TemplateModel.class);
	}

}
