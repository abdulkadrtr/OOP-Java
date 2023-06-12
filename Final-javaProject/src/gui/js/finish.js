window.onload = function() {
    getResults();
}
function getResults(){
    var urlParams = new URLSearchParams(window.location.search);
    var username = urlParams.get('username');
    var clickedBoxId = urlParams.get('clickedBoxId');
    document.getElementById("kValue").innerText = clickedBoxId;
    var xhr = new XMLHttpRequest();
    const data = {
        username: username,
        clickedBoxId: clickedBoxId
    }
    xhr.open("POST", "http://localhost:8080/getResult", true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            console.log(xhr.responseText);
            var result = JSON.parse(xhr.responseText);
            document.getElementById("questionCount").innerText = result.b;
            document.getElementById("tq").innerText = result.tq;
            document.getElementById("fq").innerText = Number(result.b) - Number(result.tq);
            document.getElementById("score").innerText = Number(result.c).toFixed(2);
            document.getElementById("completionTime").innerText = result.d + " saniye";
        }
    };
    xhr.send(JSON.stringify(data));
}
var newExerciseButton = document.querySelector("input[type='submit']");
newExerciseButton.addEventListener("click", function(){
    var urlParams = new URLSearchParams(window.location.search);
    var username = urlParams.get('username');
    var clickedBoxId = urlParams.get('clickedBoxId');
    window.location.href = "anasayfaKullanici.html" + "?id=" + username ;
});