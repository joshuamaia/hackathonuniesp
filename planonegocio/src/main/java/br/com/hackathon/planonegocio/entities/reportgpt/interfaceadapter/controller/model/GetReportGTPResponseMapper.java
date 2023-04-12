package br.com.hackathon.planonegocio.entities.reportgpt.interfaceadapter.controller.model;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.hackathon.planonegocio.entities.reportgpt.domain.ReportGPT;

@Component
public class GetReportGTPResponseMapper {

	@Autowired
	private ModelMapper modelMapper;

	public GetReportGTPResponse modelToEntity(ReportGPT model) {
		return modelMapper.map(model, GetReportGTPResponse.class);
	}

}
