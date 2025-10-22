//삭제 기능
const deleteButton = document.getElementById('delete-btn');

if(deleteButton){
	deleteButton.addEventListener('click', event => {
		let id = document.getElementById('article-id').value;
		fetch(`/api/articles/${id}`, {
			method : 'DELETE'	
		})
		.then(()=>{
			alert('삭제가 완료되었습니다!');
			location.replace('/articles');
		});
	});
}


//수정 기능
//1: id가 modify-btn인 엘리먼트 조회
const  modifyButton = document.getElementById('modify-btn');

if(modifyButton) {
	//2: 클릭 이벤트가 감지되면 수정 API 요청
	modifyButton.addEventListener('click', event=>{
		let params = new URLSearchParams(location.search);
		let id = params.get('id');
		
		fetch(`/api/articles/${id}`, {
			method : 'PUT',
			headers : {
				"Content-Type" : "application/json",
			},
			body : JSON.stringify ({
				title : document.getElementById('title').value,
				content : document.getElementById('content').value
			})
		})
		.then(() => {
			alert('수정이 완료되었습니다.');
			location.replace(`/articles/${id}`);
		});
	});	
}


//등록 기능
//1: id가 create-btn인 엘리먼
const createButton = document.getElementById("create-btn");
if(createButton) {
	//2: 클릭 이벤트가 감지되면 생성 API 요청
	createButton.addEventListener("click", (event)=> {
		fetch("/api/articles", {
			method: "POST",
			headers: {
				"Content-Type": "application/json",	
			},
			body: JSON.stringify({
				title: document.getElementById("title").value,
				content: document.getElementById("content").value,
			}),
		}).then(()=> {
			alert("등록 완료되었습니다.");
			location.replace("/articles");
		});
	});
}



/* 메시지가 존재할 경우 3초 후 fade out */
const logoutMessage = document.getElementById('logout-message');
if (logoutMessage) {
    setTimeout(() => {
        logoutMessage.style.transition = "opacity 1s ease";
        logoutMessage.style.opacity = 0;
        setTimeout(() => logoutMessage.remove(), 1000); // 완전히 제거
    }, 1000); // 3초 후 시작
}




