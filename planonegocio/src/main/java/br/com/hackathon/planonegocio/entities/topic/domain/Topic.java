package br.com.hackathon.planonegocio.entities.topic.domain;

import br.com.hackathon.planonegocio.shared.domain.DomainBase;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@FieldDefaults(level = AccessLevel.PROTECTED)
public class Topic extends DomainBase<Long> {

	private static final long serialVersionUID = 1L;

	String description;

}
