package br.com.hackathon.planonegocio.entities.reportgpt.domain;

import br.com.hackathon.planonegocio.entities.template.domain.Template;
import br.com.hackathon.planonegocio.entities.topic.domain.Topic;
import br.com.hackathon.planonegocio.shared.domain.DomainBase;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@FieldDefaults(level = AccessLevel.PROTECTED)
public class ReportGPT extends DomainBase<Long> {

	private static final long serialVersionUID = 1L;
	
	Topic topic;
	
	Template template;

	String description;
	
	Boolean abnt;

}
