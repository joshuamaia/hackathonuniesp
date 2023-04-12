package br.com.hackathon.planonegocio.entities.topic.interfaceadapter.controller.model;

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
public class GetTopicResponse extends DomainBase<Long> {

	private static final long serialVersionUID = 1L;

	String description;


}
