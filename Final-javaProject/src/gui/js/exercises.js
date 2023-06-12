window.onload = function() {
startTimer();
sendRequest();
}

function startTimer() {
var counter = 0;
setInterval(function() {
    document.getElementById("timer").innerHTML = counter++;
}, 1000);
}
var totalq = 0;
function sendRequest() {
var urlParams = new URLSearchParams(window.location.search);
var username = urlParams.get('username');
var clickedBoxId = urlParams.get('clickedBoxId');
document.getElementById("exN").innerHTML = clickedBoxId +  ".Alıştırma";
document.getElementById("username").innerHTML = "Kullanıcı Adın: " + username;
const data = {
    username: username,
    clickedBoxId: clickedBoxId,
    qNumber : "0"
}
var xhr = new XMLHttpRequest();
xhr.open("POST", "http://localhost:8080/getValues", true);
xhr.setRequestHeader("Content-Type", "application/json");
xhr.onreadystatechange = function() {
    if (xhr.readyState === 4 && xhr.status === 200) {
    console.log(xhr.responseText);
    var response = JSON.parse(xhr.responseText);
    document.getElementById("a").innerHTML = response.a;
    document.getElementById("b").innerHTML = response.b;
    totalq = response.totalq;
    document.getElementById("qc").innerHTML = "Soru " + qNumber + " / " + totalq;
    }
};
xhr.send(JSON.stringify(data));
}
var qNumber = 1;
function submitForm() {
var urlParams = new URLSearchParams(window.location.search);
var username = urlParams.get('username');
var clickedBoxId = urlParams.get('clickedBoxId');
var input = document.getElementById("input").value;
if (input == "") {
    alert("Lütfen bir sayı giriniz.");
    return;
}
var timerValue = document.getElementById("timer").innerHTML;
var xhr = new XMLHttpRequest();
xhr.open("POST", "http://localhost:8080/postValue", true);
xhr.setRequestHeader("Content-Type", "application/json");
xhr.onreadystatechange = function() {
    if (xhr.readyState === 4 && xhr.status === 200) {
    var response = JSON.parse(xhr.responseText);
    document.getElementById("a").innerHTML = response.a;
    document.getElementById("b").innerHTML = response.b;
    document.getElementById("input").value = "";

    qNumber++;
    console.log(qNumber);
    console.log(totalq);
    if (qNumber > totalq) {
        window.location.href = "finish.html?username=" + username + "&clickedBoxId=" + clickedBoxId;
    } else {
        document.getElementById("qc").innerHTML = "Soru " + (qNumber) + " / " + totalq;
    }
    }
};
var data = JSON.stringify({ "username": username, "clickedBoxId": clickedBoxId, "qNumber": qNumber.toString(), "input": input, "timerValue": timerValue });
xhr.send(data);
}