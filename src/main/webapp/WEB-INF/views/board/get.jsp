<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<jsp:include page="../includes/header.jsp" />
   <!-- Begin Page Content -->
   <div class="container-fluid">

       <!-- Page Heading -->
       <h1 class="h3 mb-2 text-gray-800">Board Read Page</h1>
       <!-- DataTales Example -->
       <div class="card shadow mb-4">
           <div class="card-header py-3">
               <h6 class="m-0 font-weight-bold text-primary">Board Read Page</h6>
           </div>
           <div class="card-body">
              <form method="post">
				  <div class="form-group">
				    <label for="bno">bno</label>
				    <input type="text" class="form-control" id="bno" placeholder="Enter bno" name="bno" readonly value="${board.bno}">
				  </div>
				  <div class="form-group">
				    <label for="title">title</label>
				    <input type="text" class="form-control" id="title" placeholder="Enter title" name="title" readonly value="${board.title}">
				  </div>
				  <div class="form-group">
				    <label for="comment">content</label>
				    <textarea rows="15" class="form-control" id="comment" name="content" readonly>${board.content}</textarea>
				  </div>
				  <div class="form-group">
				    <label for="writer">writer</label>
				    <input type="text" class="form-control" id="writer" placeholder="Enter writer" name="writer" readonly value="${board.writer}">
				  </div>
				  
				 <c:if test="${not empty board.attachs[0].uuid}">
				 <div class="form-group">
				    <label for="file">file <br> <span class="btn btn-primary">파일첨부</span></label> <!-- 화면에는 button 타입으로 보여지게 -->
				    <div class="uploadResult my-3">
				    	<ul class="list-group filenames my-3">
					    	<c:forEach items="${board.attachs}" var="attach">
						    	<li class="list-group-item" data-uuid="${attach.uuid}" data-name="${attach.name}" data-path="${attach.path}" data-image="${attach.image}">
						    		<a href="/download${attach.url}&thumb=off">
						    			<i class="far fa-file"></i>${attach.name}
						    		</a> 
		<!-- 				     	<i class="far fa-times-circle btn-remove float-right" data-file="name=santorini-ge87e05bb7_1280.jpg&amp;path=2023%2F04%2F17&amp;uuid=8737bbec-64b2-4f4c-83e6-1164ec1eab39&amp;image=true&amp;thumb=off"></i>  -->
						    	</li>
					    	</c:forEach>
				    	</ul>
				    	<ul class="nav thumbs">
					    	<c:forEach items="${board.attachs}" var="attach">
					    		<c:if test="${attach.image}">
							    	<li class="nav-item m-2" data-uuid="${attach.uuid}">
								    	<a class="img-thumb" href="">
								    		<img class="img-thumbnail" src="/display${attach.url}&thumb=on" data-src="${attach.url}&thumb=off">
								    	</a>
							    	</li>
						    	</c:if>
					 		</c:forEach>
				 		</ul>
				    </div>
				  </div>
				  </c:if>
				  <sec:authorize access="isAuthenticated() and principal.username eq #board.writer">
					  <a href="modify${cri.fullQueryString}&bno=${board.bno}" class="btn btn-outline-warning">modify</a>
				  </sec:authorize>
				  <a href="list${cri.fullQueryString}" class="btn btn-secondary">list</a>
			</form>
           </div>
       </div>
       
       <div class="card shadow mb-4">
           <div class="card-header py-3">
               <h6 class="m-0 font-weight-bold text-primary float-left">Reply</h6>
               <sec:authorize access="isAuthenticated()">
               		<button class="btn btn-primary float-right btn-sm" id="btnReg">New Reply</button>
               </sec:authorize>
           </div>
           <div class="card-body">
              <ul class="list-group chat" >
              </ul>
              <button class="btn btn-primary btn-block my-4 col-md-8 mx-auto btn-more">더보기</button>
           </div>
       </div>
   </div>
   <!-- /.container-fluid -->
   <div class="modal fade" id="replyModal" tabindex="-1" role="dialog" aria-labelledby="replyModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Reply Modal</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">
                	 <div class="form-group">
					    <label for="reply">Reply</label>
					    <input type="text" class="form-control" id="reply" placeholder="Enter reply" >
					</div>
                	 <div class="form-group">
					    <label for="replyer">Replyer</label>
					    <input type="text" class="form-control" id="replyer" placeholder="Enter replyer" readonly >
					</div>
                	 <div class="form-group">
					    <label for="replydate">Reply Date</label>
					    <input type="text" class="form-control" id="replydate">
					</div>
	                
                </div>
                <div class="modal-footer" id="modalFooter">
                    <button class="btn btn-warning" type="button" data-dismiss="modal">Modify</button>
                    <button class="btn btn-danger" type="button" data-dismiss="modal">Remove</button>
                    <button class="btn btn-primary" type="button" data-dismiss="modal">Register</button>
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
 
<script>
	var cp = '${pageContext.request.contextPath}';
</script>
<script src="${pageContext.request.contextPath}/resources/js/reply.js"></script>
<script>
	$(function() {
		var csrfHeader = '${_csrf.headerName}';
		var csrfToken = '${_csrf.token}';
		var bno = '${board.bno}';
		var replyer = '';
		
		<sec:authorize access="isAuthenticated()">
			replyer = '<sec:authentication property="principal.username"/>'
		</sec:authorize>
		
		$(document).ajaxSend(function(e, xhr) {
			xhr.setRequestHeader(csrfHeader, csrfToken);
		})
		moment.locale('ko');
		 replyService.getList({bno: bno}, function(result) {
			var str = "";
			for(var i in result){
				str += getReplyLiStr(result[i]);
			}
			$(".chat").html(str);
		});
		
		$("#btnReg").click(function() {
			$("#replyModal").find("input").val("");
			$("#replyer").val(replyer);
			$("#replydate").closest("div").hide(); //replydate의 div태그 자체를 아예 없앰
			$("#modalFooter button").hide();
			$("#modalFooter button").eq(2).show();
			$("#modalFooter button").eq(3).show();
			$("#replyModal").modal("show");
		})
		
		$(".chat").on("click", "li", function () {
			replyService.get($(this).data("rno"), function(result) {
				$("#reply").val(result.reply)
				$("#replyer").val(result.replyer)
				$("#replydate").val(moment(result.replydate).format("LLL:ss")).prop("readonly", true).closest("div").show(); //가져온 세 개의 태그를 모두 readonly로 하겠다
				
				$("#modalFooter button").show(); //일단 다 보이게 한 다음
				$("#modalFooter button").eq(2).hide(); //Register만 숨기기
				if(replyer != result.replyer){
					$("#modalFooter button").eq(1).hide();					
					$("#modalFooter button").eq(0).hide();					
				}
				$("#replyModal").modal("show").data("rno", result.rno); //화면에 보여짐과 동시에 data를 통해 rno 가져오기
				console.log(result);
			})
		})
		
		//댓글 작성 버튼 이벤트
		$("#modalFooter button").eq(2).click(function() {
			var obj = {bno:bno, reply:$("#reply").val(), replyer:$("#replyer").val()}
			console.log(obj);
			replyService.add(obj, function(result){
				$("#replyModal").find("input").val("");
				$("#replyModal").modal("hide");
				replyService.get(result, function(data) {
					$(".chat").prepend(getReplyLiStr(data)); //작성한 이후의 이벤트에서 댓글번호에 따른 댓글 객체를 가져와 맨 앞에 추가
				})
			}); 
		})
		
		//댓글 삭제 버튼 이벤트
		$("#modalFooter button").eq(1).click(function() {
			var obj = {rno:$("#replyModal").data("rno"), replyer:$("#replyer").val()}
			replyService.remove(obj, function(result) {
				$("#replyModal").modal("hide");
				console.log(result);
				$(".chat li").each(function() {
					if($(this).data("rno") == obj.rno) { //실제 삭제할 번호와 같으면
						$(this).remove(); //dom에서 나를 지우기
					}
				})
			})
		})
		
		//댓글 수정 버튼 이벤트
		$("#modalFooter button").eq(0).click (function() {
			var obj = {rno:$("#replyModal").data("rno"), reply:$("#reply").val(), replyer:$("#replyer").val()}
			replyService.modify(obj, function(result) {
				$("#replyModal").modal("hide");
				console.log(result);
				$(".chat li").each(function() {
					if($(this).data("rno") == obj.rno) { 
						var $this = $(this); //비동기 처리할 때 this의 의미가 변경될 가능성이 있기 때문에 별도의 변수로 설정
						//$(this).replyWith("")
						replyService.get($this.data("rno"), function(r) {
							$this.replaceWith(getReplyLiStr(r))
						})
					}
				})
			})
		})
		
		//더보기 버튼 클릭 이벤트
		$(".btn-more").click(function() {
			var rno = $(".chat li:last").data("rno"); //댓글 리스트의 마지막 댓글 번호 가져오기
			console.log(rno);
			
			replyService.getList({bno: bno, rno:rno}, function(result) {
					console.log(result)
					if(!result.length){
						$(".btn-more").prop("disabled", true);
						return;
					}
					var str = "";
					for(var i in result){
						str += getReplyLiStr(result[i]);
					}
					$(".chat").append(str); //덮어쓰기가 아니라 추가하는 것이므로 append
			});
		})
		
		function getReplyLiStr(obj) {
			return `<li class="list-group-item" data-rno="\${obj.rno}">
			      		<div class="header">
			  			<strong class="primary-font">\${obj.replyer}</strong>
			  			<small class="float-right text-muted">\${moment(obj.replydate).fromNow()}</small>
				  		</div>
			  			<p>\${obj.reply}</p>
			  		</li>`;
			
		}
		
		//$("#replyModal").modal("show"); //초기 작업 시 화면에 무조건 보이도록(작업하기 편하게)
		/* 
		replyService.add({bno:bno, replyer:'작성자', reply:'댓글내용'}, function(result){
			console.log(result);
		}); 
		replyService.get(26, function(result) {
			console.log(result);
		})
		replyService.remove(26, function(result) {
			console.log(result);
		})
		replyService.modify({rno:160, reply:'수정된 댓글 내용'}, function(result){
		console.log(result);
		}); 
		*/
		
		ClassicEditor.create($('#comment').get(0), {
			toolbar : [] //툴바 제거
		}).then(function(editor) {
			editor.enableReadOnlyMode('lock');
		})
		
	})
</script>
<script>
$(function() {
	$("form button").click(function() {
		event.preventDefault();
		//title, content, writer, attachs[0].uuid, 
		var str = '';
		 $(".filenames li").each(function(i, obj) {//index i, 
			console.log(i, obj, this)
			str += `
				<input type="hidden" name="attachs[\${i}].uuid" value="\${$(this).data('uuid')}">
				<input type="hidden" name="attachs[\${i}].name" value="\${$(this).data('name')}">
				<input type="hidden" name="attachs[\${i}].path" value="\${$(this).data('path')}">
				<input type="hidden" name="attachs[\${i}].image" value="\${$(this).data('image')}">
				`;
		 })
		 console.log(str)
		 $("form").append(str).submit();//form 태그에 추가 -> 페이지가 넘어가며 글 등록/수집된 첨부파일이 리스트 형태로 보여짐
		
	})
	
	function checkExtension(files) {
		const MAX_SIZE = 5 * 1024 * 1024; //5 mega byte
		const EXCLUDE_EXT = new RegExp("(.*?)\.(js|jsp|asp|php)");
		
		for(let i in files){
			if(files[i].size >= MAX_SIZE || EXCLUDE_EXT.test(files[i].name)) {
				return false;
			}
		}
		return true;
	}
	
	$("#file").change(function() {
		event.preventDefault();
		let files = $("#file").get(0).files;
		console.log(files);
		if(!checkExtension(files)){
			alert("조건 불일치");
			return false;
		}
		
		let formData = new FormData();
		
		for(let i in files){
			formData.append("files", files[i]);
		}
		
		$.ajax({
			url : "/uploadAjax",
			processData : false,
			contentType : false,
			data : formData,
			method : "post",
			success : function(data) {
				$("form").get(0).reset();
				showUploadedFile(data); //썸네일(미리 보기)
			}
		})
	})
	
	function showUploadedFile(uploadResultArr){
		console.log(uploadResultArr);
		var str = "";
		var imgStr = "";
		for(var i in uploadResultArr){
			let obj = uploadResultArr[i];
			str += `<li class="list-group-item" data-uuid="\${obj.uuid}" data-name="\${obj.name}" data-path="\${obj.path}" data-image="\${obj.image}" ><a href="/download\${obj.url}"><i class="far fa-file"></i>`;
			str += obj.name + `</a> <i class="far fa-times-circle btn-remove float-right" data-file="\${obj.url}"></i> </li>`;
			if(obj.image) {
				imgStr += `<li class="nav-item m-2" data-uuid="\${obj.uuid}"><a class="img-thumb" href="">
				<img class="img-thumbnail" src="/display\${obj.url}&thumb=on" data-src="\${obj.url}"></a></li>`;
			}
		}
		console.log(str);
		$(".uploadResult .filenames").append(str);
		$(".uploadResult .thumbs").append(imgStr);
	}
	
	$(".uploadResult ul").on("click", ".btn-remove", function() {
		var file = $(this).data("file");
		console.log(file);
		
		$.ajax({
			url : "/deleteFile"+file,
			success : function(data){
				console.log(data);
				$('[data-uuid="' + data + '"]').remove()
			}
		})
	});
	
	$(".uploadResult ul").on("click", ".img-thumb", function() {
		event.preventDefault();
		var param = $(this).find("img").data("src");
		$("#showImageModal").find("img").attr("src", "/display" + param).end().modal("show");
	});
})

</script>
<jsp:include page="../includes/footer.jsp" />