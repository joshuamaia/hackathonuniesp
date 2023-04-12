package br.com.hackathon.planonegocio.entities.reportgpt.interfaceadapter.repository.model;

import java.io.Serializable;

import br.com.hackathon.planonegocio.entities.template.interfaceadapter.repository.model.TemplateModel;
import br.com.hackathon.planonegocio.entities.topic.interfaceadapter.repository.model.TopicModel;
import br.com.hackathon.planonegocio.shared.interfaceadapter.repository.model.EntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@ToString
@FieldDefaults(level = AccessLevel.PROTECTED)
@Table(name = "reportgpt", uniqueConstraints = @UniqueConstraint(columnNames = {"topic_id", "template_id"}, name = "topic_template_uk"))
public class ReportGPTModel extends EntityBase<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "topic_id")
	TopicModel topic;

	@ManyToOne
	@JoinColumn(name = "template_id")
	TemplateModel template;

	@Column(nullable = false, columnDefinition = "TEXT")
	String description;
	
	Boolean abnt;

}
