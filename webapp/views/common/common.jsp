<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<script>


function exceptionHandler(func){
	
	try{
		func();
	}catch(e){
		console.log(e);
	}
	
}

function parseStatusArrToObj(parsedArr) {

    let statusObj = Object.create(null);
    for (let i in parsedArr) {
        statusObj[parsedArr[i].key] = parsedArr[i].code;
    }
    return statusObj;

}

function checkStrIsBlank(str){
	
	if(!str) return true;
	
	let trimed = str.replace(/\s/gi, "");
	if(trimed.length == 0) return true;
	
	return false;
	
}

(function(){
	let memberStatusCodeArr = JSON.parse('${memberStatusCode}');
	let blogStatusCodeArr = JSON.parse('${blogStatusCode}');
	
	
	exceptionHandler(function(){
		window.memberStatusCode = parseStatusArrToObj(memberStatusCodeArr);
	});
	
	exceptionHandler(function(){
		window.blogStatusCode = parseStatusArrToObj(blogStatusCodeArr);
	});

})();



</script>