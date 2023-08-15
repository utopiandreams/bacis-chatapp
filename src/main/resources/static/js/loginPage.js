console.log("js 연결")

document.getElementById('join').addEventListener('click', async function () {
    // 입력된 username과 password를 가져옵니다.
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const params = new URLSearchParams();
    params.append('username', username);
    params.append('password', password);

    join(params)
})


async function join(params) {
    fetch('http://localhost:8080/api/auth/join', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: params.toString()})
        .then(response => response.text())
        .then(data =>{
            let result = document.getElementById('msg');
            result.innerHTML = data
        });
}