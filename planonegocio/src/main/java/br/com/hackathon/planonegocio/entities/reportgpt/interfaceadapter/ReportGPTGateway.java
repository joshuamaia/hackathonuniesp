package br.com.hackathon.planonegocio.entities.reportgpt.interfaceadapter;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;

import br.com.hackathon.planonegocio.entities.reportgpt.domain.ReportGPT;
import br.com.hackathon.planonegocio.entities.reportgpt.interfaceadapter.controller.model.GetReportGTPResponse;

public interface ReportGPTGateway {

	GetReportGTPResponse saveReportGTP(ReportGPT reportGPT);

	GetReportGTPResponse updateReportGTP(ReportGPT rreportGPT);

	List<GetReportGTPResponse> getAllReportGTP();

	GetReportGTPResponse findOneReportGTP(Long id);

	Page<GetReportGTPResponse> filter(@Nullable String description, @Nullable String topicDescription,
			@Nullable String templateTitle, @Nullable Integer page, @Nullable Integer size);

	void deleteReportGTP(Long id);

	public Page<GetReportGTPResponse> searchAllPage(Integer page, Integer size, String wordSearch);

}
