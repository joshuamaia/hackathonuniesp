package br.com.hackathon.planonegocio.entities.template.interfaceadapter.repository.model;

import java.io.Serializable;

import br.com.hackathon.planonegocio.shared.interfaceadapter.repository.model.EntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Table(name = "template")
public class TemplateModel extends EntityBase<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false, length = 100)
	String title;

	@Column(nullable = false, columnDefinition = "TEXT")
	String description;

}
