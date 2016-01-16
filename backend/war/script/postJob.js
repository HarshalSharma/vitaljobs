// JavaScript Document

window.onload = init;

var locations;

function init(){
	
	sendLocationsRequest();
	
	var LocationAdd =  document.getElementById("locationAdd");
	LocationAdd.addEventListener("click", function(){ 
	var name = document.getElementById("location");
	if(!valid(name.value)){return;}
	if(name.value != "")	
	addTag(toTitleCase(name.value),document.getElementById("locationsHolder"),"location"); 
	name.value = "";
	});
	
	var SkillAdd =  document.getElementById("SkillAdd");
	SkillAdd.addEventListener("click", function(){ 
	var name = document.getElementById("SkillSets");
	if(name.value != "")	
	addTag(toTitleCase(name.value),document.getElementById("skillsHolder"),"skill"); 
	name.value = "";
	});
	
	var educationAdd =  document.getElementById("educationAdd");
	educationAdd.addEventListener("click", function(){ 
	var name = document.getElementById("Education");
	if(name.value != "")	
	addTag(toTitleCase(name.value),document.getElementById("educationHolder"),"education"); 
	name.value = "";
	});
	
	var form = document.getElementsByName("myForm")[0];
	form.addEventListener("submit", function(event) {
	  event.preventDefault();
	  sendSubmitRequest();
	});

}

function valid(name){
	if (locations.indexOf(name) > -1) { return true;}
	else {alert("Please choose an available location!"); return false;}	
}

function addTag(name,holder,type){
	var tagStructure = document.createElement("div");
	tagStructure.setAttribute("class","tagBody");
	tagStructure.appendChild(document.createTextNode(name));
	var span = document.createElement("span");
	span.setAttribute("class","Tagcross");
	span.appendChild(document.createTextNode("x"));
	tagStructure.appendChild(span);
	holder.appendChild(tagStructure);
	
	var input = document.createElement("input");
	input.setAttribute("type","text");
	input.setAttribute("hidden","true");
	input.setAttribute("name",type);
	input.setAttribute("value",name);
	holder.appendChild(input);
}

function toTitleCase(str)
		{
    		return str.replace(/\w\S*/g, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();});
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

//Requesting the server to post.
function sendSubmitRequest() {
	request = createRequest();
	if (request==null) {
		alert("Unable to create request");
		return;
	}
	if(!confirm("Submit Job?")){
		return;
	}
	var url= "/api/addJob";
	request.open("POST",url,true);
	request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	request.onreadystatechange = getStatus;
	var form = document.forms.namedItem("myForm");
	var inputs = form.getElementsByTagName("input");	
	var kvpairs = [];
	for ( var i = 0; i < inputs.length; i++ ) {
	   var e = inputs[i];
	   if(e.name != "")
	   kvpairs.push(encodeURIComponent(e.name) + "=" + encodeURIComponent(e.value));
	}
	var queryString = kvpairs.join("&");
	request.send(queryString);
	
}

//Manipulating the response		
function getStatus(){
	if (request.readyState == 4) {
		if (request.status == 200) {
			if(request.responseText == "WRONG_KEY"){
				alert("Incorrect Password!!!");
			}
			else if(request.responseText == "SUCCESS")
			{
				alert("Job Posted Successfully!!");
				document.forms.namedItem("myForm").reset();
			}
			else if(request.responseText == "NO_SUCH_LOCATION"){
				alert("Please Check!! Not all locations are available.");
			}
		}
		else{
			alert("Error:" + request.readyState);	
		}
	}
}

function sendLocationsRequest() {
			request = createRequest();
			if (request==null) {
				alert("Unable to create request");
				return;
			}
			var url= "/api/locations";
			request.open("GET",url,true);
			request.onreadystatechange = getLocations;
			request.send(null);
		}
		
		function getLocations(){
			if (request.readyState == 4) {
				if (request.status == 200) {
					locations = eval('(' + request.responseText + ')');
					addToList(locations);
				}
			}
		}

function addToList(locations){	

	var datalist = document.getElementById("LocationsList");
	for(var i=0;i<locations.length;i++)
	{
		var option = document.createElement("option");
		option.setAttribute("value",locations[i]);
		datalist.appendChild(option);	
	}
	
}