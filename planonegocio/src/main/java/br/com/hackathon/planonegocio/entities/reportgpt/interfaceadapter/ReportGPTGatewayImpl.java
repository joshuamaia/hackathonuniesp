package br.com.hackathon.planonegocio.entities.reportgpt.interfaceadapter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.hackathon.planonegocio.entities.reportgpt.businessrule.DeleteReportGPT;
import br.com.hackathon.planonegocio.entities.reportgpt.businessrule.FilterReportGPT;
import br.com.hackathon.planonegocio.entities.reportgpt.businessrule.FindOneReportGPT;
import br.com.hackathon.planonegocio.entities.reportgpt.businessrule.GetAllReportGPT;
import br.com.hackathon.planonegocio.entities.reportgpt.businessrule.SaveReportGPT;
import br.com.hackathon.planonegocio.entities.reportgpt.businessrule.SearchAllPageReportGPT;
import br.com.hackathon.planonegocio.entities.reportgpt.businessrule.UpdateReportGPT;
import br.com.hackathon.planonegocio.entities.reportgpt.domain.ReportGPT;
import br.com.hackathon.planonegocio.entities.reportgpt.interfaceadapter.controller.model.GetReportGTPResponse;
import br.com.hackathon.planonegocio.entities.reportgpt.interfaceadapter.controller.model.GetReportGTPResponseMapper;

@Service
public class ReportGPTGatewayImpl implements ReportGPTGateway {

	@Autowired
	private SaveReportGPT saveReportGPT;

	@Autowired
	private UpdateReportGPT updateReportGPT;

	@Autowired
	private GetAllReportGPT getAllReportGPT;

	@Autowired
	private FindOneReportGPT findOneReportGPT;

	@Autowired
	private DeleteReportGPT deleteReportGPT;

	@Autowired
	private SearchAllPageReportGPT searchAllPageReportGPT;

	@Autowired
	GetReportGTPResponseMapper getReportGTPResponseMapper;

	@Autowired
	FilterReportGPT filterReportGPT;

	@Override
	public GetReportGTPResponse saveReportGTP(ReportGPT reportGPT) {
		return getReportGTPResponseMapper.modelToEntity(saveReportGPT.saveReportGPT(reportGPT));
	}

	@Override
	public GetReportGTPResponse updateReportGTP(ReportGPT reportGPT) {
		return getReportGTPResponseMapper.modelToEntity(updateReportGPT.updateReportGPT(reportGPT));
	}

	@Override
	public List<GetReportGTPResponse> getAllReportGTP() {
		return getAllReportGPT.getAllReportGPT().stream().map(getReportGTPResponseMapper::modelToEntity)
				.collect(Collectors.toList());
	}

	@Override
	public GetReportGTPResponse findOneReportGTP(Long id) {
		return getReportGTPResponseMapper.modelToEntity(findOneReportGPT.findOneReportGPT(id));
	}

	@Override
	public void deleteReportGTP(Long id) {
		deleteReportGPT.deleteReportGPT(id);
	}

	@Override
	public Page<GetReportGTPResponse> searchAllPage(Integer page, Integer size, String wordSearch) {
		return searchAllPageReportGPT.searchAllPage(page, size, wordSearch)
				.map(getReportGTPResponseMapper::modelToEntity);
	}

	@Override
	public Page<GetReportGTPResponse> filter(String description, String topicDescription, String templateTitle,
			Integer page, Integer size) {
		return filterReportGPT.filter(description, topicDescription, templateTitle, page, size)
				.map(getReportGTPResponseMapper::modelToEntity);
	}

}
