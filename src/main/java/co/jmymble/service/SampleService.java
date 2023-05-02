package co.jmymble.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.jmymble.mapper.SampleMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Log4j
@Transactional
public class SampleService {
	private SampleMapper mapper;
	
	public void addData(String data){
		log.info("insert1()");
		log.info(data);
		mapper.insert1(data);
		log.info("insert2()");
		mapper.insert2(data);
		log.info("end");
	}
	public void addData2(String data){
		log.info("insert1()");
		mapper.insert1(data);
		log.info("insert2()");
		mapper.insert2(data);
		log.info("end");
		log.info("insert2()");
		mapper.insert2(data);
		log.info("insert2()");
		mapper.insert2(data);
		log.info("end");
	}
}
