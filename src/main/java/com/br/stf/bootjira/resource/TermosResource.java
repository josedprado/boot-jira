package com.br.stf.bootjira.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.stf.bootjira.entity.ErrorResponse;

import br.com.stf.service.SigctServiceWS;
import br.com.stf.util.Parameters;
import br.gov.caixa.sigct.client.SOAPException_Exception;
import br.gov.caixa.sigct.client.SigctService;
import br.gov.caixa.sigct.client.TermosView;

@RestController
@EnableScheduling
public class TermosResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(TermosResource.class);

	private SigctService service = new SigctServiceWS();

	@GetMapping
	@RequestMapping(value = "/termos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getTermos() {

		LOGGER.info("### INICIO ###");
		try {
			List<TermosView> termosList = new ArrayList<TermosView>();
			for (Parameters parameter : Parameters.getParametersConsumer(true)) {
				List<TermosView> termos = service.getTermosAlterados(parameter.getLogin(), parameter.getPass(),
						new Long(parameter.getContract()), parameter.getSite());
				termosList.addAll(termos);
			}

			return new ResponseEntity<List<TermosView>>(termosList, HttpStatus.OK);
		} catch (SOAPException_Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ErrorResponse>(new ErrorResponse("1", "Erro"), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Scheduled(fixedDelay = 4000)
	public void getTermosScheduler() {

		LOGGER.info("### INICIO ###");

		try {
			for (Parameters parameter : Parameters.getParametersConsumer(true)) {
				List<TermosView> termos = service.getTermosAlterados(parameter.getLogin(), parameter.getPass(),
						new Long(parameter.getContract()), parameter.getSite());
				System.out.println(termos);
			}
		} catch (SOAPException_Exception e) {
			e.printStackTrace();
			System.out.println(new ErrorResponse("1", "Erro"));
		}

	}

}
