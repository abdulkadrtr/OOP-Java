function girisYap(){
    window.location.href = "login.html";
}
document.getElementById('login-form').addEventListener('submit', (event) => {
event.preventDefault();
const username = document.getElementById('username').value;
const password = document.getElementById('password').value;
const password1 = document.getElementById('password1').value;
const isAdmin = document.getElementById('admin').checked.toString();

// Şifre kontrolü
if (password !== password1) {
showMessage('Girilen şifreler eşleşmiyor!');
return;
}

const data = {
username: username,
password: password,
isAdmin: isAdmin
};
var xhr = new XMLHttpRequest();
xhr.open('POST', 'http://localhost:8080/register', true);
xhr.setRequestHeader('Content-Type', 'application/json');
xhr.onreadystatechange = function() {
if (xhr.readyState === XMLHttpRequest.DONE) {
  if (xhr.status === 200) {
    showMessage('Kayıt başarılı!');
  } else if (xhr.status === 401) {
    showMessage('Kullanıcı zaten kayıtlı!');
  } else {
    // Diğer hata durumlarında farklı bir HTML sayfasına geçiş yapabilirsiniz
    window.location.href = 'error.html';
  }
}
};
xhr.send(JSON.stringify(data));
});

function showMessage(message) {
var messageElement = document.querySelector('.message');
messageElement.textContent = message;
}