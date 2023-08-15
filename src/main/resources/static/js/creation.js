console.log('연결')


document.getElementById('create').addEventListener('submit', function(e) {

    e.preventDefault(); // 폼의 기본 제출 동작을 막습니다.
    const formData = new FormData(e.currentTarget) // 현재 폼의 데이터를 가져옵니다.
    const jsonData = {};
    formData.forEach((value, key) => {
        jsonData[key] = value;
    });
    creation(jsonData);
});


function creation (jsonData) {
    console.log(jsonData);
    fetch('/api/chatroom', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(jsonData)})
        .then(response => response.text())
        .then(redirectUrl => {
            window.location.href = redirectUrl;
        })
}