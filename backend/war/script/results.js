// JavaScript Document

function underProgress(){
	alert("under progress.");	
}

window.onload = pageloaded;

function pageloaded(){

	var what = getParameterByName("What");
	var where = getParameterByName("Where");

	document.getElementById("what").value = what;	
	document.getElementById("where").value = where;

	var SearchButton = document.getElementById("SearchButton");
	SearchButton.addEventListener("click",searchClicked);

	function getPathFromUrl(url) {
		  return url.split("?")[0];
		}
	searchClicked();
}


function searchClicked(){	
	
	var what = document.getElementById("what").value;
	var where = document.getElementById("where").value;
	sendSearchRequest(what,where);
}

function inflateResultItem(title,href,desc,locations){

	var ritem = document.createElement("div");	
	ritem.setAttribute("class","resultItem");
	
	var rlink = document.createElement("a");
	rlink.setAttribute("href",href);
	
	var rtitle = document.createElement("p");
	rtitle.setAttribute("class","resultItemTitle");
	rtitle.appendChild(document.createTextNode(title));
	rlink.appendChild(rtitle);
	
	ritem.appendChild(rlink);
	
	var rdesc = document.createElement("p");
	rdesc.setAttribute("class","resultItemDescription");
	rdesc.appendChild(document.createTextNode(desc));
	
	ritem.appendChild(rdesc);

	var rloc = document.createElement("p");
	rloc.setAttribute("class","resultItemLocations");
	rloc.appendChild(document.createTextNode(locations));
	ritem.appendChild(rloc);
	
	document.body.insertBefore(ritem,document.getElementsByTagName("footer")[0]);

}

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}


/**************************** Client Endpoint ****************************/
//Creating request variable for Server Request.
function createRequest() {
		try {
			request = new XMLHttpRequest();
			} 
		catch (tryMS) {
			try {
				request = new ActiveXObject("Msxml2.XMLHTTP");
				} 
				catch (otherMS) {
					try {
						request = new ActiveXObject("Microsoft.XMLHTTP");
						} 
					catch (failed) {
				request = null;
				}
			}
		}		
		return request;
}

function sendSearchRequest(what,where) {
	request = createRequest();
	if (request==null) {
		alert("Unable to create request");
		return;
	}
	var url= "/api/WebSearch";
	var kvpairs = [];
	kvpairs.push(encodeURIComponent("what") + "=" + encodeURIComponent(what));
	kvpairs.push(encodeURIComponent("where") + "=" + encodeURIComponent(where));
	var queryString = kvpairs.join("&");
	request.open("GET",url + "?" + queryString,true);
	request.onreadystatechange = getResponse;
	
	request.send(null);
}

function getResponse(){
	if (request.readyState == 4) {
		if (request.status == 200) {
			var myjson = JSON.parse(request.responseText);		
			if(myjson.length == 0)
			{
				alert("No results found!!");
			}
			else{
				removeResults();
			}
			for(var i=0;i<myjson.length;i++){
				inflateResultItem(myjson[i].JobTitle,"",myjson[i].JobDescription,myjson[i].Locations)
			}
		
		}
	}
}

//remove old results
function removeResults(){
    var elements = document.getElementsByClassName("resultItem");
    while(elements.length > 0){
        elements[0].parentNode.removeChild(elements[0]);
    }
}