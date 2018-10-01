package com.br.stf.bootjira.service;

import java.util.List;

import br.gov.caixa.sigct.client.TermosView;

public interface SigctJiraService {

	public List<TermosView> getTermosAlterados();
	
	public List<TermosView> getTermos();
	
}
