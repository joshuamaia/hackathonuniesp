package br.com.hackathon.planonegocio.entities.template.businessrule;

import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;

import br.com.hackathon.planonegocio.entities.template.domain.Template;

public interface FilterTemplate {
	
	public Page<Template> filter(
			@Nullable String tile,
			@Nullable String description,
			@Nullable Integer page,
			@Nullable Integer size);

}
