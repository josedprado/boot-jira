package com.br.stf.bootjira.resource;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.stf.bootjira.service.SigctJiraService;
import com.br.stf.bootjira.util.SigctUtils;

import br.gov.caixa.sigct.client.TermosView;

@RestController
@EnableScheduling
public class TermosResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(TermosResource.class);

	@Autowired
	private SigctJiraService sigctService;
	
	@Autowired
	private SigctUtils utils;

	@GetMapping
	@RequestMapping(value = "/termosAlterados", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getTermosAlterados() {

		LOGGER.info("### INICIO ###");

		return new ResponseEntity<List<TermosView>>(sigctService.getTermosAlterados(), HttpStatus.OK);

	}
	
	@GetMapping
	@RequestMapping(value = "/termos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getTermos() {

		LOGGER.info("### INICIO ###");

		return new ResponseEntity<List<TermosView>>(sigctService.getTermos(), HttpStatus.OK);

	}
	
	@GetMapping
	@RequestMapping(value = "/data", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getData() {

		LOGGER.info("### INICIO ###");

		return new ResponseEntity<String>(utils.currentInvoice(), HttpStatus.OK);

	}

//	@Scheduled(fixedDelay = 4000)
//	public void getTermosScheduler() {
//
//		LOGGER.info("### INICIO ###");
//
//		try {
//			for (Parameters parameter : Parameters.getParametersConsumer(true)) {
//				List<TermosView> termos = service.getTermosAlterados(parameter.getLogin(), parameter.getPass(),
//						new Long(parameter.getContract()), parameter.getSite());
//				System.out.println(termos);
//			}
//		} catch (SOAPException_Exception e) {
//			e.printStackTrace();
//			System.out.println(new ErrorResponse("1", "Erro"));
//		}
//
//	}

}
