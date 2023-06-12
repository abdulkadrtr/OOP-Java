max = 0;
window.addEventListener('DOMContentLoaded', function(){
    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'http://localhost:8080/totalExerciseNumber', true);
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if(xhr.status === 200){
                max = parseInt(xhr.responseText);
                createExerciseBoxes(max);
            }
            else{
                console.log("Hata oluştu");

            }
        }
    };
    xhr.send();
});
function createExerciseBoxes(max) {
    var container = document.querySelector(".container");
    for (var i = 1; i <=max; i++) {
        var box = document.createElement("div");
        var boxId =  i; // Benzersiz id değeri oluştur
        box.setAttribute("id", boxId);
        box.classList.add("box");
        
        var boxText = document.createElement("div");
        boxText.classList.add("box-text");
        boxText.textContent = i + ". alıştırma";
        
        box.appendChild(boxText);
        container.appendChild(box);
        
        // Kutuya tıklama olayı dinleyicisi ekle
        box.addEventListener("click", handleBoxClick);
    }
}
function handleBoxClick(event) {
    var clickedBoxId = findClickedBoxId(event.target);
    var urlParams = new URLSearchParams(window.location.search);
    var username = urlParams.get('id');
    var mergedParams = new URLSearchParams();
    mergedParams.append("clickedBoxId", clickedBoxId);
    mergedParams.append("username", username);
    var mergedParamsString = mergedParams.toString();
    var redirectUrl = "exercises.html?" + mergedParamsString;
    window.location.href = redirectUrl;
}

function findClickedBoxId(element) {
    while (element) {
        if (element.classList.contains("box")) {
            return element.id;
        }
        element = element.parentElement;
    }
    return null;
}