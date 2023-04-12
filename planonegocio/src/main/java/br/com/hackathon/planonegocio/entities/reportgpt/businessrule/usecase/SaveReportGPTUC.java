package br.com.hackathon.planonegocio.entities.reportgpt.businessrule.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.hackathon.planonegocio.entities.reportgpt.businessrule.SaveReportGPT;
import br.com.hackathon.planonegocio.entities.reportgpt.domain.ReportGPT;
import br.com.hackathon.planonegocio.entities.reportgpt.interfaceadapter.repository.ReportGPTRepository;
import br.com.hackathon.planonegocio.entities.reportgpt.interfaceadapter.repository.model.ReportGPTMapper;
import br.com.hackathon.planonegocio.entities.reportgpt.interfaceadapter.repository.model.ReportGPTModel;
import br.com.hackathon.planonegocio.entities.reportgpt.interfaceadapter.repository.model.ReportGPTModelMapper;
import br.com.hackathon.planonegocio.shared.businessrule.ChatGptGenerate;

@Service
public class SaveReportGPTUC implements SaveReportGPT {

	@Autowired
	ReportGPTRepository reportGPTRepository;

	@Autowired
	ReportGPTMapper reportGPTMapper;

	@Autowired
	ReportGPTModelMapper reportGPTModelMapper;

	@Autowired
	ChatGptGenerate chatGptGenerate;

	@Override
	@Transactional
	public ReportGPT saveReportGPT(ReportGPT reportGPT) {

		Long countReport = reportGPTRepository.countReportByTopicAndTemplate(reportGPT.getTopic().getId(),
				reportGPT.getTemplate().getId());

		if (countReport > 0) {
			throw new RuntimeException("Já existe um relatório com este tópico e este template!");
		}
		
		String abntText = reportGPT.getAbnt() ? "De acordo com as regras da ABNT " : "";

		String questionGpt = abntText + reportGPT.getTemplate().getDescription().replaceAll(":topic",
				reportGPT.getTopic().getDescription());
		String description = chatGptGenerate.generateTextGpt(questionGpt);

		reportGPT.setDescription(description);

		ReportGPTModel toReturn = reportGPTRepository.save(reportGPTMapper.modelToEntity(reportGPT));

		return reportGPTModelMapper.modelToEntity(toReturn);
	}

}
