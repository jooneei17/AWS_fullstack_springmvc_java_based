package co.jmymble.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true) //그냥 toString만 하면 자기꺼만 해줌. callSuper해주면 조상까지 toString 만들어줌
@Alias("attach")
public class AttachVO extends AttachFileDTO{
	private Long bno;
}
