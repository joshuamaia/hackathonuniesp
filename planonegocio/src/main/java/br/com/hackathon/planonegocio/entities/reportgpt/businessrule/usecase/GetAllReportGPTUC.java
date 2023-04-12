package br.com.hackathon.planonegocio.entities.reportgpt.businessrule.usecase;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hackathon.planonegocio.entities.reportgpt.businessrule.GetAllReportGPT;
import br.com.hackathon.planonegocio.entities.reportgpt.domain.ReportGPT;
import br.com.hackathon.planonegocio.entities.reportgpt.interfaceadapter.repository.ReportGPTRepository;
import br.com.hackathon.planonegocio.entities.reportgpt.interfaceadapter.repository.model.ReportGPTModelMapper;

@Service
public class GetAllReportGPTUC implements GetAllReportGPT {

	@Autowired
	ReportGPTRepository reportGPTRepository;

	@Autowired
	ReportGPTModelMapper reportGPTModelMapper;

	@Override
	public List<ReportGPT> getAllReportGPT() {
		return reportGPTRepository.findAll().stream().map(reportGPTModelMapper::modelToEntity).collect(Collectors.toList());
	}

}
