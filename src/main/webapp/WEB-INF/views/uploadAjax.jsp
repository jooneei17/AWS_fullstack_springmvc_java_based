<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>2023. 4. 7.오전 10:35:09</title>
<link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
<style>
.bigPictureWrapper{
	position: absolute;
	display: none;
	justify-content:center;
	align-items:center;
	top:0;
	width:100%;
	height:100%;
	background-color:gray;
	z-index:100;
	background: rgba(0, 0, 0, 0.5);

}	
.bigPicture{
	position: relative;
	display: flex;
	justify-content:center;
	align-items:center;

}
.bigPicture img {
	max-width: 600px;
}
</style>

</head>
<body>
<form method="post" enctype="multipart/form-data">
	<label for="files"><i class="fas fa-folder-plus"></i></label>
	<input type="file" name="files" multiple accept="image/jpeg, impage/png" id="files" >
	<button>submit</button>
	<div></div>
</form>
<div class="uploadResult"> 
	<ul>
	
	</ul>
	
</div>
<div class="bigPictureWrapper">
	<div class="bigPicture">
	</div>
</div>
<script>


$(function() {
	$(".bigPictureWrapper").click(function() {
		$(".bigPicture").animate({width:0, height:0}, 1000);
		setTimeout(function() {
			$(".bigPictureWrapper").hide();
		}, 1000);
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

	
	/* $("form :file").change(function() {
		let str = "";
		for(let i = 0; i < this.files.length; i++) {
			str += "<p> ========================= </p>";
			str += "<p>" + this.files[i].name + "</p>";
			str += "<p>" + this.files[i].type + "</p>";
			str += "<p>" + this.files[i].size + "</p>";
		}
		$("form div").html(str);
	}) */
	
	$(".uploadResult ul").on("click", ".img-thumb", function() {
		event.preventDefault();
		$(".bigPictureWrapper").css("display", "flex").show();
		var param = $(this).find("img").data("src");
		console.log(param)
		$(".bigPicture")
		.html("<img src='/display?" + param + "'>")
		.animate({width:'100%', height:'100%'}, 1000);
	});
	
	$(".uploadResult ul").on("click", ".btn-remove", function() {
		event.preventDefault();
		var file = $(this).data("file");
		console.log(file);
		$.getJSON("/deleteFile?"+file).done(function(data){
			console.log(data);
		})
	});
	
	function showUploadedFile(uploadResultArr){
		var str = "";
		for(var i in uploadResultArr){
			let obj = uploadResultArr[i];
			obj.thumb = "on";
			var params = new URLSearchParams(obj).toString();
			if(!obj.image){
				str += '<li><a href="/download?' + params + '"><i class="far fa-file"></i>' + obj.name + '</a></li>';
				
			}
			else {
				obj.thumb = "off";
				var params2 = new URLSearchParams(obj).toString();
				str += '<li><a class="img-thumb" href=""><img src="/display?' + params + '" data-src= "' + params2 + '">';
			}
			str += obj.name + '</a> <i class="far fa-times-circle btn-remove" data-file="' + params + '"></i> </li>';
		}
		//내부적으로 스트림 사용
		$(".uploadResult ul").append(str);
	}
	
	$("form button").click(function() {
		event.preventDefault();
		let files = $(":file").get(0).files;
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
				showUploadedFile(data);
			}
		})
	})
})
</script>
</body>
</html>