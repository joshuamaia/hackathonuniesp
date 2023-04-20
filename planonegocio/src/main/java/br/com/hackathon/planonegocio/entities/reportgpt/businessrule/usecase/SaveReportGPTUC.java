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

		String questionGpt = reportGPT.getTemplate().getDescription().replaceAll(":topic",
				reportGPT.getTopic().getDescription());

		String description = mountBusinessPlan(abntText, questionGpt);

		reportGPT.setDescription(description);

		ReportGPTModel toReturn = reportGPTRepository.save(reportGPTMapper.modelToEntity(reportGPT));

		return reportGPTModelMapper.modelToEntity(toReturn);
	}

	private String mountBusinessPlan(String abntText, String questionGpt) {
		StringBuilder str = new StringBuilder();
		String resumoIdeias = String.format("%s Descreva de forma resumida o Resumo das Ideias %s", abntText,
				questionGpt);
		str.append("Resumo das Ideias: ").append(chatGptGenerate.generateTextGpt(resumoIdeias)).append("\n\n");
		System.out.println("Resumo das Ideias Executado");
		
		String oportunidadeNegocio = String.format("%s Descreva de forma resumida as Oportunidades de Negócios %s", abntText,
				questionGpt);
		str.append("Oportunidades de Negócios: ").append(chatGptGenerate.generateTextGpt(oportunidadeNegocio)).append("\n\n");
		System.out.println("Oportunidades de Negócios Executado");
		
		String analiseMercado = String.format("%s Descreva de forma resumida a Análise de Mercado %s", abntText,
				questionGpt);
		str.append("Análise de Mercado: ").append(chatGptGenerate.generateTextGpt(analiseMercado)).append("\n\n");
		System.out.println("Análise de Mercado Executado");
		
		String propostaOperacional = String.format("%s Descreva de forma resumida a Proposta de Valor %s", abntText,
				questionGpt);
		str.append("Proposta de Valor: ").append(chatGptGenerate.generateTextGpt(propostaOperacional)).append("\n\n");
		System.out.println("Proposta de Valor Executado");
		
		String estrategiaNegocio = String.format("%s Descreva de forma resumida a Estratégia de Negócio %s", abntText,
				questionGpt);		
		str.append("Estratégia de Negócio: ").append(chatGptGenerate.generateTextGpt(estrategiaNegocio)).append("\n\n");
		System.out.println("Estratégia de Negócio Executado");
		
		String desenvolvimentoProduto = String.format("%s Descreva de forma resumida o Desenvolvimento do Produto %s", abntText,
				questionGpt);
		str.append("Desenvolvimento do Produto: ").append(chatGptGenerate.generateTextGpt(desenvolvimentoProduto)).append("\n\n");
		System.out.println("Desenvolvimento do Produto Executado");
		
		String requisitosProcessosVenda = String.format("%s Descreva de forma resumida os Requisitos e Processos de Venda %s", abntText,
				questionGpt);
		str.append("Requisitos e Processos de Vendao: ").append(chatGptGenerate.generateTextGpt(requisitosProcessosVenda)).append("\n\n");
		System.out.println("Desenvolvimento do Produto Executado");
		
		String diretrizesOperacao = String.format("%s Descreva de forma resumida as Diretrizes de Operações %s", abntText,
				questionGpt);
		str.append("Diretrizes de Operações: ").append(chatGptGenerate.generateTextGpt(diretrizesOperacao)).append("\n\n");
		System.out.println("Diretrizes de Operação Executado");
		
		String financas = String.format("%s Descreva de forma resumida as Finanças %s", abntText,
				questionGpt);
		str.append("Finanças: ").append(chatGptGenerate.generateTextGpt(financas)).append("\n\n");
		System.out.println("Finanças Executado");
		
		String planoMarketing = String.format("%s Descreva de forma resumida o Plano de Marketing %s", abntText,
				questionGpt);
		str.append("Plano de Marketing: ").append(chatGptGenerate.generateTextGpt(planoMarketing)).append("\n\n");
		System.out.println("Plano de Marketing Executado");
		
		String conclusao = String.format("%s Descreva de forma resumida a Conclusão %s", abntText,
				questionGpt);
		str.append("Conclusão: ").append(chatGptGenerate.generateTextGpt(conclusao)).append("\n\n");
		System.out.println("Conclusão Executado");
		
		return str.toString();
	}

}
