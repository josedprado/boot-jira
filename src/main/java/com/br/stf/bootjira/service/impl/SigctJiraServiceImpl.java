package com.br.stf.bootjira.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.stf.bootjira.connection.ConnectionJira;
import com.br.stf.bootjira.service.SigctJiraService;

import br.com.stf.service.SigctServiceWS;
import br.com.stf.util.Parameters;
import br.gov.caixa.sigct.client.SigctService;
import br.gov.caixa.sigct.client.TermosView;

@Service
public class SigctJiraServiceImpl implements SigctJiraService {

	private SigctService service = new SigctServiceWS();
	
	@Autowired
	private ConnectionJira connJira;

	public List<TermosView> getTermosAlterados() {
		List<TermosView> termosList = new ArrayList<TermosView>();
		for (Parameters parameter : Parameters.getParametersConsumer(true)) {
			List<TermosView> termos = new ArrayList<TermosView>();
			try {
				termos = service.getTermosAlterados(parameter.getLogin(), parameter.getPass(),
						new Long(parameter.getContract()), parameter.getSite());
			} catch (Exception e) {
				e.printStackTrace();
			}

			termosList.addAll(termos);
		}
		
		connJira.consumesJira("mdtavares", "Stefanini@1994");
		
		return termosList;
	}
	
	public List<TermosView> getTermos() {
		
		List<TermosView> termosList = new ArrayList<TermosView>();
		for (Parameters parameter : Parameters.getParametersConsumer(true)) {
			List<TermosView> termos = new ArrayList<TermosView>();
			String fatura = "Outubro/2018";
			try { 
				termos = service.getTermos(parameter.getLogin(), parameter.getPass(),
						new Long(parameter.getContract()), parameter.getSite() , fatura);
			} catch (Exception e) {
				e.printStackTrace();
			}

			termosList.addAll(termos != null ? termos : null);
		}
		
		return termosList;
	}

}
