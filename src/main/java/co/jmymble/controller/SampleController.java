package co.jmymble.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.jmymble.domain.SampleVO;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("sample")
@Log4j
public class SampleController {
	@GetMapping(value="getText", produces = "text/plain; charset=utf-8")
	public String getText() {
		return "안녕하세요";
	}
	
	@GetMapping("getSample")
	public SampleVO getSampleVO() {
		return new SampleVO(112, "스타", "로드");
	}
	
	@GetMapping("getList")
	public List<SampleVO> getList(){
		//1~10 포함하여 기본 타입의 스트림을 Obj로 만드는 map 함수를 활용하여 generic 타입의 스트림으로 만든다.
		//다시 collect를 사용하여 List로 변환한다. 
		return IntStream.rangeClosed(1, 10).mapToObj(i -> new SampleVO(i, "first " +i, "last" + i)).collect(Collectors.toList());
	}
	
	@GetMapping("getMap")
	public Map<String, SampleVO> getMap() {
		Map<String, SampleVO> map = new HashMap<String, SampleVO>();
		map.put("First", new SampleVO(111, "그루트", "주니어"));
		return map;
	}
	
	@GetMapping(value="check", params= {"height", "weight"})
	public ResponseEntity<SampleVO> check (Double height, Double weight){
		SampleVO vo = new SampleVO(0, String.valueOf(height), String.valueOf(weight));
		
		ResponseEntity<SampleVO> result = null;
		
		if(height < 150) {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
		} else {
			result = ResponseEntity.ok(vo);
		}
		
		return result;
	}
	
	@GetMapping("product/{cat}/{pid}")
	public String[] getPath (@PathVariable String cat, @PathVariable String pid) {
		return new String[] {"category: " + cat, "productId : " + pid};
	}
	@GetMapping("product/{cat2}/{pid2}/{test}")
	public String[] getPath2 (@PathVariable String cat2, @PathVariable String pid2) {
		return new String[] {"category: " + cat2, "productId : " + pid2};
	}
	
	@GetMapping("sample")
	public SampleVO convert(@RequestBody SampleVO sampleVO) {
		log.warn(sampleVO);
		return sampleVO;
	}
	
	@PostMapping("sample")
	public SampleVO convert2(@RequestBody SampleVO sampleVO) {
		log.warn(sampleVO);
		return sampleVO;
	}
}
