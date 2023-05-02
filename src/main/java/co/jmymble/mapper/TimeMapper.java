package co.jmymble.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
	@Select("select sysdate from dual") //query 문. mariadb에서는 from 절 생략 가능
	public Date getTime();
	
	public String getTime2();
	
	public List<Map<String, Object>> memberList();
}
