function kayitOl() {
    window.location.href = 'kayitOl.html';
  }
document.getElementById('login-form').addEventListener('submit', (event) => {
  event.preventDefault();
  const username = document.getElementById('username').value;
  const password = document.getElementById('password').value;
  const data = {
    username: username,
    password: password
  };
  var xhr = new XMLHttpRequest();
  xhr.open('POST', 'http://localhost:8080/login', true);
  xhr.setRequestHeader('Content-Type', 'application/json');
  xhr.onreadystatechange = function() {
    if (xhr.readyState === XMLHttpRequest.DONE) {
      if (xhr.status === 200) {
        // Başarılı yanıt durumunda farklı bir HTML sayfasına geçiş yapabilirsiniz
        window.location.href = 'anasayfaAdmin.html?id=' + encodeURIComponent(username);
      } else if (xhr.status === 201) {
        // Yetkilendirme hatası durumunda farklı bir HTML sayfasına geçiş yapabilirsiniz
        window.location.href = 'anasayfaKullanici.html?id=' + encodeURIComponent(username);
      } else {
        // Diğer hata durumlarında farklı bir HTML sayfasına geçiş yapabilirsiniz
        //window.location.href = 'error.html';
        const messageElement = document.querySelector('.message');
        messageElement.innerHTML = 'Kullanıcı adı veya şifre hatalı.';
      }
    }
  };
  xhr.send(JSON.stringify(data));
});