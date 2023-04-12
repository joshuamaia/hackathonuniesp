package br.com.hackathon.planonegocio.entities.reportgpt.businessrule;

import br.com.hackathon.planonegocio.entities.reportgpt.domain.ReportGPT;
import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;

public interface FilterReportGPT {
	
	public Page<ReportGPT> filter(
			@Nullable String description,
			@Nullable String topicDescription,
			@Nullable String templateTitle,
			@Nullable Integer page,
			@Nullable Integer size);

}
