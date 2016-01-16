// JavaScript Document

function underProgress(){
	alert("under progress.");	
}

window.onload = pageloaded;

function pageloaded(){
	var SearchResumesButton = document.getElementById("SearchResumesButton");
	SearchResumesButton.onclick = underProgress;	

	var UploadResumeButton = document.getElementById("UploadResumeButton");
	UploadResumeButton.onclick = underProgress;	
}
